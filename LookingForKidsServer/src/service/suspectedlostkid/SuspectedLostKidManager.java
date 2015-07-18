package service.suspectedlostkid;

import java.util.List;

import org.hibernate.HibernateException;

import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import form.SuspectedKidForm;

public interface SuspectedLostKidManager {

	public void contact(SuspectedKidForm suspectedKidForm) throws HibernateException;
	public List<SuspectedKid> getSuspectedKids(int kidID) throws HibernateException;
	public List<SuspectedKidPhoto> getSuspectedPhotos(List<Integer> kidsID) throws HibernateException;

}