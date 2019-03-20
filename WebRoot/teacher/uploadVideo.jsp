<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Upload Video</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">

  </head>
  
  <body>
   	<div style="margin: 15px;">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>Upload Video</legend>
		</fieldset>
		
	<form action="<%=path %>/VideoServlet" enctype="multipart/form-data" name="uploadform" method="post">
    		
		<div class="layui-form-item">
			<label class="layui-form-label">Video Title</label>
		   <div class="layui-input-block">
		      <input type="text" style="width:500px; height:40px;" name="videotitle" placeholder="Video Title" >
		   </div>
		</div>
				
		<div class="layui-form-item">
			<label class="layui-form-label">Video Type</label>
			<div class="layui-input-block">

			<select name="videotype" style="width:200px; height:40px;">
				<option value="other"  > --choose--</option> 
				<option value="Agronomy">Agronomy</option> 
				<option value="Economics">Economics</option> 
				<option value="Engineering">Engineering</option> 
				<option value="History">History</option> 
				<option value="Law">Law</option> 
				<option value="Literature">Literature</option> 
				<option value="Management">Management</option> 
				<option value="Medical Science">Medical Science</option> 
				<option value="Military Science">Military Science</option> 
				<option value="Pedagogy">Pedagogy</option> 
				<option value="Philosophy">Philosophy</option> 
				<option value="Science">Science</option> 
				<option value="other">Other</option> 
			</select> 
		  </div>
	  </div>
	  	<div class="layui-form-item">
			<label class="layui-form-label">Class ID</label>
		   <div class="layui-input-block">
		      <input type="text" style="width:200px; height:40px;" name="classid" placeholder="Class" >
		   </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">Video Description</label>
		   <div class="layui-input-block">
		      <textarea name="description" rows="8" cols="100" ></textarea>
		   </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">Video</label>
		   <div class="layui-input-block">
		     <input type="file" name="uploadvideo" style="width:250px; height:35px;">
		   </div>
		</div>
		<div class="layui-form-item">
			<div class="layui-input-block">
				<button id="submitvideo" onclick="document.uploadform.submit();" class="layui-btn layui-btn-radius">Submit</button>
				<button id="resetvideo" type="reset"  class="layui-btn layui-btn-radius layui-btn-primary">Reset</button>
			
			</div>
		</div>
 </form>
    
  
	</div>
	
  </body>
</html>
