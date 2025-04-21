<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<html>
<head>
    <title>Sign Up</title>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
   <link rel="stylesheet" href="/css/styles.css" />

</head>
<body>



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
                            <select name="role" id="role" class="form-select" onchange="toggleEmployeeId()" required>
                                <option value="">Select</option>
                                <option value="employer">Employer</option>
                                <option value="employee">Employee</option>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="employerId" class="form-label">Employer ID</label>
                            <input type="text" name="employerId" id="employerId" class="form-control" required />
                        </div>

                        <div class="mb-3" id="employeeIdField" style="display: none;">
                            <label for="employeeId" class="form-label">Employee ID</label>
                            <input type="text" name="employeeId" id="employeeId" class="form-control" />
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
    function toggleEmployeeId() {
        const role = document.getElementById("role").value;
        const employeeDiv = document.getElementById("employeeIdField");
        employeeDiv.style.display = (role === "employee") ? "block" : "none";
    }
</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
