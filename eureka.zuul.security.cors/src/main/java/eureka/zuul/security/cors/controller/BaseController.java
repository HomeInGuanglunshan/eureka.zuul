package eureka.zuul.security.cors.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("base")
@RestController
public class BaseController {

	@RequestMapping("base")
	public String base() {
		return "base. It's " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
