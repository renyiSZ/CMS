<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ page import="com.kc.entity.Users"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'teacher.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
<style>
.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:160px; height:250px; word-wrap:break-word;}
		.imgdiv{margin:5px; width:150px; height:150px; background:black; }
</style>
  </head>
  
  <body  id="top">
  <jsp:include page="header.jsp"></jsp:include>
  <!-- ########################################################################################## -->
 <div class="wrapper row3">
  <div class="rnd">
    <div id="container" class="clear">
    <!-- ########-->
  <div style=" width:100%; height:650px; overflow:auto;">
   <%
     String sql= "select * from users where ujob='teacher'";
   	 List list=  DAOFactory.getUserDAOInstance().getUsers(sql); 
  	 if(list.size()>0){
   %>
    
    
   <%
		for(int i=0;i<list.size();i++){
			HashMap m=(HashMap)list.get(i);
			
   %>
   <div class="videodiv">
	 <div class="imgdiv">
	 <img src="<%=m.get("uhead") %>" width="150px" height="150px"></img>
   	 </div>
   	  &nbsp;<i class="fa fa-user" aria-hidden="true"></i>&nbsp;<%=m.get("uname") %></br>
	 &nbsp;<i class="fa fa-envelope-o" aria-hidden="true"></i>&nbsp;<%=m.get("uemail") %></br>
	 &nbsp;<i class="fa fa-phone" aria-hidden="true"></i>&nbsp;<%=m.get("uphone") %></br>
   </div>
    <%  
  	        } 	 
  	 }
  	 else{
  	   out.print("No teacher");
  	 }
   %>	
   </div>
   </div>
   </div>
  </div>
    <!-- ########################################################################################## -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
