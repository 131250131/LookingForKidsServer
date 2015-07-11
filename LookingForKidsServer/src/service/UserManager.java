package service;

import org.hibernate.HibernateException;

import form.KidPublishForm;
import form.UserRegisterForm;

public interface UserManager {

	public void register(UserRegisterForm userRegisterForm) throws HibernateException;
	public void publish(KidPublishForm kidRegisterForm) throws HibernateException;
}
