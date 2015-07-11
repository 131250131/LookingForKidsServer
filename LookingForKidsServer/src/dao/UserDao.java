package dao;

import org.hibernate.HibernateException;

import bean.User;
import bean.Kid;

public interface UserDao {

	public void register(User user) throws HibernateException;
	public void publish(Kid kid) throws HibernateException;
	
}
