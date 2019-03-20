<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'fromRAsk.jsp' starting page</title>
    
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
</select><input type="hidden" name="type" value="TCollect">
<input type="hidden" name="from" value="R">
<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

    <!-- ######################################################-->
  
  <fieldset class="layui-elem-field">
  
  <%  if(asklist!=null&&asklist.size()!=0){
  %>
  
  <% 
    for(int i=0;i<asklist.size();i++){
         HashMap m=(HashMap)asklist.get(i);
        
         String time=""+m.get("asktime");
         time=time.substring(0,16);
%>
<div style="width:100%; margin:20px;" >
<table width="65%" border="1" bordercolor="grey">
	<tr>
	<td width="65%" colspan="2" bgcolor="#E5E5E5">
	<div style="width:100%; padding:2px;">
	&nbsp;<font color="#53868B"><strong>From:</strong></font>&nbsp;&nbsp;<%=m.get("studentname") %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
	<font color="#53868B"><strong>Time:</strong></font>&nbsp;&nbsp;<%=time %>
	</div></td>
	</tr>
	
	<tr>
	<td>
	<div style="width:100%;">
	<div style="float:left;width:20%;">
	&nbsp;<font size="8" color="#53868B" > <i class="fa fa-exclamation" aria-hidden="true"></i>
	/<i class="fa fa-question" aria-hidden="true"></i></font></div>
     <div  style="float:left;width:80%;">  &nbsp;&nbsp;<%=m.get("askcontent") %><div>
     </div></td>
     
     <td align="center"><a href="<%=path%>/TeacherAnswer?type=mark&from=R&askid=<%=m.get("askid")%>"><button class="layui-btn layui-btn-radius">Mark as Read</button></a></td>
	</tr>
	
	<tr>
	<form name="formname" action="<%=path%>/TeacherAnswer" method="post">
		<input type="hidden" name="type" value="answer">
		<input type="hidden" name="from" value="R">
		<input type="hidden" name="askid" value="<%=m.get("askid")%>">
		
		<td height="100px">&nbsp;<textarea name="answercontent" rows="4" cols="75" placeholder=" type in your answer..."></textarea></td>
		<td align="center"><button class="layui-btn layui-btn-radius" onSubmit="javascript:document.formname.submit();"> Answer </button></td>
		</form>
	</tr>
</table>
</div>

  
  <%
  }}else{
   %>
   
   <center>No Advice or Questions from Subject Representative</center></br></br></br>
  <%
  }
   %>

  </fieldset>
  </div>
  </body>
</html>
