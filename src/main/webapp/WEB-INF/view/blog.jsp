<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>豪哥博客</title>

    <!-- Bootstrap -->
    <link rel="stylesheet" type="text/css" href="/libs/bootstrap/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="/css/blog.css"/>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <style type="text/css">
    </style>
  </head>
  <body>
    <jsp:include page="/WEB-INF/inc/top.jsp"></jsp:include>
	
	<!-- 横幅 -->
	<div class="container">
		<div class="row">
			<div class="col-xs-12 banner">
			</div>
		</div>
	</div>
	
	<br/>
	<!-- 主体内容区 -->
	<div class="container">
		<div class="row">
			<div class="col-md-8">
				
				<!-- 文章 -->
				<h2 align="center">${blog.title}</h2>
				<div class="text-center">
					作者：${blog.author.nickname}&nbsp;&nbsp;&nbsp;&nbsp;
					浏览：${blog.hits}
				</div>
				<hr/>
				<div class="content">
					${blog.content}
				</div>
				<div class="text-right">发布时间：<fmt:formatDate value="${blog.created}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
				
				<hr/>
				
				<h4>最新评论</h4>
				<div class="comments" id="comments">
					<c:forEach items="${page.list}" var="comment">
						<div class="media">
						  <div class="media-left">
						    <a href="#">
						      <img class="media-object" src="/images/default_avatar.png" style="max-height: 50px" alt="...">
						    </a>
						  </div>
						  <div class="media-body">
						    <h4 class="media-heading">${comment.author.nickname}：</h4>
						    <p>${comment.content}</p>
						    <p>评论时间：<fmt:formatDate value="${comment.created}" pattern="yyyy-MM-dd HH:mm:ss"/></p>
						  </div>
						</div>
					</c:forEach>
					<a href="/article/${blog.id }?pageNum=${page.pageNum > 1 ? page.pageNum - 1 : page.pageNum}">上一页</a>
					<a href="/article/${blog.id }?pageNum=${page.pageNum < page.pages ? page.pageNum + 1 : page.pageNum}">下一页</a>
				</div>
				<div>
					<form id="comment" name="comment" method="post">
						<textarea id="content" name="content" cols="3" class="form-control" placeholder="${_LOGIN_USER_.nickname}发表评论"></textarea>
						<button type="submit" class="btn btn-info btn-block">发表</button>
					</form>
				</div>
				<br/>
				<div align="center"><a href="/blog/${blog.id}?lookId=-1">上一篇：</a><a href="/blog/${blog.id}?lookId=1">下一篇：</a></div>
			</div>
			<div class="col-md-4">
				<%-- <div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">点击量排行榜</h3>
				  </div>
				  <div class="panel-body">
				  	<c:forEach items="${hitsList}" var="blog">
					    <p><a href="/blog/${blog.id}">${blog.title} (${blog.hits })</a></p>
				  	</c:forEach>
				  </div>
				</div> --%>
				
				<div class="panel panel-default">
				  <div class="panel-heading">
				    <h3 class="panel-title">评论量排行榜</h3>
				  </div>
				  <div class="panel-body">
				  	<c:forEach items="${commentList}" var="blog">
					    <p><a href="/blog/${blog.id}">${blog.title} (${blog.commentNum })</a></p>
				  	</c:forEach>
				  </div>
				</div>
			</div>
		</div>
	</div>
	
	<jsp:include page="/WEB-INF/inc/footer.jsp"/>
	
	<script type="text/javascript">
	
		var authorNickname = "${_LOGIN_USER_.nickname}";
		
		var template = "<div class=\"media\">"
		  +"<div class=\"media-left\">"
		  +"<a href=\"#\">"
		  +"<img class=\"media-object\" src=\"/images/default_avatar.png\" style=\"max-height: 50px\" alt=\"...\">"
		  +"</a>"
		  +"</div>"
		  +"<div class=\"media-body\">"
		  +"<h4 class=\"media-heading\">"+${_LOGIN_USER_.nickname}+"：</h4>"
		  +"<p>{{comment_content}}</p>"
		  +"<p>评论时间：刚刚</p>"
		  +"</div>"
		  +"</div>";
		  
		$(document).ready(function(){
			if(authorNickname.length==0){
				$("#content").attr("disabled", "disabled").attr("placeholder", "请登录后发表评论");
			}
			
			$("#comment").submit(function(){
				var content = $("#content").val();
				if(content.length==0){
					alert('请填写评论内容');
					return false;
				}
				
				$.ajax({
					url:'/my/comment/' + '${blog.id}',
					type:'post',
					data:$(this).serialize(),
					error: function(){alert('发表失败');},
					success:function(data){
						if(data){
							var comments = $("#comments").html();
							$("#comments").html(template.replace("{{comment_content}}", content) + comments);
							$("#content").val("");
							location="/article/"+'${blog.id}';
						}else{
							alert("发表失败");
						}
					}
					
				});
				return false;
			});
		}); 
	</script>
  </body>
</html>