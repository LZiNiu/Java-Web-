package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import javabean.CourseDAO;
import javabean.CourseDAOTester;
import javabean.CoursePO;
import javabean.UniqueException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CourseAction implements IAction {
    public String execute(HttpServletRequest request, Connection cn) {
        String operation = request.getParameter("operation");
        CourseDAO courseDAO = new CourseDAO(cn);
        try {
            //判断表是否存在没有则调用create_table
            if(!courseDAO.validateTableNameExist("course")){
                CourseDAOTester daoTester = new CourseDAOTester();
                daoTester.createTable();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (operation.equals("insert") || operation.equals("update") || operation.equals("delete")) {
            CoursePO course = new CoursePO();
            String no = request.getParameter("no");
            course.setCno(no);
            String name = request.getParameter("name");
            course.setCname(name);
            String credit = request.getParameter("credit");
            if(credit!=null){
                course.setCcredit(Float.parseFloat(credit));
            }
            String prerequisite = request.getParameter("prerequisite");
            course.setPrerequisite(prerequisite);
            if (name.equals(prerequisite)){
                //先修课程不会是课程本身
                course.setPrerequisite("无");
            }
            if (operation.equals("insert")) {
                courseDAO.addCourse(course);
            } else if (operation.equals("delete")) {
                //由于删除仅根据课程序号和课程名其中一个,而网页不会出现接受两个NULL的情况
                //故判断一下传入的Cno,Cname哪个为空,并且给空的参数设置为NaN以防报错empty string
                if(no.equals("")){
                    course.setCno("NaN");
                }
                else if(name.equals("")){
                    course.setCname("NaN");
                }
                courseDAO.deleteCourse(course);
            } else {
                courseDAO.updateCourse(course);
            }

        }

        //operation为query或者每次执行完别的operation时
        List<CoursePO> courses = courseDAO.queryCourses();
        HttpSession session = request.getSession(true);
        session.setAttribute("courseList",courses);
//        request.setAttribute("courseList", courses);
        return "/CourseList.jsp";
    }
}
