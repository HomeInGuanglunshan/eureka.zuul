package eureka.zuul.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * record存于内存
	 */
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
//	}
//	@Override
//	@Bean
//	public UserDetailsService userDetailsService() {
//		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//		manager.createUser(
//				User.withUsername("cralor").password(new BCryptPasswordEncoder().encode("123")).roles("USER").build());
////		manager.createUser(
////				User.withUsername("admin").password(new BCryptPasswordEncoder().encode("123")).roles("ADMIN").build());
//		manager.createUser(User.withUsername("admin").password(new BCryptPasswordEncoder().encode("123"))
//				.roles("ADMIN", "USER").build());
//		return manager;
//	}

	@Autowired
	UserDetailsService userDetailsService;

	/**
	 * 自定义方法，貌似application启动时会执行
	 * <p>
	 * 低版本里，WebSecurityConfigurerAdapter没有
	 * <code>configure(AuthenticationManagerBuilder auth)</code>
	 * 这个方法提供给子类重写，估计这是作者自定义当前方法的原因
	 *
	 * @param auth
	 *            Is auth autowired? I think it is.
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				//以“/css/**”开头的和“/index”资源不需要验证，可直接访问
				.antMatchers("/css/**", "/index").permitAll()
				//任何以“/db/”开头的URL都要求用户拥有“ROLE_USER”角色
				.antMatchers("/user/**").hasRole("USER")
				//任何以“/db/”开头的URL都要求用户同时拥有“ROLE_ADMIN”和“ROLE_DBA”。由于我们使用的是hasRole表达式，因此我们不需要指定“ROLE_”前缀。
				.antMatchers("/db/**").access("hasRole('ADMIN') and hasRole('DBA')")
				/*
				 * //确保对我们的应用程序的任何请求都要求用户进行身份验证 .anyRequest().authenticated()
				 */
				.and()
				//允许用户使用基于表单的登录进行身份验证
				.formLogin()
				//表单登陆地址“/login”，登录失败地址“/login-error”
				.loginPage("/login").failureForwardUrl("/login-error").and().logout()
				//注销地址
//                        .logoutUrl("/logout")
				//注销成功，重定向到首页
				.logoutSuccessUrl("/")
				//指定一个自定义LogoutSuccessHandler。如果指定了，logoutSuccessUrl()则忽略。
				//.logoutSuccessHandler(logoutHandler)
				//指定HttpSession在注销时是否使其无效。默认true
				.invalidateHttpSession(true)
				//允许指定在注销成功时删除的cookie的名称。这是CookieClearingLogoutHandler显式添加的快捷方式。
				.deleteCookies("name", "ss", "aa").and()
				//异常处理会重定向到“/401”页面
				.exceptionHandling().accessDeniedPage("/401")

		//      .httpBasic()//允许用户使用HTTP基本身份验证进行身份验证
		;
	}
}