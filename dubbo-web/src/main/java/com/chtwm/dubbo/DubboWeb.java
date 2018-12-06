package com.chtwm.dubbo;

import com.chtwm.dubbo.conf.DomainMapProperties;
import com.chtwm.dubbo.util.CommonUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Import;

/**
 * Date: 2018/11/27
 * Time: 13:12
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@ServletComponentScan
@SpringBootApplication
@Import({DomainMapProperties.class, CommonUtil.class})
public class DubboWeb {

    public static void main(String[] args) {

        SpringApplication.run(DubboWeb.class,args);

    }
}
