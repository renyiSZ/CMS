<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'changPic.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	
	<style>
		.file {
    position: relative;
    display: inline-block;
    background: #D0EEFF;
    border: 1px solid #99D3F5;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: #1E88C7;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
}
.file input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background: #AADFFD;
    border-color: #78C3F3;
    color: #004974;
    text-decoration: none;
}
	</style>

  </head>
  
  <body>
    <center>
    <div style="margin:10px; width:150px; height:150px; border:1px solid #E5E5E5; padding:10px; background-size:cover;background:url(images/camera.jpg) ;">
     <div style="margin:10px;    ">
    <form name="imgform" action="<%=path %>/UploadImg" method="post" enctype="multipart/form-data">  
     </br></br>
     <a href="javascript:;" class="file">
     <i class="fa fa-plus " aria-hidden="true"></i>&nbsp;picture
    	<input type="file" name="img" id="img">
	 </a>
     
     </form>
     </div>
    </div>
   <button id="changepic" onClick="javascript:document.imgform.submit();">upload</button>
    </center>
    
  </body>
</html>
