package eureka.zuul.uncle.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

import eureka.zuul.uncle.feign.fallback.BoyFeignFallback;

@Component
@FeignClient(name = "eureka.zuul.boy", fallback = BoyFeignFallback.class)
public interface BoyFeign {

	@PostMapping("sayHello")
	String sayHello();

}
