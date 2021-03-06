package eureka.zuul.consumer.backup.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessChannel {

	String PROCESSED = "processed";

	@Input(ProcessChannel.PROCESSED)
	SubscribableChannel processed();

}
