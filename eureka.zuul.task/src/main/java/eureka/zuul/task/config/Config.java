package eureka.zuul.task.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import eureka.zuul.task.component.HelloWorldTaskConfigurer;
import eureka.zuul.task.component.TaskListener;

@Configuration
public class Config {

	Logger logger = LoggerFactory.getLogger(Config.class);

	@Autowired
	private DataSource dataSource;

	@Bean
	public HelloWorldTaskConfigurer getTaskConfigurer() {
		return new HelloWorldTaskConfigurer(dataSource);
	}

	@Bean
	public TaskListener taskListener() {
		return new TaskListener();
	}

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job job2() {
		return jobBuilderFactory.get("job2").start(stepBuilderFactory.get("job2step1").tasklet(new Tasklet() {
			@Override
			public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
				logger.info("This job is from PeterWanghao");
				return RepeatStatus.FINISHED;
			}
		}).build()).build();
	}

//	@Bean
//	public TaskLauncher taskLauncher() {
//		return new MyTaskLauncher();
//	}

}
