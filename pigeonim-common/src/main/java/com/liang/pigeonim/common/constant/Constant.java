package com.liang.pigeonim.common.constant;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/6/16 8:45 AM
 */
public class Constant {

    /**
     * sessionId
     */
    public final static String SESSION_ID = "sid";

    /**
     * 账号前缀
     */
    public final static String ACCOUNT_PREFIX = "pigeonim-account:";

    /**
     * 路由信息前缀
     */
    public final static String ROUTE_PREFIX = "pigeonim-route:";

    /**
     * 登录状态前缀
     */
    public final static String LOGIN_STATUS_PREFIX = "login-status:";

    /**
     * 自定义报文类型
     */
    public static class MessageTpye{
        /**
         * 登录
         */
        public static final int LOGIN = 0;
        /**
         * 业务消息
         */
        public static final int MSG = 1;

        /**
         * ping
         */
        public static final int PING = 2;
    }

}
