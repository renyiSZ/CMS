<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'hwresult.jsp' starting page</title>
    
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
    <div class="admin-main">
 
    <blockquote class="layui-elem-quote">
  <% ArrayList classlist = (ArrayList)session.getAttribute("classlist"); 
  List hwlist=(List)request.getAttribute("hwlist"); 
  String currentclass=(String)request.getAttribute("currentclass"); 
  //System.out.println(studentlist);
    
  %>
 <form name="classform" action="<%=path%>/TeacherChartServlet" method="post">
 <select name="hw" style="width:200px; height:40px;">
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
</select><input type="hidden" name="chart" value="assignedhw">
<button onclick="javascript:document.classform.submit();">&nbsp;Search&nbsp;</button> </form>
  </blockquote></br>
  <fieldset class="layui-elem-field">
  <legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		Assigned Homework
	</legend>
		<div class="layui-field-box">
<%
     	if(hwlist.size()>0){	
%>
     	<table class="site-table table-hover">
		  <thead>
			<tr>
			<th>CLASS</th>
    		 <th>TITLE</th>
    		 <th>DESCRIPTION</th>
    		  <th>ATTACHMENT</th>
    		 <th>TIME</th>
    		 <th>DOWNLOAD</th>
    	    </tr>
    	  </thead>
  <%  	  
         for(int j=0;j<hwlist.size();j++){
    		HashMap m=(HashMap) hwlist.get(j);
    		String fileName=""+m.get("hwlink");
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
 %> 
    	<tbody>
			<tr>
				<td><%=m.get("kcid") %></td>
				<td><%=m.get("hwtitle") %></td>
				
				<td><%=m.get("hwdesc") %></td>
				<td><%=fileName %></td>
				<td><%=m.get("hwtime") %></td>
				
			<%
			if(fileName.equals("")){
			 %>	
			   <td>--</td>
			   
			 <%
			 }else{
			 %>	
				<td align="center"><a href="<%=path %>/HomeworkServlet?downloadID=<%=m.get("hwid") %>">
				<i class="fa fa-download " aria-hidden="true"></i>&nbsp;</a></td>
			 <%
			 }
			 %>
			</tr>
  	
  	  </tbody>
	  
   <%
   }%>
   </table></br></br>
    </div>
   <% }else{
     %>
     No homework
     <%} %>
  
  </fieldset>
  </div>
  </body>
</html>
