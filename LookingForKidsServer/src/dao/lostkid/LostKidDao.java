package dao.lostkid;

import java.util.List;

import org.hibernate.HibernateException;

import bean.Kid;
import bean.KidPhoto;

public interface LostKidDao {

	public void publish(Kid kid) throws HibernateException;
	public List<Kid> getKids(int kidID) throws HibernateException;
	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException;
	public int getKidID() throws HibernateException; 
	
}
