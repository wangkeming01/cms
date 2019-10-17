<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

  <head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="howsun">

    <title>CMS后台管理系统</title>

    <!-- Bootstrap core CSS-->
    <link href="/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	

    <!-- Custom fonts for this template-->
    <link href="/libs/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">

    <!-- Custom styles for this template-->
    <link href="/libs/sb-admin/sb-admin.css" rel="stylesheet">
     <!-- Bootstrap core JavaScript-->
    <script src="/libs/jquery/jquery.min.js"></script>
    <script src="/libs/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript">
    	function saveOrUpdateCatetory(){
    	
    		var cid = $("#cid").val();
    		var cateid = $("#cateid").val();
    		var catename = $("#catename").val();
    		if(cid == 0){
    			alert("请选择栏目");
    			return ;
    		}
    		alert(cateid);
    		  $.post("/admin/saveOrUpdateCatetory",{"name":catename,"chid":cid,"cateid":cateid},function(data){
    			if(data){
    				alert("添加成功");
    				location = "/admin/categories"
    				return ;
    			}
    			alert("添加失败");
    		})
    		
    	}
    	
    	function deletecate(id){
    		
    		if(window.confirm("是否确认删除")){
    			$.post("/admin/delete","id="+id,function(data){
    				if(data){
    					alert("删除成功");
    					location.reload();
    					return ;
    				}
    			})
    		}
    		
    	}
    	
    	
    	function querycate(name,id,cid){
    		$("#cid").val(id);
    		$("#catename").val(name);
    		$("#cateid").val(cid);
    	}
    	
    	function queryChannels(name,id){
    		$("#cid").val(0);
    		
    		$("#catename").val("");
    	}
    	
    </script>
  </head>

  <body id="page-top">

	<!-- 后台管理系统顶部 -->
 	<jsp:include page="_inc_top.jsp"/>

    <div id="wrapper">

 		<!-- 后台管理系统左部菜单 -->
 		<jsp:include page="_inc_left.jsp"/>

     <div id="content-wrapper">

       <div class="container-fluid">
       	<h1>显示分类列表</h1>
       	<br>
       	<button class="btn btn-primary btn-xs" onclick="queryChannels()" data-toggle="modal" data-target="#myModals">
		添加分类
		</button>
		<ul type="none">
			<c:forEach items="${listChannel }" var="channel">
        		<li>
        		<span>${channel.name }
					<input class="btn btn-danger btn-primary btn-sm" type="button" value="删除">	
					<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModals">
					编辑
					</button>	
				</span>
       			<ul type="none">
        			<c:forEach items="${channel.categoryList }" var="category">
        				<li>
        					<span>${category.name }
								<input class="btn btn-danger btn-primary btn-sm" type="button" value="删除"  onclick="deletecate(${category.id})">	
								<button class="btn btn-primary btn-sm" data-toggle="modal" data-target="#myModals" onclick="querycate('${category.name}','${channel.id}','${category.id }')">
								编辑
								</button>	
							</span>
        					
        				</li>
       		</c:forEach>
       			</ul>
        		</li>
        		</c:forEach>
		</ul>
       </div>
        <!-- /.container-fluid -->
        
        	<!-- 添加|修改 --> 
		<div class="modal fade" id="myModals" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		    <div class="modal-dialog">
		        <div class="modal-content">
		            <div class="modal-header">
		                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		            </div>
		            <div class="modal-body">
		            	<input type="hidden" id="cateid">
		            	栏目:<select id="cid">
								<option value="0">请选择栏目</option>
								<c:forEach items="${listChannel }" var="channel">
									<option value="${channel.id }">${channel.name }</option>
								</c:forEach>
		            		</select>
		            	分类:<input type="text" id="catename">
		            	
		            </div>
		            <div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" onclick="saveOrUpdateCatetory()">添加</button>
		            </div>
		        </div><!-- /.modal-content -->
		    </div><!-- /.modal -->
		</div>
		
		
		
		

        <!-- Sticky Footer -->
        <footer class="sticky-footer">
          <div class="container my-auto">
            <div class="copyright text-center my-auto">
              <span>Copyright Â© Your Website 2019</span>
            </div>
          </div>
        </footer>

      </div>
      <!-- /.content-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fas fa-angle-up"></i>
    </a>
	<script type="text/javascript">
		
	</script>
	   
	
    <!-- Custom scripts for all pages-->
  </body>

</html>
