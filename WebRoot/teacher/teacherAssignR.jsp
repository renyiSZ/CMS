<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacherAssignR.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	

  </head>
  
  <body>
   <%
    String kcid=(String)request.getAttribute("kcid"); 
    %>
   <font size="5" color="#53868B">Subject Representative</font></br></br>
   <form action="<%=path %>/AssignRTA" method="post">
   Student ID:&nbsp;<input type="text" name="studentid" placeholder="Student ID ">
   <input type="hidden" name="kcid" value="<%=kcid %>">
    <input type="hidden" name="type" value="AssignR">
   <input type="submit" value="Assign">
   </form>
   
   
  </body>
</html>
