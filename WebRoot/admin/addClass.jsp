<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Add Class</title>
    
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
				<legend>Add User</legend>
			</fieldset>

			<form name="formname" class="layui-form" action="<%=path%>/EditClass" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">Class ID</label>
					<div class="layui-input-inline">
						<input type="text" name="classid" lay-verify="title" class="layui-input">
					</div>
					
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label">Class Name</label>
					<div class="layui-input-inline">
						<input type="text" name="classname"  class="layui-input">
					</div>
					<label class="layui-form-label">Class type</label>
					<div class="layui-input-inline">
						
							<select name="classtype" lay-filter="aihao">
								<option value="other" > --choose--</option> 
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
					<label class="layui-form-label">Teacher ID</label>
					<div class="layui-input-inline">
						<input type="text" name="teacherid"  class="layui-input">
					</div>
					
				</div>
				
				
				<div class="layui-form-item">
					<label class="layui-form-label">credit</label>
					<div class="layui-input-inline">
						<input type="text" name="credit" class="layui-input">
					</div>
					
				</div>
				
				<div class="layui-form-item">
				<label class="layui-form-label">Semester</label>
					<div class="layui-input-inline">
						<select name="semester_y" lay-filter="aihao">
								<option value="2017">2017</option> 
								<option value="2018">2018</option> 
								<option value="2019">2019</option> 
								<option value="2020">2020</option> 
								<option value="2021">2021</option> 
								<option value="2022">2022</option> 
					    </select> 
					    - <select name="semester_s" lay-filter="aihao">
								<option value="Fall">Fall</option> 
								<option value="Spring">Spring</option> 
								<option value="Summer">Summer</option> 
					    </select> 
					</div>
				</div>

				<input type="hidden" name="classwhat" value="add">
				
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">Add</button>
						<button type="reset" class="layui-btn layui-btn-primary">Reset</button>
					</div>
				</div>
				</br></br></br></br>
			</form>
		</div>
		<script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script>
			layui.use(['form', 'layedit', 'laydate'], function() {
				var form = layui.form(),
					layer = layui.layer,
					layedit = layui.layedit,
					laydate = layui.laydate;

				//创建一个编辑器
				var editIndex = layedit.build('LAY_demo_editor');
				//自定义验证规则
				form.verify({
					title: function(value) {
						if(value.length ==0) {
							return 'Class ID Required';
						}
					},
					
				});

				//监听提交
				form.on('submit(demo1)', function(data) {
					document.formname.submit();
					return false;
				});
			});
		</script>
	</body>
</html>
