<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>

<html>
<head>
    <title><fmt:message key="dishes.title"/></title>
    <link rel="stylesheet" href="resources/css/style1.css">
</head>
<body>
<section>
    <h2><a href="index.jsp"><fmt:message key="app.home"/></a></h2>
    <h3><fmt:message key="dishes.title"/></h3>
    <a href="dishes?action=create&restaurantId=${restaurantId}"><fmt:message key="dishes.add"/></a>
    <hr>
    <table class="tg">
        <thead>
        <tr>
            <th><fmt:message key="dishes.date"/></th>
            <th><fmt:message key="dishes.description"/></th>
            <th><fmt:message key="dishes.price"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${dishList}" var="dish">
            <jsp:useBean id="dish" scope="page" type="ru.seleand.restaurants.model.Dish"/>
            <tr class='normal'>
                <td>
                        ${dish.date}
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:formatDateTime(meal.dateTime)}--%>
                </td>
                <td>${dish.description}</td>
                <td><fmt:formatNumber type="number" minFractionDigits="2" value="${dish.price/100}"/></td>
                <td><a href="dishes?action=update&id=${dish.id}&restaurantId=${restaurantId}"><fmt:message key="common.update"/></a></td>
                <td><a href="dishes?action=delete&id=${dish.id}&restaurantId=${restaurantId}"><fmt:message key="common.delete"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="restaurants"><fmt:message key="restaurants.title"/></a>
</section>

</body>
</html>
