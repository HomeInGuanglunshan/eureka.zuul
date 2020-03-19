package eureka.zuul.boy.backup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("eureka.zuul.uncle")
public interface UncleFeign {

	@PostMapping("/sayHello")
	String sayHello();

	@PostMapping("/busy")
	String busy();
}
