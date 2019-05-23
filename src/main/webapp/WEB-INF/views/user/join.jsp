<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link
	href="${pageContext.servletContext.contextPath}/assets/css/user.css"
	rel="stylesheet" type="text/css">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="content">
			<div id="user">

				<form:form modelAttribute="userVo" id="join-form" name="joinForm" method="post"
					action="${pageContext.servletContext.contextPath}/user/join">
					<label class="block-label" for="name">이름</label> 
					<input id="name" name="name" type="text" value="">
					<spring:hasBindErrors name="userVo">
					    <c:if test="${errors.hasFieldErrors('name') }">
							<p style="font-weight:bold; color:red; text-align:left; padding:0">
					            <spring:message 
						     		code="${errors.getFieldError( 'name' ).codes[0] }" 				     
						     		text="${errors.getFieldError( 'name' ).defaultMessage }" />
					        </p> 
					   </c:if>
					</spring:hasBindErrors>
					<label class="block-label" for="email">이메일</label> 
					<form:input path="email" />
					<input type="button" id="check-button" value="체크">
					<img style="display:none" id="check-image" src="${pageContext.servletContext.contextPath }/assets/images/check.png" />
					<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
						<form:errors path="email" />
					</p>
					<label class="block-label">패스워드</label> <input name="password"
						type="password" value="">

					<fieldset>
						<legend>성별</legend>
						<label>여</label> <input type="radio" name="gender" value="female"
							checked="checked"> <label>남</label> <input type="radio"
							name="gender" value="male">
					</fieldset>

					<fieldset>
						<legend>약관동의</legend>
						<input id="agree-prov" type="checkbox" name="agreeProv" value="y">
						<label>서비스 약관에 동의합니다.</label>
					</fieldset>

					<input type="submit" value="가입하기">

				</form:form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp">
			<c:param name="menu" value='main'></c:param>
		</c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>
<script>
	$(function() {
		$('#email').change(function() {
			$('#chekImg').hide();
			$('#check-button').show();
		})

		$('#check-button').click(function() {
			var email = $('#email').val();
			if (email == '') {
				alert('이메일을 채워주세요');
				return;
			}
			$.ajax({
				url : '/mysite2/user/api/checkemail?email=' + email,
				type : 'get',
				dataType : 'json',
				data : '',
				success : function(response) {
					if (response.result != 'success') {
						console.log(response.message);
						return;
					}
					if (response.data == true) {
						alert('존재하는 아이디 입니다');
						$('email').focus();
					}

				},
				error : function(xhr, err) {
					console.error("error:" + err)
				}
			});
		})
	});
</script>