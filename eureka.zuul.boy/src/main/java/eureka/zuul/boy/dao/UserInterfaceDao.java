package eureka.zuul.boy.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import eureka.zuul.boy.entity.User;

@Repository
public interface UserInterfaceDao extends PagingAndSortingRepository<User, Long>, JpaSpecificationExecutor<User> {

	User findByEmail(String email);

}
