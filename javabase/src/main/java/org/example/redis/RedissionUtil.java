package org.example.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;

public class RedissionUtil {

    public static RedissonClient getRedissonClient(String location) throws IOException {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource(location);
        Config config = Config.fromYAML(resource.getInputStream());
        config.useClusterServers();
        return Redisson.create(config);
    }

}
