package eureka.zuul.aunt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class AuntApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuntApplication.class, args);
	}

}
