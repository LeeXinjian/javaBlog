package org.example.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.redisson.config.ConfigSupport;
import org.springframework.core.io.DefaultResourceLoader;

import java.io.IOException;

public class ZookeeperUtils {

    public CuratorFramework getCuratorFramework(String location) throws IOException {
        // zk配置
        ZkConfig zkConfig = new ConfigSupport()
                .fromYAML(
                        new DefaultResourceLoader()
                                .getResource(location)
                                .getInputStream()
                        , ZkConfig.class);
        //返回结果
        return CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getConnectString())
                .sessionTimeoutMs(zkConfig.getSessionTimeoutMs())
                .retryPolicy(new ExponentialBackoffRetry(zkConfig.getElapsedTimeMs(), zkConfig.getRetryCount()))
                .build();
    }

}
