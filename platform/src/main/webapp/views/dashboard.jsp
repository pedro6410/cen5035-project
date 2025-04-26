<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Dashboard</title>
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
    <h2>Welcome, ${userId}</h2>

    <div class="mt-4 d-flex gap-3">

        <c:choose>
            <c:when test="${not empty administratorid}">
                <a href="/create-user" class="btn btn-outline-primary">Create User</a>
                <a href="/users" class="btn btn-outline-success">List All Users</a>
                <button class="btn btn-outline-secondary" disabled>Employer Dashboard</button>
                <button class="btn btn-outline-secondary" disabled>Employee Dashboard</button>
            </c:when>
            <c:otherwise>
                <c:choose>
                    <c:when test="${not empty employerId and empty employeeId}">
                        <a href="/employer-dashboard" class="btn btn-outline-secondary">Employer Dashboard</a>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-secondary" disabled>Employer Dashboard</button>
                    </c:otherwise>
                </c:choose>

                <c:choose>
                    <c:when test="${not empty employeeId}">
                        <a href="/employee-dashboard" class="btn btn-outline-secondary">Employee Dashboard</a>
                    </c:when>
                    <c:otherwise>
                        <button class="btn btn-outline-secondary" disabled>Employee Dashboard</button>
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
