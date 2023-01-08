<%--
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
    <title>删除学生成绩记录</title>

</head>
<body>
<%
    request.setCharacterEncoding("gbk");
    String sno=request.getParameter("sno");
    String cno=request.getParameter("cno");
%>

<h3>删除学生记录(输入学号和课程号删除)</h3>
<form name="scoreDelete" method="post" action="/JavaAdvance/ApplicationController">
    <table id="scoreInfo"  border="0" cellspacing="0" >
        <tr>
            <td> <label for="sno">学号: </label></td>
            <td> <input type="text" name="sno" id="sno" value="<%=sno%>" ></td>
        </tr>
        <tr>
            <td> <label for="cno">课程号: </label></td>
            <td> <input type="text" name="cno" id="cno" value="<%=cno%>" ></td>
        </tr>

    </table>
    <input  name="entity" id="entity" value="Score" type="hidden">
    <input name="operation" id="operation" value="delete" type="hidden">
    <p>
        <input type="submit" name="Submit" value="提交" >
        <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
    </p>
</form>

</body>
</html>
