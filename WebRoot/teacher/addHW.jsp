<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Upload Homework</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">

  </head>
  
  <body>
  <%
  ArrayList classlist = (ArrayList)session.getAttribute("classlist");
  			
  %>
   	<div style="margin: 15px;">
		<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
			<legend>Upload Homework</legend>
		</fieldset>
		
	<form class="layui-form" action="<%=path %>/HomeworkServlet" enctype="multipart/form-data" name="uploadform" method="post">
    		
				
		<div class="layui-form-item">
			<label class="layui-form-label">Class ID</label>
			<div class="layui-input-block">

			<select name="classid" style="width:200px; height:40px;">
			<%
			 	for(int i=0;i<classlist.size();i++){
			 
			 %>
				<option value="<%=classlist.get(i) %>"  > <%=classlist.get(i) %></option> 
			<%
				}
			 %>	
			</select> 
		  </div>
	  </div>
	  
	  <div class="layui-form-item">
			<label class="layui-form-label">Homework Title</label>
		   <div class="layui-input-block">
		      <input type="text" style="width:500px; height:40px;" name="homeworktitle" placeholder="Homework Title" >
		   </div>
		</div>
	  	
	  	<div class="layui-form-item">
					<label class="layui-form-label">Group work</label>
					<div class="layui-input-block">
						<input type="checkbox" checked="" name="open" lay-skin="switch" lay-filter="switchTest" title="开关">
					</div>
		</div>
	  	
	  	
		<div class="layui-form-item">
			<label class="layui-form-label">Homework Requirement</label>
		   <div class="layui-input-block">
		      <textarea name="description" rows="8" cols="100" ></textarea>
		   </div>
		</div>
		<div class="layui-form-item">
			<label class="layui-form-label">file</label>
		   <div class="layui-input-block">
		     <input type="file" name="uploadhomework" style="width:250px; height:35px;">
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
	<script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;

				
			});
		</script>
	
  </body>
</html>
