<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    
    <title>Upload File</title>
    
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
  
   <blockquote class="layui-elem-quote">
    <button class="layui-btn layui-btn-small" onclick="javascript:history.back(-1);"> <i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i></button>
   
    <!-- ########## -->
    
    </blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>Add Slide</legend>
		</fieldset>
    <form action="<%=path %>/upload" enctype="multipart/form-data" name="uploadform" method="post">
		<div class="layui-form-item">
			<label class="layui-form-label">Class ID</label>
		   <div class="layui-input-block">
		      <input type="text" style="width:200px; height:40px;" name="classID" placeholder="Class" >
		   </div>
		</div>		
		<div class="layui-form-item">
			<label class="layui-form-label">Type</label>
			<div class="layui-input-block">

			<select name="materialtype" style="width:200px; height:40px;">
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
			<label class="layui-form-label">Description</label>
		   <div class="layui-input-block">
		      <textarea name="fileDesc" rows="8" cols="100" ></textarea>
		   </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">Material</label>
		   <div class="layui-input-block">
		     <input type="file" name="material" style="width:250px; height:35px;">
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
