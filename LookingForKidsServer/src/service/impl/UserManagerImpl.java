package service.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import bean.Kid;
import bean.KidPhoto;
import bean.SuspectedKid;
import bean.User;
import dao.UserDao;
import form.KidPhotoUploadForm;
import form.KidPublishForm;
import form.SuspectedKidForm;
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


	public int publish(KidPublishForm kidPublishForm) throws HibernateException {
		Kid kid = new Kid();
		BeanUtils.copyProperties(kidPublishForm, kid);
		return userDao.publish(kid);
	}
	@Override
	public List<User> getUserByEmail(String email) throws HibernateException {
		return userDao.getUserByEmail(email);
	}

	@Override
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException {
		return userDao.getUserByPhoneNumber(phoneNumber);
	}

	public void upload(KidPhotoUploadForm kidPhotoUploadForm) throws HibernateException {
		userDao.upload(kidPhotoUploadForm.getKidPhoto());
	}

	public void contact(SuspectedKidForm suspectedKidForm) throws HibernateException {
		SuspectedKid suspectedKid = new SuspectedKid();
		BeanUtils.copyProperties(suspectedKidForm, suspectedKid);
		userDao.contact(suspectedKid);
	}

	public List<Kid> getKids(int kidID) throws HibernateException {
		return userDao.getKids(kidID);
	}

	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException {
		return userDao.getPhotos(kidsID);
	}

}
