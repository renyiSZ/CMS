<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no" />
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
	<style>
	    #divcss5{ margin:0px auto} 
       #divcss5 img{ border-radius:50%; width:45px; 
        height:45px;} 
	
	</style>
  </head>
  
  <body id="top">
    <div class="wrapper row1">
  <div id="header" class="clear">
    <div class="fl_left">
      <h1><a href="">Curriculum Management System</a></h1></br>
      <p>BUPT & QM Joint Program</p>
    </div>
    <div class="fl_right" style="font-size:15px;">
    
    <%  
    	
		if (session.getAttribute("uname") == null|| session.getAttribute("uname").equals("")) {
	%>
   <form action="<%=path %>/login" method="post"  id="login">
      <fieldset>
        <legend>User Login</legend>
        <input type="password" name="password"    style="border-radius:5px;"/>
        <input type="text" name="userid"  style="border-radius:5px;"/>
        <input type="hidden" name="action" value="login">
        <div id="forgot">Need Help ? <a href="javascript:;">Forgot Your Password ?</a></div>
        <input type="image" src="images/sign_in.gif" id="signin" alt="Sign In" />
      </fieldset>
    </form>
    
    <%
		} else {
		
	%>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	
	<!-- head   ############-->
	<div id="divcss5" style="float:right;">Welcome!&nbsp;&nbsp;<img src="${uhead }" />
	<i class="fa fa-user" aria-hidden="true"></i>${job }:
	<strong><%=session.getAttribute("uname") %></strong>&nbsp;&nbsp;&nbsp;
			
			<a href="<%=path %>/login?action=logout" ><i class="fa fa-sign-out" aria-hidden="true"></i>&nbsp;Log out</a>
	<%
		}
	%>
	</div> 
	
	
    </div>
   
  </div>
</div>
<!-- ########################################################################################## -->
<div class="wrapper row2">
  <div id="rnd">
   <!-- ###### -->
    <div id="topnav">
    <ul>
      <li >&nbsp;</li>
      <li ><a href="homepage.jsp">Homepage</a></li>
      <li><a href="<%=path %>/SearchClass?search=origin">Curriculum</a></li>
      <li><a href="teacher.jsp">Teachers</a></li>
      <li><a href="">Course Materials</a>
        <ul>
          <li><a href="${pageContext.request.contextPath }/PageServlet?thispage=1&spitPageWhat=material">Slides</a></li>
          <li><a href="${pageContext.request.contextPath }/PageServlet?thispage=1&spitPageWhat=video">Online videos</a></li>
          
        </ul>
      </li>
      <li><a href="${pageContext.request.contextPath }/PageServlet?thispage=1&time=one&spitPageWhat=post" target="_blank">Forum</a></li>
      <li><a href="chatroom.jsp" >Chat Room</a></li>
      <li class="last"><a href="<%=path%>/SpaceServlet">My account</a></li>
    </ul>
  </div>
  <!-- ###### -->
  </div>
</div>
<script type="text/javascript" src="plugins/layui/layui.js"></script>
			
			
			
			 
			<script src="js/index.js"></script>
			<script>
				layui.use('layer', function() {
					var $ = layui.jquery,
						layer = layui.layer;
					
					$('#forgot').on('click', function() {
						layer.open({
							title: 'forget password',
							maxmin: true,
							type: 2,
							content: 'findpassword.jsp',
							area: ['800px', '500px']
						});
					});
					
				});
			</script>
  </body>
</html>
