<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Delicous Pizza</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/cart.css" />" rel="stylesheet">
<link href="<c:url value="resources/css/gridsys.css" />"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/b97db328cf.js"
	crossorigin="anonymous"></script>
</head>
<body>

	<jsp:include page="header.jsp" />

	<form action="cart.htm" method="POST">
		<section class="cart">
			<div class="grid wide">
				<h2 class="main-title">Chi tiết đơn hàng</h2>
				<ul class="cart-list">
					<c:forEach items="${orderpizzalist }" var="item">
						<li class="cart-item">
							<div class="cart-item__info">
								<div class="row">
									<div class="col l-8 m-12 c-12">
										<img style="width: 100px; height: 100px"
											src="${item.getPIZZA().getImg()}" alt="">
										<div class="cart-item__detail">
											<p class="cart-item__name">${item.getPIZZA().getName() }</p>
											<p>${item.getPIZZASIZE().getSize() }</p>
											<p>${item.getPIZZAOPTION().getOption()}</p>
											<p>${item.getPIZZACURT().getCurt() }</p>
										</div>
										<div class="car-item__price">
											<c:set var="pizzaprice"
												value="PIZZAPRICE${item.getPIZZA(). getIDPizza()}"></c:set>
											<p>${requestScope[pizzaprice]}VND</p>
											<c:set var="sizeprice"
												value="PIZZASIZEPRICE${item.getPIZZASIZE().getIDPizzaSize()}"></c:set>
											<p>${requestScope[sizeprice]}VND</p>
											<c:set var="optionprice"
												value="PIZZAOPTIONPRICE${item.getPIZZAOPTION().getIDPizzaOption()}"></c:set>
											<p>${requestScope[optionprice]}VND</p>
											<p>0</p>
										</div>
									</div>
									<div class="col l-4 m-12 c-12">
										<div class="cart-item__quantity">
											<button name="btninc">
												<a
													href="pizzabtn-dec-pizza-${item.getORDERR().getIDOrder()}-${item.getPIZZA().getIDPizza()}-${item.getPIZZASIZE().getIDPizzaSize()}-${item.getPIZZAOPTION().getIDPizzaOption()}-${item.getPIZZACURT().getIDPizzaCurt()}.htm">-</a>
											</button>
											<span>${item.getQuantity() }</span>
											<button name="btndec">
												<a
													href="pizzabtn-inc-pizza-${item.getORDERR().getIDOrder()}-${item.getPIZZA().getIDPizza()}-${item.getPIZZASIZE().getIDPizzaSize()}-${item.getPIZZAOPTION().getIDPizzaOption()}-${item.getPIZZACURT().getIDPizzaCurt()}.htm">+</a>
											</button>
										</div>
										<button class="btndel" name="btndel">
											<a
												href="pizzabtndel-pizza-${item.getORDERR().getIDOrder()}-${item.getPIZZA().getIDPizza()}-${item.getPIZZASIZE().getIDPizzaSize()}-${item.getPIZZAOPTION().getIDPizzaOption()}-${item.getPIZZACURT().getIDPizzaCurt()}.htm">Bỏ
												chọn</a>
										</button>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
					<c:forEach items="${ordernonpizzalist }" var="item">
						<li class="cart-item">
							<div class="cart-item__info">
								<div class="row">
									<div class="col l-8 m-12 c-12">
										<img style="width: 100px; height: 100px"
											src="${item.getNONPIZZA().getImg()}" alt="">
										<div class="cart-item__detail">
											<p class="cart-item__name">${item.getNONPIZZA().getName() }</p>
										</div>
										<div class="car-item__price">
											<c:set var="nonpizzaprice"
												value="NONPIZZAPRICE${item.getNONPIZZA(). getIDNonPizza()}"></c:set>
											<p>${requestScope[nonpizzaprice]}VND</p>
										</div>
									</div>
									<div class="col l-4 m-12 c-12">
										<div class="cart-item__quantity">
											<button name="btninc">
												<a href="nonpizzabtn-dec-pizza-${item.getORDERR().getIDOrder()}-${item.getNONPIZZA().getIDNonPizza()}.htm">-</a>
											</button>
											<span>${item.getQuantity() }</span>
											<button name="btndec">
												<a href="nonpizzabtn-inc-pizza-${item.getORDERR().getIDOrder()}-${item.getNONPIZZA().getIDNonPizza()}.htm">+</a>
											</button>
										</div>
										<button class="btndel" name="btndel">
											<a href="nonpizzabtndel-nonpizza-${item.getORDERR().getIDOrder()}-${item.getNONPIZZA().getIDNonPizza()}.htm">Bỏ
												chọn</a>
										</button>
									</div>
								</div>
							</div>
						</li>
					</c:forEach>
				</ul>
				<div class="cart-total">
					<p>Tổng cộng:</p>
					<p style="color: red; font-weight: bold;">${sessionScope.totalprice }
						VND</p>
				</div>
			</div>
		</section>


	</form>


	<jsp:include page="footer.jsp" />
</body>
</html>