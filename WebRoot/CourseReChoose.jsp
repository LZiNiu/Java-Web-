<%@ page import="java.sql.Connection" %>
<%@ page import="javabean.*" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/9
  Time: 18:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%="css/Style.css"%>"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>ѧ���γ���ѡ</title>
</head>
<body>
<%
    String driverName = "com.mysql.cj.jdbc.Driver";
    String dbUrl = "jdbc:mysql://localhost:3306/javaCourse";
    String dbUser = "root";
    String dbPassword = "123456";
    Connection cn = DatabaseManager.getInstance(driverName,dbUrl,dbUser,dbPassword).getConnection();
    List<StudentPO> students = new StudentDAO(cn).queryStudents();
    List<CoursePO> courses = new CourseDAO(cn).queryCourses();
%>

<%--��Ҫ��������,ѧ��ѧ��,��Ҫ�ı�Ŀγ����,���ձ�ɵĿγ����(����������ѡ��)--%>
<%
    request.setCharacterEncoding("gbk");
    String sno = request.getParameter("sno");
    String cno = request.getParameter("cno");
%>

<h3>ѧ���γ���ѡ</h3>
<form name="scoreUpdate" method="post" action="/JavaAdvance/ApplicationController" >
    <table id="scoreInfo"  border="0" cellspacing="0" >

        <tr>
            <td> <label for="sno">ѧ��: </label></td>
            <td>
                <select name="sno" id="sno">
                    <%if(sno.equals("")){%>
                    <%for (StudentPO stus : students) {%>
                    <option value="<%=stus.getNo()%>"><%=stus.getNo()%>
                    </option>
                    <%
                        }
                    }else { String temp="";%>

                    <%for(int i=0;i<students.size();i++){%>
                    <% if(sno.equals(students.get(i).getNo())){temp=students.get(i).getNo();continue;}%>
                    <option value="<%=students.get(i).getNo()%>"><%=students.get(i).getNo()%>
                            <%}%>
                    <option value="<%=sno%>" selected><%=temp%></option>
                    <%}%>
                </select>
            </td>
        </tr>
        <tr>
            <td> <label for="cno">��ѡ�γ�: </label></td>
            <td>
                <select name="cno" id="cno">
                    <%if(cno.equals("")){%>
                    <option value="��" selected>��ѡ��</option>
                    <%for (CoursePO cours : courses) {%>
                    <option value="<%=cours.getCno()%>">
                        <%=cours.getCno()%>  <%=cours.getCname()%>
                    </option>
                    <%
                        }
                    }else { String temp="��"; String no="";%>

                    <%for(int i=0;i<courses.size();i++){%>
                    <% if(cno.equals(courses.get(i).getCno())){temp=courses.get(i).getCname();no=courses.get(i).getCno();continue;}%>
                    <option value="<%=courses.get(i).getCno()%>">
                            <%=courses.get(i).getCno()%>  <%=courses.get(i).getCname()%>
                            <%}%>
                    <option value="<%=cno%>" selected><%=no%>  <%=temp%></option>
                    <%}%>
                </select>
            </td>
        </tr>

        <tr>
            <td> <label for="newCno">��ѡ�γ̺�: </label></td>
            <td>
                <select name="newCno" id="newCno">
                    <option value="��" selected>��ѡ��</option>
                    <%
                        for(int i=0;i<courses.size();i++){
                    %>
                    <option value="<%=courses.get(i).getCno()%>">
                        <%=courses.get(i).getCno()%>  <%=courses.get(i).getCname()%></option>
                    <%}%>
                </select>
            </td>
        </tr>

    </table>
    <input  name="entity" id="entity" value="Score" type="hidden">
    <input name="operation" id="operation" value="rechoose" type="hidden">

    <p>
        <input type="submit" name="Submit" value="�ύ">
        <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);">
    </p>
</form>
</body>
</html>