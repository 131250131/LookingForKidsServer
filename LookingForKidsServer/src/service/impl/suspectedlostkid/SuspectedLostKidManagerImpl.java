package service.impl.suspectedlostkid;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import bean.SimilarityRecord;
import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import dao.suspectedlostkid.SuspectedLostKidDao;
import form.SuspectedKidForm;
import service.suspectedlostkid.SuspectedLostKidManager;

public class SuspectedLostKidManagerImpl implements SuspectedLostKidManager {
	
	private SuspectedLostKidDao suspectedLostKidDao;
	
	public void setSuspectedLostKidDao(SuspectedLostKidDao suspectedLostKidDao) {
		this.suspectedLostKidDao = suspectedLostKidDao;
	}
	
	public SuspectedLostKidDao getSuspectedLostKidDao() {
		return suspectedLostKidDao;
	}

	public void contact(SuspectedKidForm suspectedKidForm) throws HibernateException {
		SuspectedKid suspectedKid = new SuspectedKid();
		BeanUtils.copyProperties(suspectedKidForm, suspectedKid);
		suspectedLostKidDao.contact(suspectedKid);
	}

	public List<SuspectedKid> getSuspectedKids(int kidID) throws HibernateException {
		return suspectedLostKidDao.getSuspectedKids(kidID);
	}

	public List<SuspectedKidPhoto> getSuspectedPhotos(List<Integer> kidsID) throws HibernateException {
		return suspectedLostKidDao.getSuspectedPhotos(kidsID);
	}
	
	public List<SimilarityRecord> getSimilarityRecords(int userID) throws HibernateException {
		return suspectedLostKidDao.getSimilarityRecords(userID);
	}
	
	public List<SuspectedKid> getSuspectedKidsByID(List<Integer> kidsID) throws HibernateException {
		return suspectedLostKidDao.getSuspectedKidsByID(kidsID);
	}

}
