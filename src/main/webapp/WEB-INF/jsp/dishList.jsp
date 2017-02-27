<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>--%>

<html>
<jsp:include page="fragments/headTag1.jsp"/>
<link rel="stylesheet" href="webjars/datatables/1.10.12/css/dataTables.bootstrap.min.css">

<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron">
    <div class="container">
        <div class="shadow">
            <h3><fmt:message key="dishes.title"/></h3>

            <div class="view-box">
                <a class="btn btn-sm btn-info" onclick="addDish()"><fmt:message
                        key="dishes.add"/></a>
                <%--
                    <hr>
                    <table class="tg">
                --%>
                <table class="table table-striped" id="datatable">
                    <thead>
                    <tr>
                        <th><fmt:message key="dishes.date"/></th>
                        <th><fmt:message key="dishes.description"/></th>
                        <th><fmt:message key="dishes.price"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
<%--
                    <c:forEach items="${dishList}" var="dish">
                        <jsp:useBean id="dish" scope="page" type="ru.seleand.restaurants.model.Dish"/>
                        &lt;%&ndash;<tr class='normal'>&ndash;%&gt;
                        <tr>
                            <td>
                                    ${dish.date}
                                    &lt;%&ndash;<%=TimeUtil.toString(meal.getDateTime())%>&ndash;%&gt;
                                    &lt;%&ndash;${fn:formatDateTime(meal.dateTime)}&ndash;%&gt;
                            </td>
                            <td>${dish.description}</td>
                            <td><fmt:formatNumber type="number" minFractionDigits="2" value="${dish.price/100}"/></td>
                            <td><a class="btn btn-xs btn-primary" onclick="updateDishRow(${restaurantId},${dish.id})"><fmt:message key="common.update"/></a></td>
                            <td><a class="btn btn-xs btn-danger" onclick="deleteDishRow(${restaurantId},${dish.id})"><fmt:message key="common.delete"/></a></td>
                        </tr>
                    </c:forEach>
--%>
                </table>
                <a href="restaurants" class="btn btn-sm btn-info"><fmt:message key="restaurants.title"/></a>
                <%--</section>--%>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="editRow">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h2 class="modal-title"><fmt:message key="dishes.add"/></h2>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" method="post" id="detailsForm">
                    <input type="hidden" id="restaurantId" name="restaurantId">
                    <input type="hidden" id="id" name="id">
<%--
                    <input id="restaurantId" name="restaurantId">
                    <input id="id" name="id">
--%>

                    <div class="form-group">
                        <label for="date" class="control-label col-xs-3"><fmt:message key="dishes.date"/></label>

                        <div class="col-xs-9">
                            <input type="date" class="form-control" id="date"
                                   name="date" placeholder="<fmt:message key="dishes.date"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="description" class="control-label col-xs-3"><fmt:message
                                key="dishes.description"/></label>

                        <div class="col-xs-9">
                            <input type="text" class="form-control" id="description" name="description"
                                   placeholder="<fmt:message key="dishes.description"/>">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="price" class="control-label col-xs-3"><fmt:message key="dishes.price"/></label>

                        <div class="col-xs-9">
                            <input type="number" min="0.00" step="0.01" class="form-control" id="price" name="price"
                                   placeholder="0.00">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-xs-offset-3 col-xs-9">
                            <button class="btn btn-primary" type="button" onclick="saveDish()"><fmt:message key="common.save"/></button>
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
<script type="text/javascript" src="webjars/datatables/1.10.12/js/dataTables.bootstrap.min.js"></script>
<script type="text/javascript" src="webjars/noty/2.3.8/js/noty/packaged/jquery.noty.packaged.min.js"></script>
<script type="text/javascript" src="resources/js/datatablesUtil.js"></script>
<script type="text/javascript" src="resources/js/dishDatatable.js"></script>
<script type="text/javascript">
    var ajaxUrl = 'ajax/dishes/'+"${restaurantId}";
    var rId = "${restaurantId}";
    var ajaxUrlPost = 'ajax/dishes/';
</script>

<%--
<script type="text/javascript">
    var ajaxUrl = 'ajax/dishes/';
    var datatableApi;

/*
    function updateTable() {
        $.ajax({
            type: "POST",
            url: ajaxUrl,
//            url: ajaxUrl + 'filter',
//            data: $('#filter').serialize(),
            success: updateTableByData
        });
    }
*/
    function updateTable(restaurantId) {
        $.get(ajaxUrl + restaurantId, updateTableByData);
    }


    $(function () {
        datatableApi = $('#datatable').DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "date"
                },
                {
                    "data": "description"
                },
                {
                    "data": "price"
                },
                {
                    "defaultContent": "<fmt:message key="common.update"/>",
                    "orderable": false
                },
                {
                    "defaultContent": "<fmt:message key="common.delete"/>",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "desc"
                ]
            ]
        });
        makeEditable();
    });
</script>
--%>
</html>
