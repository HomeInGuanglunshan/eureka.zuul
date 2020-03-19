package eureka.zuul.security.second.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * https://blog.csdn.net/zhu124866/article/details/99698986
 *
 * <pre>
 *
 * 		<dependency>
 *			<groupId>org.springframework.session</groupId>
 *			<artifactId>spring-session-core</artifactId>
 *		</dependency>
 *		<dependency>
 *			<groupId>org.springframework.session</groupId>
 *			<artifactId>spring-session-data-redis</artifactId>
 *		</dependency>
 *
 * </pre>
 *
 * 在pom.xml中添加上面两个的其中一个时（暂不清楚是哪个），无论什么都不干，都会多出一个名为“SESSION”的cookie
 * <p>
 * 我认为名为“SESSION”的cookie的出现，仅仅源于spring-session-data-redis的配置
 */
@Configuration
public class SpringSessionBeanConfiguration {

	@Value("${spring.session.cookieName:'JSESSIONID'}")
	private String cookieName;

	//Cookie配置
	@Bean
	public CookieSerializer cookieSerializer() {
		DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
		cookieSerializer.setCookieName(cookieName);//sessionId名称
		return cookieSerializer;
	}

	//HttpSessionId配置
	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
		cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer());
		return cookieHttpSessionIdResolver;
	}
}
