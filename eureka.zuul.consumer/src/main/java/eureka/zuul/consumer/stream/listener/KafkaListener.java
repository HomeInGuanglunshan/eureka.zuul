package eureka.zuul.consumer.stream.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;

import eureka.zuul.consumer.stream.channel.MultiPartInput;
import eureka.zuul.consumer.stream.channel.ProcessChannel;

@EnableBinding({ Sink.class, MultiPartInput.class, ProcessChannel.class })
public class KafkaListener {

	Logger logger = LoggerFactory.getLogger(KafkaListener.class);

	/**
	 * from: https://blog.csdn.net/elim168/article/details/101233603
	 *
	 * @param message
	 */
	@StreamListener(Sink.INPUT)
	public void receiveMsg(Message<String> message) {
		String payload = message.getPayload();
		MessageHeaders headers = message.getHeaders();
		logger.info("从Binding-{}收到信息-{}， headers：{}", Sink.INPUT, payload, headers);
//		logger.info("从Binding-{}收到信息-{}", Sink.INPUT, payload);
	}

	@StreamListener(MultiPartInput.MULTI_PART_INPUT)
	public void receiveFromMultiParts(Message<String> message) {
		String payload = message.getPayload();
		MessageHeaders headers = message.getHeaders();
		logger.info("从Binding-{}收到信息-{}， headers：{}", MultiPartInput.MULTI_PART_INPUT, payload, headers);
//		logger.info("从Binding-{}收到信息-{}", MultiPartInput.MULTI_PART_INPUT, payload);
	}

	@StreamListener(ProcessChannel.PROCESSED)
	public void receiveFromProcessor(Message<String> message) {
		String payload = message.getPayload();
		MessageHeaders headers = message.getHeaders();
		logger.info("从Binding-{}收到信息-{}， headers：{}", ProcessChannel.PROCESSED, payload, headers);
//		logger.info("从Binding-{}收到信息-{}", MultiPartInput.MULTI_PART_INPUT, payload);
	}
}
