# Online Shopping System 🛒

## Project Overview:
This project involves building a console-based online shopping system using Java, Spring Core, and Hibernate. The system will allow users to manage products, customers, and orders, applying relationships between entities. Hibernate will be used to handle the database interactions and automatically create the necessary database schema. 💻📦

## Project Instructions:

### 1. Set Up the Project 🛠️
- Create a Maven project and add dependencies for Spring Core, Hibernate, and MySQL Connector. 🔗
- Configure Hibernate to manage database connections. 🔌

### 2. Design the Entities 📊
- Create entity classes to represent **products**, **customers**, and **orders** using Hibernate annotations. 🛍️👤📝
- Establish relationships between entities. 🔄
    - A **Customer** can place many **Orders**. 🧑‍💻➡️📦
    - An **Order** can contain many **Products**. 📦🔄🛍️
    - A **Product** can appear in many **Orders**. 🛍️🔄📦

### 3. Implement DAO Classes 🖥️
- Create DAO (Data Access Object) classes for handling CRUD operations for **Product**, **Customer**, and **Order** entities. 🏗️
- Implement methods to add, update, retrieve, and delete records from the database. 🔄

### 4. Build a Console Interface 💬
- Implement a menu-driven system to allow users to manage **products**, **customers**, and **orders**. 🖥️🔄
- Provide options to add new **products**, create **customers**, place **orders**, and view **order details**. 🛍️📝

### 5. Manage Transactions and Testing 🔄
- Ensure database consistency by handling transactions properly. 🏦
- Test the system by performing various operations such as adding **products**, placing **orders**, and retrieving **order information**. ✅
