package javabean;

import java.sql.*;
import java.util.ArrayList;

public class CourseDAO {
    private Connection cn;


    public CourseDAO(Connection con) {
        cn=con;
    }

    public boolean validateTableNameExist(String tableName) throws SQLException {
        //判断数据库是否存在表
        ResultSet rs = this.cn.getMetaData().getTables(null, null, tableName, null);
        if (rs.next()) {
            return true;
        }else {
            return false;
        }
    }
    public void addCourse(CoursePO course){
        try {
            String sqlStr = "insert into course(Cno,Cname,Ccredit,prerequisite) values(?,?,?,?)";
            PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
            prepStmt.setString(1, course.getCno());
            prepStmt.setString(2, course.getCname());
            prepStmt.setFloat(3, course.getCredit());
            prepStmt.setString(4, course.getPrerequisite());
            prepStmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("addCourse error:" + e);
        }
    }

    public void addCourses(ArrayList<CoursePO> courses){
        for (CoursePO course:courses){
            addCourse(course);
        }

    }
    public void deleteCourse(CoursePO course) {
        try {
            if (cn != null) {
                String sqlStr = "delete from course where Cno=? or Cname=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
                prepStmt.setString(1, course.getCno());
                prepStmt.setString(2,course.getCname());
                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("deleteCourse error:" + e);
        }
    }

    public void deleteCourses(ArrayList<CoursePO> courses){
        for (CoursePO course:courses){
            deleteCourse(course);
        }

    }
    public void updateCourse(CoursePO course) {
        try {
            if (cn != null) {
                String sqlStr = "update course set Cname=?,Ccredit=?, prerequisite=?"
                        + " where Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr); // create a statement
                prepStmt.setString(1, course.getCname());
                prepStmt.setString(3, course.getPrerequisite());
                prepStmt.setFloat(2, course.getCredit());
                prepStmt.setString(4, course.getCno());

                prepStmt.executeUpdate();
            }

        } catch (Exception e) {
            System.out.println("updateCourse error:" + e);
        }
    }

    public void updateCourses(ArrayList<CoursePO> courses){
        for (CoursePO course:courses){
            updateCourse(course);
        }

    }

    public CoursePO queryCourseByKey(String no) {
        CoursePO course=null;
        try {
            if (cn != null) {
                String sqlStr="SELECT * FROM course  where Cno=?";
                PreparedStatement prepStmt = cn.prepareStatement(sqlStr);
                prepStmt.setString(1, no);
                ResultSet rs = prepStmt.executeQuery();
                if (rs.next()) {
                    course = new CoursePO();
                    course.setCno(rs.getString("Cno"));
                    course.setCname(rs.getString("Cname"));
                    course.setCcredit(rs.getInt("Ccredit"));
                    course.setPrerequisite(rs.getString("prerequisite"));
                }
            }

        } catch (Exception e) {
            System.out.println("queryCourse error:" + e);
        }
        return course;

    }
    public ArrayList<CoursePO> queryCourses() {
        ArrayList<CoursePO> courses = new ArrayList<CoursePO>();
        CoursePO course;
        try {
            if (cn != null) {
                Statement stmt = cn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM course order by Cno");
                while (rs.next()) {
                    course = new CoursePO();
                    course.setCno(rs.getString("Cno"));
                    course.setCname(rs.getString("Cname"));
                    course.setCcredit(rs.getFloat("Ccredit"));
                    course.setPrerequisite(rs.getString("prerequisite"));
                    courses.add(course);
                }
            }

        } catch (Exception e) {
            System.out.println("queryCourse error:" + e);
        }

        return courses;
    }
}
