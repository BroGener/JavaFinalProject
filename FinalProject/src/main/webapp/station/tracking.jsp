<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>GPS Tracking – CESC</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <style>
        .badge-transit  { background-color: #dc3545; }
        .badge-docked   { background-color: #198754; }
    </style>
</head>
<body>
<%@ include file="/common/navbar.jsp" %>
<div class="container mt-4">
    <h2 class="mb-3">GPS Tracking – Scooter Locations</h2>
    <p class="text-muted">FR-03: Each scooter's latest known location.
        Maintainers can use this to locate mis-placed or discharged scooters.</p>

    <c:choose>
        <c:when test="${empty logs}">
            <div class="alert alert-info">No GPS data available yet.</div>
        </c:when>
        <c:otherwise>
            <div class="table-responsive">
                <table class="table table-bordered table-hover align-middle">
                    <thead class="table-dark">
                        <tr>
                            <th>Scooter ID</th>
                            <th>Status</th>
                            <th>Last Known Station</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>Last Seen</th>
                            <th>History</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="log" items="${logs}">
                            <tr>
                                <td><strong>#${log.scooterId}</strong></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${log.inTransit}">
                                            <span class="badge badge-transit text-white">In Transit</span>
                                        </c:when>
                                        <c:otherwise>
                                            <span class="badge badge-docked text-white">At Station</span>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${log.stationId != null}">
                                            Station #${log.stationId}
                                        </c:when>
                                        <c:otherwise><span class="text-muted">Unknown</span></c:otherwise>
                                    </c:choose>
                                </td>
                                <td>${log.latitude}</td>
                                <td>${log.longitude}</td>
                                <td>
                                    <fmt:formatDate value="${log.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
                                </td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/tracking?action=history&scooterId=${log.scooterId}"
                                       class="btn btn-sm btn-outline-secondary">History</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:otherwise>
    </c:choose>

    <%-- Show full history for a specific scooter if requested --%>
    <c:if test="${not empty scooterId}">
        <hr>
        <h4>Full History – Scooter #${scooterId}</h4>
        <c:choose>
            <c:when test="${empty logs}">
                <div class="alert alert-warning">No history found for this scooter.</div>
            </c:when>
            <c:otherwise>
                <table class="table table-sm table-striped">
                    <thead>
                        <tr>
                            <th>Log ID</th>
                            <th>Latitude</th>
                            <th>Longitude</th>
                            <th>In Transit</th>
                            <th>Station</th>
                            <th>Timestamp</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="h" items="${logs}">
                            <tr>
                                <td>${h.gpsLogId}</td>
                                <td>${h.latitude}</td>
                                <td>${h.longitude}</td>
                                <td>${h.inTransit ? 'Yes' : 'No'}</td>
                                <td>${h.stationId != null ? h.stationId : '-'}</td>
                                <td><fmt:formatDate value="${h.logTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
        <a href="${pageContext.request.contextPath}/tracking" class="btn btn-secondary mt-2">&larr; Back to All</a>
    </c:if>
</div>
<%@ include file="/common/footer.jsp" %>
</body>
</html>
