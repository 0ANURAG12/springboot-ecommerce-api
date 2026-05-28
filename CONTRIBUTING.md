# Contributing to SpringBoot E-Commerce API

Thank you for considering contributing to this project.

This project is open to improvements, bug fixes, feature additions, and backend architecture enhancements.

---

## Getting Started

### 1. Fork the Repository

Click the **Fork** button on GitHub to create your own copy of the repository.

---

### 2. Clone the Repository

```bash
git clone https://github.com/YOUR_USERNAME/springboot-ecommerce-api.git
cd springboot-ecommerce-api
```

---

### 3. Configure Environment Variables

Create a `.env` file in the root directory:

```env
DATABASE_URL=jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
DATABASE_USERNAME=YOUR_USERNAME
DATABASE_PASSWORD=YOUR_PASSWORD
```

---

### 4. Run the Application

```bash
./mvnw spring-boot:run
```

---

## Branch Naming

Use descriptive branch names:

```text
feature/jwt-authentication
fix/order-validation
enhancement/pagination
```

---

## Contribution Guidelines

Before contributing:

* Check existing issues
* Create an issue if needed
* Discuss major changes before implementation

---

## Pull Request Process

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Commit changes clearly
5. Push to your fork
6. Open a Pull Request

Example commit message:

```text
feat: added JWT authentication support
```

---

## Suggested Contribution Areas

* JWT Authentication
* Spring Security
* Global Exception Handling
* Pagination & Filtering
* Docker Support
* Unit & Integration Testing
* API Documentation Improvements
* Performance Optimization

---

## Coding Standards

* Keep controllers thin
* Place business logic inside services
* Use DTOs for request/response handling
* Follow layered architecture principles
* Write clean and readable code

---

Thank you for contributing.
