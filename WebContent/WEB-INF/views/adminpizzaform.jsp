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
	<form:form action="change-pizza${id }.htm" modelAttribute="Pizza" method="post" enctype="multipart/form-data">
		<p>Form</p>
		<div style="display: ${update==0? 'none':''}">
			<label>Mã bánh</label>
			<form:input path="IDPizza" readonly="${update==1? true: false }"/>
		</div>
		<br>
		<div>
			<label>Tên bánh</label>
			<form:input path="Name" />
		</div>
		<br>
		<div>
			<label>Mô tả</label>
			<form:input path="Descript"/>
		</div>
		<br>
		<div>
			<label>Hình ảnh</label>
			<form:input style="display:${update==1?'inline-block':'none' };" path="Img" readonly="true"/>
			<input type="file" name="image">
		</div>
		<br>
		<div>
			<label>Giá</label>
			<%-- <form:input path="PIZZAPRICES"/> --%>
			<input name="pizzaprice" value="${pizzaprice }">
		</div>
		<br>
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