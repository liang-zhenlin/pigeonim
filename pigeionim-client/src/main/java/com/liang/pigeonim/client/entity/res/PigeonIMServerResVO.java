package com.liang.pigeonim.client.entity.res;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author liangzhenlin
 * @Desc
 * @date 2021/9/7 4:34 PM
 */
@Data
@Builder
public class PigeonIMServerResVO implements Serializable {

    private static final long serialVersionUID = 7302425738084607795L;

    private int code;

    private String message;

    private Object reqNum;

    private ServerInfo data;


    @Data
    @Builder
    public static class ServerInfo {

        private String ip;

        private Integer pigeonIMServerPort;

        private Integer httpPort;
    }
}
