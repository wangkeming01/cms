<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>发布博客</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap/css/bootstrap.min.css"/>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.9/summernote.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/css/cms.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    </style>
    <script type="text/javascript" src="/libs/jquery/jquery.min.js"></script>
    <script type="text/javascript">
    	function change(id){
    		var str = "<option value='0'>请选择</option>";
    		$.post("/my/queryCategoryById","id="+id,function(data){
    			for ( var i in data) {
    				str += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
    			}
        		$("#cate").html(str);
    		})
    	}
    	
    	$(function(){
    		var id = $("[name='channel.id']").val();
    		var str = "<option value='0'>请选择</option>";
    		$.post("/my/queryCategoryById","id="+ id,function(data){
    			for ( var i in data) {
    				str += "<option value='"+data[i].id+"'>"+data[i].name+"</option>";
    			}
        		$("#cate").html(str);
        		$("#cate").val('${blog.category.id}');
    		})
    	})
    </script>
  </head>
  <body>
    <jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
	
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 my_banner">
			</div>
		</div>
	</div>
	<br/>
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-3">
				<jsp:include page="/WEB-INF/inc/my_left.jsp"><jsp:param value="blog" name="module"/></jsp:include>
			</div>
			<div class="col-md-9">
				<div class="panel panel-default">
				  <div class="panel-body">
				    	<h1>发布博客</h1>
				    	<hr/>
				    	<form:form method="post" enctype="multipart/form-data" modelAttribute="blog" action="/my/blog/save" >
				    		<form:hidden path="id"/>
				    		文章标题：<form:input path="title"/>
				    				<br><br> 
				    		 栏目：<form:select path="channel.id" onchange="change(this.value)">
				    		 <form:option value="0">请选择</form:option>
				    		 		<form:options items="${channelList }" itemValue="id" itemLabel="name"/>
				    		 	</form:select>
				    		 分类：<form:select path="category.id" id="cate">
				    		 <form:option value="0">请选择</form:option>
				    		 </form:select>
				    		 <br><br>
				    		 摘要：<form:textarea path="summary" rows="3" cols="30"></form:textarea><br><br>
				    		 内容：<form:textarea path="content" rows="3" cols="30"></form:textarea><form:errors path="content"></form:errors><br><br>
				    		 照片：<input type="file" name="file"><br><br>
				    		 <input type="checkbox" name="hots" <c:if test="${blog.hot=true}">checked="checked"</c:if> >是否上热门
				    		 <form:button>提交</form:button>
				    	</form:form>
				  </div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/inc/footer.jsp"/>
	
	
	
  </body>
</html>