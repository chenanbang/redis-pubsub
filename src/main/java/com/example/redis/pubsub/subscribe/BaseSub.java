package com.example.redis.pubsub.subscribe;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName BaseSub.java
 * @Description TODO
 * @createTime 2019年11月19日 15:35:00
 */
public interface BaseSub {
  /**
   * 接收消息
   * @param jsonMessage  json字符
   */
  void receiveMessage(String jsonMessage);
}
