<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
	pageContext.setAttribute("newline", "\n");
%>
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
			<div id="board" class="board-form">
			<form class="board-form" method="post" action="${pageContext.servletContext.contextPath }/board/reply">
				<input type = "hidden" name = "orderNo" value="${vo.orderNo}">
				<input type = "hidden" name = "depth" value="${vo.depth}">
				<input type = "hidden" name = "groupNo" value="${vo.groupNo}">
					<table class="tbl-ex">
						<tr>
							<th colspan="2">글보기</th>
						</tr>
						<tr>
							<td class="label">제목</td>
							<td>${vo.title} </td>
						</tr>
						<tr>
							<td class="label">내용</td>
							<td>
								<div class="view-content">
								${fn:replace(vo.contents,newline,'<br>') }
								</div>
							</td>
						</tr>
					</table>
					<div class="bottom">
						<a href="${pageContext.servletContext.contextPath}/board/list">글목록</a>
						<c:if test='${!empty authUser }'>
							<!-- <a href="${pageContext.servletContext.contextPath}/board/write/${vo.no}">답글</a> -->
							<input type="submit" value="답글" style="margin-left: 10px;">
						</c:if>
						<c:if test="${authUser.no eq vo.userNo}">
							<a href="${pageContext.servletContext.contextPath}/board/modify/${vo.no}">수정 </a>											
						</c:if>	
					</div>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value='board'></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>