<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ page import="com.kc.entity.ForumPost"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>forum</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/table.css" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
  <body id="top">
    <jsp:include page="header.jsp"></jsp:include>
     <!-- ########################################################################################## -->
 <div class="wrapper row3">
  <div class="rnd">
    <div id="container" class="clear">
    <!-- ########-->
    <blockquote class="layui-elem-quote">
   		 current page: <a href="homepage.jsp">home</a> > forum 
   		
   	 <div style="float:right" >
   	 	<form name="searchform" action="<%=path %>/PageServlet" method="post" >
    		
    		<input type="hidden" name="spitPageWhat" value="postselect"/>
    		<input type="hidden" name="thispage" value="1"/>
    		<select name="posttype" style="width:120px; height:25px;">
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
    	<button class="layui-btn layui-btn-small" onclick="javascript:document.searchform.submit();"> 
    		<i class="layui-icon">&#xe615;</i>&nbsp;search</i>
    	</button>
    		
   	 		</form>
    	</div>
    </blockquote>
    	
   		<!-- ########################################################################################## -->
   		<div class="layui-field-box">
   		<table class="site-table table-hover">
		  <thead align="center">
			<tr>
			<th >Type</th>
    		 <th>Title</th>
    		 <th>Poster</th>
    		 <th>Post Time</th>
    		 <th>View / Reply</th>
    		
    	    </tr>
    	  </thead>
    	<c:forEach items="${requestScope.page.list}" var="post">
    		<tbody>
    		<tr>
    			<td width="15%">[<c:out value="${post.posttype}"/>]</td>
    			<td width="40%">
    				<a href="<%=path %>/PostDetailServlet?detailWhat=notme&time=one&postid=${post.postid}">
    					<c:out value="${post.posttitle}"/>
    				</a>
    			</td>
    			<td align="center"  width="10%"><c:out value="${post.username}"/></td>
    			<td align="center" width="20%"><c:out value="${post.posttime}"/></td>
    			<td align="center" ><c:out value="${post.postclick }"/>/<c:out value="${post.postreply }"/></td>
    		</tr>
    		
    	</c:forEach>
    	</tbody>
    </table></div></br></br>
    	共${page.countrow}条记录&nbsp;
    	共${page.countpage }页&nbsp;
    	Page&nbsp;${page.thispage}&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.firstpage}&spitPageWhat=post">front page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.prepage}&spitPageWhat=post">previous page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.nextpage}&spitPageWhat=post">next page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.lastpage}&spitPageWhat=post">last page</a></br>
   </br></br>
   			
   		
    </div>
     </div>
   </div>
  </div>
  
    <!-- ########################################################################################## -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
