package service;

import org.hibernate.HibernateException;

import form.UserRegisterForm;

public interface UserManager {

	public void register(UserRegisterForm userRegisterForm) throws HibernateException;
	
}
