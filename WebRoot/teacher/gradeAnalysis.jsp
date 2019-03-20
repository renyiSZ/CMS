<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%@ page import="com.kc.entity.Grade"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'gradeAnalysis.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/admincss/table.css" />
<style>
.chosen{color:grey;}
</style>

  </head>
  
  <body>
  <div class="admin-main">
 
    <blockquote class="layui-elem-quote" style=" height:45px;">
  <% 
  ArrayList classlist = (ArrayList)session.getAttribute("classlist"); 
   List<Grade> gradelist=(List<Grade>)request.getAttribute("gradelist"); 
   System.out.println(gradelist);
   String currentclass=(String)request.getAttribute("currentclass");
   String currentgname=(String)request.getAttribute("currentgname");
  %>
   <div style="float:left;">
   <form name="gradeform" action="<%=path%>/GradeAction" method="post">
 
 <select name="kc" style="width:200px; height:40px;">
   <option class="chosen" value="<%=currentclass%>.<%=currentgname %>" >--<%=currentclass %>&nbsp;&nbsp;<%=currentgname %>--</option> 
			<%
			 	for(int i=0;i<classlist.size();i++){
			 %>
			 	<optgroup label="<%=classlist.get(i) %>"> 
			<%
			String sql="select kcid,gname from grade where kcid='"+classlist.get(i)+"'";
			List test=DAOFactory.getGradeDAOInstance().searchTestName(sql);
			for(int j=0;j<test.size();j++){
			HashMap map=(HashMap) test.get(j);
			String c=""+map.get("gname");
			String d=""+map.get("kcid");
			
			if(!c.equals(currentgname)||!d.equals(currentclass)){
			%>
			
			<option value="<%=classlist.get(i) %>.<%=map.get("gname")%>">&nbsp;&nbsp;<%=classlist.get(i) %>&nbsp;&nbsp;<%=map.get("gname")%></option>
			<%
			}}%>
			
			</optgroup>
			<% 
				}
			 %>	
</select><input type="hidden" name="type" value="list">
<button onclick="javascript:document.resultform.submit();">&nbsp;Search&nbsp;</button></form></div>

<div style="float:right;">
 <a href="<%=path%>/StudentGrade?type=showclasschart&kcid=<%=currentclass %>&gname=<%=currentgname %>">
 <button class="layui-btn layui-btn-radius layui-btn-primary" ">
 <i class="fa fa-bar-chart-o " aria-hidden="true"></i>&nbsp; Overall Analysis
 </button></a> 
 </div>
</blockquote>
<fieldset class="layui-elem-field">
  
  <%
  if(gradelist.size()>0){
  %>
  <table class="site-table table-hover">
		  <thead>
			<tr>
    		 <th>No.</th>
    		  <th>Class</th>
    		  <th>Test</th>
    		 <th>Student ID</th>
    		
    		 <th>Student Name </th>
    		 <th>Grade</th>
    		 
    	    </tr>
    	  </thead>
<%
    for(int i=0; i<gradelist.size(); i++){
        Grade grade= gradelist.get(i);
  		String time=grade.getGtime();
        time=time.substring(0,10);
   %>
   <tbody>
			<tr>
				<td><%=i+1 %></td>
   				<td><%=grade.getKcid() %></td>
   				 <th><%=grade.getGname() %></th>
   				<td><%=grade.getStudentid()%></td>
   				<td><%=grade.getStudentname()%></td>
   				<td><%=grade.getGrade() %></td>
 			</tr>
 
 
 
<%
}}else{
 %>
 <center>No grade</center>
 <%}
  %>
  </tbody>
   </table>
   </br> </br>
  </fieldset>
</div>


  </body>
</html>
