package eureka.zuul.girl.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eureka.zuul.girl.feign.AuntFeign;
import eureka.zuul.girl.service.GirlService;

@Service
public class GirlServiceImpl implements GirlService {

	@Autowired
	AuntFeign auntFeign;

	@Override
	public String growUp() {
		return auntFeign.sayHello();
	}

}
