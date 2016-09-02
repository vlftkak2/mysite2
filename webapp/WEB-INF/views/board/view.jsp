<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="kr.ac.sungkyul.mysite.vo.BoardVo"%>
<%@page import="java.util.List"%>
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
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<tr>
						<th colspan="2">글보기</th>
					</tr>
					
					<tr>
						<td class="label">제목</td>
						<td>${BoardVo.title }</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td>
							<div class="view-content">${BoardVo.content }</div>
						</td>
					</tr>
				
				</table>

				<div class="bottom">
				
				<td>
				<c:choose>
					<c:when test='${not empty authUser && authUser.no == BoardVo.userNo }'>

					<a href="/mysite2/board/modifyform?no=${BoardVo.no }">글수정</a>
					<a href="/mysite2/board/list">글목록</a>
					<a href="/mysite2/board/replyform?no=${BoardVo.no }">답글 달기</a>
					</c:when>
				    
					<c:when test='${empty authUser }'>
					<a href="/mysite2/board/list">글목록</a>				
					</c:when>
					
					<c:otherwise>
					<a href="/mysite2/board/list">글목록</a>
					<a href="/mysite2/board/replyform?no=${BoardVo.no }">답글 달기</a>
					
					</c:otherwise>
				</c:choose>
				</td>
				</div>
			</div>
		</div>
		<jsp:include page="/WEB-INF/views/include/navi.jsp" />

		<jsp:include page="/WEB-INF/views/include/footer.jsp" />

	</div>
</body>
</html>