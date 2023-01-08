<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/8
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<%@page import="javabean.CoursePO"%>
<%@page import="java.util.List"%>
<%@ page import="javabean.CoursePO" %>
<%
    List<CoursePO> courses=( List< CoursePO>)session.getAttribute("courseList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script language="javascript">
        function choose(number){
            var rowNo = eval(number);
            document.forwarder.no.value = document.all("listTable").rows[rowNo].cells[0].innerText;
            document.forwarder.name.value = document.all("listTable").rows[rowNo].cells[1].innerText;
            document.forwarder.credit.value = document.all("listTable").rows[rowNo].cells[2].innerText;
            document.forwarder.prerequisite.value = document.all("listTable").rows[rowNo].cells[3].innerText;
            if (document.all("listTable").rows.length>1) {
                for (var i=1; i<document.all("listTable").rows.length; i++){
                    document.all("listTable").rows[i].style.backgroundColor="";
                }
            }
            document.all("listTable").rows[rowNo].style.backgroundColor="#ff998f";
        }
        function gotoAdd(){
            document.forwarder.action="CourseAdd.jsp"
            document.forwarder.submit();
        }
        function gotoUpdate(){
            document.forwarder.action="CourseUpdate.jsp"
            document.forwarder.submit();
        }
        function gotoDelete(){
            document.forwarder.action="CourseDelete.jsp"
            document.forwarder.submit();
        }
        function gotoPortal(){
            document.location.href ="portal.jsp";
        }
    </script>
</head>

<body>

<Table  id="listTable" border=1>
    <Tr>
        <td>课程序号</td><td>课程名称</td><td>学分</td><td>先修课程</td>
    </Tr>
    <%
        CoursePO course;
        for (int index=0;index<courses.size();index++){
            course=courses.get(index);
    %>
    <tr onclick="choose('<%=index+1%>')">
        <td><%=course.getCno()%></td>
        <td><%=course.getCname()%></td>
        <td><%=course.getCredit()%></td>
        <td><%=course.getPrerequisite()%></td>

    </tr>
    <%} %>
</Table>
<p>
    <input type="button" name="add" value="新增" onclick="gotoAdd()">
    <input type="button" name="edit" value="修改" onclick="gotoUpdate()">
    <input type="button" name="delete" value="删除" onclick="gotoDelete()">
    <input type="button" name="exit" value="退回门户" onclick="gotoPortal()">
</p>
<form  method="post" name="forwarder" id="forwarder">
    <input type=hidden name="no" id="no" />
    <input type=hidden name="name" id="name" />
    <input type=hidden name="credit" id="credit" />
    <input type=hidden name="prerequisite" id="prerequisite" />
</form>
</body>
</html>
