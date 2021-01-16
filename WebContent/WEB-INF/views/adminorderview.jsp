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
<link href="<c:url value="resources/css/gridsys.css" />"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap"
	rel="stylesheet">

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

.table__container {
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
				<th>IDOrder</th>
				<th>Order Time</th>
				<th>Address</th>
				<th>Thanh toán </th>
				<th>Status</th>
				<th>Lựa chọn</th>
			</tr>
			<c:forEach items="${orderlist}" var="item">
				<tr>
					<td>${item.getIDOrder() }</td>
					<td>${item.getOrderTime() }</td>
					<td>${item.getDelivery() }</td>
					<td>${item.getPayment() }</td>
					<td><c:choose>
							<c:when test="${item.getOrderstatus() == 0}">Đã hoàn tất</c:when>
							<c:when test="${item.getOrderstatus() == 1}">Trong giỏ hàng</c:when>
							<c:when test="${item.getOrderstatus() == 2}">Chờ xác nhận</c:when>
							<c:when test="${item.getOrderstatus() == 3}">Đã thanh toán</c:when>
							<c:when test="${item.getOrderstatus() == 4}">Ship cod</c:when>
							<c:when test="${item.getOrderstatus() == 5}">Hủy đơn</c:when>
						</c:choose></td>
					<td>
						<a style="color: blue; ${item.getOrderstatus() ==2? 'display:block': 'display: none'}" href="accept-order${item.getIDOrder() }.htm">Xác nhận</a>
						<a style="color: blue; ${sessionScope['admin'] ==2? 'display:block': 'display: none'}" href="finish-order${item.getIDOrder() }.htm">Đã giao</a>						 
						<a style="color: red; ${item.getOrderstatus() ==2? 'display:block': 'display: none'}" href="cancel-order${item.getIDOrder() }.htm">Hủy</a>
						<a style="color: var(- -bprimary);" href="view-order${item.getIDOrder() }.htm">Xem</a>
					</td>
				</tr>
			</c:forEach>
		</table>

	</div>
	<!-- 	<a style="background-color: var(--primary); padding: 10px 20px; display: inline-block;" href="change-account0.htm">Thêm mới</a> -->
	<jsp:include page="footer.jsp" />
</body>
</html>