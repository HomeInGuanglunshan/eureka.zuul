package eureka.zuul.task.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldApplicationRunner implements ApplicationRunner {

	Logger logger = LoggerFactory.getLogger(HelloWorldApplicationRunner.class);

	@Override
	public void run(ApplicationArguments arg0) throws Exception {
		logger.info("Hello World from Spring Cloud Task!");
	}
}
