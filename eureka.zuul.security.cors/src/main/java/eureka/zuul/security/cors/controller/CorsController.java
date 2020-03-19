package eureka.zuul.security.cors.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("cors")
@RestController
//@CrossOrigin
/**
 * 加上@CrossOrigin后，能成功接收到服务器响应，而且多了两个响应头：
 *
 * <pre>
 * Vary: Origin, Access-Control-Request-Method, Access-Control-Request-Headers
 * Access-Control-Allow-Origin: *
 * </pre>
 *
 * refer to: https://blog.csdn.net/oblily/article/details/87880904
 */
public class CorsController {

	@RequestMapping("hello")
	public String hello() {
		return "hello on " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

}
