package dao;

import java.util.List;

import org.hibernate.HibernateException;

import bean.User;

public interface UserDao {

	public void register(User user) throws HibernateException;
	public List<User> getUserByEmail(String email) throws HibernateException;
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException;
}
