A full-featured backend for FoodHub developed using Spring Boot, providing authentication, authorization, ordering system, menu management, and admin functionality.
This backend powers the React frontend and integrates with Razorpay for payment processing.

🚀 Features
⭐ User Features

JWT login & registration

Browse menu items

Place orders

Razorpay payment

View order history

⭐ Admin Features

Manage menu items

Manage categories

Manage users

View all orders (joined with user + items + menu + category)

🛠️ Tech Stack

Spring Boot

Spring Security (JWT)

MySQL

Hibernate / JPA

Razorpay API

Java 17

🗂️ Project Structure
src/main/java/com/restaurant/
 ├── controller/
 ├── service/
 ├── repository/
 ├── model/
 ├── dto/
 ├── security/
 └── config/

💾 Database Structure

Tables:

users

menu_items

categories

orders

order_items

Relationships:

Category ↔ MenuItem (One-to-Many)

Order ↔ OrderItems (One-to-Many)

User ↔ Orders (One-to-Many)

🔐 Security

JWT Authentication

ROLE_USER & ROLE_ADMIN

BCrypt Password Encoding

Secured admin routes

💳 Payment Integration

Integrated with Razorpay (Test Mode) for secure payments.
