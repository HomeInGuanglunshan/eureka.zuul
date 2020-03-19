package eureka.zuul.girl.backup.service.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eureka.zuul.girl.backup.service.GirlDbService;

@Service
public class GirlDbServiceImpl implements GirlDbService {

	@Autowired
	private EntityManager entityManager;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateABC() {
		String sql = "update abc set b = b + 10, c = c + 1 where a = 1";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();
	}

}
