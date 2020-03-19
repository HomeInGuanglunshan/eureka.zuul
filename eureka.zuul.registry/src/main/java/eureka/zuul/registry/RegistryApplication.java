package eureka.zuul.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
// pom.xml中加上spring-boot-starter-data-jpa后，必须exclude掉DataSourceAutoConfiguration，否则启动报错。不然就要配置spring.datasource
@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)
public class RegistryApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistryApplication.class, args);
	}

}
