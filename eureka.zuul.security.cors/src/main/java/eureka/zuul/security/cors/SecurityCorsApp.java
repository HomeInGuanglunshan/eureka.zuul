package eureka.zuul.security.cors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EurekaClientAutoConfiguration;

@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, EurekaClientAutoConfiguration.class })
public class SecurityCorsApp {

	public static void main(String[] args) {
		SpringApplication.run(SecurityCorsApp.class, args);
	}

}
