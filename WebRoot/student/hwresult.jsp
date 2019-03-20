<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
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
     <%
     List hwResultlist= (List)request.getAttribute("hwResultlist");
    String AnalysisFlag= (String)request.getAttribute("AnalysisFlag");
 ArrayList classlist = (ArrayList)session.getAttribute("classlist");
 System.out.println("classlist in userspace:"+classlist);
 
 %>
 
  <div class="admin-main">
    <blockquote class="layui-elem-quote">
    
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/StudentHWResultServlet?action=allClassResult'">
    	All
    </button>&nbsp;&nbsp;
 	<%	for (int ii=0;ii<classlist.size();ii++){
 	 %>
    <button class="layui-btn" onClick="javascript:window.location.href='<%=path%>/StudentHWResultServlet?action=singleClassResult&classid=<%=classlist.get(ii) %>'">
    	<%=classlist.get(ii) %>
    </button>&nbsp;&nbsp;
    <%
    	} 
    %>
    </blockquote>
    <!-- ######################################################-->
    
  <fieldset class="layui-elem-field">
  <div class="layui-field-box">
<%
    if(AnalysisFlag.equals("yes")&&hwResultlist.size()>0){
    String currentclass=(String)request.getAttribute("currentclass");
%>
<font size="5"><%=currentclass %></font>
<div style="height:50px; float:right;">
<a href="<%=path%>/StudentHWResultServlet?action=showclasschart&kcid=<%=currentclass%>">
    <button class="layui-btn layui-btn-radius layui-btn-primary" onClick="javascript:window.location.href=''">
    <i class="fa fa-bar-chart-o " aria-hidden="true"></i>&nbsp; 
    	My <%=currentclass %> Homework Result Analysis
    </button></a>
</div>
<%   }
    if(hwResultlist.size()>0){
%>
		<table class="site-table table-hover">
		  <thead>
			<tr>
			<th>Number</th>
    		 <th>Class</th>
    		 <th>Homework Title</th>
    		 <th>Time</th>
    		 <th>Mark</th>
    		 <th>Overall Analysis</th>
    		
    	    </tr>
    	  </thead>
    
<%
    	for(int i=0;i<hwResultlist.size();i++){
					HashMap m=(HashMap)hwResultlist.get(i);
					String time=""+m.get("handintime");
					time= time.substring(0,10);
%>
    	<tbody>
			<tr>
				<td><%=i+1 %></td>
				<td><%=m.get("kcid") %></td>
				
				<td><%=m.get("hwtitle") %></td>
				<td><%=time %></td>
				<td><%=m.get("result") %></td>
				<td> <a href="<%=path%>/StudentHWResultServlet?action=showsinglechart&kcid=<%=m.get("kcid")%>&hwname=<%=m.get("hwtitle")%>&hwid=<%=m.get("hwid")%>">
				<i class="fa fa-bar-chart-o " aria-hidden="true"></i>&nbsp; Overall Analysis</a> </td>	
			</tr> 	  
<% } %>
</tbody></table></br></br>
<% }else{
 %>	
	<center>No Result</center>
<%} %>
	  
    </div>
  
  </fieldset>
  </div>
  </body>
</html>
