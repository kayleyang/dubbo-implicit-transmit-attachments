package com.chtwm.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.chtwm.dubbo.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2018/11/27
 * Time: 11:36
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final static String firstName = "小明";

    @Override
    public String getUserName(String lastName) {
        String userName = lastName + firstName;
        log.info("call getUserName({}) return {}", lastName, userName);
        return userName;
    }
}
