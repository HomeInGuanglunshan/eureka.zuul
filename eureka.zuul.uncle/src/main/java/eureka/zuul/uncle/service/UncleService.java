package eureka.zuul.uncle.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

public interface UncleService {

	<T> T actLikeGirl(@RequestParam Map<String, String> map, Class<T> clazz);

	Object usersShow();

}
