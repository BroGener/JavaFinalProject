<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${station.stationName} – CESC</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<div class="container mt-4">
    <a href="${pageContext.request.contextPath}/station?action=list" class="btn btn-secondary mb-3">&larr; Back to Stations</a>

    <div class="card">
        <div class="card-header bg-dark text-white">
            <h4 class="mb-0">${station.stationName}</h4>
        </div>
        <div class="card-body">
            <dl class="row">
                <dt class="col-sm-3">Station ID</dt>
                <dd class="col-sm-9">${station.stationId}</dd>

                <dt class="col-sm-3">Location</dt>
                <dd class="col-sm-9">${station.locationDescription}</dd>

                <dt class="col-sm-3">Capacity</dt>
                <dd class="col-sm-9">${station.capacity} scooters</dd>

                <dt class="col-sm-3">Latitude</dt>
                <dd class="col-sm-9">${station.latitude}</dd>

                <dt class="col-sm-3">Longitude</dt>
                <dd class="col-sm-9">${station.longitude}</dd>
            </dl>
        </div>
        <div class="card-footer">
            <a href="${pageContext.request.contextPath}/station?action=report" class="btn btn-primary">
                View Full Station Report
            </a>
        </div>
    </div>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
