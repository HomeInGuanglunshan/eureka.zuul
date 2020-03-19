package eureka.zuul.boy.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableFeignClients
@ServletComponentScan
@EnableJpaRepositories
@SpringBootApplication
public class BoyBackupApplication {

	public static void main(String[] args) {
		SpringApplication.run(BoyBackupApplication.class, args);
	}

}
