package com.example.redis.pubsub.msg;

import com.example.redis.pubsub.util.GsonUtil;
import lombok.Data;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName BasePubMessage.java
 * @Description TODO
 * @createTime 2019年11月19日 13:57:00
 */
@Data
public abstract class BaseMessage {

  /**发布订阅频道名称*/
  private String channel;

  private String extra;

  private String json;

  @Override
  public String toString() {
    return GsonUtil.toJson(this, BaseMessage.class);
  }

  public String getChannel() {
    return channel;
  }

  public void setChannel(String channel) {
    this.channel = channel;
  }

  public String getExtra() {
    return extra;
  }

  public void setExtra(String extra) {
    this.extra = extra;
  }

  public String getJson() {
    return json;
  }

  public void setJson(String json) {
    this.json = json;
  }
}
