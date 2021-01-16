<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<base href="${pageContext.servletContext.contextPath}/">

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delicous Pizza</title>
    
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Delicious Pizza</title>
    <link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/reg.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b97db328cf.js" crossorigin="anonymous"></script>
</head>
<body>
	<jsp:include page="header.jsp"/>
	 <section class="reg">
        <div class="grid wide">
            <h2 class="main-title">Đăng ký</h2>
            <div class="row">
                <form action="reg.htm" method="POST" class="col l-12 m-12 c-12">
                    <p>Tên của bạn</p>
                    <input type="text" name="name" placeholder="Tên của bạn" required>
                    <p>Sinh nhật</p>
                    <input type="date" name="birthday" placeholder="Sinh nhật" required>
                    <p>Số điện thoại</p>
                    <input type="text" name="phone" placeholder="Số điện thoại" required>
                    <p>Giới tính</p>
                    <label for="nam">Nam</label>
                    <input type="radio" name="gender" value="1" id="nam" checked="checked" >
                     <label for="nu">Nữ</label>
                    <input type="radio" name="gender" value="0" id="nu">
                    <p>Email</p>
                    <input type="email" name="email" placeholder="Email" required>
                    <p>Mật khẩu</p>
                    <input type="password" name="pass" placeholder="Mật khẩu" required>
                    <p>Nhập lại mật khẩu</p>
                    <input type="password" name="repass" placeholder="Nhập lại mật khẩu" required>
                    <p style="color: red">${Mes} </p>
                    <button name="btnreg">Đăng ký</button>
                </form>
            </div>
        </div>
    </section>
    <jsp:include page="footer.jsp"/>
</body>
</html>