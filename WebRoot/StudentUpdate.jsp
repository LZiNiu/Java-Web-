<%@ page import="javabean.StudentPO" %>
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
<title>更新学生记录</title>
<script type="text/javascript">
function checkStudentInfo(){
	var _no=document.studentUpdate.no.value;
    var _name=document.studentUpdate.name.value;
    var _sex=document.studentUpdate.sex.value;
    var _age=document.studentUpdate.age.value;
	var _dept=document.studentUpdate.dept.value;
	
	if (_name==null || _name.toString()==""){
	 	alert("姓名不能为空，请输入！");
	 	document.studentUpdate.name.focus();
	 	return false;
	}
	if (_age==null || _age.toString()==""){
	 	alert("年龄不能为空，请输入！");
	 	document.studentUpdate.age.focus();
	 	return false;
	}
	if (_dept==null || _dept.toString()==""){
	 	alert("院系不能为空，请输入！");
	 	document.studentUpdate.dept.focus();
	 	return false;
	}
	else{
	   var intAge=parseInt(_age,10);
	   if (isNaN(intAge)||intAge<10||intAge>30){
	   		alert("年龄输入了不恰当的值，请重新输入！");
	   		document.studentUpdate.age.focus();
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
	String sex=request.getParameter("sex");
	String age=request.getParameter("age");
	String dept=request.getParameter("dept");
%>




<h3>更新学生记录</h3>
 <form name="studentUpdate" method="post" action="/JavaAdvance/ApplicationController" onsubmit="return checkStudentInfo();">
	<table id="studentInfo"  border="0" cellspacing="0" >

		<tr>			
	    	<td> <label for="no">学号: </label></td>
			<td> <input type="text" name="no" id="no" value="<%=no%>"></td>
	     </tr>
	     <tr>			
	    	<td> <label for="name">姓名: </label></td>
			<td> <input type="text" name="name" id="name" value="<%=name%>" ></td>
	     </tr>
	     <tr>			
	    	<td> <label for="sex">性别: </label></td>
			<td> 
			 	<select name="sex" id="sex">
	   				<% if (sex.equals("女")){ %>
			    		<option>男</option>
			    		<option selected>女</option>  
					<% }else{ %>
			  		<option selected>男</option>
			    		<option>女</option>
					<% }%>  		
		 		</select>
		 	</td>        
	     </tr>  
	     <tr>			
	    	<td> <label for="age">年龄: </label></td>
			<td> <input type="text" name="age" id="age" value="<%=age%>" ></td>
	     </tr>   
	          
	     <tr>			
	    	<td> <label for="dept">学院: </label></td>
			<td> <input type="text" name="dept" id="dept" value="<%=dept%>"></td>
	     </tr>       
	          
	  </table>
 	   <input  name="entity" id="entity" value="Student" type="hidden">		      
       <input name="operation" id="operation" value="update" type="hidden">

	 <p> 
	    <input type="submit" name="Submit" value="提交">
	    <input type="button" name="Cancel" value="取消" onclick="javascript:history.go(-1);"> 
	 </p>
  </form>
</body>
</html>