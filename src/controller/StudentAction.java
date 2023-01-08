package controller;

import javabean.StudentDAOTester;
import javabean.StudentDAO;
import javabean.StudentPO;

import jakarta.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class StudentAction implements IAction {

	public String execute(HttpServletRequest request,Connection cn) {		
		String operation=request.getParameter("operation");
		StudentDAO studentDAO=new StudentDAO(cn);
		try {
			//判断表是否存在没有则调用create_table
			if(!studentDAO.validateTableNameExist("student")){
				StudentDAOTester daoTester = new StudentDAOTester();
				daoTester.createTable();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (operation.equals("insert")|| operation.equals("update")|| operation.equals("delete")){			
			StudentPO student=new StudentPO();
			String no=request.getParameter("no");
			student.setNo(no);
			String name=request.getParameter("name");		
			student.setName(name);
			String sex=request.getParameter("sex");		
			student.setSex(sex);
			String age=request.getParameter("age");
			if(age!=null){
				student.setAge(Integer.parseInt(age));
			}

			String dept=request.getParameter("dept");
			student.setDept(dept);
			
			if (operation.equals("insert")){			
				studentDAO.addStudent(student);
			}else if (operation.equals("delete")){
				studentDAO.deleteStudent(student);
			}else {
				studentDAO.updateStudent(student);
			}
			
		}
		//operation为query时
		List<StudentPO> students=studentDAO.queryStudents();
		request.setAttribute("studentList", students);
		return "/StudentList.jsp";
	}
}
	