package com.liang.pigeon.route.handle;

import com.liang.pigeon.route.RouteException;
import com.liang.pigeonim.common.enumeration.ResultEnum;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/13 6:39 PM
 */
@Service
public class LoopHandle {

    private AtomicLong atomicLong = new AtomicLong();

    /**
     * 在服务里进行路由
     * @param values
     * @param key
     * @return
     */
    public String routeServer(List<String> values, String key) {
        if (values.size() == 0) {
            throw new RouteException(ResultEnum.SERVER_NOT_AVAILABLE);
        }
        Long position = atomicLong.incrementAndGet() % values.size();
        if (position < 0) {
            position = 0L;
        }
        return values.get(position.intValue());
    }
}
