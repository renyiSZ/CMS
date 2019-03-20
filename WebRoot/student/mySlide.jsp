<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My Slide</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/admincss/table.css" />

  </head>
  
  <body>
  <%
    
 ArrayList classlist = (ArrayList)session.getAttribute("classlist");
 System.out.println("classlist in userspace:"+classlist);
 
 %>
 
  <div class="admin-main">
    <blockquote class="layui-elem-quote">
    
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/SlideServlet?action=allClassSlide'">
    	All Slides
    </button>&nbsp;&nbsp;
 	<%	for (int ii=0;ii<classlist.size();ii++){
 	 %>
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/SlideServlet?action=singleClassSlide&classid=<%=classlist.get(ii) %>'">
    	<%=classlist.get(ii) %>
    </button>&nbsp;&nbsp;
    <%
    	} 
    %>
    </blockquote>
    <!-- ######################################################-->
    
    
  <fieldset class="layui-elem-field">
	<legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		My Slides
	</legend>
		<div class="layui-field-box">
		
  	 	<table class="site-table table-hover">
		  <thead>
			<tr>
			<th>CLASS</th>
    		 <th>SLIDES</th>
    		 <th>TYPE</th>
    		 <th>TEACHER</th>
    		 <th>DESCRIPTION</th>
    		 <th>TIME</th>
    		 <th>DOWNLOAD</th>
    	    </tr>
    	  </thead>
  	 		
  	 <c:forEach items="${requestScope.slidelist}" var="slidelist">
    	<tbody>
			<tr>
				<td>${slidelist.kcid}</td>
				<td>${slidelist.mtitle}</td>
				
				<td>${slidelist.mtype}</td>
				<td>${slidelist.userid}</td>
				<td>${slidelist.mdesc}</td>
				
				<td>${slidelist.mtime}</td>
				<td align="center"><a href="<%=path %>/upload?downloadID=${slidelist.mid}">
				<i class="fa fa-download " aria-hidden="true"></i>&nbsp;download</a></td>
			</tr>
  	 </c:forEach>
  	  </tbody>
	  </table></br></br>
    </div>
	</fieldset>
</div>
  </body>
</html>
