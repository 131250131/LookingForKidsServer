package service;

import java.util.List;

import org.hibernate.HibernateException;

import bean.Kid;
import bean.KidPhoto;
import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
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
	public List<Kid> getKids(int kidID) throws HibernateException;
	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException;
	public List<SuspectedKid> getSuspectedKids(int kidID) throws HibernateException;
	public List<SuspectedKidPhoto> getSuspectedPhotos(List<Integer> kidsID) throws HibernateException;
	public int getKidID() throws HibernateException;
}
