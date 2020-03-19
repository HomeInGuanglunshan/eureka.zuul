package eureka.zuul.boy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eureka.zuul.boy.dao.UserDao;
import eureka.zuul.boy.dao.UserInterfaceDao;
import eureka.zuul.boy.feign.UncleFeign;
import eureka.zuul.boy.service.BoyService;

@Service
public class BoyServiceImpl implements BoyService {

	@Autowired
	UncleFeign uncleFeign;

	@Autowired
	UserDao userDao;

	@Autowired
	UserInterfaceDao userInterfaceDao;

	@Override
	public String growUp() {
		return uncleFeign.sayHello();
	}

	@Override
	public String waitForUncle() {
		return uncleFeign.busy();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object findByStatus() {
		return userDao.findByStatus();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object findByEmail(String email) {
		return userInterfaceDao.findByEmail(email);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Object findByNickname(String nickname) {
		return userDao.findByNickname(nickname);
	}
}
