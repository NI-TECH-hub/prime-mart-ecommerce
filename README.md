# Prime Mart Ecommerce

A modern, scalable multi-vendor ecommerce platform engineered by **Nikhil Chinchore**, built using Spring Boot, PostgreSQL, React, and Redux. Prime Mart delivers a seamless shopping and selling experience with robust security, responsive UI, and production-ready deployment.

***



## 🚀 Features

- **RESTful Backend:** Developed with Spring Boot, exposing APIs for product, order, and user management, secured using Spring Security, JWT authentication, and role-based access control.
- **Optimized Database:** PostgreSQL schema (see [ER diagram]) uses JPA/Hibernate for expressive relationships, validations, and efficient pagination.
- **Rich Frontend:** React + Redux for a snappy, single-page experience including shopping cart, order tracking, and vendor dashboards.
- **Multi-vendor Support:** Vendors can onboard, manage their product catalog, and view independent sales statistics for true marketplace scalability.
- **Secure Payments:** Integrated Stripe for reliable payment processing.
- **Production Deploy:** Full containerization with Docker and AWS, enabling easy scaling, rolling upgrades, and high availability.
- **User Management:** Registration and login interfaces for new buyers, sellers, and admin roles, with easy onboarding.

***

## 🧩 Tech Stack

| Layer     | Stack                                      |
| --------- | ------------------------------------------ |
| Frontend  | React, Redux, CSS                          |
| Backend   | Spring Boot (Java), Spring Security, JWT   |
| Database  | PostgreSQL, JPA/Hibernate                  |
| Payments  | Stripe                                     |
| DevOps    | Docker, AWS                                |

***

## 🗂️ Architectural Overview

- **ER Diagram**: (see attached `ecommerce-er-diagram.pdf`)  
- Key Entities: Users, Products, Orders, Payments, Categories, Vendors.
- Relationships:  
  - Users ↔ Addresses, Roles, Orders, Carts  
  - Products ↔ Categories, Sellers  
  - Orders ↔ OrderItems, Payments, Users

***

## ⚙️ Installation

### Prerequisites

- **Java 17+**
- **Node.js & npm**
- **PostgreSQL**
- **Docker** (for optional containerized deployment)

### Clone and Setup

```bash
git clone https://github.com/NI-TECH-hub/prime-mart-ecommerce.git
```

**Backend Setup:**
- Configure database credentials in `/backend/src/main/resources/application.properties`:
  ```
  spring.datasource.url=jdbc:postgresql://localhost:5432/primemart
  spring.datasource.username=
  spring.datasource.password=
  ```
- Build and run backend:
  ```bash
  cd backend
  mvn clean install
  mvn spring-boot:run
  ```

**Frontend Setup:**
```bash
cd frontend
npm install
npm start
```
- Frontend available at `http://localhost:3000`
- Backend REST API at `http://localhost:8080`

**Production Deploy:**
- Use provided Dockerfiles:
  ```bash
  docker-compose up --build
  ```
- Set AWS environment variables/secrets as required for cloud deployment.

***

## 🧑💻 Usage

- **Shop as a user**: Register/Login, browse products, add to cart, and checkout using Stripe.
- **Sell as a vendor**: Register/Login, list products, track sales in dedicated dashboard.
- **Admin**: Manage users, approve vendors, moderate listings and orders.

***

## 🟦 API Sample

| Method | Endpoint                | Description                  |
|--------|-------------------------|------------------------------|
| POST   | `/api/auth/register`    | Register user or vendor      |
| POST   | `/api/auth/login`       | Authenticate and receive JWT |
| GET    | `/api/products`         | List products (paged/filter) |
| POST   | `/api/cart/add`         | Add item to shopping cart    |
| POST   | `/api/payment/stripe`   | Process Stripe payment       |
| GET    | `/api/orders/{id}`      | Get order status/details     |

(See source and Swagger/OpenAPI docs for all endpoints.)

***

## 📝 Database ER Diagram

See attached: **`ecommerce-er-diagram.pdf`** for full data model and schema details.

***

## 🤝 Contributing

Contributions, bug reports, and suggestions are welcome!

***


## 👤 Author

Built and maintained by **Nikhil Chinchore**

***

