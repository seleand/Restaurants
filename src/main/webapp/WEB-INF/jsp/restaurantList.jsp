<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title><fmt:message key="restaurants.title"/></title>
    <link rel="stylesheet" href="resources/css/style1.css">
</head>
<body>
<section>
    <h2><a href="index.jsp"><fmt:message key="app.home"/></a></h2>
    <h3><fmt:message key="restaurants.title"/></h3>
    <a href="restaurants?action=create"><fmt:message key="restaurants.add"/></a>
    <hr>
    <table class="tg">
        <thead>
        <tr>
            <th><fmt:message key="restaurants.description"/></th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${restaurantList}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.seleand.restaurants.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td><a href="dishes?restaurantId=${restaurant.id}"><fmt:message key="restaurants.dishes"/></a></td>
                <td><a href="restaurants?action=update&id=${restaurant.id}"><fmt:message key="common.update"/></a></td>
                <td><a href="restaurants?action=delete&id=${restaurant.id}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
