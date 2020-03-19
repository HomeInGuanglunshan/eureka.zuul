package eureka.zuul.processor.stream;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.SendTo;

import eureka.zuul.processor.stream.channel.ProcessChannel;

@EnableBinding(ProcessChannel.class)
//@EnableBinding(Processor.class)
public class Repeater {

	Logger logger = LoggerFactory.getLogger(Repeater.class);

	@ServiceActivator(inputChannel = ProcessChannel.UNPROCESSED, outputChannel = ProcessChannel.PROCESSED)
//	@ServiceActivator(inputChannel = Processor.INPUT, outputChannel = Processor.OUTPUT)
	public Object process(Message<String> message) {
		String payload = message.getPayload();
		logger.info("收到信息：{}", payload);
		payload = payload + "[processed on " + new Date() + ", forwarded to " + ProcessChannel.PROCESSED + "]";
		return payload;
	}

	/**
	 * 实验结果：当同时存在process()和forward()两个方法时，假如processor要转发10条信息，则process()转发5条，
	 * forward()转发5条，不会每个方法都转发10条
	 */

	@StreamListener(ProcessChannel.UNPROCESSED)
	@SendTo(ProcessChannel.MULTI_PART_OUTPUT)
	public Object forward(Message<String> message) {
		String payload = message.getPayload();
		logger.info("收到信息：{}", payload);
		payload = payload + "[processed on " + new Date() + ", forwarded to " + ProcessChannel.MULTI_PART_OUTPUT + "]";
		return payload;
	}

}
