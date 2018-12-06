package com.chtwm.dubbo.dubbo.filter;

import com.alibaba.dubbo.rpc.Filter;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.Result;
import com.alibaba.dubbo.rpc.RpcContext;
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
//@Activate(group = {Constants.CONSUMER}, order = 1)
public class RbacSetDomainFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        final String domain = DomainContext.getContext().getDomain();
        log.info("RbacSetDomainFilter get domain info {} from DomainContext.getContext()", domain);
        // set domain to Invocation for remote
        Map<String, String> map = invocation.getAttachments();
        map.put("domain", domain);
        return invoker.invoke(invocation);
    }
}
