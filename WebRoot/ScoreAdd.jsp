<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/9
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<link rel="stylesheet" href="<%="css/Style.css"%>"/>
<html>
<head>
<title>教师给定成绩</title>
<script type="text/javascript">
    function checkScoreInfo(){
        var _grade=document.scoreAdd.grade.value;
        var floatCredit=parseFloat(_grade);
        if (isNaN(floatCredit)||floatCredit<0||floatCredit>100){
            alert("成绩输入了不恰当的值，请重新输入！");
            document.scoreAdd.grade.focus();
            return false;
        }

        return true;
    }

</script>
</head>
<body>
<%
    request.setCharacterEncoding("gbk");
    String sno = request.getParameter("sno");
    String cno = request.getParameter("cno");
%>
<div>
<h3>填写学生课程成绩</h3>
<form name="scoreAdd" id="scoreAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkScoreInfo();">
    <table id="scoreInfo"   border="0" cellspacing="0" >
        <tr>
            <td> <label for="sno">学号: </label></td>
            <td> <input type="text" name="sno" id="sno" value="<%=sno%>" readonly></td>
        </tr>
        <tr>
            <td> <label for="cno">课程序号: </label></td>
            <td> <input type="text" name="cno" id="cno" value="<%=cno%>" readonly></td>
        </tr>

        <tr>
            <td> <label for="grade">成绩: </label></td>
            <td> <input type="text" name="grade" id="grade"></td>
        </tr>

    </table>
    <input  name="entity" id="entity" value="Score" type="hidden">
    <input  name="operation" id="operation" value="insert" type="hidden">
    <p>
        <input type="submit" name="Submit" value="提交" >
        <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
    </p>
</form>
</div>
</body>
</html>

