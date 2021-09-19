package com.liang.pigeonim.common.asyn;

import org.apache.skywalking.apm.toolkit.trace.TraceCrossThread;
import org.slf4j.MDC;

import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author liangzhenlin
 * @Desc    Callable的包装类，支持skywalking链路和线程间参数传递
 * @date 2021/6/16 1:30 PM
 */
@TraceCrossThread
public class MDCCallable<V> implements Callable<V> {

    private final Callable<V> callable;

    private transient final Map<String, String> map = MDC.getCopyOfContextMap();

    public MDCCallable(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public V call() throws Exception {
        if (map != null) {
            MDC.setContextMap(map);
        }
        try {
            return callable.call();
        } finally {
            MDC.clear();
        }
    }

}
