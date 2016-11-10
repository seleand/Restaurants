<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Dish</title>
    <link rel="stylesheet" href="css/style2.css">
<%--
    <style>
        dl {
            background: none repeat scroll 0 0 #FAFAFA;
            margin: 8px 0;
            padding: 0;
        }

        dt {
            display: inline-block;
            width: 170px;
        }

        dd {
            display: inline-block;
            margin-left: 8px;
            vertical-align: top;
        }
    </style>
--%>
</head>
<body>
<section>
    <h2><a href="index.html">Home</a></h2>
    <h3>${param.action == 'create' ? 'Create dish' : 'Edit dish'}</h3>
    <hr>
    <jsp:useBean id="dish" type="ru.seleand.restaurants.model.Dish" scope="request"/>
    <form method="post" action="dishes">
        <input type="hidden" name="id" value="${dish.id}">
        <input type="hidden" name="restaurantId" value="${dish.restaurantId}">
        <dl>
            <dt>Date:</dt>
            <dd><input type="date" value="${dish.date}" name="date"></dd>
        </dl>
        <dl>
            <dt>Description:</dt>
            <dd><input type="text" value="${dish.description}" size=40 name="description"></dd>
        </dl>
        <dl>
            <dt>Price:</dt>
            <dd><input type="number" min="0.00" step="0.01" placeholder="0.00" value="${dish.price/100}" name="price"></dd>
        </dl>
        <button type="submit">Save</button>
        <button onclick="window.history.back()">Cancel</button>
    </form>
</section>
</body>
</html>
