package eureka.zuul.consumer.backup.stream.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface MultiPartInput {

	String MULTI_PART_INPUT = "multiPartInput";

	/**
	 * 这里用MessageChannel取代SubscribableChannel也没有问题？
	 *
	 * @return
	 */
	@Input(MultiPartInput.MULTI_PART_INPUT)
	SubscribableChannel multiPartInput();

}
