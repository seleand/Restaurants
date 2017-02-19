<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>

<html>
<%--
<head>
    <title><fmt:message key="dishes.title"/></title>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <link rel="stylesheet" href="resources/css/style1.css">
</head>
--%>
<jsp:include page="fragments/headTag1.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <%--<section>--%>
            <%--<h2><a href="index.jsp"><fmt:message key="app.home"/></a></h2>--%>
            <h3><fmt:message key="dishes.title"/></h3>

            <div class="view-box">
                <a href="dishes/create?restaurantId=${restaurantId}" class="btn btn-sm btn-info"><fmt:message
                        key="dishes.add"/></a>
                <%--
                    <hr>
                    <table class="tg">
                --%>
                <table class="table table-striped">
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
                        <%--<tr class='normal'>--%>
                        <tr>
                            <td>
                                    ${dish.date}
                                    <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                                    <%--${fn:formatDateTime(meal.dateTime)}--%>
                            </td>
                            <td>${dish.description}</td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" value="${dish.price/100}"/></td>
                            <td><a href="dishes/update?id=${dish.id}&restaurantId=${restaurantId}"
                                   class="btn btn-xs btn-primary"><fmt:message key="common.update"/></a></td>
                            <td><a href="dishes/delete?id=${dish.id}&restaurantId=${restaurantId}"
                                   class="btn btn-xs btn-danger"><fmt:message key="common.delete"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
                <a href="restaurants" class="btn btn-sm btn-info"><fmt:message key="restaurants.title"/></a>
                <%--</section>--%>
            </div>
        </div>
    </div>
</div>

</body>
</html>
