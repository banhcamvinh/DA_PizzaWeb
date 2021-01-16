<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Delicious Pizza</title>
<link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/gridsys.css" />"	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">


<style type="text/css">
	ul{
		margin: 0;
		padding: 0;
	}
	button{
		background: hotpink;
		outline: none;
		border:none;
		padding: 10px 20px;
		transition: all 0.3s ease;
		cursor:pointer;
	}
	button:hover{
		background-color: pink;
		color:black;
	}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<form:form action="change-account${id }.htm" modelAttribute="Account">
		<p>Form</p>
		<div style="display: ${update==0? 'none':''}">
			<label>Mã số</label>
			<form:input path="IDAccount" readonly="${update==1? true: false }"/>
		</div>
		<br>
		<div>
			<label>Username</label>
			<form:input path="Username" readonly="true"/>
		</div>
		<br>
		<div>
			<label>Quyền</label>
			<form:input path="Userrole" />
		</div>
		<p> ${messerr } </p>
		<p> ${mess } </p>
		<form:errors path="*" element="ul"></form:errors>
		<button name="update">Cập nhật</button>
		<button name="refresh">Nhập lại</button>
		<button name="exit">Thoát</button>
	</form:form>
	<jsp:include page="footer.jsp" />
</body>
</html>