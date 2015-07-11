package service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import bean.Kid;
import bean.User;
import dao.UserDao;
import form.KidPublishForm;
import form.UserRegisterForm;
import service.UserManager;

public class UserManagerImpl implements UserManager {
	
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public UserDao getUserDao() {
		return userDao;
	}
	
	public void register(UserRegisterForm userRegisterForm) throws HibernateException {
		User user = new User();
		BeanUtils.copyProperties(userRegisterForm, user);
		userDao.register(user);
	}


	public void publish(KidPublishForm kidPublishForm) throws HibernateException {
		Kid kid = new Kid();
		BeanUtils.copyProperties(kidPublishForm, kid);
		userDao.publish(kid);
	}
	@Override
	public List<User> getUserByEmail(String email) throws HibernateException {
		return userDao.getUserByEmail(email);
	}

	@Override
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException {
		return userDao.getUserByPhoneNumber(phoneNumber);
	}

}
