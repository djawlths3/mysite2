<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="content">
			<div id="guestbook" class="delete-form">
				<form method="post" action="${pageContext.servletContext.contextPath}/guestbook/delete">
					<input type="hidden" name="a" value="delete"> <input
						type='hidden' name="no" value="${no }">
					<label>비밀번호</label> <input type="password" name="password">
					<input type="submit" value="확인">
				</form>
				<a href="/WEB-INF/views/guestbook/list.jsp">방명록 리스트</a>
			</div>
		</div>
				<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value='guestbook'></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>