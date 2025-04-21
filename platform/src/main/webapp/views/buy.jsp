<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Buy Carbon Credits</title>


    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="/css/styles.css" />

</head>
<body>


<nav class="navbar navbar-dark bg-dark">
    <div class="container-fluid">
        <span class="navbar-brand">Carbon Credit Portal</span>
        <a href="/dashboard" class="btn btn-outline-light me-2"> Home</a>
        <a href="/logout" class="btn btn-outline-light"> Logout</a>
    </div>
</nav>


<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h4 class="card-title mb-4 text-center text-success"> Buy Carbon Credits</h4>

                    <form action="/submit-buy" method="post">
                        <input type="hidden" name="employerId" value="${employerId}" />

                        <div class="mb-3">
                            <label for="bankId" class="form-label">Select Bank</label>
                            <select name="bankId" id="bankId" class="form-select" required>
                                <c:forEach var="bank" items="${banks}">
                                    <option value="${bank.bankId}">${bank.bankName}</option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="amount" class="form-label">Amount to Buy (Credits)</label>
                            <input type="number" name="amount" id="amount" class="form-control" step="0.01" min="0" required />
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-success">Buy Credits</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
