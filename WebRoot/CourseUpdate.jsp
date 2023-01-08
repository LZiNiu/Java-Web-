<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/8
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<%@ page import="javabean.CoursePO" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%="css/Style.css"%>"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>���¿γ̼�¼</title>
    <script type="text/javascript">
        function checkCourseInfo(){
            var _no=document.courseUpdate.no.value;
            var _name=document.courseUpdate.name.value;
            var _credit=document.courseUpdate.credit.value;

            if (_no==null || _no.toString()==""){
                alert("���Ʋ���Ϊ�գ������룡");
                document.courseUpdate.no.focus();
                return false;
            }
            if (_name==null || _name.toString()==""){
                alert("���Ʋ���Ϊ�գ������룡");
                document.courseUpdate.name.focus();
                return false;
            }
            if (_credit==null || _credit.toString()==""){
                alert("ѧ�ֲ���Ϊ�գ������룡");
                document.courseUpdate.credit.focus();
                return false;
            }
            else{
                var floatCredit=parseFloat(_credit);
                if (isNaN(floatCredit)||floatCredit<=0||floatCredit>5){
                    alert("ѧ�������˲�ǡ����ֵ�����������룡");
                    document.courseUpdate.credit.focus();
                    return false;
                }

            }
            return true;

        }

    </script>
</head>
<body>
<%
    request.setCharacterEncoding("gbk");
    String no=request.getParameter("no");
    String name=request.getParameter("name");
    String credit=request.getParameter("credit");
    String prerequisite=request.getParameter("prerequisite");
%>




<h3>���¿γ̼�¼</h3>
<form name="courseUpdate" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkCourseInfo();">
    <table id="courseInfo"  border="0" cellspacing="0" >

        <tr>
            <td> <label for="no">�γ����: </label></td>
            <td> <input type="text" name="no" id="no" value="<%=no%>"></td>
        </tr>
        <tr>
            <td> <label for="name">�γ�����: </label></td>
            <td> <input type="text" name="name" id="name" value="<%=name%>" ></td>
        </tr>
        <tr>
            <td> <label for="credit">ѧ��: </label></td>
            <td> <input type="text" name="credit" id="credit" value="<%=credit%>" ></td>
        </tr>
        <tr>
            <td> <label for="prerequisite">���޿γ�: </label></td>
            <td>
                <select name="prerequisite" id="prerequisite">
                    <option value="��" selected>��ѡ��</option>
                    <%
                        List<CoursePO> courses=( List<CoursePO>)session.getAttribute("courseList");
                        for(int i=0;i<courses.size();i++){
                    %>
                    <option value="<%=courses.get(i).getCname()%>">
                        <%=courses.get(i).getCno()%>  <%=courses.get(i).getCname()%></option>
                    <%}%>
                </select>
            </td>
        </tr>

    </table>
    <input  name="entity" id="entity" value="Course" type="hidden">
    <input name="operation" id="operation" value="update" type="hidden">

    <p>
        <input type="submit" name="Submit" value="�ύ">
        <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);">
    </p>
</form>
</body>
</html>
