package com.orange.mall.service;

import com.orange.mall.common.CommonResult;

/**
 * @author xxkun
 * @creed Awaken the Giant Within
 * @description:
 * @date 2020-12-19 21:13
 */
public interface UmsMemberService {
    CommonResult generateAuthCode(String telephone);

    CommonResult verifyAuthCode(String telephone, String authCode);
}
