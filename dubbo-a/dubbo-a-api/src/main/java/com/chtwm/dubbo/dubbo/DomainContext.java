package com.chtwm.dubbo.dubbo;

import lombok.Data;

/**
 * Date: 2018/11/29
 * Time: 13:25
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Data
public class DomainContext {

    private static final ThreadLocal<DomainContext> LOCAL = new ThreadLocal<DomainContext>() {
        @Override
        protected DomainContext initialValue() {
            return new DomainContext();
        }
    };

    private String domain;

    protected DomainContext () {}

    public static DomainContext getContext() {
        return LOCAL.get();
    }

}
