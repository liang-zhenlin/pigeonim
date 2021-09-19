package com.liang.pigeonim.common.filter;

import cn.hutool.core.util.ArrayUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.extension.Activate;
import org.apache.dubbo.rpc.*;
import org.apache.skywalking.apm.toolkit.trace.ActiveSpan;

import static org.apache.dubbo.common.constants.CommonConstants.CONSUMER;
import static org.apache.dubbo.common.constants.CommonConstants.PROVIDER;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/15 4:08 PM
 */
@Slf4j
@Activate(group = {CONSUMER, PROVIDER})
public class DubboAdviceFilter implements Filter {
    @Override
    public Result invoke(Invoker<?> invoker, Invocation invocation) throws RpcException {
        ActiveSpan.setOperationName("dubboFilter");
        ActiveSpan.tag("arguments", ArrayUtil.toString(invocation.getArguments()));
        Result result = null;
        try {
            result = invoker.invoke(invocation);
            if (result != null) {
                if (result.hasException()) {
                    ActiveSpan.error(result.getException());
                } else {
                    ActiveSpan.tag("result", JSON.toJSONString(result.getValue()));
                }
            } else {
                ActiveSpan.tag("result", JSON.toJSONString(result.getValue()));
            }
        } catch (RpcException e) {
            log.error("dubbo调用异常", e);
            throw e;
        }
        return result;
    }
}
