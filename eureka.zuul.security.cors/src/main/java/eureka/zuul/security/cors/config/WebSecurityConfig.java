package eureka.zuul.security.cors.config;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.ImmutableList;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

//	@Value("${server.servlet.session.cookie.name:JSESSIONID}")
//	String COOKIE_NAME;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().and().authorizeRequests().anyRequest().authenticated().and()
				// by default uses a Bean by the name of corsConfigurationSource
				// 貌似不太行。refer to: https://blog.csdn.net/oblily/article/details/87880904
				.cors().and() // 没有这句，跨域login失败
				.csrf().disable();
		http.exceptionHandling().authenticationEntryPoint((request, response, exceptioin) -> {
			response.setContentType("application/json;charset=utf-8");

			Map<String, Object> map = new HashMap<>();
			map.put("status", 401);
			map.put("message", "please login");

			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.toJSONString(map));
			writer.flush();
			writer.close();
		});
		http.formLogin().successHandler((request, response, authentication) -> {
			response.setContentType("application/json;charset=utf-8");

			Map<String, Object> map = new HashMap<>();
			map.put("status", 200);
			map.put("message", "login successfully");

			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.toJSONString(map));
			writer.flush();
			writer.close();
		});
//		http.logout().clearAuthentication(true).invalidateHttpSession(true).deleteCookies(COOKIE_NAME);
		http.logout().logoutSuccessHandler((request, response, authentication) -> {
			response.setContentType("application/json;charset=utf-8");

			Map<String, Object> map = new HashMap<>();
			map.put("status", 200);
			map.put("message", "logout successfully");

			PrintWriter writer = response.getWriter();
			writer.write(JSONObject.toJSONString(map));
			writer.flush();
			writer.close();
		});
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/cors/hello");
	}

	/**
	 * 貌似不太行
	 * <p>
	 * refer to: https://blog.csdn.net/oblily/article/details/87880904
	 *
	 * @return
	 */
//	@Bean
	public CorsConfigurationSource configurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("*"));
		configuration.setAllowedMethods(Arrays.asList("*"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		return source;
	}

	/**
	 * 貌似不太行
	 * <p>
	 * refer to: https://www.it1352.com/978249.html
	 *
	 * @return
	 */
//	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		final CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(ImmutableList.of("*"));
		configuration.setAllowedMethods(ImmutableList.of("HEAD", "GET", "POST", "PUT", "DELETE", "PATCH"));
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(ImmutableList.of("Authorization", "Cache-Control", "Content-Type"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	/**
	 * refer to: https://blog.csdn.net/oblily/article/details/87880904
	 *
	 * @return
	 */
	@Bean
	public CorsFilter corsFilter() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.addAllowedOrigin("*");
		configuration.setAllowCredentials(true);
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);

		CorsFilter corsFilter = new CorsFilter(source);

		// 这句貌似多余
//		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>(corsFilter);
//		registrationBean.setOrder(0);

		return corsFilter;
	}

}
