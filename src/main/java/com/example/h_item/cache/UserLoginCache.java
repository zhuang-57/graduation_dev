package com.example.h_item.cache;

import cn.hutool.core.lang.UUID;
import com.example.h_item.common.BusinessErrorCodeEnum;
import com.example.h_item.common.Status;
import com.example.h_item.common.StatusCodeException;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class UserLoginCache {

    private final Map<String, Object> userCache = new ConcurrentHashMap<>();

    /**
     * 用户登录 返回token
     */
    public String login(Long userId) {
        String token = UUID.fastUUID().toString();
        userCache.put(token, userId);
        return token;
    }

    public Long getUserIdByToken(String token) {
        Object tokenObj = userCache.get(token);
        if (tokenObj == null) {
            throw new StatusCodeException(Status.create(BusinessErrorCodeEnum.TOKEN_OVERDUE.getCode(),
                    BusinessErrorCodeEnum.TOKEN_OVERDUE.getDesc()));
        }
        return (Long) tokenObj;
    }

    public void delete(String token) {
        userCache.remove(token);
    }
}
