package eureka.zuul.boy.backup.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@Primary
@ConfigurationProperties(prefix = "spring.datasource")
public class MyDruidConfig extends DruidDataSource {

	private static final long serialVersionUID = 1L;

}
