package eureka.zuul.ribbon;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

@Configuration
public class FooConfiguration {

	//通过方法返回的实例类型指定负载均衡算法规则
	@Bean
	public IRule ribbonRule(IClientConfig config) {
		//RandomRule ： 负载均衡规则：随机
		//RoundRobinRule: 轮询
		//WeightedResponseTimeRule：加权

		return new RandomRule(); //负载均衡算法：随机
	}

}
