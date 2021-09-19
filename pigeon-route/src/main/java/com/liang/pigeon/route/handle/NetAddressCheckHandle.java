package com.liang.pigeon.route.handle;

import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/14 4:28 PM
 */
public class NetAddressCheckHandle {

    public static boolean addressCheck(String address, int port, int timeout) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(address, port), timeout);
            return true;
        }catch (Exception e) {
            return false;
        }finally {
            try {
                socket.close();
            }catch (Exception e) {
                return false;
            }
        }
    }
}
