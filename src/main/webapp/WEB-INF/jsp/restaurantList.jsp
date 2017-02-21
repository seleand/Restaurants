<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<%--
<head>
    <title><fmt:message key="restaurants.title"/></title>
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
    <link rel="stylesheet" href="resources/css/style1.css">
</head>
--%>
<jsp:include page="fragments/headTag1.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/jquery.dataTables.min.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <%--<h2><a href="index.jsp"><fmt:message key="app.home"/></a></h2>--%>
            <h3><fmt:message key="restaurants.title"/></h3>

            <div class="view-box">
                <%--<a href="restaurants/create" class="btn btn-sm btn-info"><fmt:message key="restaurants.add"/></a>--%>
                    <a class="btn btn-sm btn-info" onclick="add()"><fmt:message key="restaurants.add"/></a>


                <%--<a href="restaurants/create"><fmt:message key="restaurants.add"/></a>--%>
                <%--<hr>--%>
                <%--<table class="tg">--%>
                <table class="table table-striped display" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="restaurants.description"/></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <c:forEach items="${restaurantList}" var="restaurant">
                        <jsp:useBean id="restaurant" scope="page" type="ru.seleand.restaurants.model.Restaurant"/>
                        <tr>
                            <td>${restaurant.name}</td>
                            <td><a href="dishes?restaurantId=${restaurant.id}"
                                   class="btn btn-xs btn-primary"><fmt:message key="restaurants.dishes"/></a></td>
 <%--                           <td><a href="restaurants/update?id=${restaurant.id}"
                                   class="btn btn-xs btn-primary"><fmt:message key="common.update"/></a></td>
 --%>
                            <td><a class="btn btn-xs btn-primary edit" id="${restaurant.id}"><fmt:message key="common.update"/></a></td>
<%--
                            <td><a href="restaurants/delete?id=${restaurant.id}"
                                   class="btn btn-xs btn-danger"><fmt:message key="common.delete"/></a></td>
--%>
                            <td><a class="btn btn-xs btn-danger delete" id="${restaurant.id}"><fmt:message key="common.delete"/></a></td>

                                <%--
                                                <td><a href="dishes?restaurantId=${restaurant.id}"><fmt:message key="restaurants.dishes"/></a></td>
                                                <td><a href="restaurants/update?id=${restaurant.id}"><fmt:message key="common.update"/></a></td>
                                                <td><a href="restaurants/delete?id=${restaurant.id}"><fmt:message key="common.delete"/></a></td>
                                --%>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="restaurants.edit"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="text" hidden="hidden" id="id" name="id">

                    <div class="form-group">
                        <label for="name" class="control-label col-xs-3">Name</label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="name" name="name" placeholder="Name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button type="submit" class="btn btn-primary">Save</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="webjars/bootstrap/3.3.7-1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/datatables/1.10.12/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript">

    var ajaxUrl = 'ajax/restaurants/';
    var datatableApi;

    // $(document).ready(function () {
    $(function () {
        datatableApi = $('#datatable').dataTable({
            "bPaginate": false,
            "bInfo": false,
            "aoColumns": [
                {
                    "mData": "name"
                },
                {
                    "sDefaultContent": "",
                    "bSortable": false
                },
                {
                    "sDefaultContent": "Edit",
                    "bSortable": false
                },
                {
                    "sDefaultContent": "Delete",
                    "bSortable": false
                }
            ],
            "aaSorting": [
                [
                    0,
                    "asc"
                ]
            ]
        });
        makeEditable();
    });
</script>

</html>
