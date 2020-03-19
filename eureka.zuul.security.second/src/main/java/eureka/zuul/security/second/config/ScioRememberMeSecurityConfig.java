package eureka.zuul.security.second.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.RememberMeAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;

/**
 * https://www.jianshu.com/p/0d25c70eb435
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ScioRememberMeSecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String SECRET = "scio@2019";

	@Autowired
	private UserDetailsService scioUserDetailsService;

	/**
	 * password encoder
	 *
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}

	/**
	 * RememberMeAuthenticationProvider.
	 *
	 * @return
	 */
	@Bean
	public RememberMeAuthenticationProvider rememberMeAuthenticationProvider() {
		return new RememberMeAuthenticationProvider(SECRET);
	}

	/**
	 * TokenBasedRememberMeServices.
	 *
	 * @return
	 */
	@Bean("tokenBaseRememberMeServices")
	public TokenBasedRememberMeServices tokenBasedRememberMeServices() {
		TokenBasedRememberMeServices rememberMeServices = new TokenBasedRememberMeServices(SECRET,
				scioUserDetailsService);
		rememberMeServices.setAlwaysRemember(false); // 如果设为true，则无论勾不勾选“Remember me on this computer.”，都会“remember me”
//		rememberMeServices.setCookieName("remember-me");
		rememberMeServices.setCookieName("do-not-forget-me"); // 效果同上
		rememberMeServices.setTokenValiditySeconds(AbstractRememberMeServices.TWO_WEEKS_S);
		return rememberMeServices;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(scioUserDetailsService).passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.antMatcher("/api/**");
		// .antMatchers("/api/login", "/api/logout")
		http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
		// permit login
		http.formLogin().permitAll().defaultSuccessUrl("/info").and().authorizeRequests().anyRequest().authenticated();
		http.rememberMe()
				// tokenBased, store token and user name in token data.
				.rememberMeServices(tokenBasedRememberMeServices())
				// .tokenRepository(new InMemoryTokenRepositoryImpl())
				// persistent serials/token, invalidate after delete from token store
				.and().authenticationProvider(rememberMeAuthenticationProvider());
//		http.sessionManagement().maximumSessions(1).maxSessionsPreventsLogin(false);
		http.headers().cacheControl();
	}

	/**
	 * https://blog.csdn.net/yuanlaijike/article/details/80249869
	 */
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		http.authorizeRequests()
//				// 如果有允许匿名的url，填在下面
////	                .antMatchers().permitAll()
//				.anyRequest().authenticated().and()
//				// 设置登陆页
//				.formLogin().loginPage("/login")
//				// 设置登陆成功页
//				.defaultSuccessUrl("/").permitAll()
//				// 自定义登陆用户名和密码参数，默认为username和password
////	                .usernameParameter("username")
////	                .passwordParameter("password")
//				.and().logout().permitAll()
//				// 自动登录
//				.and().rememberMe();
//
//		// 关闭CSRF跨域
//		http.csrf().disable();
//	}
}
