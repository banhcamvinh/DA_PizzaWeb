<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Delicious Pizza</title>
<link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/admin.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/gridsys.css" />"	rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">

<style>
table {
	border-collapse: collapse;
	width: 100%;
	text-align: center;
}

th, td {
	line-height: 25px;
	border: 1px solid black;
	padding: 5px;
}

th {
	background-color: var(--primary);
}

img {
	max-width: 100px;
	display: inline-block;
}
.table__container{
	height: 80vh;	
	overflow: auto;
}
</style>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="table__container">
		<table>
			<tr>
				<th>Tên Bánh</th>
				<th>Mô tả</th>
				<th>Hình ảnh</th>
				<th>Giá</th>
				<th></th>
			</tr>
			<c:forEach items="${pizzalist}" var="item">
				<tr>
					<td>${item.getName() }</td>
					<td>${item.getDescript() }</td>
					<td>
						<div style="text-align: center;">
							<img alt="" src="${item.getImg() }">
						</div>
					</td>
					<td><c:set var="pizzaprice"
							value="productprice${item.getIDPizza()}" />
						<p>${requestScope[pizzaprice] }VNĐ</p></td>
					<td><a style="color: blue"
						href="change-pizza${item.getIDPizza() }.htm">Sửa</a> <a
						style="color: red" href="delete-pizza${item.getIDPizza() }.htm">Xóa</a></td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<a style="background-color: var(--primary); padding: 10px 20px; display: inline-block;" href="change-pizza0.htm">Thêm mới</a>
	<jsp:include page="footer.jsp" />
</body>
</html>