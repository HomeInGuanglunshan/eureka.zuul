package eureka.zuul.aunt.cache.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisClusterConf {

	@Bean
	public JedisPoolConfig getJedisPoolConfig() {
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(100);
		poolConfig.setMinIdle(10);
		poolConfig.setTestOnBorrow(true);
		return poolConfig;
	}

	@Bean
	public JedisCluster getJedisCluster() {

		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.1.59", 7001));
		nodes.add(new HostAndPort("192.168.1.59", 7002));
		nodes.add(new HostAndPort("192.168.1.59", 7003));
		nodes.add(new HostAndPort("192.168.1.59", 7004));
		nodes.add(new HostAndPort("192.168.1.59", 7005));
		nodes.add(new HostAndPort("192.168.1.59", 7006));

		return new JedisCluster(nodes, getJedisPoolConfig());
	}

}
