<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>All Users</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
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
    <h2 class="mb-4">Registered Users</h2>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>User ID</th>
            <th>Enroll Date</th>
            <th>User Name</th>
            <th>Employee ID</th>
            <th>Employer ID</th>
            <th>Role</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="userWithRole" items="${users}">
            <tr>
                <td>${userWithRole.user.id}</td>
                <td>${userWithRole.user.enrollDate}</td>
                <td>${userWithRole.userName}</td>
                <td>${userWithRole.user.employeeId}</td>
                <td>${userWithRole.user.employerId}</td>
                <td><strong>${userWithRole.role}</strong></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
