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
            //�жϱ��Ƿ����û�������create_table
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
                //���޿γ̲����ǿγ̱���
                course.setPrerequisite("��");
            }
            if (operation.equals("insert")) {
                courseDAO.addCourse(course);
            } else if (operation.equals("delete")) {
                //����ɾ�������ݿγ���źͿγ�������һ��,����ҳ������ֽ�������NULL�����
                //���ж�һ�´����Cno,Cname�ĸ�Ϊ��,���Ҹ��յĲ�������ΪNaN�Է�����empty string
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

        //operationΪquery����ÿ��ִ������operationʱ
        List<CoursePO> courses = courseDAO.queryCourses();
        HttpSession session = request.getSession(true);
        session.setAttribute("courseList",courses);
//        request.setAttribute("courseList", courses);
        return "/CourseList.jsp";
    }
}
