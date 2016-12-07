<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <meta charset="UTF-8">
    <title><fmt:message key="app.title"/></title>
</head>
<body>

<hr>
<form method="post" action="users">
    <fmt:message key="app.login"/>:
    <select name="userId">
        <option value="100000">User</option>
        <option value="100001">Admin</option>
    </select>
    <button type="submit"><fmt:message key="common.select"/></button>
</form>

</body>
</html>
