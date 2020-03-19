package eureka.zuul.provider.stream.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface ProcessChannel {

	String UNPROCESSED = "unprocessed";

	@Output(ProcessChannel.UNPROCESSED)
	MessageChannel unprocessed();

}
