package com.example.redis.pubsub.subscribe;

import com.example.redis.pubsub.msg.IMUserMessage;
import com.example.redis.pubsub.util.GsonUtil;
import com.example.redis.pubsub.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName LiveChangeSub.java
 * @Description TODO
 * @createTime 2019年11月19日 15:35:00
 */
@Component
public class IMUserSub implements BaseSub{

  /**
   * 只是定义的注解测试，可以换成自己的
   */
//  @Autowired
//  private CategoryMapper categoryMapper;

  @Autowired
  private RedisUtil redisUtil;

  @Override
  public void receiveMessage(String jsonMessage) {

    System.out.println("docker2-server.....................");
    //注意通道调用的方法名要和RedisConfig2的listenerAdapter的MessageListenerAdapter参数2相同
    System.out.println("这是 jsonMessage" + "-----" + jsonMessage);

    Object username = redisUtil.get("username");
    System.out.println("username: " + username);

    IMUserMessage imUserMessage = GsonUtil.getBean(jsonMessage, IMUserMessage.class);
    System.out.println("imUserMessage: "+imUserMessage);

//    Category category = categoryMapper.get(1L);
//    System.out.println("category:" + category);

  }

}
