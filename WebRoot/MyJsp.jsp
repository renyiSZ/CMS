<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>video</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	 <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	 
		<link rel="stylesheet" href="css/admincss/global.css" media="all"/>

	<style>
		.allvideo{ border:1px solid #E5E5E5; margin:0px; width:100%; height:850px; }
		.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:200px; height:200px; }
		.imgdiv{margin:5px; width:190px; height:110px; background:black; }
		.pagediv{margin:5px;float:left;}	
	</style>
  </head>
  
  <body>
  <jsp:include page="header.jsp"></jsp:include>
  <!-- ########################################################################################## -->
 <div class="wrapper row3">
  <div class="rnd">
    <div id="container" class="clear">
    
     <!-- #######-->
     <blockquote class="layui-elem-quote">
    <form name="searchform" action="<%=path %>/PageServlet" method="post">
   		<input type="text" name="classid" style="width:80px; height:25px;" placeholder="Class ID" >&nbsp;
   		<input type="text" name="teachername" style="width:100px; height:25px;" placeholder="teacher name" >&nbsp;
   	    <select name="classtype" style="width:120px; height:25px;">
								<option value="" > --type--</option> 
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
		
		<input type="hidden" name="spitPageWhat" value="videoselect"/>
    		<input type="hidden" name="thispage" value="1"/>
    		
    	<button class="layui-btn layui-btn-small" onclick="javascript:document.searchform.submit();"> 
    		<i class="layui-icon">&#xe615;</i>&nbsp;search</i>
    	</button>
    	</form>
    </blockquote>
    
    <!-- #######-->
    <div class="allvideo">
    <c:forEach items="${requestScope.page.list}" var="video">
   
	<div class="videodiv">
	 <div class="imgdiv">
	 	<a href="<%=path %>/VideoDetailServlet?videoAction=listvideodetail&vid=${video.vid}&kcid=${video.kcid}">
	 	<img src="<c:out value="${video.vpic}"/>" width="190px" height="110px"></img>
	 	</a>
	 </div>
	 &nbsp;Title:&nbsp;${video.vtitle}</br>
	 &nbsp;<i class="fa fa-user" aria-hidden="true"></i>&nbsp;${video.username}</br>
	 &nbsp;${video.vtime}
	 
	 
	</div>
	</c:forEach>
	</div>
	<!-- ######################## -->
	</br></br>
	<div class="pagediv">
    	共${page.countrow}条记录&nbsp;
    	共${page.countpage }页&nbsp;
    	Page&nbsp;${page.thispage}&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.firstpage}&spitPageWhat=video">front page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.prepage}&spitPageWhat=video"><i class="fa fa-arrow-left" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.nextpage}&spitPageWhat=video"><i class="fa fa-arrow-right" aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.lastpage}&spitPageWhat=video">last page</a></br>
   </br></br>
   </div>
   <!-- ######################## -->
   </div>
   </div>
  </div>
    <!-- ########################################################################################## -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
