<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/8
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=gbk"   pageEncoding="gbk"%>
<%@page import="javabean.CoursePO"%>
<%@page import="java.util.List"%>
<%@ page import="javabean.CoursePO" %>
<link rel="stylesheet" href="<%="css/Style.css"%>"/>
<html>
<head>
<body>
<title>增加课程记录</title>
<script type="text/javascript">
    function checkCourseInfo(){
        var _no=document.courseAdd.no.value;
        var _name=document.courseAdd.name.value;
        var _credit=document.courseAdd.credit.value;
        var _prerequisite=document.courseAdd.prerequisite.value;

        if (_no==null || _no.toString()==""){
            alert("课程序号不能为空，请输入！");
            document.courseAdd.no.focus();
            return false;
        }
        if (_name==null || _name.toString()==""){
            alert("课程名称不能为空，请输入！");
            document.courseAdd.name.focus();
            return false;
        }
        if (_prerequisite==null || _prerequisite.toString()==""){
            alert("先修课程不能为空，请输入！");
            document.courseAdd.prerequisite.focus();
            return false;
        }else{
            var floatCredit=parseFloat(_credit);
            if (isNaN(floatCredit)||floatCredit<=0||floatCredit>5){
                alert("学分输入了不恰当的值，请重新输入！");
                document.courseAdd.credit.focus();
                return false;
            }

        }

        return true;

    }

</script>
</head>
<body>

<h3>增加课程记录</h3>
<form name="courseAdd" id="courseAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkCourseInfo();">
    <table id="courseInfo"   border="0" cellspacing="0" >
        <tr>
            <td> <label for="no">课程序号: </label></td>
            <td> <input type="text" name="no" id="no"></td>
        </tr>
        <tr>
            <td> <label for="name">课程名: </label></td>
            <td> <input type="text" name="name" id="name"></td>
        </tr>

        <tr>
            <td> <label for="credit">学分: </label></td>
            <td> <input type="text" name="credit" id="credit"></td>
        </tr>

        <tr>
            <td> <label for="prerequisite">先修课程: </label></td>
            <td>
                <select name="prerequisite" id="prerequisite">
                    <option value="无" selected>请选择</option>
                    <%
                        List<CoursePO> courses=( List< CoursePO>)session.getAttribute("courseList");
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
    <input  name="operation" id="operation" value="insert" type="hidden">
    <p>
        <input type="submit" name="Submit" value="提交" >
        <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);">
    </p>
</form>


</body>
</body>
</html>
