package com.chtwm.dubbo.util;

import com.chtwm.dubbo.conf.DomainMapProperties;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 通用工具类
 *
 * @author chenzhangyan  on 2017/8/22.
 */
@Slf4j
public class CommonUtil {

    private final static String DOMAIN_REG = "[0-9a-zA-Z]+((\\.com.cn)|(\\.com)|(\\.org)|(\\.net)|(\\.cn))";

    private static DomainMapProperties domainMapProperties;

    public CommonUtil(DomainMapProperties domainMapProperties) {
        CommonUtil.domainMapProperties = domainMapProperties;
    }


    public static String getDomain(HttpServletRequest request) {
        StringBuffer url = request.getRequestURL();
        Pattern pattern = Pattern.compile(DOMAIN_REG);
        Matcher matcher = pattern.matcher(url);
        String domain = null;
        while(matcher.find()){
            domain = matcher.group();
            break;
        }
        return domain;
    }

    public static String getDomainFlag(HttpServletRequest request) {

        String domain = getDomain(request);
        final Map<String, String> domainMap = domainMapProperties.getDomainMap();
        final Set<String> domainFlagSet = domainMap.keySet();
        for (String domainFlag : domainFlagSet) {
            final String domainValue = domainMap.get(domainFlag);
            if (domainValue.equals(domain)) {
                return domainFlag;
            }
        }
        return null;
    }
}
