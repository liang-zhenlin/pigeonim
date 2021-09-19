package com.liang.pigeonim.common.util;

import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/6 4:53 PM
 */
public class SessionSocketUtil {

    private static final Map<Long, NioSocketChannel> CHANNEL_MAP = new ConcurrentHashMap<>(16);
    private static final Map<Long, String> SESSION_MAP = new ConcurrentHashMap<>(16);

    public static void saveSession(Long userId, String userName) {
        SESSION_MAP.put(userId, userName);
    }

    public static void removeSession(Long userId) {
        SESSION_MAP.remove(userId);
    }

    public static void put(Long id, NioSocketChannel socketChannel) {
        CHANNEL_MAP.put(id, socketChannel);
    }

    public static NioSocketChannel get(Long id) {
        return CHANNEL_MAP.get(id);
    }

    public static void remove(NioSocketChannel nioSocketChannel) {
        CHANNEL_MAP.entrySet().stream().filter(v -> v.getValue() == nioSocketChannel).forEach(v -> CHANNEL_MAP.remove(v.getKey()));
    }
}
