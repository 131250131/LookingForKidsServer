package dao.suspectedlostkid;

import java.util.List;

import org.hibernate.HibernateException;

import bean.SimilarityRecord;
import bean.SuspectedKid;
import bean.SuspectedKidPhoto;

public interface SuspectedLostKidDao {
	
	public void contact(SuspectedKid suspectedKid) throws HibernateException;
	public List<SuspectedKid> getSuspectedKids(int kidID) throws HibernateException;
	public List<SuspectedKidPhoto> getSuspectedPhotos(List<Integer> kidsID) throws HibernateException;
	public List<SimilarityRecord> getSimilarityRecords(int userID) throws HibernateException;
	public List<SuspectedKid> getSuspectedKidsByID(List<Integer> kidsID) throws HibernateException;
}
