<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My account</title>
    
	
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">

		<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
		<link rel="stylesheet" href="css/admincss/global.css" media="all">
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
  <body>
  
    <div class="layui-layout layui-layout-admin">
			<div class="layui-header header header-demo">
				<div class="layui-main">
					<div class="admin-login-box">
						<a class="logo" style="left: 0;" href="studentSpace.jsp">
							<span style="font-size: 22px;">My account</span>
						</a>
						<div class="admin-side-toggle">
							<i class="fa fa-bars" aria-hidden="true"></i>
						</div>
					</div>
					<ul class="layui-nav admin-header-item">
						<li class="layui-nav-item">
							<a href="homepage.jsp">
							<i class="fa fa-desktop" aria-hidden="true"></i>
							homepage</a>
						</li>
						
						<li class="layui-nav-item">
							<a href="javascript:;">
							<i class="fa fa-envelope-o" aria-hidden="true"></i>
							email</a>
						</li>
						<li class="layui-nav-item">
							<a href="javascript:;" class="admin-header-user">
								<img src="${uhead}" />
								<span>${uname}</span>
							</a>
							
							<dl class="layui-nav-child">
							
								<dd id="studentinfo">
									<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> My Info</a>
								</dd>
								
								<dd id="setting">
									<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>
								</dd>
								
								<dd>
									<a href="<%=path %>/login?action=logout"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
								</dd>
							</dl>
						</li>
					</ul>
					<ul class="layui-nav admin-header-item-mobile">
						<li class="layui-nav-item">
							<a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
						</li>
					</ul>
				</div>
			</div>
			<div class="layui-side layui-bg-black" id="admin-side">
				<div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side"></div>
			</div>
			<div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
				<div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
					<ul class="layui-tab-title">
						<li class="layui-this">
							<i class="fa fa-dashboard" aria-hidden="true"></i>
							<cite> dashboard</cite>
						</li>
					</ul>
					<div class="layui-tab-content" style="min-height: 150px; padding: 5px 0 0 0;">
						<div class="layui-tab-item layui-show">
							<iframe src="student/dashboard.jsp"></iframe>
						</div>
					</div>
				</div>
			</div>
			<div class="layui-footer footer footer-demo" id="admin-footer">
				<div class="layui-main">
					<p>2017 &copy;
					</p>
				</div>
			</div>
			<div class="site-tree-mobile layui-hide">
				<i class="layui-icon">&#xe602;</i>
			</div>
			<div class="site-mobile-shade"></div>
			
			
			
			<script type="text/javascript" src="plugins/layui/layui.js"></script>
			
			<script type="text/javascript" src="datas/TARnav.js"></script>
			
			 
			<script src="js/index.js"></script>
			<script>
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;
					
					$('#studentinfo').on('click', function() {
						layer.open({
							title: 'My Info',
							maxmin: true,
							type: 2,
							content: 'userInfo.jsp',
							area: ['800px', '500px']
						});
					});
					$('#setting').on('click', function() {
						layer.open({
							title: 'setting',
							maxmin: true,
							type: 2,
							content: 'student/studentInfo.jsp',
							area: ['800px', '500px']
						});
					});
					
				});
			</script>
		</div>
  </body>
</html>
