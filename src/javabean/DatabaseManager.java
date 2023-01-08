package javabean;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;

import controller.IAction;

public class DatabaseManager {	
	private Connection cn;
	private static DatabaseManager instance = null;//单例对象
	private DatabaseManager(String jdbcDriver,String dbUrl,String dbUser,String dbPassword) {
		try{
			Class.forName(jdbcDriver).newInstance();// 加载驱动
			cn=DriverManager.getConnection(dbUrl, dbUser, dbPassword); 
		}catch(Exception e){			
			System.out.println("open databse connection error:"+e);
		}
		
	}
	public static DatabaseManager getInstance(String jdbcDriver,String dbUrl,String dbUser,String dbPassword){
		if(instance==null){
			instance = new DatabaseManager(jdbcDriver,dbUrl,dbUser,dbPassword);
		}
		return instance;
	}
	
	public Connection getConnection(){
		return cn;
	}
	
	public void closeConnection(){
		try{
			cn.close();
		}catch (Exception e){
			System.out.println("close databse connection error:"+e);
		}
	}
	
}
