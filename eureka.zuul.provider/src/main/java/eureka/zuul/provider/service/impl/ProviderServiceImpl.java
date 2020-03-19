package eureka.zuul.provider.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

import eureka.zuul.provider.service.ProviderService;
import eureka.zuul.provider.stream.channel.MultiPartOutput;
import eureka.zuul.provider.stream.channel.ProcessChannel;

//@Service // 有@EnableBinding就没必要加上@Service了，不过加上也没有问题
@EnableBinding({ Source.class, MultiPartOutput.class, ProcessChannel.class })
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	Source source;

	@Autowired
	MultiPartOutput multiPartOutput;

	@Autowired
	ProcessChannel processChannel;

	@Override
	public void sendMsg(String msg) {
		source.output().send(MessageBuilder.withPayload(msg).build());
	}

	@Override
	public void sendToMultiParts(String msg) {
		multiPartOutput.multiPartOutput().send(MessageBuilder.withPayload(msg).build());
	}

	@Override
	public void sendToProcessor(String msg) {
		processChannel.unprocessed().send(MessageBuilder.withPayload(msg).build());
	}

}
