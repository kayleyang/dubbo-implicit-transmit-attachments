package com.chtwm.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.chtwm.dubbo.service.HelloService;
import com.chtwm.dubbo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Date: 2018/11/27
 * Time: 13:14
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Slf4j
@RestController
@RequestMapping("/")
public class HelloController {

    @Reference
    UserService userService;

    @Reference
    HelloService helloService;

    @RequestMapping("/sayHello")
    public String sayHello(String lastName) {
        helloService.sayHello(lastName);
        return helloService.sayHello(lastName);
    }

    @RequestMapping("/getUserName")
    public String getUserName(String lastName) {
        final String userName = userService.getUserName(lastName);
        log.info(userName);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return userService.getUserName(lastName + "..");
    }
}
