package com.example.redis.pubsub.config;

import com.example.redis.pubsub.enums.RedisChannelEnums;
import com.example.redis.pubsub.subscribe.BaseSub;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName RedisConfig.java
 * @Description TODO
 * @createTime 2019年11月19日 14:34:00
 */
@Service
@Configuration
@EnableCaching
public class RedisConfig {

  /**
   * 存放策略实例
   * classInstanceMap : key-beanName value-对应的策略实现
   */
  private ConcurrentHashMap<String, BaseSub> classInstanceMap = new ConcurrentHashMap<>(20);

  /**
   * 注入所有实现了Strategy接口的Bean
   *
   * @param strategyMap
   *         策略集合
   */
  @Autowired
  public RedisConfig(Map<String, BaseSub> strategyMap) {
    this.classInstanceMap.clear();
    strategyMap.forEach((k, v) ->
        this.classInstanceMap.put(k.toLowerCase(), v)
    );
  }


  /**
   * Redis消息监听器容器
   *
   * @param connectionFactory
   *
   * @return
   */
  @Bean
  RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {

    RedisMessageListenerContainer container = new RedisMessageListenerContainer();
    container.setConnectionFactory(connectionFactory);

    RedisChannelEnums[] redisChannelEnums = RedisChannelEnums.values();
    if (redisChannelEnums.length > 0) {
      for (RedisChannelEnums redisChannelEnum : redisChannelEnums) {
        if (redisChannelEnum == null || StringUtils.isEmpty(redisChannelEnum.getCode()) || redisChannelEnum.getClassName()==null) {
          continue;
        }
        //订阅了一个叫pmp和channel 的通道，多通道
        //一个订阅者接收一个频道信息，新增订阅者需要新增RedisChannelEnums定义+BaseSub的子类

        String toLowerCase = redisChannelEnum.getClassName().getSimpleName().toLowerCase();
        BaseSub baseSub = classInstanceMap.get(toLowerCase);
        container.addMessageListener(listenerAdapter(baseSub), new PatternTopic(redisChannelEnum.getCode()));
      }
    }
    return container;
  }

  /**
   * 配置消息接收处理类
   *
   * @param baseSub
   *         自定义消息接收类
   *
   * @return MessageListenerAdapter
   */
  @Bean()
  @Scope("prototype")
  MessageListenerAdapter listenerAdapter(BaseSub baseSub) {
    //这个地方 是给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
    //也有好几个重载方法，这边默认调用处理器的方法 叫handleMessage 可以自己到源码里面看
    //注意2个通道调用的方法都要为receiveMessage
    return new MessageListenerAdapter(baseSub, "receiveMessage");
  }

}
