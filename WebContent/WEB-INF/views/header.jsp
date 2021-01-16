<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"> 
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.1/css/all.css" integrity="sha384-vp86vTRFVJgpjF9jiIGPEEqYqlDwgyBgEF109VFjmqGmIY/Y4HV4d3Gp2irVfcrp" crossorigin="anonymous">
    <link rel="icon" href="resources/img/Logo.png" type="image/icon type">
    <title>Delicious Pizza</title>
</head>
<body>
	    <header class="header">
        <div class="grid wide">
            <div class="row">
                <a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm' : 'home.htm' }">
                    <img class="header__logo-img" src="resources/img/Logo.png" alt="">
                </a>
                <label for="header__menu" class="l-dn m-dn c-db"><i class="fas fa-bars"></i> Menu</label>
                <input style="display: none;" type="checkbox" id="header__menu">
                <ul class="header__menu-list">
                    <label for="header__menu" class="l-dn m-dn c-db"><i class="far fa-times-circle"></i></label>
                    <li class="header__menu-item active">
                        <a href="${ sessionScope.admin == 1|| sessionScope.admin==2? 'admin.htm' : 'home.htm' }">Trang chủ</a>
                    </li>
                    <li class="header__menu-item">
                        <a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'menu-pizza-page-1-.htm'}">Thực đơn</a>
                    </li>
                    <li class="header__menu-item">
                        <a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'#'}">Khuyến mãi</a>
                    </li>
                    <li class="header__menu-item">
                        <a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'#'}">Tin tức</a>
                    </li>
                    <li class="header__menu-item">
                        <a href="#">Liên hệ</a>
                    </li>
                    <li class="header__menu-item cart-item">
                        <a href="cart.htm"><i class="fas fa-shopping-cart"><span>${numofcart } </span></i><span class="l-dn m-dn c-db">Giỏ hàng</span></a>
                    </li>
                    
                    <li class="header__menu-item header__menu-item-account">
                        <a>
                            <i class="fas fa-user-circle"> ${sessionScope.username }</i> 
                            <span class="l-dn m-dn c-db">Đăng ký/Đăng nhập</span> 
                        </a>
                        <ul class="header__menu-account-list">
                        	<c:choose>
                        		<c:when test="${not empty sessionScope.username }">
                        			 <li> <a href="logout.htm">Đăng xuất</a></li>
                        		</c:when>
                        		<c:when test="${empty sessionScope.username  }">
                        			<li> <a href="login.htm">Đăng nhập</a></li>
                            		<li> <a href="reg.htm">Đăng ký</a></li>
                        		</c:when>
                        	</c:choose>

                        </ul>
                    </li>
                </ul>
                
            </div>
        </div>
    </header>
</body>
</html>