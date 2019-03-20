<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'plan.jsp' starting page</title>
    
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
  // String uid = (String)session.getAttribute("uid"); 
  String currentclass=(String)request.getAttribute("currentclass"); 
  List planList =(List)request.getAttribute("planList");
  %>
   <div class="admin-main">
   
 <blockquote class="layui-elem-quote">
 <form name="formname" action="<%=path%>/PlanServlet" method="post" >
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
</select><input type="hidden" name="type" value="read">
<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

    <!-- ######################################################-->
   

<%
     	if(planList.size()>0){
         for(int j=0;j<planList.size();j++){ 
    		HashMap m=(HashMap) planList.get(j);
%> 



 <div style="float:left;padding:5px; border:1px solid #E5E5E5; margin:10px; width:550px; height:270px;">
<font size=4><%=m.get("kcid")%></font> </br></br>
Name: <%=m.get("kcname")%> &nbsp;&nbsp;&nbsp;</br></br>
Teacher: <%=m.get("teachername")%></br></br>
Credit: <%=m.get("credit")%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Semester: <%=m.get("semester")%></br></br>
  
 <textarea rows="8" cols="85" onfocus=this.blur(); readOnly><%=m.get("teachingplan")%></textarea> 
   
<%
}}else{
 %>
 <center> No Class</center>
 <%} %>

   
   </div> 
  </body>
</html>
