<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'feedback.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
  </head>
  
  <body>
  <% 
  ArrayList classlist = (ArrayList)session.getAttribute("classlist");  
  String currentclass=(String)request.getAttribute("currentclass"); 
  List asklist =(List)request.getAttribute("asklist");
  %>
  <div class="admin-main">
  <blockquote class="layui-elem-quote">
 <form name="formname" action="<%=path%>/StudentAsk" method="post" >
 <select name="kc" style="width:200px; height:40px;">
 <option value="<%=currentclass %>"  ><%=currentclass %></option> 
			<%
			 	for(int i=0;i<classlist.size();i++){
			 	String c=""+classlist.get(i);
			 	if(!c.equals(currentclass)){
			 
			 %>
				<option value="<%=classlist.get(i) %>"  > <%=classlist.get(i) %></option> 
			<%
				}}
			 %>	
</select><input type="hidden" name="type" value="feedback">

<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

 <!-- ######################################################-->
    <fieldset class="layui-elem-field">
  
  <%  if(asklist!=null&&asklist.size()!=0){
  %>
  
  <% 
    for(int i=0;i<asklist.size();i++){
         HashMap m=(HashMap)asklist.get(i);
        
         String asktime=""+m.get("asktime");
         asktime=asktime.substring(0,16);
         
         String answertime=""+m.get("answertime");
         answertime=answertime.substring(0,16);
%>
<div style="width:100%; margin:20px;" >
<table width="65%" border="1" bordercolor="#E5E5E5">
	<tr>
	<td width="65%"  bgcolor=" #B0E0E6 ">
	<div style="width:100%; padding:2px;">
	&nbsp;<font color="#53868B"><strong>Ask:</strong></font>&nbsp;&nbsp;<%=m.get("studentname") %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	<font color="#53868B"><strong>Answer:</strong></font>&nbsp;&nbsp;<%=m.get("teachername") %>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	<font color="#53868B"><strong>Time:</strong></font>&nbsp;&nbsp;<%=answertime %>
	</div></td>
	</tr>
	
	<tr>
	<td>
	<div style="width:100%;">
	<div style="float:left;width:15%;">
	&nbsp;&nbsp;<font size="7" color="#53868B" >Q</font></div>
     <div  style="float:left;width:85%;">&nbsp;&nbsp;<%=m.get("askcontent") %>
     <div>
     </div></td>
	</tr>
	
	<tr>
	<td>
	<div style="width:100%;">
	<div style="float:left;width:15%;">
	&nbsp;&nbsp;<font size="7" color="#53868B" >A</font></div>
     <div  style="float:left;width:85%;">&nbsp;&nbsp;<%=m.get("answercontent") %><div>
     </div></td>
	</tr>
</table>
</div>

<%
  }}else{
%>
   
   <center>No Answers or feedback from teachers</center></br></br></br>
  <%
  }
   %>  
    </fieldset>
  </div>
  </body>
</html>
