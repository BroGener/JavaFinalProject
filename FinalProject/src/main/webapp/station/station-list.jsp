<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Charging Stations – CESC</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Charging Stations</h2>
        <a href="${pageContext.request.contextPath}/station?action=report" class="btn btn-outline-primary">
            Station Report
        </a>
    </div>

    <c:choose>
        <c:when test="${empty stations}">
            <div class="alert alert-info">No charging stations found.</div>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead class="table-dark">
                        <tr>
                            <th>#</th>
                            <th>Station Name</th>
                            <th>Location</th>
                            <th>Capacity</th>
                            <th>Coordinates</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="s" items="${stations}" varStatus="loop">
                            <tr>
                                <td>${loop.count}</td>
                                <td>${s.stationName}</td>
                                <td>${s.locationDescription}</td>
                                <td>${s.capacity}</td>
                                <td>${s.latitude}, ${s.longitude}</td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/station?action=details&stationId=${s.stationId}"
                                       class="btn btn-sm btn-info text-white">View</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
