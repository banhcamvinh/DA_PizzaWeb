<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<base href="${pageContext.servletContext.contextPath}/">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Delicious Pizza</title>
    <link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/home.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/homeres.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b97db328cf.js" crossorigin="anonymous"></script>
	
</head>
<body>
	<%-- <%@ include file="header.jsp" %> --%>
	<jsp:include page="header.jsp"/>

	    <div class="home-section-1 section--slide" > 
        <div class="container">
            <h1>Delicious Pizza</h1>
            <p>Thưởng thức bữa ăn tuyệt vời</p>
        </div>
        <div class="section--slide__overlay" ></div>
    </div>
    <div class="home-section-2">
        <div class="grid wide">
            <ul class="row home-secton-2__list">
                <li class="home-section-2__item col l-4 m-4 c12">
                    <div class="home-section-2__item__icon">
                        <i class="fas fa-pizza-slice"></i>
                    </div>
                    <div class="home-section-2__item__content">
                        <h3>Healthy Food</h3>
                        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
                    </div>
                </li>
                <li class="home-section-2__item col l-4 m-4 c12">
                    <div class="home-section-2__item__icon">
                        <i class="fas fa-people-carry"></i>
                    </div>
                    <div class="home-section-2__item__content">
                        <h3>Fast Delivery</h3>
                        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
                    </div>
                </li>
                <li class="home-section-2__item col l-4 m-4 c12">
                    <div class="home-section-2__item__icon">
                        <i class="fas fa-concierge-bell"></i>
                    </div>
                    <div class="home-section-2__item__content">
                        <h3>Original Food</h3>
                        <p>Even the all-powerful Pointing has no control about the blind texts it is an almost unorthographic.</p>
                    </div>
                </li>
            </ul>
        </div>
    </div>
    <div class="home-section-3 section--slide">
        <div class="container">
            <div class="row">
                <div class="col l-4 m-4 c-12">
                    <h2>Khuyến Mãi</h2>
                    <a href="#" class="home-section-3__btn">Xem Ngay</a>
                </div>
                <div class="col l-4 m-4 c-12 center">
                    <h2>Menu Món Ăn</h2>
                    <a href="menu-pizza-page-1-.htm" class="home-section-3__btn">Xem Ngay</a>
                </div>
                <div class="col l-4 m-4 c-12">
                    <h2>Tin tức</h2>
                    <a href="#" class="home-section-3__btn">Xem Ngay</a>
                </div>
            </div>
        </div>
        <div class="section--slide__overlay"></div>
    </div>
    <div class="home-section-4">
        <div class="row no-gut">
            <div class="col l-6 m-6 c-12 home-section-4__img">
                <img src="resources/img/section-4.jpg" alt="">
            </div>
            <div class="col l-6 m-6 c-12 home-section-4__content">
                <div class="home-section-4__des">
                    <input type="checkbox" id="doctiep" style="display: none;">
                    <h3>WELCOME TO <span>DELICIOUS PIZZA</span> A RESTAURANT</h3>
                    <P>On her way she met a copy. The copy warned the Little Blind Text, that where it came from it would have been rewritten a thousand times and everything that was left from its origin would be the word "and" and the Little Blind Text should turn around and return to its own, safe country. But nothing the copy said could convince her and so it didn’t take long until a few insidious Copy Writers ambushed her, made her drunk with Longe and Parole and dragged her into their agency, where they abused her for their.</P>
                    <label class="l-dn m-db c-db doctiep" for="doctiep">Đọc tiếp</label>
                </div>
            </div>
        </div>
    </div>
	
	
	
	
	
	
	
	
	
	<%-- <%@ include file="footer.jsp" %> --%>
	<jsp:include page="footer.jsp"/>
</body>
</html>