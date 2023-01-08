package javabean;

import java.sql.Statement;
import java.util.ArrayList;

public class CourseDAOTester {
    private DatabaseManager dbManager;
    private CourseDAO dao;

    public CourseDAOTester() {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String dbUrl = "jdbc:mysql://localhost:3306/javaCourse";
        String dbUser = "root";
        String dbPassword = "123456";
        dbManager = DatabaseManager.getInstance(driverName, dbUrl, dbUser, dbPassword);
        dao = new CourseDAO(dbManager.getConnection());
    }

    public void closeConnection() {
        dbManager.closeConnection();
    }

    public void createTable() {
        try {
            Statement stmt = dbManager.getConnection().createStatement();
            stmt.execute("create table course(Cno varchar(12) primary key,Cname varchar(20)," + "" +
                    " Ccredit float, prerequisite varchar(25))");
            System.out.println("Created table course");
        } catch (Exception e) {
            System.out.println("Created table course error:" + e);
        }
    }

    private void dropTable() {
        try {
            Statement stmt = dbManager.getConnection().createStatement();
            stmt.execute("drop table course");
            System.out.println("drop table course");
        } catch (Exception e) {
            System.out.println("drop table course error:" + e);
        }
    }

    private void insertInitRecords() {
        ArrayList<CoursePO> courses = new ArrayList<CoursePO>();

        CoursePO course = new CoursePO();
        course.setCno("A001");
        course.setCname("Web");
        course.setCcredit(1);
        course.setPrerequisite("HTML");
        courses.add(course);

        course = new CoursePO();
        course.setCno("A002");
        course.setCname("数据分析与可视化");
        course.setCcredit(1);
        course.setPrerequisite("Python");
        courses.add(course);

        course = new CoursePO();
        course.setCno("A003");
        course.setCname("毛概");
        course.setCcredit(4);
        course.setPrerequisite("马原");
        courses.add(course);

        dao.addCourses(courses);
        System.out.println("insert Init 3 Records");
        System.out.println("==列出现有课程名单==");
        testQueryRecords();


    }

    private void testQueryRecords() {

        System.out.printf("%s  %-12s\t%s  %-12s\n", "课程序号", "课程名",  "学分", "先修课程");
        ArrayList<CoursePO> list = dao.queryCourses();
        if (list != null && list.size() > 0) {
            for (CoursePO one : list) {
                System.out.println(one.toString());
            }
        }
    }

    private void testInsertRecord() {
        CoursePO course = new CoursePO();
        course.setCno("A000");
        course.setCname("机器学习");
        course.setCcredit(2);
        course.setPrerequisite("高数、线代");
        dao.addCourse(course);

        System.out.println();
        System.out.println("==增加机器学习后的课程名单==");
        testQueryRecords();

    }

    private void testUpdateRecord() {
        CoursePO course = dao.queryCourseByKey("A000");
        course.setCname("深度学习");
        dao.updateCourse(course);

        System.out.println();
        System.out.println("==课程改名后的课程名单==");
        testQueryRecords();

    }

    private void testDeleteRecord() {
        CoursePO course = dao.queryCourseByKey("A000");
        dao.deleteCourse(course);
        System.out.println();
        System.out.println("==课程取消后的课程名单==");
        testQueryRecords();
    }

    public static void main(String arg[]) {
        javabean.CourseDAOTester daoTester = new javabean.CourseDAOTester();
        daoTester.dropTable();
        daoTester.createTable();
        daoTester.insertInitRecords();
        daoTester.testInsertRecord();
        daoTester.testUpdateRecord();
        daoTester.testDeleteRecord();
        daoTester.closeConnection();
    }
}


