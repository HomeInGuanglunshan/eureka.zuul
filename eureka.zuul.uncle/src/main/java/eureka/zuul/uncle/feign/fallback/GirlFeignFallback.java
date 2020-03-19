package eureka.zuul.uncle.feign.fallback;

import org.springframework.stereotype.Component;

import eureka.zuul.uncle.feign.GirlFeign;

@Component
public class GirlFeignFallback implements GirlFeign {

	@Override
	public String wakeUpAfter(int millis) {
		return "I wanna sleep forever";
	}

	@Override
	public Long getId(Long id) {
		return 88888888L;
	}

}
