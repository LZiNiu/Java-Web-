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
  	<input type="button" name="student" value="ѧ������" onclick="manageStudent()"> 
  </p> 
  <p>
  	<input type="button" name="course" value="�γ̹���" onclick="manageCourse()">
  </p>
   <p>
   	<input type="button" name="grad" value="�ɼ�����" onclick="manageScore()">
   </p>
   <p>
   	<input type="button" name="exit" value="�˳�" onclick="gotoExit()">
   </p>
  </body>
</html>