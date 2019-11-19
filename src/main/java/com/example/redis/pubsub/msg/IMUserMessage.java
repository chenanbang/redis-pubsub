package com.example.redis.pubsub.msg;

import com.example.redis.pubsub.util.GsonUtil;
import lombok.Data;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName OnlineUserMessage.java
 * @Description TODO
 * @createTime 2019年11月19日 17:03:00
 */
@Data
public class IMUserMessage extends BaseMessage {

  private String userId;

  @Override
  public String toString() {
    return GsonUtil.toJson(this, IMUserMessage.class);
  }
}
