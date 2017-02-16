<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<%--
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="app.title"/></title>
</head>
--%>
<jsp:include page="fragments/headTag1.jsp"/>
<body>

<div class="jumbotron">
    <div class="container">
        <p/>
        <form method="post" action="users">
            <fmt:message key="app.login"/>:
            <select name="userId">
                <option value="100000">User</option>
                <option value="100001">Admin</option>
            </select>
            <button type="submit"><fmt:message key="common.select"/></button>
        </form>
    </div>
</div>

</body>
</html>
