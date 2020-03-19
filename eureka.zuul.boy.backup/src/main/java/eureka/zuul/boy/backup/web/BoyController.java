package eureka.zuul.boy.backup.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import eureka.zuul.boy.backup.service.BoyService;

@Controller
public class BoyController {

	@Autowired
	BoyService boyService;

	@RequestMapping("sayHello")
	@ResponseBody
	public String sayHello() {
		return "Hello! I am a boy [from boy backup]";
	}

	@RequestMapping("growUp")
	@ResponseBody
	public String growUp() {
		return boyService.growUp();
	}

	@RequestMapping("waitForUncle")
	@ResponseBody
	public String waitForUncle() {
		return boyService.waitForUncle();
	}

	@RequestMapping("findByStatus")
	@ResponseBody
	public Object findByStatus() {
		return boyService.findByStatus();
	}

	@RequestMapping("findByEmail")
	@ResponseBody
	public Object findByEmail(String email) {
		return boyService.findByEmail(email);
	}

	@RequestMapping("findByNickname")
	@ResponseBody
	public Object findByNickname(String nickname) {
		return boyService.findByNickname(nickname);
	}
}
