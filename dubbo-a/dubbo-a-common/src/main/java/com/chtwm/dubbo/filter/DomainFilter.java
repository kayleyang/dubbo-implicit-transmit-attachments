package com.chtwm.dubbo.filter;

import com.chtwm.dubbo.dubbo.DomainContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.chtwm.dubbo.util.CommonUtil.getDomainFlag;

/**
 * Date: 2018/11/27
 * Time: 14:09
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 */
@Slf4j
@Order(1)
@WebFilter(filterName = "domainFilter", urlPatterns = "/*")
public class DomainFilter implements Filter {

    private final static String DOMAIN_REG = "[0-9a-zA-Z]+((\\.com.cn)|(\\.com)|(\\.org)|(\\.net)|(\\.cn))";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //设置域名标识到DomainContext中
        setDomain2DomainContext((HttpServletRequest) servletRequest);
        filterChain.doFilter(servletRequest, servletResponse);
        return;
    }

    @Override
    public void destroy() {

    }

    private void setDomain2DomainContext(HttpServletRequest request) {

        final String domainFlag = getDomainFlag(request);
        DomainContext.getContext().setDomain(domainFlag);
        log.info("set domain {} to RbacContext", domainFlag);
    }
}
