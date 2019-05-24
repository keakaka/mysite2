<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			<div id="board">
				<form id="search_form" action="" method="post">
					<input type="text" id="kwd" name="kwd" value="">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>		
					<c:set var='count' value='${fn:length(list) }'/>
					<c:forEach items='${list }' var='vo' varStatus='status'>
					<c:choose>
					<c:when test="${vo.depth != 0 }">
					<tr>
						<td>${count-status.index }</td>
						<c:if test="${vo.status eq 'on' }">
						<td style="text-align:left; padding-left:${20*vo.depth}px"><img width="20px" height="20px" src="${PATH }/assets/images/reply.png"/><a href="${PATH }/board/view?no=${vo.no}">${vo.title }</a></td>
						</c:if>
						<c:if test="${vo.status eq 'off' }">
						<td style="text-align:left; padding-left:${20*vo.depth}px">삭제된 게시물입니다.</td>
						</c:if>
						<td>${vo.writer }</td>
						<td>${vo.hit }</td>
						<td>${vo.writeDate }</td>
						<c:if test="${authUser.no eq vo.writerNo && vo.status ne 'off' }">
						<td><a href="${PATH }/board/delete?no=${vo.no }" class="del">삭제</a></td>
						</c:if>
					</tr>
					</c:when>
					<c:otherwise>
					<tr>
						<td>${count-status.index }</td>
						<c:if test="${vo.status eq 'on' }">
						<td style="text-align:left; padding-left:${20*vo.depth}px"><a href="${PATH }/board/view?no=${vo.no}">${vo.title }</a></td>
						</c:if>
						<c:if test="${vo.status eq 'off' }">
						<td style="text-align:left; padding-left:${20*vo.depth}px">삭제된 게시물입니다.</td>
						</c:if>
						<td>${vo.writer }</td>
						<td>${vo.hit }</td>
						<td>${vo.writeDate }</td>
						<c:if test="${authUser.no eq vo.writerNo && vo.status ne 'off' }">
						<td><a href="${PATH }/board/delete?no=${vo.no }" class="del">삭제</a></td>
						</c:if>
					</tr>
					</c:otherwise>
					</c:choose>
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->
				
				<div class="bottom">
					<c:choose>
					<c:when test='${empty authUser }'>
						<a href="${PATH }/user/login" id="new-book">글쓰기는 로그인 필요 </a>
					</c:when>
					<c:otherwise>
						<a href="${PATH }/board/write" id="new-book">글쓰기</a>
					</c:otherwise>
					</c:choose>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp"/>
		<c:import url="/WEB-INF/views/includes/footer.jsp"/>
	</div>
</body>
</html>