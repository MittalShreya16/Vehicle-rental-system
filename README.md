# 🚗 Vehicle Rental Management System

A full-stack web application built with **Spring Boot 3**, **Spring Data JPA**, **Hibernate**, **JSP/JSTL**, and **Bootstrap 5** for managing vehicle rentals, customer records, and booking transactions.

---

## 📋 Table of Contents
- [Overview](#-overview)
- [Key Features](#-key-features)
- [Tech Stack](#-tech-stack)
- [System Architecture & Modules](#-system-architecture--modules)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Application Routes](#-application-routes)
- [Database Schema](#-database-schema)
- [Project Structure](#-project-structure)
- [Author](#-author)

---

## 🌟 Overview

The **Vehicle Rental Management System** provides rental agencies with a centralized system to track their fleet, manage customer details, record vehicle reservations, and process returns. It includes automatic availability checking so vehicles cannot be double-booked during overlapping rental dates.

---

## ✨ Key Features

- **Vehicle Management**:
  - Add, view, edit, and delete vehicles.
  - Track vehicle make, model, year, license plate, and daily rental rate.
  - Real-time availability indicator on the vehicle catalog.

- **Customer Management**:
  - Register new customers with name, email, phone number, and address.
  - Edit customer contact details and maintain rental client history.

- **Rental & Booking Management**:
  - Book a vehicle for a customer with custom start and end dates.
  - Automatic rental duration and total cost calculation.
  - Overlap prevention: Ensures a vehicle cannot be reserved if already rented during the requested period.
  - Return workflow: Simple one-click vehicle return action.

- **Responsive UI**:
  - Clean Bootstrap 5 interface with intuitive navigation bar across all views.
  - Form validation with descriptive error feedback.

---

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| **Backend** | Java 17, Spring Boot 3.4.3, Spring MVC |
| **Data Persistence** | Spring Data JPA, Hibernate, MySQL 8.x |
| **Frontend** | JSP (JavaServer Pages), JSTL, Bootstrap 5, HTML5, CSS3 |
| **Build & Tooling** | Apache Maven 3.9+, Lombok |
| **Server** | Embedded Apache Tomcat (Jasper JSP Engine) |

---

## ⚙️ Prerequisites

Before running the application locally, ensure you have:

- **Java JDK 17** or higher installed ([Download JDK 17](https://www.oracle.com/java/technologies/downloads/#java17))
- **MySQL Server 8.0+** installed and running ([Download MySQL](https://dev.mysql.com/downloads/installer/))
- **Git** (optional, for version control)

---

## 🚀 Installation & Setup

### 1. Clone or Download the Repository

```bash
git clone https://github.com/MittalShreya16/Vehicle-rental-system.git
cd Vehicle-rental-system
```

### 2. Configure MySQL Database

1. Open your MySQL client (e.g., MySQL Workbench, MySQL CLI).
2. Create the database:
   ```sql
   CREATE DATABASE RentalService;
   ```

### 3. Configure Database Credentials

Open `VehicleRentalManagement/src/main/resources/application.properties` (or copy from `application-template.properties`) and configure your local MySQL credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/RentalService?createDatabaseIfNotExist=true&useSSL=false
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

*(Note: Hibernate will automatically generate and update all required tables on startup.)*

### 4. Build and Run the Application

Navigate to the project root containing Maven wrapper and start the application:

```bash
cd VehicleRentalManagement
```

**On Windows (PowerShell / Command Prompt):**
```powershell
.\mvnw.cmd spring-boot:run
```

**On Linux / macOS:**
```bash
chmod +x mvnw
./mvnw spring-boot:run
```

### 5. Access the Web Application

Once the console displays `Started VehicleRentalManagementApplication`, open your browser at:

👉 **[http://localhost:8080](http://localhost:8080)**

---

## 🗺️ Application Routes

### Vehicle Management
| Method | URL | Description |
|---|---|---|
| `GET` | `/` | Redirects to `/vehicles/list` |
| `GET` | `/vehicles/list` | View vehicle catalog with live availability status |
| `GET` | `/vehicles/new` | Display add vehicle form |
| `POST` | `/vehicles/save` | Save new or updated vehicle |
| `GET` | `/vehicles/edit/{id}` | Display edit form for a vehicle |
| `GET` | `/vehicles/delete/{id}` | Delete a vehicle |

### Customer Management
| Method | URL | Description |
|---|---|---|
| `GET` | `/customers/list` | List all registered customers |
| `GET` | `/customers/new` | Display new customer registration form |
| `POST` | `/customers/save` | Save new or updated customer |
| `GET` | `/customers/edit/{id}` | Display edit form for a customer |
| `GET` | `/customers/delete/{id}` | Delete a customer |

### Rental Management
| Method | URL | Description |
|---|---|---|
| `GET` | `/rentals/list` | View all active and completed rentals |
| `GET` | `/rentals/new` | Display rental booking form |
| `POST` | `/rentals/create` | Validate dates, check availability, and create rental |
| `GET` | `/rentals/return/{id}` | Mark rental as returned |

---

## 🗄️ Database Schema

The application uses three relational tables mapped via JPA entities:

- **`customer`**: Stores client contact and address records.
- **`vehicle`**: Stores vehicle specifications (make, model, year, license plate, daily rate).
- **`rental`**: Links `customer_id` and `vehicle_id` with `start_date`, `end_date`, `total_cost`, and `returned` status.

---

## 📁 Project Structure

```text
VehicleRentalManagement/
├── src/
│   ├── main/
│   │   ├── java/in/shreya/
│   │   │   ├── controller/      # Spring MVC controllers (HomeController, Vehicle, Customer, Rental)
│   │   │   ├── entity/          # JPA entity models (Customer, Vehicle, Rental)
│   │   │   ├── repository/      # Spring Data JPA repositories
│   │   │   ├── service/         # Service layer interfaces
│   │   │   │   └── impl/        # Business logic implementations
│   │   │   ├── ServletInitializer.java
│   │   │   └── VehicleRentalManagementApplication.java
│   │   ├── resources/
│   │   │   ├── application.properties
│   │   │   └── application-template.properties
│   │   └── webapp/Views/        # JSP views with Bootstrap 5
│   │       ├── customerForm.jsp
│   │       ├── customerList.jsp
│   │       ├── rentalForm.jsp
│   │       ├── rentalList.jsp
│   │       ├── vehicleForm.jsp
│   │       └── vehicleList.jsp
│   └── test/java/in/shreya/     # Application test suite
├── pom.xml                      # Maven project configuration
└── mvnw / mvnw.cmd              # Maven wrapper executables
```

---

## 👤 Author

- **Developer**: Shreya Mittal
- **GitHub**: [@MittalShreya16](https://github.com/MittalShreya16)
