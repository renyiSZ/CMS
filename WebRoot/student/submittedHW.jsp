<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Submitted Homework</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="css/upload.css" media="all">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<script src="assets/js/jquery.min.js"></script>
	<style>
		
	</style>

  </head>
  
  <body>
  <%
    
 ArrayList classlist = (ArrayList)session.getAttribute("classlist");
 System.out.println("classlist in userspace:"+classlist);
 
 %>
 
  <div class="admin-main">
    <blockquote class="layui-elem-quote">
    
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/StudentHomeworkServlet?require=submitted&action=allClassHW'">
    	All Homework
    </button>&nbsp;&nbsp;
 	<%	for (int ii=0;ii<classlist.size();ii++){
 	 %>
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/StudentHomeworkServlet?require=submitted&action=singleClassHW&classid=<%=classlist.get(ii) %>'">
    	<%=classlist.get(ii) %>
    </button>&nbsp;&nbsp;
    <%
    	} 
    %>
    </blockquote>
    <!-- ######################################################-->
    
    
  <fieldset class="layui-elem-field">
	<legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		My Submitted Homework
	</legend>
		<div class="layui-field-box">
<%
    List hwlist= (List)request.getAttribute("hwlist");
    if(hwlist.size()>0){
    	for(int i=0;i<hwlist.size();i++){
					HashMap m=(HashMap) hwlist.get(i);
					String fileName=""+m.get("hwlink");
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					String fileName2=""+m.get("handinlink");
					fileName2 = fileName2.substring(fileName2.lastIndexOf("\\")+1);
 %>
    
  	
  	    <div style="width:80%; margin:15px; padding:10px;  border:2px solid #E5E5E5; word-wrap:break-word;">
   		<font size="4" color="#53868B"><strong><%=m.get("kcid") %></strong></font></br></br>
   		
   		<font color="#53868B"><strong>Title:</strong></font>&nbsp;<%=m.get("hwtitle") %>&nbsp;&nbsp;&nbsp;&nbsp;
   		<font color="#53868B"><strong>Time:</strong></font>&nbsp;<%=m.get("hwtime") %></br></br>
   		<font color="#53868B"><strong>Requirement:</strong></font>&nbsp;<%=m.get("hwdesc") %>
   		<%if(!fileName.equals("")){ %></br></br>
   		<font color="#53868B"><strong>Attachment:</strong></font>&nbsp;<%=fileName %>&nbsp;&nbsp;&nbsp;&nbsp;
   		
   		<a href="<%=path %>/HomeworkServlet?downloadID=<%=m.get("hwid") %>"><i class="fa fa-download " aria-hidden="true"></i><strong> &nbsp;download</strong></a>
   		<%} %>
   		</br><hr>
   		
   		<font color="#53868B"><strong>Your submission:</strong></font>&nbsp;<%=fileName2 %>
   		&nbsp;&nbsp;&nbsp;&nbsp;
   		<a href="<%=path %>/HWforCorrect?downloadID=<%=m.get("correctid") %>"><i class="fa fa-download " aria-hidden="true"></i><strong> &nbsp;download</strong></a>
   		</br></br>
   		<font color="#53868B"><strong>Submission time:</strong></font>&nbsp;<%=m.get("handintime") %></br>
   		</div>
 <%
 }}else{
  %>
  <center>No Homework</center>
  <%} %>
   </div>
	</fieldset>
</div>

  </body>
</html>
