package com.edm.camera.utils;

import org.springframework.cglib.beans.BeanCopier;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Edm
 * @create 2020-06-20 14:00
 * @email edisonmiao@icloud.com
 */
public enum BeanCopierUtils {
    X;

    private static final Map<String, BeanCopier> CACHE = new ConcurrentHashMap<>();

    public void copy(Object source, Object target) {
        String key = String.format("%s-%s", source.getClass().getName(), target.getClass().getName());
        BeanCopier copier;
        if (CACHE.containsKey(key)) {
            copier = CACHE.get(key);
        } else {
            copier = BeanCopier.create(source.getClass(), target.getClass(), false);
            CACHE.put(key, copier);
        }
        copier.copy(source, target, null);
    }
}
