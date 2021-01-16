<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delicous Pizza</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value="resources/css/base.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/all.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/allres.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/b97db328cf.js" crossorigin="anonymous"></script>
</head>
<body>
		
	<jsp:include page="header.jsp"/>
	<p> ${mes}</p>
    <jsp:include page="footer.jsp"/>
</body>
</html>