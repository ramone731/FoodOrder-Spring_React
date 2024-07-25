Ecommerce Food Delivery App
Ecommerce Food Delivery App repository! This application aims to provide a seamless experience for users to order food online. Below you'll find important information about the app, its dependencies, and how to get started.

Dependencies Used

Backend:
Spring Web
Lombok
Spring Boot DevTools
Spring Data JPA
MySQL Driver
Spring Security
JWT Token

Frontend:
React
Tailwind CSS


API Testing and Database:
API - Postman
DB - MySQL

Getting Started
To get a local copy of the project up and running follow these steps:

Prerequisites
Java JDK (version 17)
Node.js 
MySQL Workbench: 8.0.36
MySQL Server 
IDE (IntelliJ IDEA Ultimate/Eclipse/VS Code)

Installation
Clone the repository:

To create database:
Use MySQL CLI: (make sure Workbench DB is up and running)
My_SQL_CLI
mysql> CREATE DATABASE FoodOrder_Spring_React;

Backend Setup:
Open the backend project in your IDE.
Configure MySQL database credentials in application.properties.
Run the application.
Frontend Setup:

Navigate to the frontend directory.

Install dependencies:
npm install in the terminal:
npm i
npm install -D tailwindcss
npx tailwindcss init

Start the frontend server:
npm start
Testing APIs:

Import the Postman collection (collection.json) located in the postman directory to test APIs.
Database Setup:

Use SQL Workbench to connect to your MySQL database and execute the database.sql script to create necessary tables and data.
Usage
Access the application at http://localhost:3306 in your web browser after starting both backend and frontend servers.




