package eureka.zuul.uncle.web;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eureka.zuul.uncle.feign.BoyFeign;
import eureka.zuul.uncle.feign.GirlFeign;
import eureka.zuul.uncle.service.UncleService;

@Controller
public class UncleController {

	@Autowired
	HttpServletRequest request;

	/**
	 * 受yml的ribbon.ReadTimeout、ribbon.ConnnectionTimeout等参数的影响
	 */
	@Autowired
	BoyFeign boyFeign;

	/**
	 * 受yml的ribbon.ReadTimeout、ribbon.ConnnectionTimeout等参数的影响
	 */
	@Autowired
	GirlFeign girlFeign;

	@Autowired
	UncleService uncleService;

	@RequestMapping("sayHello")
	@ResponseBody
	public String sayHello() {
		return "Hello! I am your uncle";
	}

	@RequestMapping("sayHelloAsBoy")
	@ResponseBody
	public String sayHelloAsBoy() {
		return boyFeign.sayHello();
	}

	@RequestMapping("wakeUpAsGirlAfter")
	@ResponseBody
	public String wakeUpAsGirlAfter(int millis) {
		return girlFeign.wakeUpAfter(millis);
	}

	@RequestMapping("getIdFromGirl")
	@ResponseBody
	public Long getIdFromGirl(Long id) {
		return girlFeign.getId(id);
	}

	@RequestMapping("busy")
	@ResponseBody
	public String busy() {
		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "Your uncle stops busying";
	}

	@RequestMapping("eatCookies")
	@ResponseBody
	public String eatCookies() {
		StringBuffer stringBuffer = new StringBuffer();
		for (Cookie cookie : request.getCookies()) {
			stringBuffer.append(cookie.getName() + "=" + cookie.getValue() + ";");
		}
		return stringBuffer.toString();
	}

	/**
	 * 鲜有Read timed out executing
	 * POST的问题出现，貌似不受yml的ribbon.ReadTimeout、ribbon.ConnnectionTimeout等参数的影响
	 *
	 * @param map
	 * @return
	 */
	@RequestMapping("actLikeGirl")
	@ResponseBody
	public String actLikeGirl(@RequestParam Map<String, String> map) {
		return uncleService.actLikeGirl(map, String.class);
	}

	@RequestMapping("usersShow")
	@ResponseBody
	public Object usersShow() {
		return uncleService.usersShow();
	}

}
