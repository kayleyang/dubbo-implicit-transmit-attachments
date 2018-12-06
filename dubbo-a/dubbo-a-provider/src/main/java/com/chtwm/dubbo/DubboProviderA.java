package com.chtwm.dubbo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Date: 2018/11/27
 * Time: 11:35
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@SpringBootApplication
public class DubboProviderA {
    public static void main(String[] args) {

        new SpringApplicationBuilder(DubboProviderA.class)
                .web(WebApplicationType.NONE) // 非 Web 应用
                .run(args);
    }
}
