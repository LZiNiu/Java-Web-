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
		student.setName("����");
		student.setSex("��");
		student.setAge(23);
		student.setDept("��Ϣ");
		students.add(student);
		
	    student=new StudentPO();
		student.setNo("95002");
		student.setName("�ŷ�");
		student.setSex("��");
		student.setAge(24);
		student.setDept("��е");
		students.add(student);	
	    
	    student=new StudentPO();
		student.setNo("95003");
		student.setName("����");
		student.setSex("Ů");
		student.setAge(22);
		student.setDept("��ұ");
		students.add(student);	
		
		dao.addStudents(students);
		System.out.println("insert Init 3 Records");
		System.out.println("==�г�����ѧ������==");
		testQueryRecords();	
	
		
	}
	
	private void testQueryRecords(){		
		System.out.println(" ѧ��  " + "      ����  " + "  �Ա�  " + " ���� " + "  Ժϵ ");		
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
		student.setName("���");
		student.setSex("��");
		student.setAge(15);
		student.setDept("��ѧ");
		dao.addStudent(student);
	
		System.out.println();
		System.out.println("==������ź��ѧ������==");			
		testQueryRecords();
		
	}
	
	private void testUpdateRecord(){		
		StudentPO student=dao.queryStudentByKey("88888");
		student.setName("�ϴ�");
		dao.updateStudent(student);
	
		System.out.println();
		System.out.println("==��Ÿ������ѧ������==");
		testQueryRecords();
		
	}
	private void testDeleteRecord(){	
		StudentPO student=dao.queryStudentByKey("88888");
		dao.deleteStudent(student);
		System.out.println();
		System.out.println("==���תѧ���ѧ������==");
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
