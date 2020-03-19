package eureka.zuul.boy.backup.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eureka.zuul.boy.backup.entity.User;

@Repository
public class UserDao {

	@Autowired
	EntityManager entityManager;

	public Object findByStatus() {
		String sql = "select * from U_User where status = 1";
		Query query = entityManager.createNativeQuery(sql, User.class);
		return query.getResultList();
	}

	public Object findByNickname(String nickname) {
		String hql = "from User where nickname = :nickname";
		Query query = entityManager.createQuery(hql);
		query.setParameter("nickname", nickname);
		return query.getResultList();
	}
}
