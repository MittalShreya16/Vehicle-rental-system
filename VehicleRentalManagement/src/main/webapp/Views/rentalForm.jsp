<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Create Rental</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container">
            <a class="navbar-brand fw-bold" href="/vehicles/list">🚗 Vehicle Rental System</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="/vehicles/list">Vehicles</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/customers/list">Customers</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active" href="/rentals/list">Rentals</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-4">
        <div class="card shadow-lg">
            <div class="card-header bg-primary text-white text-center">
                <h2>Create a New Rental</h2>
            </div>
            <div class="card-body">
                <!-- Display error message if present -->
                <c:if test="${not empty error}">
                    <div class="alert alert-danger text-center">${error}</div>
                </c:if>
                
                <!-- Form submitting to /rentals/create with POST method -->
                <form action="/rentals/create" method="post">
                    <div class="mb-3">
                        <label for="customer_id" class="form-label">Customer:</label>
                        <select id="customer_id" name="customer_id" class="form-select">
                            <c:forEach var="customer" items="${customers}">
                                <option value="${customer.id}">${customer.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="vehicle_id" class="form-label">Vehicle:</label>
                        <select id="vehicle_id" name="vehicle_id" class="form-select">
                            <c:forEach var="vehicle" items="${vehicles}">
                                <option value="${vehicle.id}">${vehicle.make} ${vehicle.model}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3">
                        <label for="startDate" class="form-label">Start Date:</label>
                        <input type="date" id="startDate" name="startDate" class="form-control">
                    </div>

                    <div class="mb-3">
                        <label for="endDate" class="form-label">End Date:</label>
                        <input type="date" id="endDate" name="endDate" class="form-control">
                    </div>

                    <div class="text-center">
                        <button type="submit" class="btn btn-success">Create Rental</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
