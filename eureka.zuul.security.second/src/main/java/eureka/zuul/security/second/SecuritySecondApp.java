package eureka.zuul.security.second;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, EurekaClientAutoConfiguration.class })
public class SecuritySecondApp {

	public static void main(String[] args) {
		SpringApplication.run(SecuritySecondApp.class, args);
	}

}
