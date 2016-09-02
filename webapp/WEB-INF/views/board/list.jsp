<%@page import="kr.ac.sungkyul.mysite.vo.BoardVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<jsp:include page="/WEB-INF/views/include/header.jsp" />

		<div id="content">
		<jsp:include page="/WEB-INF/views/include/board.jsp"/>
		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp" />

		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>
</body>
</html>