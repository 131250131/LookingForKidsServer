package dao.impl.suspectedlostkid;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.hibernate.HibernateException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.facepp.error.FaceppParseException;

import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import dao.suspectedlostkid.SuspectedLostKidDao;
import facerec.RecognizitionController;

public class SuspectedLostKidDaoImpl extends HibernateDaoSupport implements SuspectedLostKidDao {
	String url="jdbc:mysql://localhost";
	String user="root";
	String password="hongmao";
	String sql="";
	
	
	//这是随手拍发布的信息，需要调用匹配函数，返回一个map之后，需要给对应的用户推送
	//这是随手拍发布的信息，需要调用匹配函数，返回一个map之后，需要给对应的用户推送
		public void contact(SuspectedKid suspectedKid) throws HibernateException {
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.setCheckWriteOperations(false);
			hibernateTemplate.save(suspectedKid);
			int kidID = suspectedKid.getKidID();
			RecognizitionController rc = RecognizitionController.getInstacen();
			for(SuspectedKidPhoto suspectedKidPhoto : suspectedKid.getPhotos()){
				suspectedKidPhoto.setKidID(kidID);
				hibernateTemplate.save(suspectedKidPhoto);
				File fileImage = new File(suspectedKidPhoto.getPhotoPath());
				try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn = DriverManager.getConnection(url,user,password);
					Statement stmt = conn.createStatement();
					sql = "use LookingForKidsDB;";
					stmt.execute(sql);		
					ArrayList<Entry<String, Double>> tempMap = rc.recognizeOneImage(fileImage);
					//把识别匹配的结果传入数据库
					for(Map.Entry<String,Double> mapping : tempMap){
						System.out.print(mapping.getKey()+":"+mapping.getValue()+"\t");
						sql = "INSERT INTO SimilarityMap(userID,MBKidIDsimilarity) "
								+ "VALUES("+mapping.getKey()+", "+kidID+", "+mapping.getValue()+");";
						stmt.execute(sql);
					}
					stmt.close();
					conn.close();
				} catch (FaceppParseException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (Exception e){
					e.printStackTrace();
				}
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
