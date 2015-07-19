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
import org.json.JSONException;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import com.facepp.error.FaceppParseException;

import bean.Kid;
import bean.SimilarityRecord;
import bean.SuspectedKid;
import bean.SuspectedKidPhoto;
import dao.suspectedlostkid.SuspectedLostKidDao;
import facerec.RecognizitionController;

public class SuspectedLostKidDaoImpl extends HibernateDaoSupport implements SuspectedLostKidDao {
	String url="jdbc:mysql://localhost";
	String user="root";
	String password="hongmao";
	String sql="";
	
	//锟斤拷锟斤拷锟斤拷锟斤拷锟侥凤拷锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷要锟斤拷锟斤拷匹锟戒函锟斤拷锟揭伙拷锟絤ap之锟斤拷锟斤拷要锟斤拷锟接︼拷锟斤拷没锟斤拷锟斤拷锟�
	//锟斤拷锟斤拷锟斤拷锟斤拷锟侥凤拷锟斤拷锟斤拷锟斤拷息锟斤拷锟斤拷要锟斤拷锟斤拷匹锟戒函锟斤拷锟揭伙拷锟絤ap之锟斤拷锟斤拷要锟斤拷锟接︼拷锟斤拷没锟斤拷锟斤拷锟�
		public void contact(SuspectedKid suspectedKid) throws HibernateException {
			HibernateTemplate hibernateTemplate = getHibernateTemplate();
			hibernateTemplate.setCheckWriteOperations(false);
			hibernateTemplate.save(suspectedKid);
			int kidID = suspectedKid.getKidID();
			RecognizitionController rc = RecognizitionController.getInstacen();
			for(SuspectedKidPhoto suspectedKidPhoto : suspectedKid.getPhotos()){
				suspectedKidPhoto.setKidID(kidID);
				hibernateTemplate.save(suspectedKidPhoto);
				String path = "D:/ee_workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/LookingForKidsServer/" + suspectedKidPhoto.getPhotoPath();
				File filesimage = new File(path);
				
				ArrayList<Entry<String, Double>> tempMap;
				try {
					
					tempMap = rc.recognizeOneImage(filesimage);
					for(Map.Entry<String,Double> mapping : tempMap){
						System.out.print(mapping.getKey()+":"+mapping.getValue()+"\t");
						SimilarityRecord record = new SimilarityRecord();
						Kid kid = (Kid) hibernateTemplate.findByNamedParam("from bean.Kid k where k.kidID=:id", "id", Integer.parseInt(mapping.getKey())).get(0);
						record.setUserID(kid.getUserID());
						record.setSuspectedkidID(kidID);
						record.setSimilarity((int)(double)mapping.getValue());
						System.out.println("user"+record.getUserID()+"kid"+record.getSuspectedkidID()+"sim"+record.getSimilarity());
						hibernateTemplate.persist(record);
						try {
							Class.forName("com.mysql.jdbc.Driver");
							Connection conn = DriverManager.getConnection(url,user,password);
							Statement stmt = conn.createStatement();
							sql = "use LookingForKidsDB;";
							stmt.execute(sql);		
							sql = ""
								+ "insert into SimilarityMap(userID,MBKidID,similarity)"
								+ "values("+kid.getUserID()+", "+kidID+", "+(int)(double)mapping.getValue()+")"
								;
							stmt.execute(sql);
							stmt.close();
							conn.close();
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
						
						
					}
					
				} catch (FaceppParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
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
	public List<SuspectedKid> getSuspectedKidsByID(List<Integer> kidsID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		List<SuspectedKid> kidsList = new LinkedList<SuspectedKid>();
		String queryString = "from bean.SuspectedKid k where k.kidID=:id";
	    String paramName = "id";
		for(Integer loopID: kidsID) 
		    kidsList.addAll((List<SuspectedKid>)hibernateTemplate.findByNamedParam(queryString, paramName, loopID));
		return kidsList;
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
	
	@SuppressWarnings("unchecked")
	public List<SimilarityRecord> getSimilarityRecords(int userID) throws HibernateException {
		HibernateTemplate hibernateTemplate = getHibernateTemplate();
		return (List<SimilarityRecord>) hibernateTemplate.findByNamedParam("from bean.SimilarityRecord k where k.userID:=userID order by k.similarity desc",
				                                  "userID", userID);
		
	}

}
