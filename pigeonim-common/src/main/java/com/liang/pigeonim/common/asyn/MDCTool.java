package com.liang.pigeonim.common.asyn;

import java.util.concurrent.Callable;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 1:29 PM
 */
public final class MDCTool {

    public static MDCRunnable wrap(Runnable runnable) {
        return new MDCRunnable(runnable);
    }

    public static <V> MDCCallable<V> wrap(Callable<V> callable) {
        return new MDCCallable<>(callable);
    }

}
