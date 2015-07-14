package dao.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import bean.Kid;
import bean.KidPhoto;
import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import bean.User;
import dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	public void register(User user) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(user);
	}

	public int publish(Kid kid) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(kid);
		return kid.getKidID();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByEmail(String email) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		return (List<User>)hibernateTemplate.find("from bean.User u where u.email=?", email);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUserByPhoneNumber(String phoneNumber) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		return (List<User>)hibernateTemplate.find("from bean.User u where u.phonenumber=?", phoneNumber);
	}

	public void upload(KidPhoto kidPhoto) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(kidPhoto);
	}

	public void contact(SuspectedKid suspectedKid) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(suspectedKid);
		int kidID = suspectedKid.getKidID();
		for(SuspectedKidPhoto suspectedKidPhoto : suspectedKid.getPhotos()){
			suspectedKidPhoto.setKidID(kidID);
			hibernateTemplate.save(suspectedKidPhoto);
		}
	}

}
