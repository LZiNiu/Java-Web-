package javabean;

import java.sql.*;
import java.util.ArrayList;


public class StudentDAOTester {
	private DatabaseManager dbManager;
	private StudentDAO dao;	
	
	public StudentDAOTester() {
		String driverName="com.mysql.cj.jdbc.Driver";
		String dbUrl = "jdbc:mysql://localhost:3306/javaCourse";
//		String driverName="org.apache.derby.jdbc.ClientDriver";
//		String dbUrl = "jdbc:derby://localhost:1527/javaCourse;create=true";
		String dbUser="root";
		String dbPassword="123456";
		dbManager= DatabaseManager.getInstance(driverName,dbUrl,dbUser,dbPassword);
		dao=new StudentDAO(dbManager.getConnection());
	}
	public void closeConnection(){
		dbManager.closeConnection();
	}
	public void createTable(){
		try{
			Statement stmt= dbManager.getConnection().createStatement();		
			stmt.execute("create table student(Sno varchar(12) primary key,Sname varchar(20),"+"" +
				" Ssex char(2),Sage int,Sdept varchar(30))");
			System.out.println("Created table student");
		}catch(Exception e){
			System.out.println("Created table student error:"+e);
		}		
	}
	private void dropTable(){
		try{
			Statement stmt= dbManager.getConnection().createStatement();		
			stmt.execute("drop table student");
			System.out.println("drop table student");
		}catch(Exception e){
			System.out.println("drop table student error:"+e);
		}		
	}
	
	private void insertInitRecords(){
		ArrayList<StudentPO> students=new ArrayList<StudentPO>();
		
		StudentPO student=new StudentPO();
		student.setNo("95001");
		student.setName("李勇");
		student.setSex("男");
		student.setAge(23);
		student.setDept("信息");
		students.add(student);
		
	    student=new StudentPO();
		student.setNo("95002");
		student.setName("张飞");
		student.setSex("男");
		student.setAge(24);
		student.setDept("机械");
		students.add(student);	
	    
	    student=new StudentPO();
		student.setNo("95003");
		student.setName("刘云");
		student.setSex("女");
		student.setAge(22);
		student.setDept("材冶");
		students.add(student);	
		
		dao.addStudents(students);
		System.out.println("insert Init 3 Records");
		System.out.println("==列出现有学生名单==");
		testQueryRecords();	
	
		
	}
	
	private void testQueryRecords(){		
		System.out.println(" 学号  " + "      姓名  " + "  性别  " + " 年龄 " + "  院系 ");		
		ArrayList<StudentPO> list = dao.queryStudents();
		if (list != null && list.size() > 0) {
			for (StudentPO one : list) {
				System.out.println(one.toString());
			}
		}
	}
	
	private void testInsertRecord(){		
		StudentPO student = new StudentPO();
		student.setNo("88888");
		student.setName("天才");
		student.setSex("男");
		student.setAge(15);
		student.setDept("理学");
		dao.addStudent(student);
	
		System.out.println();
		System.out.println("==增加天才后的学生名单==");			
		testQueryRecords();
		
	}
	
	private void testUpdateRecord(){		
		StudentPO student=dao.queryStudentByKey("88888");
		student.setName("聪聪");
		dao.updateStudent(student);
	
		System.out.println();
		System.out.println("==天才改名后的学生名单==");
		testQueryRecords();
		
	}
	private void testDeleteRecord(){	
		StudentPO student=dao.queryStudentByKey("88888");
		dao.deleteStudent(student);
		System.out.println();
		System.out.println("==天才转学后的学生名单==");
		testQueryRecords();	
	}
	
	public static void main(String arg[]){			
		StudentDAOTester daoTester = new StudentDAOTester();		
		daoTester.dropTable();
		daoTester.createTable();
		daoTester.insertInitRecords();
		daoTester.testInsertRecord();
		daoTester.testUpdateRecord();
		daoTester.testDeleteRecord();		
		daoTester.closeConnection();
	}
}
