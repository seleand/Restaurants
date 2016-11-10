<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Restaurant list</title>
    <link rel="stylesheet" href="css/style1.css">
<%--
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
--%>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>Restaurant list</h3>
    <a href="restaurants?action=create">Add restaurant</a>
    <hr>
    <table class="tg">
        <thead>
        <tr>
            <th>Description</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${restaurantList}" var="restaurant">
            <jsp:useBean id="restaurant" scope="page" type="ru.seleand.restaurants.model.Restaurant"/>
            <tr>
                <td>${restaurant.name}</td>
                <td><a href="dishes?restaurantId=${restaurant.id}">Dishes</a></td>
                <td><a href="restaurants?action=update&id=${restaurant.id}">Update</a></td>
                <td><a href="restaurants?action=delete&id=${restaurant.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>
