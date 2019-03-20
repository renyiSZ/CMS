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
    
    <title>My Homework</title>
    
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
    
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/StudentHomeworkServlet?require=handin&action=allClassHW'">
    	All Homework
    </button>&nbsp;&nbsp;
 	<%	for (int ii=0;ii<classlist.size();ii++){
 	 %>
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/StudentHomeworkServlet?require=handin&action=singleClassHW&classid=<%=classlist.get(ii) %>'">
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
		My Homework
	</legend>
		<div class="layui-field-box">
<%
    List hwlist= (List)request.getAttribute("hwlist");
    if(hwlist.size()>0){
    	for(int i=0;i<hwlist.size();i++){
					HashMap m=(HashMap) hwlist.get(i);
					String fileName=""+m.get("hwlink");
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
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
   		</br></br>
   		<hr>
   		
   		<form action="<%=path %>/HWforCorrect" enctype="multipart/form-data" name="uploadform" method="post"  onsubmit="return doSubmit()">
   		<a href="javascript:;" class="file">
   		<i class="fa fa-plus " aria-hidden="true"></i> &nbsp;Your Homework
    	<input type="file" name="hw" id="hw">
	    </a>&nbsp;&nbsp;<div class="showFileName" style="height:10px;color:grey;"></div></br>
	    <input type="hidden" name="homeworkid" value="<%=m.get("hwid") %>">
	    <input type="hidden" name="teacherid" value="<%=m.get("teacherid") %>">
	    <input type="hidden" name="kcid" value="<%=m.get("kcid") %>">
        &nbsp;&nbsp; <input type="submit" value="&nbsp;hand in&nbsp;"></form>
        </div>
 <%
 }}else{
  %>
  <center>No Homework</center>
  <%} %>
   </div>
	</fieldset>
</div>
<script type="text/javascript">

$(function(){
$(".file").on("change","input[type='file']",function(){
    var filePath=$(this).val();
    if(filePath.indexOf("pdf")!=-1 || filePath.indexOf("txt")!=-1|| filePath.indexOf("doc")!=-1|| filePath.indexOf("docx")!=-1|| filePath.indexOf("xls")!=-1){
        $(".fileerrorTip").html("").hide();
        var arr=filePath.split('\\');
        var fileName=arr[arr.length-1];
        $(".showFileName").html(fileName);
    }else{
        $(".showFileName").html("");
        $(".fileerrorTip").html("您未上传文件，或者您上传文件类型有误！").show();
        return false 
    }
});
});


 function doSubmit(){ 
    var file = document.getElementById('hw'); 
    if(file.value == ""||file.value == null) { 
	   alert("Please choose a file!"); 
	  return false;
	}else{ 
	   return true;
	} 
} 
</script>
  </body>
</html>
