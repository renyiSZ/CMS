<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.kc.factory.DAOFactory"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Edit Class</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	  <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all">
		<link rel="stylesheet" href="css/admincss/global.css" media="all"/>
		<link rel="stylesheet" href="plugins/font-awesome/css/font-awesome.min.css">
		<link rel="stylesheet" href="css/admincss/table.css"/>
  </head>
  
  <body>
   <div class="admin-main">
   			<blockquote class="layui-elem-quote">
				
				<a href="javascript:;"  class="layui-btn layui-btn-small"  id="delete">
					<i class="fa fa-trash-o" aria-hidden="true"></i> delete
				</a>
				<a href="javascript:;" class="layui-btn layui-btn-small" id="search">
					<i class="layui-icon">&#xe615;</i> search
				</a>
			</blockquote>
   <fieldset class="layui-elem-field">
			<legend>Classes</legend>
				<div class="layui-field-box">
				
 <%
  	 String sql= "select * from kcdesc";
   	 List list=  DAOFactory.getClassInfoDAOInstance().getClass(sql); 
  	 if(list.size()>0){
 %>
					<table class="site-table table-hover">
						<thead>
							<tr>
								<th><input type="checkbox" id="selected-all"></th>
								<th>No.</th>
								<th>Class ID</th>
								<th>Class Name</th>
								<th>Class Type</th>
								<th>Semester</th>
								<th>Teacher ID</th>
								<th>Credit</th>
								<th>Operation</th>
							</tr>
						</thead>

						<tbody>
<%
		for(int i=0;i<list.size();i++){
			HashMap m=(HashMap)list.get(i);
 %>
							<tr>
								<td><input type="checkbox" name="checkBox" value="<%=m.get("kcid") %>"></td>
								<td><%=i+1 %></td>
								<td>
									<%=m.get("kcid") %>
								</td>
								<td><%=m.get("kcname") %></td>
								<td><%=m.get("kctype") %></td>
								<td><%=m.get("semester") %></td>
								<td><%=m.get("teacherid") %></td>
								<td ><%=m.get("credit") %></td>
								
								<td>
									<a href="<%=path %>/EditClass?classwhat=edit&kcid=<%=m.get("kcid") %>" class="layui-btn layui-btn-mini">
									<i class="fa fa-edit " aria-hidden="true"></i>
									</a>
									<a href="<%=path %>/DeleteClass?deleteNumber=single&kcid=<%=m.get("kcid")%>" data-id="1" data-opt="del" class="layui-btn layui-btn-danger layui-btn-mini">
									<i class="fa fa-trash-o" aria-hidden="true"></i> 
									</a>
								</td>
							</tr>
<%      
  	        } 	 
  	 }
  	 else{
  	   out.print("No class");
  	 }
 %>							
					</tbody>

			</table>
		</div>
	</fieldset>
   </div>
   <script type="text/javascript" src="plugins/layui/layui.js"></script>
		<script>
			layui.config({
				base: 'plugins/layui/modules/'
			});

			layui.use(['icheck', 'laypage','layer'], function() {
				var $ = layui.jquery,
					laypage = layui.laypage,
					layer = parent.layer === undefined ? layui.layer : parent.layer;
				$('input').iCheck({
					checkboxClass: 'icheckbox_flat-green'
				});
				$('#search').on('click', function() {
					parent.layer.alert('你点击了搜索按钮')
				});

				$('.site-table tbody tr').on('click', function(event) {
					var $this = $(this);
					var $input = $this.children('td').eq(0).find('input');
					$input.on('ifChecked', function(e) {
						$this.css('background-color', '#EEEEEE');
					});
					$input.on('ifUnchecked', function(e) {
						$this.removeAttr('style');
					});
					$input.iCheck('toggle');
				}).find('input').each(function() {
					var $this = $(this);
					$this.on('ifChecked', function(e) {
						$this.parents('tr').css('background-color', '#EEEEEE');
					});
					$this.on('ifUnchecked', function(e) {
						$this.parents('tr').removeAttr('style');
					});
				});
				$('#selected-all').on('ifChanged', function(event) {
					var $input = $('.site-table tbody tr td').find('input');
					$input.iCheck(event.currentTarget.checked ? 'check' : 'uncheck');
				});
				
				$('#delete').click(function(){
						deleteSeletedRecords();
				});
				
			});
			
		function deleteSeletedRecords(){  
         	var chckBox= document.getElementsByName('checkBox');  
       		var num = chckBox.length;  
         	var ids = new Array(); 
         	var i=0; 
	        for(var index =0 ; index<num ; index++){  
             	if(chckBox[index].checked){  
                 ids[i] = chckBox[index].value; 
                 i++;               
             	}  
         	}  
         	if(ids!=""){  
             if(window.confirm("Are you sure to delete? id:"+ ids)){  
                 location.href="<%=path%>/DeleteClass?deleteNumber=many&kcid=" + ids
             }  
         	}else{  
             alert("ids"+ids);  
             }  
         	} 
			
		</script>
  </body>
</html>
