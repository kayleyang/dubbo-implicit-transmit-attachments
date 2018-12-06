package com.chtwm.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.chtwm.dubbo.service.HelloService;
import com.chtwm.dubbo.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * Date: 2018/11/27
 * Time: 13:02
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {

    @Reference
    UserService userService;

    @Override
    public String sayHello(String lastName) {
        final String userName = userService.getUserName(lastName);
        log.info("call getUserName({}) return {}", lastName, userName);
        String hello = "嗨！" + userName;
        log.info("call sayHello({}) return {}", userName, hello);
        return hello;
    }
}
