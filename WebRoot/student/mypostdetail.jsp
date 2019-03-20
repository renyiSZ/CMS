<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>post detail</title>
   
 
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport"
        content="width=device-width, initial-scale=1">
    <meta name="format-detection" content="telephone=no">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
	
	<link rel="stylesheet" type="text/css" href="css/message.css"/>
	
	<link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="css/admincss/global.css" media="all">
	<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
	<link rel="stylesheet" href="css/admincss/table.css" />
  
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!-- UM相关资源 -->
<link href="assets/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/umeditor/lang/zh-cn/zh-cn.js"></script>

	
  </head>
  
  <body >
  	<div class="admin-main">
    <blockquote class="layui-elem-quote">
    <button class="layui-btn layui-btn-small" onclick="javascript:history.back(-1);"> <i class="fa fa-arrow-left" aria-hidden="true">&nbsp;back</i></button>
   
    <!-- ########## -->
    
    </blockquote>
 
  		
  	<!-- ########################################################################################## -->
 
    
    <div class="messages">
		 <div class="messages_left">
			<div class="nickName">${requestScope.map.username}</div>
			<div class="headphoto"> <img src="${requestScope.map.posthead}" alt="" /> </div>
		</div>

		<div class="messages_right">
			<div class="time" >
				　[${requestScope.map.posttype}]&nbsp;&nbsp;${requestScope.map.posttitle}&nbsp;&nbsp;&nbsp;
				&nbsp;&nbsp;&nbsp;${requestScope.map.postclick}/${requestScope.map.postreply}
				&nbsp;&nbsp;${requestScope.map.posttime}
			</div>
			<div >
				${requestScope.map.postcontent}
			</div> 
			
		</div>
	</div>
    <!-- ########################################################################################## -->
    <!-- ##   reply 部分     ## -->
    
    <c:forEach items="${requestScope.list}" var="list">
        <div class="messages-reply">
		 <div class="messages_left">
			<div class="nickName">${list.replyusername}</div>
			<div class="headphoto"> <img src="${list.replyhead}" alt="${list.replyuserid}" /> </div>
		</div>

		<div class="messages_right">
			<div class="time" align="right">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				${list.replytime}&nbsp;
			</div>
			<div >
				${list.replycontent}
			</div> 
			
		</div>
	</div>
    </c:forEach>
    </br></br></br>
    <!-- ########################################################################################## -->
     <!-- ##   回复框 部分     ## -->
     <form name="formname" action="<%=path %>/AddReplyServlet" method="post">
     <div id="EditBox" class="am-g am-g-fixed">
     <input name="replycontent" type="hidden" id="replycontent">
     <input name="postuserid" type="hidden" id="postuserid" value="${requestScope.map.userid}">
     <input name="flag" type="hidden"  value="mydetail">
     <input name="postid" type="hidden" id="postid" value="${requestScope.map.postid}">
     <script type="text/plain" id="myEditor" style="width:100%;height:150px;"></script>
	 <div style="float:right;width:15%;">
	   <button id="send" type="button" class="am-btn am-btn-primary am-btn-block" style="width:100%;">reply</button>
	 </div>
	</div>
	</form>
	</br></br>
	<script type="text/javascript">

$(function(){


	//实例化编辑器
    var um = UM.getEditor('myEditor',{
    	initialContent:"content here",
    	autoClearinitialContent :true, 
    	autoHeightEnabled:false,
    	    
    	toolbar:[
            'source | undo redo | bold italic underline strikethrough | superscript subscript | forecolor backcolor | removeformat |',
            'insertorderedlist insertunorderedlist | selectall cleardoc paragraph | fontfamily fontsize' ,
            '| justifyleft justifycenter justifyright justifyjustify |',
            'link unlink | emotion'
        ]
    });
    
    
    
   
    $("#send").click(function(){
    	if (!um.hasContents()) {  // 判断消息输入框是否为空
            // 消息输入框获取焦点
            um.focus();
            // 添加抖动效果
            $('.edui-container').addClass('am-animation-shake');
            setTimeout("$('.edui-container').removeClass('am-animation-shake')", 1000);
        } else {
        	//获取输入框的内容
        	var txt = um.getContent();
        	document.formname.replycontent.value=txt;
        	
        	document.formname.submit();
            // 清空消息输入框
            um.setContent('');
            // 消息输入框获取焦点
            um.focus();
        }
    
    });
   
});
</script>
 </div>
  </body>
</html>

