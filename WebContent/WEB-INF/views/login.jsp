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
    <link href="<c:url value="resources/css/login.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b97db328cf.js" crossorigin="anonymous"></script>

</head>
<body>
	<jsp:include page="header.jsp"/>
	
	 <section class="login">
        <div class="grid wide">
            <h2 class="main-title">Login</h2>
            <div class="row">
                <form action="login.htm" method="POST" class="form col l-12 c-12 m-12">
                    <p>Địa chỉ Email: </p>
                    <input type="text" name="username" placeholder="Nhập địa chỉ email">
                    <p>Mật khẩu: </p>
                    <input type="password" name="userpass" placeholder="Nhập mật khẩu" required>
                    <a href="">Quên mật khẩu?</a>
                    <p style="color: red;">${Mes }</p>
                    <button name="btnsubmit">Đăng nhập</button>
                </form>
            </div>
        </div>
    </section>
	
	<jsp:include page="footer.jsp"/>
</body>
</html>