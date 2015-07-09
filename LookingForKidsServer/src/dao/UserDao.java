package dao;

import org.hibernate.HibernateException;

import bean.User;

public interface UserDao {

	public void register(User user) throws HibernateException;
	
}
