package com.chtwm.dubbo.conf;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Date: 2018/11/28
 * Time: 13:25
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Data
@Component
public class DomainMapProperties {

    @Value("#{${domain-maps}}")
    private Map<String, String> domainMap;

}
