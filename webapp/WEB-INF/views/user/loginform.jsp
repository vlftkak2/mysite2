<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html; charset=UTF-8"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite2/assets/css/user.css" rel="stylesheet"
	type="text/css">

<c:if test='${param.r=="fail" }'>
<script>	
alert("로그인을 실패하셨습니다.")								
</script>
</c:if>
</head>
<body>
	<div id="container">
		<c:import url='/WEB-INF/views/include/header.jsp'/>

		<div id="content">
			<div id="user">
				<form id="login-form" name="loginform" method="post" action="/mysite2/user/login">
					<label class="block-label" for="email" >이메일</label> 
					<input id="email" name="email" type="text" placeholder="email" value=""> 
					<label class="block-label" placeholder="password">패스워드</label> 
					<input name="password" type="password" value="">
					
					
					<input type="submit" value="로그인">
				</form>
			</div>
		</div>
	    <c:import url='/WEB-INF/views/include/navi.jsp'/>
		<c:import url='/WEB-INF/views/include/footer.jsp'/>

	</div>
</body>
</html>