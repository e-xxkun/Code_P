package com.orange.mall.service;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description: redis操作Service
 * @date 2020-12-19 20:46
 */
public interface RedisService {

    void set(String key, String value);

    String get(String key);

    /**
     * @describe: 设置超时时间
     * @param key
     * @param auth_code_expire_seconds
     * @date 2020/12/20 18:08
     */
    void expire(String key, long auth_code_expire_seconds);

    void remove(String key);

    Long increment(String key, long delta);
}
