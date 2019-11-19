package com.example.redis.pubsub.publish;

import com.example.redis.pubsub.enums.RedisChannelEnums;
import com.example.redis.pubsub.msg.BaseMessage;
import java.util.Objects;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName RedisPubImpl.java
 * @Description TODO
 * @createTime 2019年11月19日 14:33:00
 */
@Service
public class RedisPubImpl implements RedisPub {
  @Resource
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public void sendMessage(RedisChannelEnums redisChannelEnums, BaseMessage basePubMessage) {

    if (Objects.isNull(redisChannelEnums) || Objects.isNull(basePubMessage)) {
      return;
    }

    basePubMessage.setChannel(redisChannelEnums.getCode());
    stringRedisTemplate.convertAndSend(redisChannelEnums.getCode(), basePubMessage.toString());
    System.out.println("发布成功！");
  }
}
