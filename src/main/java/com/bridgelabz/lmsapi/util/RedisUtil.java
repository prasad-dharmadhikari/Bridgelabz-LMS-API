package com.bridgelabz.lmsapi.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * Utility to handle redis related stuff
 */
@Component
public class RedisUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * @param RedisKey
     * @param email
     * @param authenticationToken
     */
    public void save(String RedisKey, String email, String authenticationToken) {
        redisTemplate.opsForHash().put(RedisKey, email, authenticationToken);
    }

    /**
     * @param redisIndexKey
     * @param username
     * @return value in redis database by key and hash key
     */
    public Object get(String redisIndexKey, String username) {
        return redisTemplate.opsForHash().get(redisIndexKey, username);
    }
}