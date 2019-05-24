<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%pageContext.setAttribute("newline", "\n");%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${PATH }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp"/>
		<div id="content">
			<div id="board" class="board-form">
				<table class="tbl-ex">
					<input type="hidden" value="${vo.groupNo }" name="groupNo"/>
					<input type="hidden" value="${vo.orderNo }" name="orderNo"/>
					<input type="hidden" value="${vo.depth }" name="depth"/>
					<tr>
						<th colspan="4">글보기</th>
					</tr>
					<tr>
						<td class="label">제목</td>
						<td>${vo.title }</td>
					</tr>
					<tr>
						<td class="label">작성자</td>
						<td>${vo.writer }</td>
						<td class="label">작성일</td>
						<td>${vo.writeDate }</td>
					</tr>
					<tr>
						<td class="label">첨부파일</td>
						<td colspan="3">
							<a href="${PATH }/board/fileDown?downUrl=${fileVo.url }&oriName=${fileVo.oriName }">${fileVo.oriName }<sub>&nbsp; (${fileVo.size }KB)</sub></a>
						</td>
					</tr>
					<tr>
						<td class="label">내용</td>
						<td colspan="3">
							<div class="view-content">
								${vo.content }
							</div>
						</td>
					</tr>
				</table>
				<div class="bottom">
					<a href="${PATH }/board/list">글목록</a>
					<c:if test="${authUser.no eq vo.writerNo }">
					<a href="${PATH }/board/update?no=${vo.no }&writerNo=${authUser.no }">글수정</a>
					</c:if>
					<a href="${PATH }/board/reply?no=${vo.no }&writerNo=${authUser.no }&groupNo=${vo.groupNo}&orderNo=${vo.orderNo}&depth=${vo.depth}">답글달기</a>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>