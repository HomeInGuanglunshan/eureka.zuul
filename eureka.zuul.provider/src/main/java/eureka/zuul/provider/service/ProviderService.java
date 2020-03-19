package eureka.zuul.provider.service;

public interface ProviderService {

	void sendMsg(String msg);

	void sendToMultiParts(String msg);

	void sendToProcessor(String msg);

}
