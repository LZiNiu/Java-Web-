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
<title>���ӿγ̼�¼</title>
<script type="text/javascript">
    function checkCourseInfo(){
        var _no=document.courseAdd.no.value;
        var _name=document.courseAdd.name.value;
        var _credit=document.courseAdd.credit.value;
        var _prerequisite=document.courseAdd.prerequisite.value;

        if (_no==null || _no.toString()==""){
            alert("�γ���Ų���Ϊ�գ������룡");
            document.courseAdd.no.focus();
            return false;
        }
        if (_name==null || _name.toString()==""){
            alert("�γ����Ʋ���Ϊ�գ������룡");
            document.courseAdd.name.focus();
            return false;
        }
        if (_prerequisite==null || _prerequisite.toString()==""){
            alert("���޿γ̲���Ϊ�գ������룡");
            document.courseAdd.prerequisite.focus();
            return false;
        }else{
            var floatCredit=parseFloat(_credit);
            if (isNaN(floatCredit)||floatCredit<=0||floatCredit>5){
                alert("ѧ�������˲�ǡ����ֵ�����������룡");
                document.courseAdd.credit.focus();
                return false;
            }

        }

        return true;

    }

</script>
</head>
<body>

<h3>���ӿγ̼�¼</h3>
<form name="courseAdd" id="courseAdd" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkCourseInfo();">
    <table id="courseInfo"   border="0" cellspacing="0" >
        <tr>
            <td> <label for="no">�γ����: </label></td>
            <td> <input type="text" name="no" id="no"></td>
        </tr>
        <tr>
            <td> <label for="name">�γ���: </label></td>
            <td> <input type="text" name="name" id="name"></td>
        </tr>

        <tr>
            <td> <label for="credit">ѧ��: </label></td>
            <td> <input type="text" name="credit" id="credit"></td>
        </tr>

        <tr>
            <td> <label for="prerequisite">���޿γ�: </label></td>
            <td>
                <select name="prerequisite" id="prerequisite">
                    <option value="��" selected>��ѡ��</option>
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
        <input type="submit" name="Submit" value="�ύ" >
        <input type="button" name="Cancel" value="ȡ��" onclick="javascript:history.go(-1);">
    </p>
</form>


</body>
</body>
</html>
