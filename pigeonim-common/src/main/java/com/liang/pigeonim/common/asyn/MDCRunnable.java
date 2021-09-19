package com.liang.pigeonim.common.asyn;

import org.apache.skywalking.apm.toolkit.trace.TraceCrossThread;
import org.slf4j.MDC;

import java.util.Map;

/**
 * @author liangzhenlin
 * @Desc    Runnable的包装类，支持skywalking链路和线程间参数传递
 * @date 2021/6/16 1:29 PM
 */
@TraceCrossThread
public class MDCRunnable implements Runnable {

    private final Runnable runnable;

    private transient final Map<String, String> map = MDC.getCopyOfContextMap();

    public MDCRunnable(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        if (map != null) {
            MDC.setContextMap(map);
        }
        try {
            runnable.run();
        } finally {
            MDC.clear();
        }
    }
}
