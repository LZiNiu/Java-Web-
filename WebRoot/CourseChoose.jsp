<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/18
  Time: 11:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<%@ page import="java.util.List"%>
<%@ page import="java.sql.Connection" %>
<%@ page import="javabean.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%="css/Style.css"%>"/>
<%
    String driverName = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost:3306/javaCourse";
    String dbUser = "root";
    String dbPassword = "123456";
    Connection cn = DatabaseManager.getInstance(driverName,dbUrl,dbUser,dbPassword).getConnection();
    List<StudentPO> students = new StudentDAO(cn).queryStudents();
    List<CoursePO> courses = new CourseDAO(cn).queryCourses();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>学生选课系统</title>
</head>
<body>
<%
    request.setCharacterEncoding("gbk");
    String sno = request.getParameter("sno");
    String cno = request.getParameter("cno");
%>

<%--学号和课程号都是下拉选择（选项来源），此功能成绩项隐含或者干脆没有--%>

<h3>学生选课</h3>
<form name="scoreUpdate" method="post" action="/JavaAdvance/ApplicationController" >
    <table id="scoreInfo"  border="0" cellspacing="0" >

        <tr>
            <td> <label for="sno">学号: </label></td>
            <td>
                <select name="sno" id="sno">
                    <%
                        for(int i =0;i<students.size();i++){
                    %>
                    <option value="<%=students.get(i).getNo()%>"><%=students.get(i).getNo()%></option>
                    <%}%>
                </select>
            </td>
        </tr>
        <tr>
            <td> <label for="cno">课程号: </label></td>
            <td><select name="cno" id="cno">

                <option value="无" selected>请选择</option>
                <%for (CoursePO cours : courses) {%>
                <option value="<%=cours.getCno()%>">
                    <%=cours.getCno()%>  <%=cours.getCname()%>
                </option>
                <%}%>

            </select>
            </td>
        </tr>

    </table>
    <input  name="entity" id="entity" value="Score" type="hidden">
    <input name="operation" id="operation" value="choose" type="hidden">

    <p>
        <input type="submit" name="Submit" value="提交">
        <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
    </p>
</form>
</body>
</html>