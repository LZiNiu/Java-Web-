<%--
  Created by IntelliJ IDEA.
  User: LZN
  Date: 2022/12/9
  Time: 18:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=gbk" pageEncoding="gbk" language="java" %>
<%@page import="javabean.ScorePO"%>
<%@page import="java.util.List"%>
<%
    request.setCharacterEncoding("gbk");
    List<ScorePO> scores=(List<ScorePO>)request.getAttribute("scoreList");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <script language="javascript">
        function choose(number){
            var rowNo = eval(number);
            document.forwarder.sno.value = document.all("listTable").rows[rowNo].cells[0].innerText;
            document.forwarder.cno.value = document.all("listTable").rows[rowNo].cells[1].innerText;
            document.forwarder.grade.value = document.all("listTable").rows[rowNo].cells[2].innerText;
            if (document.all("listTable").rows.length>1) {
                for (var i=1; i<document.all("listTable").rows.length; i++){
                    document.all("listTable").rows[i].style.backgroundColor="";
                }
            }
            document.all("listTable").rows[rowNo].style.backgroundColor="#ff998f";
        }
        function gotoAdd(){
            document.forwarder.action="ScoreAdd.jsp"
            document.forwarder.submit();
        }
        function gotoReChoose(){
            document.forwarder.action="CourseReChoose.jsp"
            document.forwarder.submit();
        }
        function gotoDelete(){
            document.forwarder.action="ScoreDelete.jsp"
            document.forwarder.submit();
        }
        function gotoChoose(){
            document.forwarder.action="CourseChoose.jsp"
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
        <td>ѧ��</td><td>�γ����</td><td>�ɼ�</td>
    </Tr>
    <%
        ScorePO score;
        for (int index=0;index<scores.size();index++){
            score=scores.get(index);
    %>
    <tr onclick="choose('<%=index+1%>')">
        <td><%=score.getSno()%></td>
        <td><%=score.getCno()%></td>
        <td><%=score.getGrade()%></td>
    </tr>
    <%} %>
</Table>
<p>
    <input type="button" name="choose" value="ѧ��ѡ��" onclick="gotoChoose()">
    <input type="button" name="edit" value="�γ���ѡ" onclick="gotoReChoose()">
    <input type="button" name="add" value="��ʦ�����ɼ�" onclick="gotoAdd()">
    <input type="button" name="delete" value="ɾ��" onclick="gotoDelete()">
    <input type="button" name="exit" value="�˻��Ż�" onclick="gotoPortal()">
</p>
<form  method="post" name="forwarder" id="forwarder">
    <input type=hidden name="sno" id="sno" />
    <input type=hidden name="cno" id="cno" />
    <input type=hidden name="grade" id="grade" />
</form>
</body>
</html>

