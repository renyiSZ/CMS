<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Correct Homework</title>
    
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
 List correctlist =(List)request.getAttribute("correctlist");
  %>
   <div class="admin-main">
   
 <blockquote class="layui-elem-quote">
 <form name="formname" action="<%=path%>/TeacherHWServlet" method="post" >
 <select name="kcid" style="width:200px; height:40px;">
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
</select><input type="hidden" name="type" value="correct">
<button onclick="javascript:document.formname.submit();">&nbsp;Search&nbsp;</button> </form>
</blockquote>

    <!-- ######################################################-->
    
    
  <fieldset class="layui-elem-field">
	<legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		Correct Homework
	</legend>
		<div class="layui-field-box">
		
<%
if(correctlist.size()>0){

	
	
%>
  	
  	 	<table class="site-table table-hover">
		  <thead>
			<tr>
    		 <th>Homework ID</th>
    		 <th>Student ID</th>
    		 <th>Student Name</th>
    		 <th>Homework</th>
    		 <th>TIME</th>
    		  
    	    </tr>
    	  </thead>
<% 
				for(int j=0;j<correctlist.size();j++){
					HashMap ma=(HashMap) correctlist.get(j);
					String fileName=""+ma.get("handinlink");
					fileName=fileName.substring(fileName.lastIndexOf("\\")+1);
					int status=9;
					String state=""+ma.get("status");
					if(!state.equals(null)){
					  status=Integer.parseInt(state);
					}
					if(status==0){
%>
  	 
    	<tbody>
			<tr>
				
				<td><%=ma.get("hwid") %></td>
				<td><%=ma.get("studentid") %></td>
				<td><%=ma.get("studentname") %></td>
				<td>
					<a href="<%=path %>/HWforCorrect?downloadID=<%=ma.get("correctid")%>">
					<i class="fa fa-download " aria-hidden="true"></i>&nbsp;<%=fileName %>
					</a>
				</td>
				<td><%=ma.get("handintime") %></td>
				
			</tr>
			
			<tr><form name=<%="form"+ma.get("correctid")%> action="<%=path %>/CorrectServlet" method="post">
				<td>mark</td>
				<td>
					<input type="text" name="mark" style="width:70px;height:40px">
					
				</td>
				<td>comment</td>
				<td colspan="3"><textarea name="comment" rows="4" cols="70"></textarea></td>
				<td>
					<a id="correct" href="javascript:document.<%="form"+ma.get("correctid")%>.submit();">
					<i class="fa fa-check " aria-hidden="true"></i>&nbsp;Correct
					</a>
				</td>
				<input type="hidden" name="correctid" value="<%=ma.get("correctid")%>">
				</form>
			</tr>
  	 <%
  	           }
  	        } 	 
  		
  	
  	 %>
  	  </tbody>
	  </table></br></br>
	  <%}else{ 
	  %>
	  <center>No Submitted Homework</center>
	  <%} %>
    </div>
    
	</fieldset>
</div>


  </body>
</html>
