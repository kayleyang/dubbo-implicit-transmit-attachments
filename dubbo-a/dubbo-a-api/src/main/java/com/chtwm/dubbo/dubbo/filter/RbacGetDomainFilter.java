package com.chtwm.dubbo.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcException;
import com.chtwm.dubbo.dubbo.DomainContext;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * Date: 2018/11/26
 * Time: 15:40
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 * @author Kayle
 */
@Slf4j
//@Activate(group = {Constants.PROVIDER}, order = 1)
public class RbacGetDomainFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        Map<String, String> map = invocation.getAttachments();
        final String domain = map.get("domain");
        log.info("RbacGetDomainFilter get domain info {} from invocation", domain);
        // set domain to DomainContext for RbacLoadBalance
        DomainContext.getContext().setDomain(domain);
        return invoker.invoke(invocation);
    }
}
