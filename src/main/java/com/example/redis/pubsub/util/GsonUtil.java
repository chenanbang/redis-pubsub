package com.example.redis.pubsub.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * @author chenanbang
 * @version 1.0.0
 * @ClassName GsonUtil.java
 * @Description TODO
 * @createTime 2019年11月19日 14:01:00
 */
public class GsonUtil {

  public static String toJson(Object obj, Class cls) {
    Gson gson = new Gson();
    return gson.toJson(obj, cls);
  }

  /**
   * 使用Gson进行解析Bean
   * @param jsonString
   * @param cls
   * @param <T>
   * @return
   */
  public static <T> T getBean(String jsonString, Class<T> cls) {
    T t = null;
    Gson gson = new Gson();
    t = gson.fromJson(jsonString, cls);
    return t;
  }

  /**
   * 使用Gson进行解析 List<Beans>
   * @param jsonString
   * @param cls
   * @param <T>
   * @return
   */
  public static <T> List<T> getBeans(String jsonString, Class<T> cls) {
    List<T> list = new ArrayList<T>();
    Gson gson = new Gson();
    list = gson.fromJson(jsonString, new TypeToken<List<T>>() {
    }.getType());
    return list;
  }
}
