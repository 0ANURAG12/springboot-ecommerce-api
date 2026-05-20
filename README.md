# Spring Boot E-Commerce Store Demo

This is a demo RESTful API for an e-commerce store built with Spring Boot. It provides basic functionalities to manage products and handle customer orders. The project demonstrates the use of Spring Data JPA for data persistence, validation, and OpenAPI for API documentation.

## Application Architecture & Workflow

This application follows a standard multi-tiered architecture common in Spring Boot applications, ensuring a clean separation of concerns:

1. **Client Request**: A client (like a web browser, Postman, or a frontend app) sends an HTTP request to the API.
2. **Controller Layer (`@RestController`)**: The request is first intercepted by the Controllers (`ProductController`, `OrderController`). The controller's job is purely routing: it receives the HTTP request, extracts the payload (often as a DTO), and passes it to the Service layer.
3. **Service Layer (`@Service`)**: This is the heart of the application where all the **business logic** resides. For example, `OrderService` handles complex logic like:
   - Verifying if a requested product exists.
   - Checking if there is enough stock quantity available.
   - Calculating the total price of an order.
   - Deducting the purchased quantity from the product's available stock.
   - Using `@Transactional` to ensure that either all database operations (creating order, updating stock) succeed, or they all roll back if an error occurs.
4. **Repository Layer (`@Repository`)**: The Service layer calls the Repository layer (`OrderRepository`, `ProductRepository`) to interact with the database. These are interfaces extending Spring Data JPA's `JpaRepository`, which provides built-in methods for CRUD operations without writing boilerplate SQL queries.
5. **Database**: PostgreSQL securely stores all the persistent data (Products, Orders, OrderItems).

## Core Concepts Explained

### 1. DTOs (Data Transfer Objects)
DTOs like `OrderRequest` and `OrderItemRequest` are used to define the exact structure of data expected from the client. 
* **Separation of Concerns**: They prevent direct exposure of our internal database Entities (`Order`, `Product`) to the outside world.
* **Validation**: They are heavily annotated with validation constraints (e.g., `@NotBlank`, `@Email`, `@Valid`, `@NotEmpty`). This ensures that invalid data is rejected at the Controller level before it even reaches the Service layer.

### 2. Service Layer
The Service layer (e.g., `OrderService`, `ProductService`) encapsulates the core business rules. Keeping this logic out of the controllers makes the code reusable, easier to test, and strictly separates the HTTP protocol details from the business rules.

### 3. Entities
Entities (`Product`, `Order`, `OrderItem`) represent the actual tables in the PostgreSQL database. They use JPA annotations (like `@Entity`, `@Id`, `@OneToMany`, `@ManyToOne`) to define the schema and relationships between tables.

## Features

* **Product Management**: Create, read, update, and delete (CRUD) products. Stock quantities are tracked.
* **Order Management**: Create new orders and fetch order details. Automatically handles stock deduction and total price calculation.
* **Validation**: Input validation for products and orders to ensure data integrity using Spring Boot Validation.
* **API Documentation**: Automated interactive API documentation using Swagger/OpenAPI.
* **PostgreSQL Integration**: Relational database storage.

## Technologies Used

* **Java 21**
* **Spring Boot 3.x**
  * Spring Web
  * Spring Data JPA
  * Spring Boot Validation
* **PostgreSQL** (Database)
* **Lombok** (Boilerplate code reduction)
* **Springdoc OpenAPI** (Swagger UI)
* **Dotenv-java** (Environment variable management)
* **Maven** (Build tool)

## Prerequisites

Before running the application, ensure you have the following installed:

* Java Development Kit (JDK) 21 or later
* Maven
* PostgreSQL server

## Getting Started

### 1. Clone the repository

```bash
git clone <repository-url>
cd demo
```

### 2. Configure Environment Variables

Create a `.env` file in the root directory of the project (if it doesn't exist) and configure your database credentials:

```env
DATABASE_URL=jdbc:postgresql://localhost:5332/YOUR_DATABASE_NAME
DATABASE_USERNAME=YOURNAME
DATABASE_PASSWORD=PASSWORD
```

Make sure you have created the database `YOUR_DATABASE_NAME` in your PostgreSQL server or update the URL to match your existing database.

### 3. Build and Run the Application

You can use the Maven wrapper to run the application directly:

```bash
./mvnw spring-boot:run
```

Alternatively, you can build the project and run the generated JAR:

```bash
./mvnw clean package
java -jar target/demo-0.0.1-SNAPSHOT.jar
```

The application will start on port `8080` by default. The database tables will be automatically created or updated based on the entity definitions (`ddl-auto: update`).

## API Endpoints

The application exposes the following RESTful endpoints:

### Products API (`/api/products`)

* `GET /api/products` - Retrieve all products
* `GET /api/products/{id}` - Retrieve a specific product by ID
* `POST /api/products` - Create a new product
* `PUT /api/products/{id}` - Update an existing product
* `DELETE /api/products/{id}` - Delete a product

### Orders API (`/api/orders`)

* `GET /api/orders` - Retrieve all orders
* `GET /api/orders/{id}` - Retrieve a specific order by ID
* `POST /api/orders` - Create a new order

## API Documentation (Swagger UI)

This project uses Springdoc OpenAPI to automatically generate API documentation. 
Once the application is running, you can access the interactive Swagger UI at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Or view the OpenAPI JSON definition at:

[http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

## License

This project is open-source and available under the [MIT License](LICENSE).
