package eureka.zuul.uncle.feign.fallback;

import org.springframework.stereotype.Component;

import eureka.zuul.uncle.feign.BoyFeign;

@Component
public class BoyFeignFallback implements BoyFeign {

	@Override
	public String sayHello() {
		return "he...he...hello";
	}

}
