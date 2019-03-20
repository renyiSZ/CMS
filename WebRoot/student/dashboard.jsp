<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'dashboard.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style>

</style>
  </head>
  
  <body><center>
 <%
 ArrayList classlist = (ArrayList)session.getAttribute("classlist");
 System.out.println("classlist in userspace:"+classlist);
 String representative =(String)request.getSession().getAttribute("representative");
 String assistant =(String)request.getSession().getAttribute("assistant");
 %>
 <%  if(representative!=null&&assistant==null){%>
</br>
  <font size="4" color="#53868B"> Hello ! ${representative} Subject Representative: ${uname} </font>
   <%}
   
      else if(assistant!=null&&representative==null){%>
</br>
  <font size="4" color="#53868B"> Hello ! ${assistant} Teaching Assistant: ${uname} </font>
   <%}else if(assistant!=null&&representative!=null){%>
</br>
  <font size="4" color="#53868B"> Hello ! ${assistant} Teaching Assistant and ${representative} Subject Representative: ${uname} </font>
   <%}
     else{  
   %></br>
   <font size="4" color="#53868B"> Hello ! ${job}: ${uname}</font>
   <%
   }
    %></center>
  </body>
</html>
