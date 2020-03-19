package eureka.zuul.girl.backup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eureka.zuul.girl.backup.service.GirlDbService;
import eureka.zuul.girl.backup.service.GirlService;

@Controller
public class GirlController {

	@Autowired
	GirlService girlService;

	@Autowired
	GirlDbService girlDbService;

	@RequestMapping("sayHello")
	@ResponseBody
	public String sayHello() {
		return "Hello, I am a girl. [from girl backup]";
	}

	@RequestMapping("growUp")
	@ResponseBody
	public String growUp() {
		return girlService.growUp();
	}

	@GetMapping("getId/{id}")
	@ResponseBody
	public Long getId(@PathVariable Long id) {
		return id;
	}

	@RequestMapping("updateABC")
	@ResponseBody
	public void updateABC() {
		girlDbService.updateABC();
	}

	@RequestMapping("wakeUpAfter")
	@ResponseBody
	public String wakeUpAfter(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return "wake up after " + millis + " millis. [from girl backup]";
	}

	@RequestMapping("wakeUpAgainAfter")
	@ResponseBody
	public void wakeUpAgainAfter(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
