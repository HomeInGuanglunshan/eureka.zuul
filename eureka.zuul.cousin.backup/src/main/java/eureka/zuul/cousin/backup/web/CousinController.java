package eureka.zuul.cousin.backup.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RefreshScope
public class CousinController {

	/**
	 * 如果git中配置文件是yml格式，那么值要用双引号包起来，否则报错：Could not resolve placeholder 'hello'
	 * in value "${hello}" ???————后来发现，不加双引号好像也没有问题
	 */
	@Value("${hello}")
	String hello;

	@RequestMapping("hello")
	@ResponseBody
	public String hello() {
		return this.hello;
	}

}
