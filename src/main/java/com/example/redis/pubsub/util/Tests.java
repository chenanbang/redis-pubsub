package com.example.redis.pubsub.util;

import com.example.redis.pubsub.enums.RedisChannelEnums;
import org.apache.commons.lang.StringUtils;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName Tests.java
 * @Description TODO
 * @createTime 2019年11月19日 15:43:00
 */
public class Tests {

  public static void main(String[] args) {
    RedisChannelEnums[] redisChannelEnums = RedisChannelEnums.values();
    if (redisChannelEnums.length > 0) {
      for (RedisChannelEnums redisChannelEnum : redisChannelEnums) {

        System.out.println("simpleName: "+redisChannelEnum.getClass().getSimpleName().toLowerCase());

        if (redisChannelEnum == null || StringUtils.isEmpty(redisChannelEnum.getCode()) || redisChannelEnum.getClass().getSimpleName()==null) {
          continue;
        }
        //订阅了一个叫pmp和channel 的通道，多通道
        //一个订阅者接收一个频道信息，新增订阅者需要新增RedisChannelEnums定义+BaseSub的子类
      }
    }
  }

}
