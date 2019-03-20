<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'releaseGrade.jsp' starting page</title>
    
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
  ArrayList classlist = (ArrayList)session.getAttribute("classlist");  
  String currentclass=(String)request.getAttribute("currentclass"); 
 // List notelist =(List)request.getAttribute("notelist");
  %>
   <div class="admin-main">
   
 <blockquote class="layui-elem-quote">
 <form name="formname" action="<%=path%>/GradeAction" method="post" >
 <select name="kc" style="width:200px; height:40px;">
 <option value="<%=currentclass %>"  ><%=currentclass %></option> 
			<%
			 	for(int i=0;i<classlist.size();i++){
			 	String c=""+classlist.get(i);
			 	if(!c.equals(currentclass)){
			 
			 %>
				<option value="<%=classlist.get(i) %>"  > <%=classlist.get(i) %></option> 
			<%
				}}
			 %>	
</select><input type="hidden" name="type" value="grade">
<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

    <!-- ######################################################-->
  
  
  <font size="4" color="#53868B"><strong><%=currentclass %> Mark Release</strong></font>
  </br></br>
  <form name="gradeform" action="<%=path %>/GradeServlet" enctype="multipart/form-data"  method="post">
  	 choose .xls file: </br></br>
  	 Sample: &nbsp;<img src="images/Excelsample.png"></img></br></br>
  	 upload .xls file:&nbsp;<input type="file" name="file">
 <input type="submit" value="release">
 <input type="hidden" name="classid" value="<%=currentclass %>">
  
  </form>

   </div>
  </body>
</html>
