package com.example.redis.pubsub.publish;

import com.example.redis.pubsub.enums.RedisChannelEnums;
import com.example.redis.pubsub.msg.BaseMessage;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName RedisPub.java
 * @Description TODO
 * @createTime 2019年11月19日 14:11:00
 */
public interface RedisPub {
  /**
   * 集成redis实现消息发布订阅模式-双通道
   * @param redisChannelEnums 枚举定义
   * @param basePubMessage 消息
   */
  void sendMessage(RedisChannelEnums redisChannelEnums, BaseMessage basePubMessage);

}
