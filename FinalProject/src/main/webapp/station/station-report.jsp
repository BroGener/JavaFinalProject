<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Station Distribution Report – CESC</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center mb-3">
        <h2>Station Distribution Report</h2>
        <a href="${pageContext.request.contextPath}/station?action=list" class="btn btn-secondary">
            &larr; All Stations
        </a>
    </div>

    <c:choose>
        <c:when test="${empty reports}">
            <div class="alert alert-info">No report data available.</div>
        </c:when>
        <c:otherwise>
            <div class="row">
                <c:forEach var="r" items="${reports}">
                    <div class="col-md-6 mb-3">
                        <div class="card h-100 shadow-sm">
                            <div class="card-header d-flex justify-content-between align-items-center
                                        ${r.scooterCount == 0 ? 'bg-warning' : 'bg-success text-white'}">
                                <strong>${r.station.stationName}</strong>
                                <span class="badge bg-light text-dark fs-6">${r.scooterCount} / ${r.station.capacity}</span>
                            </div>
                            <div class="card-body">
                                <p class="text-muted mb-1"><small>${r.station.locationDescription}</small></p>
                                <p class="mb-1">
                                    <strong>Capacity:</strong> ${r.station.capacity} &nbsp;|&nbsp;
                                    <strong>Docked:</strong> ${r.scooterCount}
                                </p>
                                <c:choose>
                                    <c:when test="${empty r.scooterIds}">
                                        <p class="text-muted fst-italic">No scooters currently docked.</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="mb-1"><strong>Scooter IDs:</strong>
                                            <c:forEach var="sid" items="${r.scooterIds}" varStatus="st">
                                                <span class="badge bg-secondary">#${sid}</span>
                                            </c:forEach>
                                        </p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="card-footer text-muted small">
                                Coords: ${r.station.latitude}, ${r.station.longitude}
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
