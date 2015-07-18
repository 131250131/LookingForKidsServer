package dao.impl.lostkid;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.HibernateException;
import org.json.JSONException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.facepp.error.FaceppParseException;

import bean.Kid;
import bean.KidPhoto;
import dao.lostkid.LostKidDao;
import facerec.GroupManager;

public class LostKidDaoImpl extends HibernateDaoSupport implements LostKidDao {

	//新添了一个走丢的小孩，需要把他的照片加到face++的服务器上
	public void publish(Kid kid) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		hibernateTemplate.setCheckWriteOperations(false);
		hibernateTemplate.save(kid);
		int kidID = kid.getKidID();
		for(KidPhoto kidPhoto : kid.getPhotos()){
			kidPhoto.setKidID(kidID);
			hibernateTemplate.save(kidPhoto);
		}
		String path = "./photo/"+kid.getKidID()+"/";
		GroupManager gm = GroupManager.getInstance();
		try {
			gm.addSomePhoto(path);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Kid> getKids(int kidID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		if(kidID == 0) {
			hibernateTemplate.setMaxResults(15);
			return (List<Kid>)hibernateTemplate.find("from bean.Kid k order by k.kidID desc");
		} else {
			String queryString = "from bean.Kid k where k.kidID<:id order by k.kidID desc";
			String paramName = "id";
			int value = kidID;
			hibernateTemplate.setMaxResults(15);
			return (List<Kid>)hibernateTemplate.findByNamedParam(queryString, paramName, value);
		}
	}

	@SuppressWarnings("unchecked")
	public List<KidPhoto> getPhotos(List<Integer> kidsID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<KidPhoto> photos = new LinkedList<KidPhoto>();
		String queryString = "from bean.KidPhoto k where k.kidID=:id";
		String paramName = "id";
		for(int i=0;i<kidsID.size();i++){
			int value = kidsID.get(i);
			photos.addAll((List<KidPhoto>)hibernateTemplate.findByNamedParam(queryString, paramName, value));
		}
		return photos;
	}

	@SuppressWarnings("unchecked")
	public int getKidID() throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		return ((List<Kid>)hibernateTemplate.find("from bean.Kid k order by k.kidID desc")).get(0).getKidID();
	}

}
