package eureka.zuul.aunt.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("eureka.zuul.girl")
public interface GirlFeign {

	@PostMapping("/sayHello")
	String becomeYounger();
}
