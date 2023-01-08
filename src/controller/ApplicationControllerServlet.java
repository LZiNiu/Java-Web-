package controller;

import java.io.IOException;

import javabean.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class ApplicationControllerServlet extends HttpServlet {
	private DatabaseManager dbManager;
	
	
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("gbk");
		String entityName=request.getParameter("entity");
		if (entityName!=null){
			IAction entityAction=getEntityAction(entityName);
			String actionUrl=entityAction.execute(request, dbManager.getConnection());
			RequestDispatcher dipather = getServletContext().getRequestDispatcher(actionUrl);
			///将请求转发给StudentList.jsp,CourseList.jsp等页面进行处理
			dipather.forward(request,response);
		}
	}	
	
	public void init() throws ServletException {
			String jdbcDriver=getServletConfig().getInitParameter("jdbcDriver");
			String dbUrl = getServletConfig().getInitParameter("dbURL");
			String dbUser = getServletConfig().getInitParameter("dbUser");
			String dbPassword= getServletConfig().getInitParameter("dbPassword");
			//数据库连接
			dbManager=DatabaseManager.getInstance(jdbcDriver,dbUrl,dbUser,dbPassword);
	}
	public void destroy() {
		dbManager.closeConnection();
	}
	private IAction getEntityAction(String entityName){	
		IAction entityAction=null;
		try{
			//获取要操作的类对象Student,Course或者Score
			Class actionClass = Class.forName("controller."+entityName+"Action");
			Object actionObj=actionClass.newInstance();
			entityAction=(IAction)actionObj;
		}catch (Exception e){
			System.out.println("get "+entityName+" dao instance error:"+e);
		}		
		return entityAction;
		
	}

}
