package dataAccess;

import java.sql.*;


public class StudentTester {
	private DataAccessManager dbManager;
	private StudentDAO dao;
	
	public StudentTester() {		
		dbManager=new DataAccessManager();
		dao=new StudentDAO(dbManager);
	}
	public void closeConnection(){
		dbManager.closeConnection();
	}
	private void createTable(){
		try{
			Statement stmt= dbManager.getConnection().createStatement();
			stmt.execute("create table stu(id varchar(12) primary key,name varchar(20),"+"" +
				" sex varchar(20),college varchar(30),major varchar(25), grade varchar(20))");
			//System.out.println("Created table stu");
		}catch(Exception e){
			System.out.println("Created table stu error:"+e);
		}		
	}
	private void dropTable(){
		try{
			Statement stmt= dbManager.getConnection().createStatement();		
			stmt.execute("drop table stu");
			//System.out.println("drop table stu");
		}catch(Exception e){
			System.out.println("drop table stu error:"+e);
		}		
	}
	
	private void initExcelFile(){
		StudentSet stus=new StudentSet();
		StudentPO stu=new StudentPO();
	    stu.setName("卢子牛");
		stu.setId("20205401");
		stu.setSex("男");
		stu.setCollege("信息科学与工程学院");
		stu.setMajor("工业智能");
		stu.setGrade("2002");
		stus.add(stu);
		
		stu=new StudentPO();
		stu.setId("20200000");
		stu.setName("子");
		stu.setCollege("外国语学院");
		stu.setSex("男");
		stu.setMajor("英语");
		stu.setGrade("2002");
		stus.add(stu);	
		
		stu=new StudentPO();
		stu.setId("20200001");
		stu.setName("牛");
		stu.setCollege("理学院");
		stu.setSex("男");
		stu.setMajor("工业智能");
		stu.setGrade("2002");
		stus.add(stu);

		dao.writeStudentsToExcelFile(stus);	
		System.out.println("=============初始化Excel文件=====================");
		System.out.println(String.format("%-10s\t%-5s\t%-3s\t", "学号","姓名","性别")
				+String.format("%-18s\t\t%-8s\t%-8s","学院","专业","班级"));
		System.out.println("--------------------------------------------------------------- ");
		if (stus != null && stus.size() > 0) {
			for (StudentPO one : stus) {
				System.out.println(one.toString());
			}
		}
	}
	
	private StudentSet testLoadStudentsFromExcelFile(){
		System.out.println("=============加载Excel文件得到学生信息=====================");
		System.out.println(String.format("%-10s\t%-5s\t%-3s\t", "学号","姓名","性别")
				+String.format("%-18s\t\t%-8s\t%-8s","学院","专业","班级"));
		System.out.println("--------------------------------------------------------------- ");
		StudentSet stus = dao.loadStudentsFromExcelFile();
		if (stus != null && stus.size() > 0) {
			for (StudentPO one : stus) {
				System.out.println(one.toString());
			}
		}
		return stus;
	}
	private void testWriteStudentsToXMLFile(StudentSet stus){
		System.out.println("=============把学生信息写入XML文件=====================");
		System.out.println(String.format("%-10s\t%-5s\t%-3s\t", "学号","姓名","性别")
				+String.format("%-18s\t\t%-8s\t%-8s","学院","专业","班级"));
		System.out.println("--------------------------------------------------------------- ");
		if (stus != null && stus.size() > 0) {
			for (StudentPO one : stus) {
				System.out.println(one.toString());
			}
		}
		dao.writeStudentsToXmlFile(stus);
		
	}
	private StudentSet testLoadStudentsFromXMLFile(){
		System.out.println("=============加载XML文件得到学生信息=====================");
		System.out.println(String.format("%-10s\t%-5s\t%-3s\t", "学号","姓名","性别")
				+String.format("%-18s\t\t%-8s\t%-8s","学院","专业","班级"));
		System.out.println("--------------------------------------------------------------- ");
		StudentSet stus = dao.loadStudentsFromXMLFile();
		if (stus != null && stus.size() > 0) {
			for (StudentPO one : stus) {
				System.out.println(one.toString());
			}
		}
		return stus;
	}
	private void testQueryRecords(){
		System.out.println(String.format("%-10s\t%-5s\t%-3s\t", "学号","姓名","性别")
				+String.format("%-18s\t\t%-8s\t%-8s","学院","专业","班级"));
		System.out.println("--------------------------------------------------------------- ");
		StudentSet stus = dao.queryStudents();
		if (stus != null && stus.size() > 0) {
			for (StudentPO one : stus) {
				System.out.println(one.toString());
			}
		}
	}
	
	private void testInsertRecord(){		
		StudentPO stu = new StudentPO();
		stu.setId("20205403");
		stu.setName("天才");
		stu.setCollege("机械学院");
		stu.setSex("男");
		stu.setMajor("机械");
		stu.setGrade("2002");
		dao.addStudent(stu);	
		System.out.println();
		System.out.println("==========增加天才后学生信息==========");
		testQueryRecords();
		
	}
	
	private void testUpdateRecord(){		
		StudentPO stu=dao.queryStudentByName("天才");
		stu.setMajor("自动化");
		dao.updateStudent(stu);	
		System.out.println();
		System.out.println("==========天才更新专业后学生信息==========");
		testQueryRecords();		
	}
	private void testBactchAddRecords(StudentSet stus){		
		dao.addStudents(stus);	
		System.out.println();
		System.out.println("=============向数据库中批量增加如下学生信息=====================");
		testQueryRecords();		
	}
	private void testDeleteRecord(){	
		StudentPO stu=dao.queryStudentByName("天才");
		dao.deleteStudent(stu);
		System.out.println();
		System.out.println("==========天才退学后的学生信息==========");
		testQueryRecords();	
	}
	
	public static void main(String[] arg){
		StudentTester daoTester = new StudentTester();	
		daoTester.initExcelFile();
		StudentSet stus=daoTester.testLoadStudentsFromExcelFile();
		daoTester.testWriteStudentsToXMLFile(stus);
		stus=daoTester.testLoadStudentsFromXMLFile();
		daoTester.dropTable();
		daoTester.createTable();
		daoTester.testBactchAddRecords(stus);
//		daoTester.testQueryRecords();
		
		daoTester.testInsertRecord();
//		daoTester.testQueryRecords();
		daoTester.testUpdateRecord();
//		daoTester.testQueryRecords();
		daoTester.testDeleteRecord();
//		daoTester.testQueryRecords();
		daoTester.closeConnection();

	}
}
