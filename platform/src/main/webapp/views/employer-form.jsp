<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Find Trips by Employer</title>

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

<hr>

   <div class="container mt-5">
       <div class="row justify-content-center">
           <div class="col-md-6">
               <div class="card shadow-sm">
                   <div class="card-body">
                       <h4 class="card-title mb-4 text-center">Search Trips by Employer ID</h4>

                       <form action="trips" method="post">
                           <div class="mb-3">
                               <label for="employerId" class="form-label">Employer ID</label>
                               <input type="text" id="employerId" name="employerId" class="form-control" required>
                           </div>

                           <div class="d-grid">
                               <button type="submit" class="btn btn-primary"> Search Trips</button>
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
