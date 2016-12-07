<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="dish.title"/></title>
    <link rel="stylesheet" href="resources/css/style2.css">
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <%--<h2><a href="index.jsp"><fmt:message key="app.home"/></a></h2>--%>
    <h3><fmt:message key="${param.action == 'create' ? dishes.create : dishes.edit}"/></h3>
    <hr>
    <jsp:useBean id="dish" type="ru.seleand.restaurants.model.Dish" scope="request"/>
    <form method="post" action="dishes">
        <input type="hidden" name="id" value="${dish.id}">
        <input type="hidden" name="restaurantId" value="${restaurantId}">
        <dl>
            <dt><fmt:message key="dishes.date"/>:</dt>
            <dd><input type="date" value="${dish.date}" name="date"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="dishes.description"/>:</dt>
            <dd><input type="text" value="${dish.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt><fmt:message key="dishes.price"/>:</dt>
            <dd><input type="number" min="0.00" step="0.01" placeholder="0.00" value="${dish.price/100}" name="price"></dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()"><fmt:message key="common.cancel"/></button>
    </form>
</section>
</body>
</html>
