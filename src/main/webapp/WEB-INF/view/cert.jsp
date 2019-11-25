<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<title>证书查询</title>
	<link rel="stylesheet" type="text/css" href="<c:url value="/resource/js/bootstrap/css/bootstrap.min.css"/>" />
	<script type="text/javascript" src="<c:url value="/resource/js/jquery/jquery-1.11.1.min.js" />"></script>
	<script type="text/javascript" src="<c:url value="/resource/js/bootstrap/js/bootstrap.min.js" />"></script>
	<script type="text/javascript">
		$(function(){
			$("#valid").click(function(){
				//alert($("#certCode").val());
			});
			if("${certCode}"){
				$("#valid").click();
			}
		});
	</script>
</head>
<body>
<div class="jumbotron">
	<div class="container">
	  <h2>说明：在输入框中输入验证码，点击验证按钮验证证书是否有效！</h2>
	  <div class="input-group">
	      <input id="certCode" type="text" class="form-control" value="${certCode}" placeholder="请输入证书验证码...">
	      <span class="input-group-btn">
	        <button id="valid" class="btn btn-default" type="button">验证</button>
	      </span>
	    </div><!-- /input-group -->
	</div>
</div>
</body>
</html>