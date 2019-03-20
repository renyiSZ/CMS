
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Date" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <meta charset="UTF-8">
  <title>post</title>
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport"
        content="width=device-width, initial-scale=1">
  <meta name="format-detection" content="telephone=no">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Cache-Control" content="no-siteapp"/>
  <link rel="alternate icon" type="image/png" href="assets/i/favicon.png">
  <link rel="stylesheet" href="assets/css/amazeui.min.css"/>
  
<script src="assets/js/jquery.min.js"></script>
<script src="assets/js/amazeui.min.js"></script>
<!-- UM相关资源 -->
<link href="assets/umeditor/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="assets/umeditor/umeditor.min.js"></script>
<script type="text/javascript" src="assets/umeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body >
	
	<div style="width:90%;margin:0 auto">
	<form name="formname" action="<%=path %>/AddPostServlet" method="post">
	<div id="main">
	<!-- 内容发送区域 -->
	<div id="EditBox" class="am-g am-g-fixed">
	
	</br></br></br><input type="text" id="title" name="title" style="width:60%;" placeholder="input title here">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<select name="posttype">
	<option value="other" > --choose--</option> 
	<option value="Agronomy">Agronomy</option> 
	<option value="Economics">Economics</option> 
	<option value="Engineering">Engineering</option> 
	<option value="History">History</option> 
	<option value="Law">Law</option> 
	<option value="Literature">Literature</option> 
	<option value="Management">Management</option> 
	<option value="Medical Science">Medical Science</option> 
	<option value="Military Science">Military Science</option> 
	<option value="Pedagogy">Pedagogy</option> 
	<option value="Philosophy">Philosophy</option> 
	<option value="Science">Science</option> 
	<option value="other">Other</option> 
	</select> 

	</br></br>
	<!--style给定宽度可以影响编辑器的最终宽度-->
	<input name="postcontent" type="hidden" id="postcontent">
	<script type="text/plain" id="myEditor" style="width:100%;height:150px;"></script>
	 <div style="float:right;width:20%;">
	   <button id="send" type="button" class="am-btn am-btn-primary am-btn-block" style="width:100%;">send</button>
	 </div>
	</div>
   
   </div>
   </div>
   </form>
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
    
    var title = document.getElementById("title");
   
	
    
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
        	//var date=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        	//构建一个标准格式的JSON对象
        	//var date=new date();
        	//发送消息
        	///////////////////////////////
        	document.formname.postcontent.value=txt;
        	document.formname.submit();
        	
            // 清空消息输入框
            um.setContent('');
            
            
            // 消息输入框获取焦点
            um.focus();
        }
    
    });  
    
});
</script>

</body>
</html>
