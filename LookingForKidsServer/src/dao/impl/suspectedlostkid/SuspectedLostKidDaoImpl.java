package dao.impl.suspectedlostkid;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import dao.suspectedlostkid.SuspectedLostKidDao;

public class SuspectedLostKidDaoImpl extends HibernateDaoSupport implements SuspectedLostKidDao {

	//这是随手拍发布的信息，需要调用匹配函数，返回一个map之后，需要给对应的用户推送
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
	public List<SuspectedKid> getSuspectedKids(int kidID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		if(kidID == 0) {
			hibernateTemplate.setMaxResults(15);
			return (List<SuspectedKid>)hibernateTemplate.find("from bean.SuspectedKid k order by k.kidID desc");
		} else {
			String queryString = "from bean.SuspectedKid k where k.kidID<:id order by k.kidID desc";
			String paramName = "id";
			int value = kidID;
			hibernateTemplate.setMaxResults(15);
			return (List<SuspectedKid>)hibernateTemplate.findByNamedParam(queryString, paramName, value);
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<SuspectedKidPhoto> getSuspectedPhotos(List<Integer> kidsID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<SuspectedKidPhoto> photos = new LinkedList<SuspectedKidPhoto>();
		String queryString = "from bean.SuspectedKidPhoto k where k.kidID=:id";
		String paramName = "id";
		for(int i=0;i<kidsID.size();i++){
			int value = kidsID.get(i);
			photos.addAll((List<SuspectedKidPhoto>)hibernateTemplate.findByNamedParam(queryString, paramName, value));
		}
		return photos;
	}

}
