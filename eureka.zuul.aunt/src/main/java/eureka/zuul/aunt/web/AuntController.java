package eureka.zuul.aunt.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eureka.zuul.aunt.feign.GirlFeign;

@Controller
public class AuntController {

	@Autowired
	GirlFeign girlFeign;

	@RequestMapping("sayHello")
	@ResponseBody
	public String sayHello() {
		return "Hello, I am your sexy aunt. [from aunt]";
	}

	@RequestMapping("becomeYounger")
	@ResponseBody
	public String becomeYounger() {
		return girlFeign.becomeYounger();
	}

}
