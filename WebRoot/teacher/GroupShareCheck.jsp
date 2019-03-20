<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'GroupShareCheck.jsp' starting page</title>
    
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
    <button class="layui-btn layui-btn-small" onclick="javascript:window.location.href='<%=path%>/TeacherGroupWork?type=group';"> 
    <i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i></button>
    </blockquote>
   
   <%
   List groupNameList=(List)request.getAttribute("groupNameList"); 
   String kcid=(String)request.getAttribute("kcid"); 
    %>
    
    </br> <font size="5" color="#53868B"><%=kcid %> Group Work</font></br></br>
     
     
    <%
    if(groupNameList!=null){
    	for(int i=0;i<groupNameList.size();i++){
				HashMap m= (HashMap) groupNameList.get(i);
     %>
    
     <%
    String sql="select * from studentgroup where kcid='"+kcid+"' and groupname='"+m.get("groupname")+"'";
	List groupMemberList=DAOFactory.getHomeworkDAOInstance().getGroupInfo(sql);
	
      %>
      
   
    <%
		if(groupMemberList!=null){
		if(groupMemberList.size()>0){
	%>
		<font size="3" color="#53868B">Group &nbsp;<%=m.get("groupname") %>&nbsp; Members:&nbsp;</font>
	<% 
			for(int j=0;j<groupMemberList.size();j++){
				HashMap mm= (HashMap) groupMemberList.get(j);
				if(j==0){
     %>
    
    <%= mm.get("studentname")%>&nbsp;(<%= mm.get("studentid")%>)
    <%
    }else{
     %>
     ,&nbsp;<%= mm.get("studentname")%>&nbsp;(<%= mm.get("studentid")%>)
     <%}}
     %>
    <hr>
     <% 
     }}else{
     %>
     
     No group members yet</br>
     <% } %>
     
      <%
    	if(groupMemberList!=null){
		if(groupMemberList.size()>0){
      String sql3="select * from share where classid='"+kcid+"' and groupname='"+m.get("groupname")+"'";
	List notelist=DAOFactory.getMaterialDAOInstance().getShareFile(sql3);
	 if(notelist.size()>0){
	%>
	<table class="site-table table-hover">
     <thead>
     <tr>
    		 <th>Student ID</th>
    		 <th>Student Name</th>
    		 <th>File</th>
    		 <th>Time</th>
    	    </tr>
     </thead>
	<% 
    
        for(int k=0;k<notelist.size();k++){
         HashMap mmm=(HashMap)notelist.get(k);
         String link=""+mmm.get("sharelink");
		 String filename=link.substring(link.lastIndexOf("\\")+1);
		 String time=""+mmm.get("sharetime");
    	 time=time.substring(0,10);
     %>
     
   <tr>
   		<td><%=mmm.get("uploaderid") %></td>
   		<td><%=mmm.get("uploadername") %></td>
   		<td><a href="<%=path%>/UploadGroupShare?downloadID=<%=mmm.get("shareid")%>"><%=filename %></a></td>
   		<td><%=time %></td>
   </tr>
   
     <%}
     
     %>
     </table></br></br>
     <% }else{
     
    %>
    
    <center>- No group files -</center></br></br>
    <% 
    } }} %>
     <%
     
    	}
    }
     %>
     </div>
  </body>
</html>
