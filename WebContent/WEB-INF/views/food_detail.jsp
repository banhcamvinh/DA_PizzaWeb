<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delicous Pizza</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
     <link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/food_detail.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b97db328cf.js" crossorigin="anonymous"></script>
    <script src="<c:url value="resources/js/food_detail.js" />"></script>
</head>
<body>
		<jsp:include page="header.jsp"/>


	    <section class="${type== 'pizza'? 'food__detail pizza' : 'food__detail' }">
        <div class="grid wide">
            <h2 class="main-title">Chi tiết</h2>
            <div class="row">
                <div class="food__detail-des col l-6 m-6 c-12">
                    <h2>${product.getName() }</h2>
                    <p>${product.getDescript() }.</p>
                    <div class="food__detail-img">
                        <img src="${product.getImg()}" alt="">
                    </div>
                    <span style="margin-top: 20px;">Giá</span>
                    <span class="pizzaprice"> ${productprice} VNĐ</span>
                </div>
                <div class="food__detail-option col l-6 m-6 c-12">
                	<c:if test="${type == 'pizza' }">
                		<c:set var="formpizzavalue" value="menu-${type }-${product.getIDPizza() }.htm"></c:set>
                	</c:if>
                	<c:if test="${type != 'pizza' }">
                		<c:set var="formvalue" value="menu-${type }-${product.getIDNonPizza() }.htm"></c:set>
                	</c:if>
                    <form action="${formvalue }" method="post">
                        <p>Chọn cỡ bánh</p>
                        <c:forEach items="${sizelist }" var="item" varStatus="loop">
                        	<c:if test="${loop.index == 0 }">
                        		<input class="size" type="radio" name="size" value="${item.getIDPizzaSize() }" checked> ${item.getSize() }
                        	</c:if>
                        	<c:if test="${loop.index != 0 }">
                        		<input class="size" type="radio" name="size" value="${item.getIDPizzaSize() }"> ${item.getSize() }
                        	</c:if>        	
                        </c:forEach>
                        <p>Chọn đế bánh</p>
                         <c:forEach items="${curtlist }" var="item" varStatus="loop">
                        	<c:if test="${loop.index == 0 }">
                        		<input type="radio" name="curt" value="${item.getIDPizzaCurt() }" checked> ${item.getCurt() }
                        	</c:if>
                        	<c:if test="${loop.index != 0 }">
                        		<input type="radio" name="curt" value="${item.getIDPizzaCurt() }"> ${item.getCurt() }
                        	</c:if>        	
                        </c:forEach>
                        <p>Tùy chọn thêm</p>
                         <c:forEach items="${optionlist }" var="item" varStatus="loop">
                        	<c:if test="${loop.index == 0 }">
                        		<input class="option" type="radio" name="option" value="${item.getIDPizzaOption() }" checked> ${item.getOption() }
                        	</c:if>
                        	<c:if test="${loop.index != 0 }">
                        		<input class="option" type="radio" name="option" value="${item.getIDPizzaOption() }"> ${item.getOption() }
                        	</c:if>        	
                        </c:forEach>
                        <div class="total-price">
                            <ul class="price-list">
                            
                                <li class="price-item">
                                    <div class="price-item__title">Size</div>
                                     <c:forEach items="${sizelist }" var="item" varStatus="loop">
                                     	<c:forEach items="${item.getPIZZASIZEPRICES()}" var="itemm" >
                                     		<c:set var="price" value="sizeprice${item.getIDPizzaSize()}"></c:set>
                                     			<c:if test="${requestScope[price] == itemm.getPrice()}">
                                     				<c:if test="${loop.index == 0 }">
                                     					<div class="size-price-item__value" style="display: block;"><c:out value = "${requestScope[price]}VND"/></div>
                                     				</c:if>
                                     				<c:if test="${loop.index!=0 }">
                                     					<div class="size-price-item__value" style="display: none;"><c:out value = "${requestScope[price]}VND"/></div>
                                     				</c:if>													
												</c:if>
                                     	</c:forEach>
                       				 </c:forEach>       
                                </li>
                                
                                
                                <li class="price-item">
                                    <div class="price-item__title">Tùy chọn thêm</div>
                                     <c:forEach items="${optionlist }" var="item" varStatus="loop">
                                     	<c:forEach items="${item.getPIZZAOPTIONPRICES()}" var="itemm" >
                                     		<c:set var="price" value="optionprice${item.getIDPizzaOption()}"></c:set>
                                     			<c:if test="${requestScope[price] == itemm.getPrice()}">
                                     				<c:if test="${loop.index == 0 }">
                                     					<div class="option-price-item__value" style="display: block;"><c:out value = "${requestScope[price]}VND"/></div>
                                     				</c:if>
                                     				<c:if test="${loop.index!=0 }">
                                     					<div class="option-price-item__value" style="display: none;"><c:out value = "${requestScope[price]}VND"/></div>
                                     				</c:if>													
												</c:if>
                                     	</c:forEach>
                       				 </c:forEach>       
                                </li>
                                
                                
                                <li class="price-item">
                                    <div class="price-item__title">Tổng cộng</div>
                                    <div class="price-item__value total"></div>
                                </li>
                                
                                
                            </ul>
                        </div>
                        <button class="btnord">Thêm vào giỏ hàng</button>
                    </form>
                </div>
            </div>
        </div>
    </section>
    
    <jsp:include page="footer.jsp"/>
</body>

</html>