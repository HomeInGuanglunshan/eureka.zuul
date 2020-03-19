package eureka.zuul.aunt.backup.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import eureka.zuul.aunt.backup.service.AuntService;
import redis.clients.jedis.JedisCluster;

@Component
public class MyTimer {

	@Autowired
	JedisCluster jedisCluster;

	@Autowired
	AuntService auntService;

	@Autowired
	ThreadPoolTaskExecutor executor;

//	@Scheduled(cron = "*/1 * * * * ?")
//	public void sayHello() {
//		System.out.println(jedisCluster.get("foo" + new Random().nextInt(100)));
//	}

	@Scheduled(cron = "*/3 * * * * ?")
	public void insertIncrementally() {

		for (int i = 0; i < 4; i++) {
			executor.execute(new Runnable() {
				@Override
				public void run() {
//					String requestId = UUID.randomUUID().toString();
//					while (!RedisDistributedLock.tryGetDistributedLock(jedisCluster, "insertLock", requestId, 5000))
//						;

					auntService.insertIncrementally(10);

//					RedisDistributedLock.releaseDistributedLock(jedisCluster, "insertLock", requestId);
				}
			});
		}

	}

}
