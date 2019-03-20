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
<style>
.chosen{color:grey;}
</style>
  </head>
  
  <body>
  <div class="admin-main">
 
    <blockquote class="layui-elem-quote" style=" height:45px;">
  <% 
  ArrayList classlist = (ArrayList)session.getAttribute("classlist"); 
   List homeworklist=(List)request.getAttribute("homeworklist"); 
   //int submitcount=request.getAttribute("submitcount"); 
  int submitcount= Integer.parseInt(String.valueOf(request.getAttribute("submitcount")  != null ? request.getAttribute("submitcount") : "0").trim());
   //int handinNotCorrect=Integer.parseInt(request.getAttribute("handinNotCorrect")); 
     int handinNotCorrect= Integer.parseInt(String.valueOf(request.getAttribute("handinNotCorrect")  != null ? request.getAttribute("handinNotCorrect") : "0").trim());
   String currentclass=(String)request.getAttribute("currentclass");
   String currenthw=(String)request.getAttribute("currenthw");
    String currenthwtitle=(String)request.getAttribute("currenthwtitle");
    String currenthwtime=(String)request.getAttribute("currenthwtime");
    
    int size=homeworklist.size();
  %>
  <div style="float:left;">
 <form name="resultform" action="<%=path%>/TeacherChartServlet" method="post">
 
 <select name="hw" style="width:200px; height:40px;">
   <option class="chosen" value="<%=currentclass%>.<%=currenthw %>:<%=currenthwtitle %>" >--<%=currentclass %>&nbsp;&nbsp;<%=currenthwtitle %>--</option> 
			<%
			 	for(int i=0;i<classlist.size();i++){
			 %>
			 	<optgroup label="<%=classlist.get(i) %>"> 
			<%
			String sql="select * from homework where kcid='"+classlist.get(i)+"'";
			List homework=DAOFactory.getHomeworkDAOInstance().getHomework(sql);
			for(int j=0;j<homework.size();j++){
			HashMap map=(HashMap) homework.get(j);
			String c=""+map.get("hwid");
			if(!c.equals(currenthw)){
			%>
			
			<option value="<%=classlist.get(i) %>.<%=map.get("hwid")%>:<%=map.get("hwtitle")%>">&nbsp;&nbsp;<%=classlist.get(i) %>&nbsp;&nbsp;<%=map.get("hwtitle")%></option>
			<%
			}}%>
			
			</optgroup>
			<% 
				}
			 %>	
</select><input type="hidden" name="chart" value="result">
<button onclick="javascript:document.resultform.submit();">&nbsp;Search&nbsp;</button></form></div>
<% 
	if(submitcount!=0)	{	//有同学已被打分	的情况下
%>
<div style="float:right;">
 <a href="<%=path%>/StudentHWResultServlet?action=showsinglechart&kcid=<%=currentclass%>&hwname=<%=currenthwtitle%>&hwid=<%=currenthw%>">
 <button class="layui-btn layui-btn-radius layui-btn-primary" ">
 <i class="fa fa-bar-chart-o " aria-hidden="true"></i>&nbsp; Overall Analysis
 </button></a> 
 </div>
<% 
	}			
%>	

  </blockquote></br>
 <fieldset class="layui-elem-field">
 <table class="site-table table-hover">
		  <thead>
			<tr>
			
			  <th>Class</th>
    		  <th>Homework ID</th>
    		  <th>Homework Title</th>
    		  <th>Time</th>
    		  <th>Graded</th>
    		  <th>pending</th>
    		  <th>Not Submitted</th>
    		  
    	    </tr>
    	  </thead>
    	  <tr>
			  <td><%=currentclass %></td>
			  <td><%=currenthw %></td>
			  <td><%=currenthwtitle %></td>
			  <td><%=currenthwtime %></td>

			  <td><%=submitcount %></td>
			  <td><%=handinNotCorrect %></td>
			  <td><%=size-submitcount-handinNotCorrect %></td>
			  
			  
  </table></br></br>
    <%
     if(homeworklist.size()>0){
     %>  
     <table class="site-table table-hover">
		  <thead>
			<tr>
			  <th>Student ID</th>
    		  <th>Student Name</th>
    		  <th>Hand in time</th>
    		  <th>Homework</th>
    		  <th>Status</th>
    		  <th>Grade</th> 
    	    </tr>
    	  </thead>
     <%
    	for(int i=0;i<homeworklist.size();i++){
			HashMap hm=(HashMap) homeworklist.get(i);
			int status=Integer.parseInt(""+hm.get("status"));
			String status2="";
			String grade="";
			if(status==9) {status2="Not Submitted";grade="--";}
			if(status==0) {
					status2="Pending to be corrected";
					grade="--";
			}
			if(status==1) {status2="Graded"; grade=""+hm.get("result");}
			String link=""+hm.get("handinlink");
			String filename=link.substring(link.lastIndexOf("\\")+1);
			String time=""+hm.get("handintime");
			System.out.println(time);
			if(time.equals("null")||time.equals("")) {time="--";System.out.println(time);}
			if(filename.equals("null")||filename.equals("")) {filename="--";}
    %>
    		<tr>
    			<td><%=hm.get("studentid") %></td>
    			<td><%=hm.get("studentname") %></td>
    			<td><%=time%></td>
    			<td>
   <%
    if(!filename.equals("--")){
    %> 			
    			<a href="<%=path %>/HWforCorrect?downloadID=<%=hm.get("correctid")%>">
    				<%= filename%>
    			</a>
    <%
    }else{
    %>
    			<%= filename%>
    <%
    }
    %>			
    			</td>
    			<td><%=status2 %></td>
    			<td><%=grade %></td>
    		</tr>
    <%
    }}
    %>
    </table>
 </fieldset>
</div>

  </body>
</html>
