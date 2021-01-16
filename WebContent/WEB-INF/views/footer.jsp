
<%@ page pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>chao thay </title>
  
</head>
<body>
	    <footer class="footer">
        <div class="t-footer">
            <div class="grid wide">
                <div class="row">
                    <div class="col l-3 m-6 c-12">
                        <a href="#">
                            <img class="logo-img" src="resources/img/Logo.png" alt="">
                        </a>
                    </div>
                    <div class="col l-3 m-6 c-12">
                        <h3>Delicious Pizza</h3>
                        <ul class="t-footer__menu-list"> 
                            <li class="t-footer__menu-item"><a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'home.htm'}">Trang chủ</a></li>
                            <li class="t-footer__menu-item"><a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'menu-pizza-page-1-.htm'}">Menu</a></li>
                            <li class="t-footer__menu-item"><a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'#'}">Khuyến mãi</a></li>
                            <li class="t-footer__menu-item"><a href="${ sessionScope.admin == 1 || sessionScope.admin==2 ? 'admin.htm':'#'}">Tin tức</a></li>
                            <li class="t-footer__menu-item"><a href="">Liên hệ</a></li>
                        </ul>
                    </div>
                    <div class="col l-3 m-6 c-12">
                        <h3>Liên hệ với Delicious</h3>
                        <ul class="t-footer__social-list">
                            <li class="t-footer__social-item"><a href=""><i class="fab fa-facebook"></i></a></li>
                            <li class="t-footer__social-item"><a href=""><i class="fab fa-instagram"></i></a></li>
                            <li class="t-footer__social-item"><a href=""><i class="fab fa-youtube"></i></a></li>
                            <li class="t-footer__social-item"><a href=""><i class="fas fa-envelope"></i></a></li>
                        </ul>
                        <h3>Hotline</h3>
                        <img src="resources/img/hotline.png" alt="">
                    </div>
                    <div class="col l-3 m-6 c-12">
                        <h3>Nhận thông tin ưu đãi</h3>
                        <form action="home.htm" method="post">
                            <input name="email" type="email" placeholder="Nhập Email của bạn">
                            <button name="btnreg">Đăng ký</button>
                        </form>
                        <p>${Mes}</p>
                        <img src="resources/img/bocongthuong.png" alt="">
                    </div>
                </div>
            </div>
        </div>
        <div class="b-footer">
            <p>© 2020 Delicious Pizza VietNam</p>
        </div>
    </footer>
</body>
</html>