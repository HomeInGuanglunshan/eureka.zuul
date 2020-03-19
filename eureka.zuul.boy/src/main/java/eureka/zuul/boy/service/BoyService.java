package eureka.zuul.boy.service;

public interface BoyService {

	String growUp();

	String waitForUncle();

	Object findByStatus();

	Object findByEmail(String email);

	Object findByNickname(String nickname);

}
