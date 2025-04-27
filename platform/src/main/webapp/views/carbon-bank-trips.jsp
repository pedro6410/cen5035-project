<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Trips - Carbon Credit Bank</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-brand">Carbon Credit Portal</span>
        <div>
            <a href="/dashboard" class="btn btn-outline-light me-2">Home</a>
            <a href="/logout" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <h2 class="mb-4">All trips under bank: ${bankName}</h2>

    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Trip ID</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Distance (Miles)</th>
                <th>Method</th>
                <th>Trip Carbon Credits</th>
                <th>Employee ID</th>
                <th>Employer ID</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="trip" items="${trips}">
                <tr>
                    <td>${trip.tripId}</td>
                    <td>${trip.startTime}</td>
                    <td>${trip.endTime}</td>
                    <td>${trip.distanceMiles}</td>


                    <td>
                        <c:choose>
                            <c:when test="${trip.method == 1}">Driving</c:when>
                            <c:when test="${trip.method == 2}">Carpooling</c:when>
                            <c:when test="${trip.method == 3}">Ridesharing</c:when>
                            <c:when test="${trip.method == 4}">Walking</c:when>
                            <c:when test="${trip.method == 5}">Biking</c:when>
                            <c:otherwise>Unknown</c:otherwise>
                        </c:choose>
                    </td>

                    <td>${trip.tripCarbonCredits}</td>
                    <td>${trip.employeeId}</td>
                    <td>${trip.employerId}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <c:if test="${empty trips}">
        <div class="alert alert-info text-center mt-3">
            No trips found under Carbon Credit Bank: ${bankName}
        </div>
    </c:if>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
