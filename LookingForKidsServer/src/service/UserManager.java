package service;

import java.util.List;

import org.hibernate.HibernateException;

import bean.User;
import form.KidPublishForm;
import form.SuspectedKidForm;
import form.UserRegisterForm;

public interface UserManager {

	public void register(UserRegisterForm userRegisterForm) throws HibernateException;
	public void publish(KidPublishForm kidRegisterForm) throws HibernateException;
	public List<User> getUserByEmail(String email) throws HibernateException;
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException;
	public void contact(SuspectedKidForm suspectedKidForm) throws HibernateException;
//	public List<>
}
