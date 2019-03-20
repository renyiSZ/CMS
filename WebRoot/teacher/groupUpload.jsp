<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'groupUpload.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
   <body>
   <% 
  String kcid=(String)request.getAttribute("kcid"); 
 String hwid=(String)request.getAttribute("hwid");
 System.out.println("upload page kcid + hwid"+kcid+" "+hwid); 
  %>
   <div class="admin-main">
   
 <blockquote class="layui-elem-quote">
  <button class="layui-btn layui-btn-small" onclick="javascript:window.location.href='<%=path%>/TeacherGroupWork?type=group';"> 
    <i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i></button>
</blockquote>

    <!-- ######################################################-->
  
  
  <font size="4" color="#53868B"><strong>Divide Groups</strong></font>
  </br></br>
  <form name="gradeform" action="<%=path %>/UploadGroup" enctype="multipart/form-data"  method="post">
  	 choose .xls file: </br></br>
  	 Sample: &nbsp;<img src="images/groupExample.png"></img></br></br>
  	 upload .xls file:&nbsp;<input type="file" name="file">
 <input type="submit" value="release">
 <input type="hidden" name="classid" value="<%=kcid %>">
  <input type="hidden" name="hwid" value="<%=hwid %>">
  
  </form>

   </div>
  </body>
</html>
