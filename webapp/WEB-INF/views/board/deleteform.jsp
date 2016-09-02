<%@ page contentType="text/html;charset=UTF-8"%>


<%
	String no = request.getParameter("no");
	String result = request.getParameter("r");
	
%>
<!doctype html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/guestbook.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
			<div id="guestbook"  class="delete-form">
				<form method="post" name="deleteform" action="/mysite/board?a=delete">
					<input type='hidden' name="no" value="<%=no%>"> <label>비밀번호</label>
					<input type="password" name="password"> <input
						type="submit" value="확인">


					<%
						if ("false".equals(result)) {
					%>
					<p>비밀번호를 다시 입력해주세요</p>
					<%
						}
					%>

				</form>
				<a href="/mysite/board?a=listform">게시판리스트</a>


			</div>

		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp" />

		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>
</body>
</html>