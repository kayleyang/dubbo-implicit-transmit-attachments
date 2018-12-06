package com.chtwm.dubbo.dubbo.loadblance;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.rpc.Invocation;
import com.alibaba.dubbo.rpc.Invoker;
import com.alibaba.dubbo.rpc.RpcException;
import com.alibaba.dubbo.rpc.cluster.loadbalance.AbstractLoadBalance;
import com.chtwm.dubbo.dubbo.DomainContext;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Date: 2018/11/20
 * Time: 16:19
 * User: yangkai
 * EMail: yangkai01@chtwm.com
 *
 * @author Kayle
 */
@Slf4j
public class RbacLoadBalance extends AbstractLoadBalance {

    public static final String NAME = "rbac";

    private final Random random = new Random();

    @Override
    public <T> Invoker<T> doSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) throws RpcException {
        final String domain = DomainContext.getContext().getDomain();
        log.info("RbacLoadBalance get domain info: {}", domain);

        if (domain == null || "".equals(domain)) {
            log.error("RbacLoadBalance get domain is null");
            return null;
        }
        int length = invokers.size();
        List<Invoker<T>> subInvokers = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            final Invoker<T> tInvoker = invokers.get(i);
            final String layer = getLayer(tInvoker);
            if (domain.equals(layer)) {
                subInvokers.add(tInvoker);
            }
        }
        return doNextSelect(subInvokers, url, invocation);
    }

    private <T> Invoker<T> doNextSelect(List<Invoker<T>> invokers, URL url, Invocation invocation) {
        // 总个数
        int length = invokers.size();
        // 总权重
        int totalWeight = 0;
        // 权重是否都一样
        boolean sameWeight = true;
        for (int i = 0; i < length; i++) {
            int weight = getWeight(invokers.get(i), invocation);
            // 累计总权重
            totalWeight += weight;
            if (sameWeight && i > 0
                    && weight != getWeight(invokers.get(i - 1), invocation)) {
                // 计算所有权重是否一样
                sameWeight = false;
            }
        }
        if (totalWeight > 0 && !sameWeight) {
            // 如果权重不相同且权重大于0则按总权重数随机
            int offset = random.nextInt(totalWeight);
            // 并确定随机值落在哪个片断上
            for (int i = 0; i < length; i++) {
                offset -= getWeight(invokers.get(i), invocation);
                if (offset < 0) {
                    return invokers.get(i);
                }
            }
        }
        // 如果权重相同或权重为0则均等随机
        return invokers.get(random.nextInt(length));
    }

    protected String getLayer(Invoker<?> invoker) {
        try {
            final Field providerUrl = invoker.getClass().getDeclaredField("providerUrl");
            providerUrl.setAccessible(true);
            final URL url = (URL) providerUrl.get(invoker);
            Map<String, String> map = url.getParameters();

            if (map != null && map.size() > 0) {
                final String layer = map.get("default.layer");
                return layer;
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
