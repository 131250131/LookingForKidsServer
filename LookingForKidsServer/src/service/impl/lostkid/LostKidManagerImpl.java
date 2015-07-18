package service.impl.lostkid;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.BeanUtils;

import bean.Kid;
import bean.KidPhoto;
import dao.lostkid.LostKidDao;
import form.KidPublishForm;
import service.lostkid.LostKidManager;

public class LostKidManagerImpl implements LostKidManager {
	
	private LostKidDao lostKidDao;
	
	public void setlostKidDao(LostKidDao lostKidDao) {
		this.lostKidDao = lostKidDao;
	}
	
	public LostKidDao getlostKidDao() {
		return lostKidDao;
	}


	public void publish(KidPublishForm kidPublishForm) throws HibernateException {
		Kid kid = new Kid();
		BeanUtils.copyProperties(kidPublishForm, kid);
		lostKidDao.publish(kid);
	}

	public List<Kid> getKids(int kidID) throws HibernateException {
		return lostKidDao.getKids(kidID);
	}

	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException {
		return lostKidDao.getPhotos(kidsID);
	}

	public int getKidID() throws HibernateException {
		return lostKidDao.getKidID();
	}

}
