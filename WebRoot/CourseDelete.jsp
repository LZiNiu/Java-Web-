<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/8
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%="css/Style.css"%>"/>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=gbk">
    <title>删除课程记录</title>
    <script type="text/javascript">
        function checkCourseInfo(){
            var _no=document.courseDelete.no.value;
            var _name=document.courseDelete.name.value;
            //判断课程序号和课程名称是不是未输入
            if ((_no==null || _no.toString()=="")&&(_name==null || _name.toString()=="")){
                alert("课程序号和课程名称不能同时为空，请输入！");
                document.courseDelete.no.focus();
                document.courseDelete.name.focus();
                return false;
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
%>

<h3>删除课程记录</h3>
<form name="courseDelete" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkCourseInfo();">
    <table id="courseInfo"  border="0" cellspacing="0" >
        <tr>
            <td> <label for="no">课程序号: </label></td>
            <td> <input type="text" name="no" id="no" value="<%=no%>" ></td>
        </tr>
        <tr>
            <td> <label for="name">课程名: </label></td>
            <td> <input type="text" name="name" id="name" value="<%=name%>"></td>
        </tr>

    </table>
    <input  name="entity" id="entity" value="Course" type="hidden">
    <input name="operation" id="operation" value="delete" type="hidden">
    <p>
        <input type="submit" name="Submit" value="提交" >
        <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
    </p>
</form>

</body>
</html>
