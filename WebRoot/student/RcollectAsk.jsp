<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'RcollectAsk.jsp' starting page</title>
    
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
  String representative=(String)session.getAttribute("representative"); 
  List asklist=(List)request.getAttribute("asklist"); 
  String teacherid=(String)request.getAttribute("teacherid");
  System.out.println(" R to teacherid:"+teacherid);
  %>
  <div style="float:left;padding:5px; border:1px solid #E5E5E5; margin:10px; width:550px; height:180px;">
  <form name="formname" action="<%=path %>/StudentAsk" method="post">
   <div style="float:left;">
   <textarea name="advice" rows="8" cols="85" placeholder=" Report to teacher..."></textarea></div>
   <div style="float:right;">
   <button class="layui-btn layui-btn-radius" onSubmit="javascript:document.formname.submit();">&nbsp;Send&nbsp;</button></div>
   <input type="hidden" name="asktoid" value="<%=teacherid %>">
   <input type="hidden" name="type" value="addask">
   </form>
   </div>
   <div style="float:left;width:100%; height:5px;"></div>
   
<div style="float:left;width:100%;">
 
  <% 
  if(asklist.size()>0){
  %>
  
		
  	 	<table class="site-table table-hover">
		  <thead>
			<tr>
			<th>No.</th>
    		 <th>From</th>
    		 <th>Content</th>
    		 <th>Time</th>
    		 <th>Mark</th>
    	    </tr>
    	  </thead>
  <%
  	for(int i=0;i<asklist.size();i++){
  		HashMap m=(HashMap)asklist.get(i);
  		String time=""+m.get("asktime");
         time=time.substring(0,16);
  %>
  <tbody>
			<tr>
				<td><%=i+1 %></td>
				<td><%=m.get("studentname") %></td>
				<td><%=m.get("askcontent") %></td>
				<td><%=time %></td>
  				<td>
  				<a href="<%=path%>/TeacherAnswer?type=mark&from=Rmark&askid=<%=m.get("askid")%>">
  				<button class="layui-btn layui-btn-radius">Mark as Read</button></a>
  				</td>
  			</tr>
  <%
  }}else{
   %>
    
  <center>- No Advice or Questions -</center>
  <%
  }
   %>
   </tbody></table>
   </div>
   </fieldset>
  </body>
</html>
