package service.user;

import java.util.List;

import org.hibernate.HibernateException;

import bean.User;
import form.UserRegisterForm;

public interface UserManager {

	public void register(UserRegisterForm userRegisterForm) throws HibernateException;
	public User getInfo(int userID) throws HibernateException;
	public List<User> getUserByEmail(String email) throws HibernateException;
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException;
	
}
