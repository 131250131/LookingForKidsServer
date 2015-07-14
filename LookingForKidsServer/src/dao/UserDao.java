package dao;

import java.util.List;

import org.hibernate.HibernateException;

import bean.Kid;
import bean.KidPhoto;
import bean.SuspectedKid;
import bean.User;

public interface UserDao {

	public void register(User user) throws HibernateException;
	public int publish(Kid kid) throws HibernateException;
	public List<User> getUserByEmail(String email) throws HibernateException;
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException;
	public void upload(KidPhoto kidPhoto) throws HibernateException;
	public void contact(SuspectedKid suspectedKid) throws HibernateException;
}
