package eureka.zuul.girl.backup.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient("eureka.zuul.aunt")
public interface AuntFeign {

	@PostMapping("/sayHello")
	String sayHello();
}
