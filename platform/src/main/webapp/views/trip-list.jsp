<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Trips for Employer ID: ${employerId}</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css" />

</head>
<body>

<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-brand mb-0 h1">Carbon Credit Portal</span>
        <div>
            <a href="/dashboard" class="btn btn-outline-light me-2">Home</a>
            <a href="/logout" class="btn btn-outline-light">Logout</a>
        </div>
    </div>
</nav>

<div class="container mt-4">

    <h2 class="mb-4">Trips for Employer ID: <span class="text-primary">${employerId}</span></h2>

    <table class="table table-bordered table-striped table-hover">
        <thead class="table-dark">
            <tr>
                <th>Trip ID</th>
                <th>Start Time</th>
                <th>End Time</th>
                <th>Distance (miles)</th>
                <th>Method</th>
                <th>Employee ID</th>
                <th>Carbon Credits</th>
                <th>Remarks</th>
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
                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>

                    <td>${trip.employeeId}</td>
                    <td>${trip.tripCarbonCredits}</td>

                    <td>
                        <c:choose>

                            <c:when test="${(trip.distanceMiles == null || trip.distanceMiles == 0) && trip.tripCarbonCredits > 0}">
                                <span class="badge bg-info text-dark">Carbon Credit Bought</span>
                            </c:when>


                            <c:when test="${trip.tripCarbonCredits > 0}">
                                <span class="badge bg-success">Carbon Credit Earned</span>
                            </c:when>


                            <c:when test="${trip.tripCarbonCredits < 0}">
                                <span class="badge bg-danger">Carbon Credit Sold</span>
                            </c:when>

                            <c:otherwise>-</c:otherwise>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <h4 class="mt-4">Total Carbon Credits: <span class="text-success">${totalCredits}</span></h4>

    <div class="mt-3">
        <form action="/sell" method="get" style="display: inline;">
            <input type="hidden" name="employerId" value="${employerId}" />
            <button type="submit" class="btn btn-danger">Sell Carbon Credits</button>
        </form>

        <form action="/buy" method="get" style="display: inline; margin-left: 10px;">
            <input type="hidden" name="employerId" value="${employerId}" />
            <button type="submit" class="btn btn-success">Buy Carbon Credits</button>
        </form>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
