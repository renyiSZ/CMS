<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'TAReport.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
  </head>
  
  <body>
    <% 
  String assistant=(String)session.getAttribute("assistant"); 
 
  String teacherid=(String)request.getAttribute("teacherid");

  %>
  <div style="float:left;padding:5px; border:1px solid #E5E5E5; margin:10px; width:700px; height:180px;">
  <form name="formname" action="<%=path %>/StudentAsk" method="post">
   <div style="float:left;">
   <textarea name="advice" rows="8" cols="85" placeholder=" Report to teacher..."></textarea></div>
   <div style="float:right;">
   <button class="layui-btn layui-btn-radius" onSubmit="javascript:document.formname.submit();">&nbsp;Send&nbsp;</button></div>
   <input type="hidden" name="asktoid" value="<%=teacherid %>">
   <input type="hidden" name="type" value="addask">
   </form>
   </div>
  </body>
</html>
