<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Add User</title>
    
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

			<form name="formname" class="layui-form" action="<%=path%>/AddUser" method="post">
				<div class="layui-form-item">
					<label class="layui-form-label">User ID</label>
					<div class="layui-input-inline">
						<input type="text" name="userid" lay-verify="title" class="layui-input">
					</div>
					
				</div>
				<div class="layui-form-item">
				<label class="layui-form-label">User Name</label>
					<div class="layui-input-inline">
						<input type="text" name="username"  class="layui-input">
					</div>
				</div>
				<div class="layui-form-item">
					<label class="layui-form-label">Gender</label>
					<div class="layui-input-inline">
						<input type="radio" name="sex" value="0" title="male" checked="">
						<input type="radio" name="sex" value="1" title="female">
						
					</div>
					
					<label class="layui-form-label">User type</label>
					<div class="layui-input-inline">
						<select name="usertype" lay-filter="aihao">
							<option value=""></option>
							<option value="teacher">teacher</option>
							<option value="student" selected="">student</option>
							<option value="admin">admin</option>
							
						</select>
					</div>
				</div>
				
				
				<div class="layui-form-item">
					
					<div class="layui-inline">
						<label class="layui-form-label">Birthday</label>
						<div class="layui-input-block">
							<input type="text" name="date" id="date" lay-verify="date" placeholder="yyyy-mm-dd" autocomplete="off" class="layui-input" onclick="layui.laydate({elem: this})">
						</div>
					</div>
					
				</div>
				
				
				<div class="layui-form-item">
					<label class="layui-form-label">E-mail</label>
					<div class="layui-input-inline">
						<input type="text" name="email" lay-verify="email" autocomplete="off" class="layui-input">
					</div>
					<label class="layui-form-label">Phone</label>
					<div class="layui-input-inline">
						<input type="tel" name="phone" lay-verify="phone" autocomplete="off" class="layui-input">
					</div>
				</div>

				
				
				<div class="layui-form-item">
					<label class="layui-form-label">Password</label>
					<div class="layui-input-inline">
						<input type="password" name="password"  value="111111" lay-verify="pass" placeholder="password" autocomplete="off" class="layui-input">
					</div>
					<div class="layui-form-mid layui-word-aux"> 6-12 digits, initial password:111111</div>
				</div>
				
				<div class="layui-form-item">
					<div class="layui-input-block">
						<button class="layui-btn" lay-submit="" lay-filter="demo1">Submit</button>
						<button type="reset" class="layui-btn layui-btn-primary">Reset</button>
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

				//创建一个编辑器
				var editIndex = layedit.build('LAY_demo_editor');
				//自定义验证规则
				form.verify({
					title: function(value) {
						if(value.length ==0) {
							return 'User ID Required';
						}
					},
					pass: [/(.+){6,12}$/, '密码必须6到12位'],
					content: function(value) {
						layedit.sync(editIndex);
					}
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
