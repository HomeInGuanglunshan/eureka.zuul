package eureka.zuul.uncle.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 无论是通过Feign还是通过RestTemple调用服务，此设置均能生效
 */
@Configuration
//@RibbonClients(value = { @RibbonClient(name = "eureka.zuul.girl", configuration = FooConfiguration.class),
//		@RibbonClient(name = "eureka.zuul.boy", configuration = FooConfiguration.class) })
public class RibbonClientConfig {

	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}

}
