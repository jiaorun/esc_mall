package com.esc.mall;

import org.springframework.cglib.beans.BeanCopier;

import java.util.HashMap;
import java.util.Map;

/**
 * BeanCopier缓存 工具类
 * @author jiaorun
 * @date 2021/12/13 12:09
 **/
public class BeanCopierUtils {

    public static Map<String, BeanCopier> beanCopierCacheMap = new HashMap<String, BeanCopier>();

    /**
     * 缓存BeanCopier
     * @author jiaorun
     * @data 2021/12/13 12:10
     * @return void
     */
    public static void copyProperties(Object source, Object target) {
        StringBuilder cacheKey = new StringBuilder();
        cacheKey.append(source.getClass().toString()).append(target.getClass().toString());

        BeanCopier beanCopier = null;
        if (!beanCopierCacheMap.containsKey(cacheKey.toString())) {
            synchronized (BeanCopierUtils.class) {
                if (!beanCopierCacheMap.containsKey(cacheKey.toString())) {
                    beanCopier = BeanCopier.create(source.getClass(), target.getClass(), false);
                    beanCopierCacheMap.put(cacheKey.toString(), beanCopier);
                }
            }
        } else {
            beanCopier = beanCopierCacheMap.get(cacheKey.toString());
        }
        beanCopier.copy(source, target, null);
    }
}
