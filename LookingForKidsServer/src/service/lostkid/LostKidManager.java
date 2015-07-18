package service.lostkid;

import java.util.List;

import org.hibernate.HibernateException;

import bean.Kid;
import bean.KidPhoto;
import form.KidPublishForm;

public interface LostKidManager {

	public void publish(KidPublishForm kidRegisterForm) throws HibernateException;
	public List<Kid> getKids(int kidID) throws HibernateException;
	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException;
	public int getKidID() throws HibernateException;
}
