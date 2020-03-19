package eureka.zuul.uncle.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import eureka.zuul.uncle.service.UncleService;

@Service
public class UncleServiceImpl implements UncleService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	@HystrixCommand(fallbackMethod = "failToActLikeGirl")
	public <T> T actLikeGirl(Map<String, String> map, Class<T> clazz) {

		StringBuffer url = new StringBuffer("http://eureka.zuul.girl/" + map.get("methodName") + "?");

		map.remove("methodName");
		for (String key : map.keySet()) {
			url.append(key);
			url.append("=");
			url.append(map.get(key));
			url.append("&");
		}

		return restTemplate.getForObject(url.toString(), clazz);
	}

	/**
	 * 方法参数和方法返回类型，必须和actLikeGirl完全一致，否则报错
	 *
	 * @param map
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T failToActLikeGirl(Map<String, String> map, Class<T> clazz) {
		return (T) "You can't imitate a girl";
	}

	/**
	 * 把yml中ribbon.okhttp.enabled和ribbon.restclient.enabled的注释去掉才能正常运行，否则报 No
	 * instances available for XXX
	 */
	@Override
	public Object usersShow() {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("access_token", "2.00uhIi9DqMGZFC7aef25045eiMijDD");
		paramMap.put("uid", "3172655810");
		Object result = restTemplate
				.getForEntity("https://api.weibo.com/2/users/show.json?access_token={access_token}&uid={uid}",
						Map.class, paramMap)
				.getBody();
		return result;
	}

}
