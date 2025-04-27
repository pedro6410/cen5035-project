<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Sign Up</title>
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

<div class="container mt-5">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h3 class="card-title text-center mb-4">Sign Up</h3>

                    <form action="/signup" method="post">

                        <div class="mb-3">
                            <label for="userId" class="form-label">User ID (Username)</label>
                            <input type="text" name="userId" id="userId" class="form-control" required />
                        </div>

                        <div class="mb-3">
                            <label for="password" class="form-label">Password</label>
                            <input type="password" name="password" id="password" class="form-control" required />
                        </div>

                        <div class="mb-3">
                            <label for="role" class="form-label">Role</label>
                            <select name="role" id="role" class="form-select" onchange="toggleRoleFields()" required>
                                <option value="">Select</option>
                                <option value="employer">Employer</option>
                                <option value="employee">Employee</option>
                                <option value="bank">Carbon Credit Bank</option>
                            </select>
                        </div>


                        <div class="mb-3" id="employerIdField" style="display: none;">
                            <label for="employerId" class="form-label">Employer ID</label>
                            <input type="text" name="employerId" id="employerId" class="form-control" value="${employerId}" />
                        </div>


                        <div id="employeeFields" style="display: none;">
                            <div class="mb-3">
                                <label for="employeeId" class="form-label">Employee ID</label>
                                <input type="text" name="employeeId" id="employeeId" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="employeeName" class="form-label">Employee Name</label>
                                <input type="text" name="employeeName" id="employeeName" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="departmentId" class="form-label">Department ID</label>
                                <input type="text" name="departmentId" id="departmentId" class="form-control" />
                            </div>
                        </div>


                        <div id="employerFields" style="display: none;">
                            <div class="mb-3">
                                <label for="employerName" class="form-label">Employer Name</label>
                                <input type="text" name="employerName" id="employerName" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="registrationNumber" class="form-label">Registration Number</label>
                                <input type="text" name="registrationNumber" id="registrationNumber" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="associatedBank" class="form-label">Associated Bank</label>
                                <input type="text" name="associatedBank" id="associatedBank" class="form-control" />
                            </div>
                        </div>


                        <div id="bankFields" style="display: none;">
                        <div class="mb-3">
                                <label for="bankID" class="form-label">Bank ID</label>
                                <input type="text" name="bankID" id="bankID" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="bankName" class="form-label">Bank Name</label>
                                <input type="text" name="bankName" id="bankName" class="form-control" />
                            </div>

                            <div class="mb-3">
                                <label for="licenseNumber" class="form-label">License Number</label>
                                <input type="text" name="licenseNumber" id="licenseNumber" class="form-control" />
                            </div>
                        </div>

                        <div class="d-grid">
                            <button type="submit" class="btn btn-primary">Create Account</button>
                        </div>
                    </form>

                    <c:if test="${not empty error}">
                        <div class="alert alert-danger mt-3 text-center">${error}</div>
                    </c:if>

                </div>
            </div>
        </div>
    </div>
</div>


<script>
    function toggleRoleFields() {
        const role = document.getElementById("role").value;

        document.getElementById("employeeFields").style.display = (role === "employee") ? "block" : "none";
        document.getElementById("employerFields").style.display = (role === "employer") ? "block" : "none";
        document.getElementById("bankFields").style.display = (role === "bank") ? "block" : "none";


        document.getElementById("employerIdField").style.display = (role === "employer" || role === "employee") ? "block" : "none";
    }

    window.onload = function() {
        var autoRole = '${autoSelectRole}';
        if (autoRole === 'employee') {
            document.getElementById("role").value = 'employee';
            toggleRoleFields();
        }
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
