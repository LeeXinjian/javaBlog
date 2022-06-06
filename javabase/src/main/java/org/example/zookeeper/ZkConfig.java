package org.example.zookeeper;

import lombok.Data;

@Data
public class ZkConfig {
    /**
     * 连接重试次数
     */
    private int retryCount;
    /**
     * 重试的间隔时间
     */
    private int elapsedTimeMs;
    /**
     * zookeeper连接地址
     */
    private String connectString;
    /**
     * session超时时间
     */
    private int sessionTimeoutMs;
    /**
     * 链接超时时间
     */
    private int connectionTimeoutMs;

}
