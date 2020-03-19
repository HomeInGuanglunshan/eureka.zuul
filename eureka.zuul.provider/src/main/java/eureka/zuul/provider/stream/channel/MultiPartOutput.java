package eureka.zuul.provider.stream.channel;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface MultiPartOutput {

	String MULTI_PART_OUTPUT = "multiPartOutput";

	@Output(MultiPartOutput.MULTI_PART_OUTPUT)
	MessageChannel multiPartOutput();

}
