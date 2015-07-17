package dao.impl;

import java.util.LinkedList;
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

	public void publish(Kid kid) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(kid);
		int kidID = kid.getKidID();
		for(KidPhoto kidPhoto : kid.getPhotos()){
			kidPhoto.setKidID(kidID);
			hibernateTemplate.save(kidPhoto);
		}
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

	@SuppressWarnings("unchecked")
	public List<Kid> getKids(int kidID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		if(kidID == 0) {
			hibernateTemplate.setMaxResults(15);
			return (List<Kid>)hibernateTemplate.find("from bean.Kid k order by k.kidID desc");
		} else {
			hibernateTemplate.setMaxResults(15);
			return (List<Kid>)hibernateTemplate.find("from bean.Kid k where k.kidID < ? order by k.kidID desc", kidID);
		}
	}

	@SuppressWarnings("unchecked")
	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<KidPhoto> photos = new LinkedList<KidPhoto>();
		for(int i=0;i<kidsID.size();i++){
			photos.addAll((List<KidPhoto>)hibernateTemplate.find("from bean.KidPhoto k where k.kidID = ?",kidsID.get(i)));
		}
		return photos;
	}

}
