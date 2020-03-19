package eureka.zuul.aunt.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class Conf {

	@Bean
	public ThreadPoolTaskExecutor getThreadPoolTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(20);
		executor.setMaxPoolSize(40);
		executor.setKeepAliveSeconds(120);
		executor.setQueueCapacity(50);
		executor.setAllowCoreThreadTimeOut(false);
//		executor.setRejectedExecutionHandler(new DiscardOldestPolicy());
		return executor;
	}

}
