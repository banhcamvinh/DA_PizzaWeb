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
    <link href="<c:url value="resources/css/admin.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b97db328cf.js" crossorigin="anonymous"></script>

</head>
<body>
	<%-- <%@ include file="header.jsp" %> --%>
	<jsp:include page="header.jsp"/>

	<section class="admin grid wide">
        <form action="adminmenu.htm">
            <div class="row">
                <div class="card l-4">
                    <button name="pizza">Chỉnh sửa bánh</button>
                </div>
<!--                 <div class="card l-4"> -->
<!--                     <button>Chỉnh sửa Size bánh</button> -->
<!--                 </div> -->
<!--                 <div class="card l-4"> -->
<!--                     <button>Chỉnh sửa loại bánh</button> -->
<!--                 </div> -->
<!--                 <div class="card l-4"> -->
<!--                     <button>Chỉnh sửa đế bánh</button> -->
<!--                 </div> -->
<!--                 <div class="card l-4"> -->
<!--                     <button>Chỉnh sửa món khác</button> -->
<!--                 </div> -->
                <div class="card l-4">
                    <button name="order">Xem các đơn hàng</button>
                </div>
<!--                 <div class="card l-4"> -->
<!--                     <button>Chỉnh sửa khuyến mãi</button> -->
<!--                 </div> -->
<!--                  <div class="card l-4"> -->
<!--                     <button>Chỉnh sửa tin tức</button> -->
<!--                 </div> -->
                <div class="card l-4">
                    <button name="account">Quản lí tài khoản</button>
                </div>
            </div>
        </form>
    </section>
	
	
	
	
	<%-- <%@ include file="footer.jsp" %> --%>
	<jsp:include page="footer.jsp"/>
</body>
</html>