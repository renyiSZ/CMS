<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'MyTest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
  <body>
   <font size="5" > <i class="fa fa-file-pdf-o" aria-hidden="true"></i></font>
    <font size="10" > <i class="fa fa-exclamation" aria-hidden="true"></i>/<i class="fa fa-question" aria-hidden="true"></i></font>
     <font size="20" color="green"> </font>
      <font size="50" color="blue"> <i class="fa fa-file-pdf-o" aria-hidden="true"></i></font>
  </body>
</html>
