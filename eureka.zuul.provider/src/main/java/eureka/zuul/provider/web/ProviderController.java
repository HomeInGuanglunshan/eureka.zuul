package eureka.zuul.provider.web;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import eureka.zuul.provider.service.ProviderService;

@Controller
public class ProviderController {

	@Autowired
	ProviderService providerService;

	@RequestMapping("sendMsg")
	@ResponseBody
	public void sendMsg(String msg) {
		providerService.sendMsg(msg);
	}

	@RequestMapping("sendMsgContinuously")
	@ResponseBody
	public void sendMsgContinuously(@RequestParam(required = false, defaultValue = "1") Integer times) {
		for (int i = 0; i < times; i++) {
			providerService.sendMsg(String.valueOf(new Random().nextInt(1000)));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("sendToMultiParts")
	@ResponseBody
	public void sendToMultiParts(@RequestParam(required = false, defaultValue = "1") Integer times) {
		for (int i = 0; i < times; i++) {
			providerService.sendToMultiParts(String.valueOf(new Random().nextInt(10000)));
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@RequestMapping("sendToProcessor")
	@ResponseBody
	public void sendToProcessor(@RequestParam(required = false, defaultValue = "1") Integer times) {
		for (int i = 0; i < times; i++) {
			providerService.sendToProcessor(String.valueOf(new Random().nextInt(10000)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
