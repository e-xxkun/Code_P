package com.orange.mall.service.impl;

import com.orange.mall.common.CommonResult;
import com.orange.mall.service.RedisService;
import com.orange.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description:
 * @date 2020-12-19 21:23
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {
    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private String AUTH_CODE_EXPIRE_SECONDS;


    @Override
    public CommonResult generateAuthCode(String telephone) {

        return null;
    }

    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {
        return null;
    }
}
