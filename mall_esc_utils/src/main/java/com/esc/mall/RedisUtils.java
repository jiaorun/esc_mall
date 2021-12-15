package com.esc.mall;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.concurrent.TimeUnit;

/**
 * Redis 常用操作 工具类
 *
 * @author jiaorun
 * @date 2021/09/14 19:39
 **/
@Component
public class RedisUtils {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisUtils(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 指定缓存失效时间
     *
     * @param key
     * @param time
     * @return boolean
     * @author jiaorun
     * @date 2021/09/14 19:50
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 根据key获取过期时间
     *
     * @param key
     * @return long
     * @author jiaorun
     * @date 2021/09/14 19:53
     */
    public long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return boolean
     * @author jiaorun
     * @date 2021/09/14 19:54
     */
    public boolean hashKey(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 删除缓存
     *
     * @param key 可以传一个或多个值
     * @return void
     * @author jiaorun
     * @date 2021/09/14 19:57
     */
    public void del(String... key) {
        if (key != null && key.length > 0) {
            redisTemplate.delete(key[0]);
        } else {
            redisTemplate.delete(CollectionUtils.arrayToList(key));
        }
    }

    /**
     * 添加缓存
     *
     * @param key
     * @param value
     * @return void
     * @author jiaorun
     * @date 2021/09/14 20:05
     */
    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return java.lang.String
     * @author jiaorun
     * @date 2021/09/14 20:05
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     * @return void
     * @author jiaorun
     * @date 2021/09/14 20:06
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item
     * @param by   要增加几(大于0)
     * @return double
     * @author jiaorun
     * @date 2021/09/14 20:07
     */
    public double hincr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item
     * @param by   要减小几(小于0)
     * @return double
     * @author jiaorun
     * @date 2021/09/14 20:09
     */
    public double hdecr(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }
}
