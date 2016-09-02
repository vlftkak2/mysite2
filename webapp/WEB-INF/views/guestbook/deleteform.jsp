<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
	
	<c:if test='${"fail"==param.r }'>
	<script>
		alert("비밀번호를 다시 입력해주세요");
		
	</script>
</c:if>
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
			<div id="guestbook"  class="delete-form">
				<form method="post" name="deleteform" action="/mysite2/guest/delete">
					<input type='hidden' name="no" value=${no }> <label>비밀번호</label>
					<input type="password" name="password"> 
					<input type="submit" value="확인">

                
				</form>
				<a href="/mysite2/guest/list">방명록 리스트</a>


			</div>

		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp" />

		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>
</body>
</html>