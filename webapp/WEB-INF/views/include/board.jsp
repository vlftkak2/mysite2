<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="board">
	<form id="search_form" action="/mysite2/board/list" method="get">
		<input type="text" id="kwd" name="kwd" value="${map.keyword }"> <input
			type="submit" value="찾기">
	</form>
<h4>
전체 글수 : <span>${map.totalCount }</span>
</h4>
	<table class="tbl-ex">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>조회수</th>
			<th>작성일</th>
			<th>&nbsp;</th>
		</tr>
	
		<c:set var="firstIndex" value="${map.totalCount - (map.currentPage - 1)*map.sizeList }" />
		<c:forEach var='vo' items='${map.list}' varStatus='status'>
			<tr>
				<td>${firstIndex - status.index }</td>	
				<td style="text-align:left; padding-left:${(vo.depth-1)*20}px">
	                <c:if test='${vo.depth > 1 }'>
    		          >>>
            	    </c:if>
					<a href="/mysite2/board/viewform?no=${vo.no}">${vo.title }</a>
				</td>
				<td>${vo.name }</td>
				<td>${vo.count }</td>
				<td>${vo.date }</td>
				<td><c:choose>
						<c:when test='${not empty authUser && authUser.no == vo.userNo }'>

							<a href="/mysite2/board/delete?no=${vo.no}" class="del">삭제</a>
						</c:when>
						<c:otherwise>
					&nbsp;
					</c:otherwise>
					</c:choose></td>
			</tr>
		</c:forEach>
	</table>

	<!-- begin:paging -->
	<div class="pager">
		<ul>
			<c:if test="${map.prevPage > 0 }">
				<li><a href="/mysite2/board/list?p=${map.prevPage }">◀</a></li>
			</c:if>
			<c:forEach begin='${map.firstPage }' end='${map.lastPage }' step='1' var='i'>
				<c:choose>
					<c:when test='${map.currentPage == i }'>
						<li class="selected">${i }</li>
					</c:when>

					<c:when test='${i > map.pageCount }'>
						<li>${i }</li>
					</c:when>

					<c:otherwise>
						<li><a href="/mysite2/board/list?p=${i }">${i }</a></li>
					</c:otherwise>
				</c:choose>
			</c:forEach>

			<c:if test='${map2.nextPage > 0 }'>
				<li><a href="/mysite2/board/list?p=${map.nextPage }">▶</a></li>
			</c:if>
		</ul>
	</div>

	<!-- end:paging -->
	<c:choose>
		<c:when test='${empty authUser }'>

	&nbsp;		
	</c:when>
		<c:otherwise>
			<div class="bottom">

				<a href="/mysite2/board/writeform?userno=${authUser.no}"
					id="new-book">글쓰기</a>
			</div>

		</c:otherwise>
	</c:choose>
</div>