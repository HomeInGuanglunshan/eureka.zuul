package eureka.zuul.security.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import eureka.zuul.security.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
