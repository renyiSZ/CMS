<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Edit</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	
<script src="assets/js/jquery.min.js"></script>
  </head>
  
  <body>
  
   <div style="margin: 15px;">
   <blockquote class="layui-elem-quote">
    <button class="layui-btn layui-btn-small" onclick="javascript:history.back(-1);"> <i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i></button>
   
    <!-- ########## -->
    
    </blockquote>
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>Edit</legend>
		</fieldset>
		
	<!-- ########## -->	
		
	<form action="<%=path %>/TeacherEdit" enctype="multipart/form-data" name="editform" method="post">
    		
		<div class="layui-form-item">
			<label class="layui-form-label">Class ID</label>
		   <div class="layui-input-block">
		      <input type="text" style="width:200px; height:40px;" name="classid" value="${requestScope.map.kcid}">
		   </div>
		</div>
				
		<div class="layui-form-item">
			<label class="layui-form-label">Type</label>
			<div class="layui-input-block">

			<select name="materialtype" style="width:200px; height:40px;">
				<option value="${requestScope.map.mtype}"> ${requestScope.map.mtype}</option> 
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
		      <textarea name="description" rows="8" cols="100">${requestScope.map.mdesc}</textarea>
		   </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">Material</label>
		   <div class="layui-input-block">
		   	 <input id="title" type="text" name="title" value="${requestScope.map.mtitle}" readonly="true">
    		  <input id="change" type="button" value="change" ></br>
		     <input id="file" type="file" name="material" style="width:250px; height:35px;">
		   </div>
		</div>
		
		
		<input type="hidden" id="mid" name="mid" value="${requestScope.map.mid}">
		<input type="hidden" id="mlink" name="mlink" value="${requestScope.map.mlink}">
		<input type="hidden" id="mtitle" name="mtitle" value="${requestScope.map.mtitle}">
		<input type="hidden" id="status" name="status">
		
		<div class="layui-form-item">
			<div class="layui-input-block">
			 
				<button id="submitbutton" class="layui-btn layui-btn-radius">Submit</button>
				<button id="resetvideo" type="reset"  class="layui-btn layui-btn-radius layui-btn-primary">Reset</button>
			
			</div>
		</div>
		
 </form>
 </div>
 
 
<script type="text/javascript">

$(function(){
$('#file').hide();
$("#change").click(function(){
	$('#title').hide();
	$('#change').hide();
	$('#file').show();
   });
$("#submitbutton").click(function(){

	 if( document.editform.material.value == ""){
        document.editform.status.value="nofile";
      }
      else{
        document.editform.status.value="file";
      }
   document.editform.submit();
   });
});
</script>
  
	</div>
  </body>
</html>
