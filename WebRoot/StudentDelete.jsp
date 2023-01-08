<%@ page language="java" contentType="text/html; charset=gbk" pageEncoding="gbk"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel="stylesheet" href="<%=basePath+"css/Style.css"%>"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>删除学生记录</title>

</head>
<body>
<%
	request.setCharacterEncoding("gbk");
	String no=request.getParameter("no");
	String name=request.getParameter("name");
	String sex=request.getParameter("sex");
	String age=request.getParameter("age");
	String dept=request.getParameter("dept");
%>

<h3>删除学生记录(输入学号删除)</h3>
<form name="studentDelete" method="post" action="/JavaAdvance/ApplicationController">
	<table id="studentInfo"  border="0" cellspacing="0" >
		<tr>			
	    	<td> <label for="no">学号: </label></td>
			<td> <input type="text" name="no" id="no" value="<%=no%>" ></td>
	     </tr>
	     <tr>			
	    	<td> <label for="name">姓名: </label></td>
			<td> <input type="text" name="name" id="name" value="<%=name%>" readonly></td>        
	     </tr>
	     <tr>			
	    	<td> <label for="sex">性别: </label></td>
			<td><input type="text" name="sex" id="sex" value="<%=sex%>" readonly></td>        
	     </tr>
	          
	     <tr>			
	    	<td> <label for="dept">学院: </label></td>
			<td> <input type="text" name="dept" id="dept"  value="<%=dept%>" readonly></td>        
	     </tr> 	          
	  </table>	
	  <input  name="entity" id="entity" value="Student" type="hidden">		
       	<input name="operation" id="operation" value="delete" type="hidden">
  <p> 
    <input type="submit" name="Submit" value="提交" >
    <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);"> 
 </p>
 </form>
 
</body>
</html>