package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DataBaseCreator {
	String url="jdbc:mysql://localhost";
	String user="root";
	String password="hongmao";
	String sql="";
	
	//�˴����ô������ݿ�ķ���
	public static void main(String args[]){
		DataBaseCreator creator = new DataBaseCreator();
		creator.createLookingForKidsDB();
	}
	
	public void createLookingForKidsDB(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url,user,password);
			sql = "create database LookingForKidsDB";
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
			System.out.println("LookingForKidsDB has been created");
			sql = "use LookingForKidsDB;";
			stmt.execute(sql);		
			sql = ""
				+ "create table LostKidsInfo("
				+ "kidID int auto_increment primary key,"
				+ "userID int not null,"
				+ "kidName varchar(10) not null,"
				+ "kidAge int not null,"
				+ "lostTime varchar(20) not null,"
				+ "lostPlace varchar(20) not null,"
				+ "clothes_up varchar(40) not null,"
				+ "clothes_down varchar(40) not null,"
				+ "clothes_shoes varchar(40) not null,"
				+ "addedInfo varchar(100) not null,"
				+ "contactInfo varchar(40) not null,"
				+ "appearanceDescription varchar(100) not null"
				+ ")";
			stmt.execute(sql);
			System.out.println("LostKidsInfo has been created");
			sql = "use LookingForKidsDB;";
			stmt.execute(sql);
			sql = ""
				+"create table kidsPhotos("
				+ "photoID int auto_increment primary key,"
				+ "kidID int not null,"
				+ "photoPath varchar(200) not null"
				+ ")";
			stmt.execute(sql);
			System.out.println("kidsPhotoes has been created");
			sql = "use LookingForKidsDB;";
			stmt.execute(sql);
			sql = ""
				+ "create table usersInfo("
				+ "userID int auto_increment primary key,"
				+ "nickName varchar(20) not null,"
				+ "email varchar(40) not null,"
				+ "password varchar(100) not null,"
				+ "phonenumber varchar(20),"
				+ "authority int not null "
				+ ")";
			stmt.execute(sql);
			System.out.println("usersInfo has been created");
			stmt.close();
			conn.close();
			System.out.println("");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
