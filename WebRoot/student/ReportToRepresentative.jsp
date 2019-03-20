<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'ReportToRepresentative.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	
<style>
.videodiv{float:left; border:1px solid #E5E5E5; margin:10px; width:120px; height:190px; word-wrap:break-word;}
		.imgdiv{ width:120px; height:120px; background:black; }
.rightform{float:left;padding:5px; border:1px solid #E5E5E5; margin:10px; width:550px; height:180px; }
</style>
  </head>
  
  <body>
   <body>
   <% 
   String me = (String)session.getAttribute("uid");  
  ArrayList classlist = (ArrayList)session.getAttribute("classlist");  
  List representativelist=(List)request.getAttribute("representativelist"); 
 String people=(String)request.getAttribute("people");
  %>
   <div class="admin-main">
  <%
    if(representativelist.size()>0){
      for(int i=0;i<representativelist.size();i++){
       	HashMap m=(HashMap) representativelist.get(i);
       	String person=""+m.get("uid");
       	if (!me.equals(person)){
  %>
  
   <div class="videodiv">
	 	<div class="imgdiv">
			 <img src="<%=m.get("uhead") %>" width="120px" height="120px"></img>
   	 	</div></br>
  <%if(people.equals("toRepresentative")) {%>
   	 	&nbsp;<%= m.get("representative")%></br>
   	 	&nbsp;Representative
   	 	&nbsp;<%= m.get("uname")%>
   <% }else if(people.equals("toTeacher")){ %> 
        &nbsp;Teacher</br>
   		&nbsp;<%= m.get("uname")%></br>
   	 	
   <%} %> 	  	
   </div>
   <div class="rightform">
   <form name="formname" action="<%=path %>/StudentAsk" method="post">
   <div style="float:left;">
   <textarea name="advice" rows="8" cols="85" placeholder=" type in your advice or questions..."></textarea></div>
   <div style="float:right;">
   <button class="layui-btn layui-btn-radius" onSubmit="javascript:document.formname.submit();">&nbsp;Send&nbsp;</button></div>
   <input type="hidden" name="asktoid" value="<%=m.get("uid") %>">
   <input type="hidden" name="type" value="addask">
   </form>
   </div>
   <div style="float:left;width:100%; height:1px;"></div>
  <%
  }else{%>
  <center>None</center>
  <% }}
  }else{
  %>
  <center>None</center>
 <%}
 %>
   </div>
  </body>
</html>
