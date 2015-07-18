package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseCreator {
	String url="jdbc:mysql://localhost";
	String user="root";
	String password="hongmao";
	String sql="";
	
	public static void main(String args[]){
		DataBaseCreator creator = new DataBaseCreator();
		creator.createSimilarityMap();
	}
	
//	public void createLookingForKidsDB(){
//		try{
//			Class.forName("com.mysql.jdbc.Driver");
//			Connection conn = DriverManager.getConnection(url,user,password);
//			sql = "create database LookingForKidsDB";
//			Statement stmt = conn.createStatement();
//			stmt.execute(sql);
//			System.out.println("LookingForKidsDB has been created");
//			sql = "use LookingForKidsDB;";
//			stmt.execute(sql);		
//			sql = ""
//				+ "create table LostKidsInfo("
//				+ "kidID int auto_increment primary key,"
//				+ "userID int not null,"
//				+ "kidName varchar(10) not null,"
//				+ "gender varchar(10) not null,"
//				+ "birthday varchar(20) not null,"
//				+ "height int not null,"
//				+ "lostTime varchar(40) not null,"
//				+ "lostPlace varchar(20) not null,"
//				+ "homeTown varchar(40) not null,"
//				+ "description varchar(200) not null"
//				+ ")"
//				+ "DEFAULT CHARSET=utf8";
//			stmt.execute(sql);
//			System.out.println("LostKidsInfo has been created");
//			sql = "use LookingForKidsDB;";
//			stmt.execute(sql);
//			sql = ""
//				+"create table kidsPhotos("
//				+ "photoID int auto_increment primary key,"
//				+ "kidID int not null,"
//				+ "photoPath varchar(200) not null"
//				+ ")"
//				+ "DEFAULT CHARSET=utf8";
//			stmt.execute(sql);
//			System.out.println("kidsPhotoes has been created");
//			sql = "use LookingForKidsDB;";
//			stmt.execute(sql);
//			sql = ""
//				+ "create table usersInfo("
//				+ "userID int auto_increment primary key,"
//				+ "nickName varchar(20) not null,"
//				+ "email varchar(40) not null,"
//				+ "password varchar(100) not null,"
//				+ "phonenumber varchar(20),"
//				+ "authority int not null "
//				+ ")"
//				+ "DEFAULT CHARSET=utf8";
//			stmt.execute(sql);
//			System.out.println("usersInfo has been created");
//			sql = "use LookingForKidsDB;";
//			stmt.execute(sql);		
//			sql = ""
//				+ "create table MayBeLostKidsInfo("
//				+ "MBKidID int auto_increment primary key,"
//				+ "userID int not null,"
//				+ "time varchar(20) not null,"
//				+ "place varchar(20) not null,"
//				+ "description varchar(200) not null"
//				+ ")"
//				+ "DEFAULT CHARSET=utf8";
//			stmt.execute(sql);
//			System.out.println("MayBeLostKidsInfo has been created");
//			sql = "use LookingForKidsDB;";
//			stmt.execute(sql);		
//			sql = ""
//				+ "create table MayBeLostKidsPhotos("
//				+ "photoID int auto_increment primary key,"
//				+ "MBKidID int not null,"
//				+ "photoPath varchar(200) not null"
//				+ ")"
//				+ "DEFAULT CHARSET=utf8";
//			stmt.execute(sql);
//			System.out.println("MayBeLostKidsPhotos has been created");
//			stmt.close();
//			conn.close();
//			System.out.println("");
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//	}
	
	public void createSimilarityMap(){
		try{
			System.out.println("wtf");
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,password);
			Statement stmt = conn.createStatement();
			sql = "use LookingForKidsDB;";
			stmt.execute(sql);		
			sql = ""
				+ "create table SimilarityMap("
				+ "userID int not null,"
				+ "MBKidID int not null,"
				+ "similarity int not null"
				+ ")"
				+ "DEFAULT CHARSET=utf8";
			stmt.execute(sql);
			stmt.close();
			conn.close();
			System.out.println("SimilarityMap has been created");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
