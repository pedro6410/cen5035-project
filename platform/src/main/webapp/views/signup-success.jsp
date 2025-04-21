<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Signup Success</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/css/styles.css" />

</head>
<body>

<div class="container d-flex justify-content-center align-items-center" style="height: 80vh;">
    <div class="text-center border p-5 shadow rounded bg-white">
        <h1 class="text-success mb-3">Signup Successful</h1>
        <p class="text-muted">${message}</p>

        <a href="/login" class="btn btn-primary mt-3">Go to Login</a>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
