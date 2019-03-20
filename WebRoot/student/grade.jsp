<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.kc.entity.Grade"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'grade.jsp' starting page</title>
    
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
  String currentclass=(String)request.getAttribute("currentclass"); 
  List<Grade> gradelist=( List<Grade>)request.getAttribute("gradelist"); 
 // List notelist =(List)request.getAttribute("notelist");
  %>
   <div class="admin-main">
   
 <blockquote class="layui-elem-quote">
 <form name="formname" action="<%=path%>/StudentGrade" method="post" >
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
</select><input type="hidden" name="type" value="grade">
<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

    <!-- ######################################################-->
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
    		 <th>Time</th>
    		 <th>Grade</th>
    		 <th>Overall Analysis</th>
    	    </tr>
    	  </thead>
  <% 
     for(int i=0;i<gradelist.size();i++){
        Grade grade=gradelist.get(i);
        String time=grade.getGtime();
        time=time.substring(0,10);
   %>
   <tbody>
			<tr>
				<td><%=i+1 %></td>
   				<td><%=grade.getKcid() %></td>
   				<td><%=grade.getGname() %></td>
   				<td><%=time %></td>
   				<td><%=grade.getGrade() %></td>
   				<td> 
   					<a href="<%=path%>/StudentGrade?type=showclasschart&kcid=<%=grade.getKcid() %>&gname=<%=grade.getGname() %>">
						<i class="fa fa-bar-chart-o " aria-hidden="true"></i>&nbsp; Overall Analysis
					</a>
				</td>
   			</tr>
  <%
  }
  }else{
   %>
   <center>No Grade</center>
   <%
  }
   %>
   </tbody>
   </table>
   </br> </br>
  </fieldset>

   </div>
  </body>
</html>
