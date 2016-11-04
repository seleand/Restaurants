<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>

<html>
<head>
    <title>Dish list</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #ccc;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #f0f0f0;
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #ccc;
            color: #333;
            background-color: #49c7cc;
        }

        .tg .tg-4eph {
            background-color: #f9f9f9
        }
    </style>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>Dish list</h3>
    <a href="dishes?action=create&restaurantId=${restaurantId}">Add dish</a>
    <hr>
    <table class="tg">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Price</th>
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
                <td><a href="dishes?action=update&id=${dish.id}&restaurantId=${restaurantId}">Update</a></td>
                <td><a href="dishes?action=delete&id=${dish.id}&restaurantId=${restaurantId}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
    <a href="restaurants">Restaurant list</a>
</section>

</body>
</html>
