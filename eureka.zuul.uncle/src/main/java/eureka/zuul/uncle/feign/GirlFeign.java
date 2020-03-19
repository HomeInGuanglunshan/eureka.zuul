package eureka.zuul.uncle.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import eureka.zuul.uncle.feign.fallback.GirlFeignFallback;

@Component
//@FeignClient(name = "EUREKA.ZUUL.GIRL", fallback = GirlFeignFallback.class) // name的值对大小不敏感
@FeignClient(name = "eureka.zuul.girl", fallback = GirlFeignFallback.class) // name的值对大小不敏感
public interface GirlFeign {

	@PostMapping("wakeUpAfter")
	String wakeUpAfter(@RequestParam("millis") int millis);

	@GetMapping("getId/{id}")
	Long getId(@PathVariable("id") Long id);

}
