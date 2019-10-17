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

  </head>

  <body id="page-top">

	<!-- 后台管理系统顶部 -->
 	<jsp:include page="_inc_top.jsp"/>

    <div id="wrapper">

 		<!-- 后台管理系统左部菜单 -->
 		<jsp:include page="_inc_left.jsp"/>

      <div id="content-wrapper">

        <div class="container-fluid">

          <!-- Breadcrumbs-->
       <table>
       		<tr>
       			<td>文章编号</td>
       			<td>文章标题</td>
       			<td>文章内容</td>
       			<td>发布人</td>
       			<td>发布时间</td>
       			<td>审核</td>
       			<td>操作</td>
       		</tr>
       		<c:forEach items="${articleList }" var="i">
       		<tr>
       			<td>${i.id }</td>
       			<td>${i.title }</td>
       			<td>${i.content }</td>
       			<td>${i.author.nickname }</td>
       			<td>
       				${i.status == 1 ?"已审核":i.status == 0 ? "不通过":"未审核" }
       			</td>
       			<td>
       				<fmt:formatDate value="${i.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
       			</td>
       			<td>
       			
					<c:choose>
						<c:when test="${i.status == null }">
	       				<input type="button" class="btn btn-success" style="font-size: x-small;" value="通过" onclick="success(${i.id})">
						<input type="button" class="btn btn-danger" style="font-size: x-small;" value="未通过">
						</c:when>
						<c:when test="${i.status == 0 }">
	       				<input type="button" class="btn btn-success" style="font-size: x-small;" value="通过">
						<input type="button" class="btn btn-danger" style="font-size: x-small;" value="未通过">
						</c:when>
						<c:otherwise>
	       				<input type="button" class="btn btn-success" style="font-size: x-small;" value="上热门">
						<input type="button" class="btn btn-danger" style="font-size: x-small;" value="撤销热门">
						</c:otherwise>
					</c:choose>
				</td>
       		</tr>
       		</c:forEach>
       </table>
   
        </div>
        <!-- /.container-fluid -->

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

    <!-- Bootstrap core JavaScript-->
    <script src="/libs/jquery/jquery.min.js"></script>
    <script src="/libs/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="/libs/sb-admin/sb-admin.min.js"></script>
    <script type="text/javascript" src="/libs/jquery/jquery.min.js"></script>
    <script type="text/javascript">
    	function success(id){
    		$.post("/admin/success","id="+id,function(data){
    			if(data){
    				location = "/admin/toArticle";
    			}
    		})
    	}
    </script>
  </body>

</html>
