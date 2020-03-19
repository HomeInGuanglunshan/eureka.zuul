package eureka.zuul.security.second.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;

/**
 * https://blog.csdn.net/qq351790934/article/details/54930049
 */
//maxInactiveIntervalInSeconds为session过期时间，这里注意session过期时间配置在web.xml里面是不起作用的
@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 999)
public class HttpSessionConfig {

	//这里有个小坑，如果服务器用的是云服务器，不加这个会报错
	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}

	//这里是reids连接配置
//	@Bean
//	public JedisConnectionFactory connectionFactory() {
//		JedisConnectionFactory connection = new JedisConnectionFactory();
//		connection.setPort(6379);
//		connection.setHostName("127.0.0.1");
//		connection.setPassword("ps123456");
//		connection.setDatabase(8);
//		return connection;
//	}

	//使用HttpSessionStrategy需引入spring-session
	//session策略，这里配置的是Header方式（有提供Header，Cookie等方式），可自定义，后面会详细讲
//	@Bean
//	public HttpSessionStrategy httpSessionStrategy() {
//		return new HeaderHttpSessionStrategy();
//	}

	/**
	 * spring-boot 2.x 的正确做法
	 * <p>
	 * CookieHttpSessionIdResolver也同时存在，则HeaderHttpSessionIdResolver不生效？
	 *
	 * @return
	 */
	@Bean
	public HeaderHttpSessionIdResolver headerHttpSessionIdResolver() {
		return new HeaderHttpSessionIdResolver("x-auth-token");
	}
}
