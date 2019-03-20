<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GroupWork.jsp' starting page</title>
    
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
  List hwlist=(List)request.getAttribute("gwlist");  
  %>
   <fieldset class="layui-elem-field">
   </br></br>
  <legend>
	<i class="fa fa-navicon " aria-hidden="true"></i>&nbsp;
		Group Homework
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
    		 <th>Group Division</th>
    		 <th>Student Performance</th>
    	    </tr>
    	  </thead>
  <%  	  
         for(int j=0;j<hwlist.size();j++){
    		HashMap m=(HashMap) hwlist.get(j);
    		String fileName=""+m.get("hwlink");
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
			String status=""+m.get("answerlink");
 %> 
    	<tbody>
			<tr>
				<td><%=m.get("kcid") %></td>
				<td><%=m.get("hwtitle") %></td>
				
				<td><%=m.get("hwdesc") %></td>
				
				<%
					if(fileName.equals("")){
			 	%>	
			  	 <td>--</td>
			   
			 	<%
				 }else{
			 	%>	
					<td align="center"><a href="<%=path %>/HomeworkServlet?downloadID=<%=m.get("hwid") %>">
					<%=fileName %></a></td>
			 	<%
			 	}
			 	%>
				
				
				<td><%=m.get("hwtime") %></td>
				
				<%
					if(status.equals("divided")){
			 	%>	
			  	 <td align="center">
			  	 	<i class="fa fa-check " aria-hidden="true"></i>
			  	 </td>
			   
			 	<%
				 }else{
			 	%>	
					<td align="center">
					<a href="TeacherGroupWork?type=divide&hwid=<%=m.get("hwid")%>&kcid=<%=m.get("kcid")%>">
						<font color="blue">Divide Group</font>
					</a>
					</td>
			 	<%
			 	}
			 	%>
			 	
			 	
				<%
					if(status.equals("divided")){
			 	%>	
			  	 <td align="center">
			  	 <a href="TeacherGroupWork?type=check&hwid=<%=m.get("hwid")%>&kcid=<%=m.get("kcid")%>">Check</a>	
			  	 </td>
			   
			 	<%
				 }else{
			 	%>	
					<td align="center">
					--
					</td>
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
     No Group Work
     <%} %>
  
  </fieldset>
  </body>
</html>
