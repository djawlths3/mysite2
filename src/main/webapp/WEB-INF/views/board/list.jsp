<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/board.css" rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
			<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
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
					<c:set var='count' value="${fn:length(list) }" scope="page"/>
					<c:forEach items='${list }' var='vo' varStatus="index">			
						<tr>
							<td>${count - index.index }</td>
							<td style="text-align:left; padding-left:${10 * vo.depth }px">
							<c:if test="${vo.depth ne 0}">
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'>							
							</c:if>
								<a href="${pageContext.servletContext.contextPath}/board/view/${vo.no}">${vo.title }</a></td>
							<td>${vo.userName }</td>
							<td>${vo.hit }</td>
							<td>${vo.regDate }</td>
							<td><a href="" class="del">삭제</a></td>
						</tr>
						<!-- 
						<tr>
							<td>1</td>
							<td style="text-align:left; padding-left:${20*2 }px">
								<img src='${pageContext.servletContext.contextPath }/assets/images/reply.png'>
								<a href="">첫 번째 글입니다.</a>
							</td>
							<td>안대혁</td>
							<td>3</td>
							<td>2015-09-25 07:24:32</td>
							<td><a href="" class="del">삭제</a></td>
						</tr>
						 -->
					</c:forEach>
				</table>
				
				<!-- pager 추가 -->
				<div class="pager">
					<ul>
						<li><a href="">◀</a></li>
						<li><a href="">1</a></li>
						<li class="selected">2</li>
						<li><a href="/board/list?p=3">3</a></li>
						<li>4</li>
						<li>5</li>
						<li><a href="">▶</a></li>
					</ul>
				</div>					
				<!-- pager 추가 -->				
				
				<div class="bottom">
				<c:if test='${!empty authUser }'>
					<a href="${pageContext.servletContext.contextPath}/board/write" id="new-book">글쓰기</a>				
				</c:if>
				</div>				
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value='board'></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>