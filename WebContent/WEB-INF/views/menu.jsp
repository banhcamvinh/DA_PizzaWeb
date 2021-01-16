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
    <link href="<c:url value="resources/css/menu.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/menures.css" />" rel="stylesheet">
    <link href="<c:url value="resources/css/gridsys.css" />" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Poppins:ital,wght@0,100;0,300;0,500;0,700;0,900;1,400&display=swap" rel="stylesheet">
</head>
<body>
		
		<jsp:include page="header.jsp"/>
		
		<%-- 	<c:set var="price" value="price_${id}"/>
		<p> ${requestScope[price] } </p> --%>


		
<%-- 		<c:forEach items="${MENULIST}" var="item" >
			<p>${item.getName()} </p>
			<p>${item.getDescript() } </p>
			<img alt="" src="${item.getImg()}">
			<c:forEach items="${item.getNONPIZZAPRICES()}" var="itemm" >
				<c:set var="productprice" value="productprice${item.getIDNonPizza()}"/>				
				<c:if test="${requestScope[productprice] == itemm.getPrice()}">
					<p>My salary is:  <c:out value = "${requestScope[productprice]}"/><p>
				</c:if>
			</c:forEach>
			<br>
		</c:forEach>
		 --%>
		
	    <section class="menu">
        <div class="grid wide">
            <h2 class="main-title" style="margin-bottom: 20px">Thực đơn</h2>
            
            <form action="search-${type }.htm" method="post" style="margin-bottom: 10px">
            	<input name="search" type="search" placeholder="Tìm sản phẩm" style="padding: 12px;" value="${key }">
            	<button name="btnsearch" style="padding: 10px; display: inline-block;">Tìm </button>
            </form>
            
            <ul class="menu-type-list row no-guts">
                <li class="${type == 'menu-pizza' ? 'menu-type-item col l-3 m-3 c-6 active' : 'menu-type-item col l-3 m-3 c-6' }">
                    <a href="menu-pizza-page-1-${key}.htm">Pizza</a>
                </li>
                <li class="${type == 'menu-nonpizza' ? 'menu-type-item col l-3 m-3 c-6 active' : 'menu-type-item col l-3 m-3 c-6' }">
                    <a href="menu-nonpizza-page-1-${key}.htm">Món khác</a>
                </li>
                <li class="${type == 'menu-drink' ? 'menu-type-item col l-3 m-3 c-6 active' : 'menu-type-item col l-3 m-3 c-6' }">
                    <a href="menu-drink-page-1-${key }.htm">Giải khát</a>
                </li>
                <li class="${type == 'menu-dessert' ? 'menu-type-item col l-3 m-3 c-6 active' : 'menu-type-item col l-3 m-3 c-6' }">
                    <a href="menu-dessert-page-1-${key }.htm">Tráng miệng</a>
                </li>
            </ul>
           
            <ul class="menu-list row">
            	 <c:forEach  items="${MENULIST}" var="item">
            	 <li class="menu-item col l-3 m-4 c-12">
                    <div class="menu-item__content">
                        <div class="menu-item__img">
                            <img src="${item.getImg()}" alt="">
                        </div>
                        <div class="menu-item__des">
                            <h5>${item.getName()}</h5>
                            <p>${item.getDescript() }</p>
                            <c:if test="${type == 'menu-pizza' }">
                            	<a class="btnorder" href="${type}-${item.getIDPizza()}.htm">Đặt món</a>
                            </c:if>
                              <c:if test="${type != 'menu-pizza' }">
                            	<a class="btnorder" href="${type}-${item.getIDNonPizza()}.htm">Đặt món</a>
                            </c:if>
                        </div>
                    </div>
                </li>
            </c:forEach>
            	
              
            </ul>
        </div>
		<c:set var="npagemin" value="${type}-page-${pagemin }-${key }.htm"></c:set>
       	<c:set var="npagemax" value="${type}-page-${pagemax }-${key}.htm"></c:set>
        <c:set var="npagepre" value="${type}-page-${pagepre}-${key }.htm"></c:set>
        <c:set var="npagenext" value="${type}-page-${pagenext }-${key }.htm"></c:set> 
        <c:set var="npageindex" value="${type}-page-${pageindex }-${key }.htm"></c:set> 
        
<%--         <p> ${npagemin }</p> --%>
<%--         <p> ${npagemax }</p> --%>
        <ul class="pagination">
        	<li> <a href="${npageindex eq npagemin ? npagemax : npagepre} "><i class="fas fa-arrow-left"></i></a> </li>
        	<c:forEach begin="1" end="${(numofproduct-1)/8+1 }" var="i">		
        		<li> <a href="${type}-page-${i}-${key }.htm"> <c:out value = "${i}"/></a> </li>
        	</c:forEach>
        	<li> <a href="${npageindex eq npagemax? npagemin: npagenext} "><i class="fas fa-arrow-right"></i></a> </li>
        </ul>
    </section>
    
    <jsp:include page="footer.jsp"/>
</body>
</html>