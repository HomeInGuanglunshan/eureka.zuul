package eureka.zuul.boy.entity;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import eureka.zuul.boy.dao.UserDao;

@Component
public class UserFactoryBean implements FactoryBean<User> {

	@Autowired
	UserDao userDao;

	@Override
	@SuppressWarnings("rawtypes")
	public User getObject() throws Exception {
		String[] nicknames = { "管理员", "soso", "8446666" };
		return (User) ((List) userDao.findByNickname(nicknames[new Random().nextInt(3)])).get(0);
	}

	@Override
	public Class<?> getObjectType() {
		return (Class<?>) ((ParameterizedType) this.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0];
	}

}
