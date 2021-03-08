package com.lin945.makefriend.utils;

import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * @author lwm
 * @date 2020-09-16 15:26
 * @description redis的帮助类
 */
public class RedisUtil {

    // String类型的Key-Value
    private static final StringRedisTemplate stringRedisTemplate;

    /**
     * 初始化
     */
    static {
        stringRedisTemplate = (StringRedisTemplate) SpringContextUtils.getBeanByClass(StringRedisTemplate.class);
    }

    /**
     * 通过key设置value，并设置有效时间
     * @param key 键
     * @param value 值
     * @param expire 有效时间
     * @param unit 时间单位
     */
    public static void set(String key, String value, int expire, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, expire, unit);
    }

    /**
     * 通过key设置value
     * @param key 键
     * @param value 值
     */
    public static void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 通过key获取value
     * @param key 键
     * @return 返回String值
     */
    public static String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 设置key的有效时间
     * @param key 键
     * @param expire 时间
     * @param unit 时间单位
     */
    public static void expire(String key, int expire, TimeUnit unit) {
        stringRedisTemplate.expire(key, expire, unit);
    }

    public static void del(String key) {
        stringRedisTemplate.delete(key);
    }

}
