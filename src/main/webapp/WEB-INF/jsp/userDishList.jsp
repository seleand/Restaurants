<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/dataTables.bootstrap.min.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="dishes.title"/> "${restaurantName}"</h3>

            <div class="view-box">
<%--
                <a class="btn btn-sm btn-info" onclick="addDish('<fmt:message key="dishes.add"/>')"><fmt:message
                        key="dishes.add"/></a>
--%>
                <table class="table table-striped" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.date"/></th>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
<%--
                        <th></th>
                        <th></th>
--%>
                    </tr>
                    </thead>
                </table>
                <a href="restaurants" class="btn btn-sm btn-info"><fmt:message key="restaurants.title"/></a>
                <%--</section>--%>
            </div>
        </div>
    </div>
</div>

</body>
<script type="text/javascript">
    var i18n = [];
    <c:forEach var='key' items='<%=new String[]{"common.failed"}%>'>
    i18n['${key}'] = '<fmt:message key="${key}"/>';
    </c:forEach>
    <%--var edit_title ='<fmt:message key="dishes.edit"/>';--%>
</script>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/userDishDatatable.js"></script>
<script type="text/javascript">
    var ajaxUrl = 'ajax/dishes/'+"${restaurantId}";
    var rId = "${restaurantId}";
//    var ajaxUrlPost = 'ajax/dishes/';
</script>

</html>
