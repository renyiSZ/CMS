<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ page import="com.kc.entity.Material"%>
<%@ page import="com.kc.service.MaterialService"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <title>learning materials</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/styles.css"/>
<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">

 
  </head>
  
  <body id="top">
  <jsp:include page="header.jsp"></jsp:include>
  <!-- ########################################################################################## -->
 <div class="wrapper row3">
  <div class="rnd">
    <div id="container" class="clear">
    <!-- ########-->
       
       <div>
   	 		<form name="searchform" action="<%=path %>/PageServlet" method="post" >
    		<input type="text" name="classid" style="width:90px; height:25px;" placeholder="Class ID" >&nbsp;
    		<input type="hidden" name="spitPageWhat" value="materialselect"/>
    		<input type="hidden" name="thispage" value="1"/>
    	<button class="layui-btn layui-btn-small" onclick="javascript:document.searchform.submit();"> 
    		search
    	</button>
   	 		</form></div>
    	
    </br>
    <table cellspadding="0">
    	<tr height="25" bgcolor="#C1CDCD"color="black">
    		 <!-- <td>material id</td>-->
    		<td>SLIDE</td>
    		<td>CLASS</td>
    		<td>TYPE</td>
    		<td>TEACHER</td>
    		<td>DESCRIPTION</td>
    		<!--<td>link</td>-->
    		<td>TIME</td>
    		<td>DOWNLOAD</td>
    	</tr>	
    	<c:forEach items="${requestScope.page.list}" var="material">
    		<tr>
    			<td><c:out value="${material.mtitle}"/></td>
    			<td><c:out value="${material.kcid }"/></td>
    			<td><c:out value="${material.mtype }"/></td>
    			<td><c:out value="${material.userid }"/></td>
    			<td><c:out value="${material.mdesc }"/></td>
    			<td><c:out value="${material.mtime }"/></td>
    			<td align="center"><a href="<%=path %>/upload?downloadID=${material.mid}">download</a></td>
    		</tr>
    	</c:forEach>
    	
    </table></br></br>
    	共${page.countrow}条记录&nbsp;
    	共${page.countpage }页&nbsp;
    	Page&nbsp;${page.thispage}&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.firstpage}&spitPageWhat=material">front page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.prepage}&spitPageWhat=material">previous page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.nextpage}&spitPageWhat=material">next page</a>&nbsp;&nbsp;
    <a href="${pageContext.request.contextPath}/PageServlet?thispage=${page.lastpage}&spitPageWhat=material">last page</a></br>
   </br></br>
  
    </div>
   </div>
  </div>
    <!-- ########################################################################################## -->
    <jsp:include page="footer.jsp"></jsp:include>
  </body>
</html>
