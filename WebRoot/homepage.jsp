<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Curriculum Management System</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="imagetoolbar" content="no" />
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	
	 <link rel="stylesheet" type="text/css" href="css/styles.css"/>
	
  </head>
  
  
 <body id="top">
   <jsp:include page="header.jsp"></jsp:include>


<!-- ####################################################################################################### -->
<div class="wrapper row3">

  <div class="rnd">
  
    <div id="container" class="clear">
      <!-- ####################################################################################################### -->
      <div id="homepage" class="clear">
        <!-- ###### -->
        <div id="left_column">
          <h2>Curriculum</h2>
          <div class="imgholder"><a href="<%=path %>/SearchClass?search=origin"><img src="images/curriculum.jpg" height="80" width="220" alt="" /></a></div>
          <h2>Videos</h2>
          <div class="imgholder"><a href="${pageContext.request.contextPath }/PageServlet?thispage=1&spitPageWhat=video"><img src="images/video.jpg" height="80" width="220" alt="" /></a></div>
          <h2>Teachers</h2>
          <div class="imgholder"><a href="teacher.jsp"><img src="images/teacher.jpg" height="80" width="220" alt="" /></a></div>
           <h2>Forum</h2>
          <div class="imgholder"><a href="${pageContext.request.contextPath }/PageServlet?thispage=1&time=one&spitPageWhat=post" target="_blank"><img src="images/forum.jpg" height="80" width="220" alt="" /></a></div>
        </div>
        <!-- ###### -->
        <div id="latestnews">
          <h2>Latest News &amp; Events</h2>
          <ul>
            <li class="clear">
              <div class="imgl"><img src="images/news1.jpg" height="125" width="125"alt="" /></div>
              <div class="latestnews">
                <p><a href="#">news1 2017.5.1</a></p>
                <p>A North Korean government official in a rare interview promised his country's nuclear tests would "never stop" as long as the US continued what they viewed as "acts of aggression."</p>
              </div>
            </li>
            <li class="clear">
              <div class="imgl"><img src="images/news2.png" height="125" width="125" alt="" /></div>
              <div class="latestnews">
                <p><a href="#">news2 2017.4.27</a></p>
                <p>A North Korean government official in a rare interview promised his country's nuclear tests would "never stop" as long as the US continued what they viewed as "acts of aggression."</p>
              </div>
            </li>
            <li class="last clear">
              <div class="imgl"><img src="images/news3.jpg" height="125" width="125" alt="" /></div>
              <div class="latestnews">
                <p><a href="#">news3 2017.4.15</a></p>
                <p> A North Korean government official in a rare interview promised his country's nuclear tests would "never stop" as long as the US continued what they viewed as "acts of aggression."</p>
              </div>
            </li>
          </ul>
          <p class="readmore"><a href="#">Click here to view all of the latest news and events &raquo;</a></p>
        </div>
        <!-- ###### -->
        <div id="right_column">
          <div class="holder">
            <h2>Online courses</h2>
           &nbsp; <a href="${pageContext.request.contextPath }/PageServlet?thispage=1&spitPageWhat=video">
            <img src="images/video.gif" height="120" width="160"alt="" /></a></br></br>
           &nbsp; <a href="${pageContext.request.contextPath }/PageServlet?thispage=1&spitPageWhat=video">
            <img src="images/video.gif" height="120" width="160"alt="" /></a></br>
           </div>
          <div class="holder">
            <h2>Quick Information</h2>
            <div class="apply"><a href="http://www.bupt.edu.cn/" target="_blank" ><img src="images/bupt.jpg" height="100" width="100" alt="" /> <strong>BUPT Homepage</strong></a></div>
            <div class="apply"><a href="http://www.qmul.ac.uk/" target="_blank" ><img src="images/QM.jpg" height="100" width="100" alt="" /> <strong>QM Homepage</strong></a></div>
          </div>
        </div>
        
      </div>

 		

 
 	  <!-- ####################################################################################################### -->
    </div>
  </div>
</div>
<!-- ####################################################################################################### -->

<jsp:include page="footer.jsp"></jsp:include>

  </body>
</html>
