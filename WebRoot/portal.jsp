<%@ page language="java" pageEncoding="gbk"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<link rel="stylesheet" href="<%="css/portal.css"%>"/>
<html>
  <head>
	<script language="javascript">
   
   function manageStudent(){
   		document.location.href ="/JavaAdvance/ApplicationController?entity=Student&&operation=query";
   }
   function manageCourse(){
   		document.location.href ="/JavaAdvance/ApplicationController?entity=Course&&operation=query";
   }
   function manageScore(){
   		document.location.href ="/JavaAdvance/ApplicationController?entity=Score&&operation=query";
   }
   function gotoExit(){
   		window.close();   		
   }
   
</script>
  </head>
  
  <body> 

  <p>
  	<input type="button" name="student" value="学生管理" onclick="manageStudent()"> 
  </p> 
  <p>
  	<input type="button" name="course" value="课程管理" onclick="manageCourse()">
  </p>
   <p>
   	<input type="button" name="grad" value="成绩管理" onclick="manageScore()">
   </p>
   <p>
   	<input type="button" name="exit" value="退出" onclick="gotoExit()">
   </p>
  </body>
</html>
