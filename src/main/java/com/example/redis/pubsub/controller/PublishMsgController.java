package com.example.redis.pubsub.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.redis.pubsub.enums.RedisChannelEnums;
import com.example.redis.pubsub.msg.IMUserMessage;
import com.example.redis.pubsub.publish.RedisPub;
import com.example.redis.pubsub.util.GsonUtil;
import com.example.redis.pubsub.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName PublishMsgController.java
 * @Description TODO
 * @createTime 2019年11月19日 16:57:00
 */
@Slf4j
@RestController
@RequestMapping("/publish")
public class PublishMsgController {

  @Autowired
  private RedisPub redisPub;

  @Autowired
  private RedisUtil redisUtil;

  @RequestMapping(value = "/pubMsg", produces = MediaType.TEXT_PLAIN_VALUE)
  public ResponseEntity pubMsg(@RequestBody JSONObject jsonParams) {
    String params = jsonParams.toJSONString();
//    log.info("publish msg,params:{}", params);
    System.out.println("publish msg,params:" + params);

    redisUtil.set("username", "陈安邦");
    IMUserMessage imUserMessage = GsonUtil.getBean(params, IMUserMessage.class);
    redisPub.sendMessage(RedisChannelEnums.IM_QUEUE_CHANNLID, imUserMessage);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

}
