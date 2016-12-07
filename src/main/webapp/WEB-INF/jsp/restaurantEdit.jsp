<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <title><fmt:message key="restaurant.title"/></title>
    <link rel="stylesheet" href="resources/css/style2.css">
</head>
<body>
<section>
    <h2><a href="index.jsp"><fmt:message key="app.home"/></a></h2>
    <h3><fmt:message key="${param.action == 'create' ? restaurants.create : restaurants.edit}"/></h3>
    <hr>
    <jsp:useBean id="restaurant" type="ru.seleand.restaurants.model.Restaurant" scope="request"/>
    <form method="post" action="restaurants">
        <input type="hidden" name="id" value="${restaurant.id}">
        <dl>
            <dt>Name:</dt>
            <dd><input type="text" value="${restaurant.name}" size=40 name="name"></dd>
        </dl>
        <button type="submit"><fmt:message key="common.save"/></button>
        <button onclick="window.history.back()"><fmt:message key="common.cancel"/></button>
    </form>
</section>
</body>
</html>
