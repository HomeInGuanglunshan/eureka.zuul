package eureka.zuul.processor.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface ProcessChannel {

	String UNPROCESSED = "unprocessed";

	@Input(ProcessChannel.UNPROCESSED)
	SubscribableChannel unprocessed();

	String PROCESSED = "processed";

	@Output(ProcessChannel.PROCESSED)
	MessageChannel processed();

	String MULTI_PART_OUTPUT = "multiPartOutput";

	@Output(ProcessChannel.MULTI_PART_OUTPUT)
	MessageChannel multiPartOutput();
}
