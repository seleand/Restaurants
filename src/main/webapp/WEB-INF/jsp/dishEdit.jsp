<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title><fmt:message key="dish.title"/></title>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <link rel="stylesheet" href="resources/css/style2.css">
</head>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<section>
    <jsp:useBean id="dish" type="ru.seleand.restaurants.model.Dish" scope="request"/>
    <h3><fmt:message key="${dish.isNew() ? 'dishes.create' : 'dishes.edit'}"/></h3>
    <hr>
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
