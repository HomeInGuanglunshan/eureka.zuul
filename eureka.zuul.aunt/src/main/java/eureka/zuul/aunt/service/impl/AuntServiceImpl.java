package eureka.zuul.aunt.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import eureka.zuul.aunt.cache.redis.RedisDistributedLock;
import eureka.zuul.aunt.service.AuntService;
import redis.clients.jedis.JedisCluster;

@Service
public class AuntServiceImpl implements AuntService {

	@Autowired
	EntityManager entityManager;

	@Autowired
	JedisCluster jedisCluster;

	@Autowired
	PlatformTransactionManager transactionManager;

	@Override
	public void insertIncrementally(int step) {
		String requestId = UUID.randomUUID().toString();
		TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
		try {
			while (!RedisDistributedLock.tryGetDistributedLock(jedisCluster, "insertLock", requestId, 5000))
				;

			String sql = " select current from locker ";
			Query query = entityManager.createNativeQuery(sql);
			int former = (Integer) query.getResultList().get(0);

			int added = former + step;

			sql = " update locker set current = :added where current = :former ";
			query = entityManager.createNativeQuery(sql);
			query.setParameter("added", added);
			query.setParameter("former", former);
			query.executeUpdate();

			String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
			sql = " insert into ghi (h, i) values (:h, :i) ";
			query = entityManager.createNativeQuery(sql);
			query.setParameter("h", added);
			query.setParameter("i", timeStr);
			query.executeUpdate();

			transactionManager.commit(status);

		} catch (Exception e) {
			e.printStackTrace();
			transactionManager.rollback(status);
		} finally {
			RedisDistributedLock.releaseDistributedLock(jedisCluster, "insertLock", requestId);
		}
	}

//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void insertIncrementally(int step) {
//
//		String requestId = UUID.randomUUID().toString();
//		while (!RedisDistributedLock.tryGetDistributedLock(jedisCluster, "insertLock", requestId, 5000))
//			;
//
//		String sql = " select current from locker ";
//		Query query = entityManager.createNativeQuery(sql);
//		int former = (Integer) query.getResultList().get(0);
//
//		int added = former + step;
//
//		sql = " update locker set current = :added where current = :former ";
//		query = entityManager.createNativeQuery(sql);
//		query.setParameter("added", added);
//		query.setParameter("former", former);
//		query.executeUpdate();
//
//		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		sql = " insert into ghi (h, i) values (:h, :i) ";
//		query = entityManager.createNativeQuery(sql);
//		query.setParameter("h", added);
//		query.setParameter("i", timeStr);
//		query.executeUpdate();
//
//		// 事务不能没有提交就释放锁
//		RedisDistributedLock.releaseDistributedLock(jedisCluster, "insertLock", requestId);
//	}

	/**
	 * 获得锁、释放锁的操作在该方法被调用处前后
	 */
//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public void insertIncrementally(int step) {
//
//		String sql = " select current from locker ";
//		Query query = entityManager.createNativeQuery(sql);
//		int former = (Integer) query.getResultList().get(0);
//
//		int added = former + step;
//
//		sql = " update locker set current = :added where current = :former ";
//		query = entityManager.createNativeQuery(sql);
//		query.setParameter("added", added);
//		query.setParameter("former", former);
//		query.executeUpdate();
//
//		String timeStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
//		sql = " insert into ghi (h, i) values (:h, :i) ";
//		query = entityManager.createNativeQuery(sql);
//		query.setParameter("h", added);
//		query.setParameter("i", timeStr);
//		query.executeUpdate();
//	}

}
