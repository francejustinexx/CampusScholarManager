Campus Scholar Manager
JavaFX + JDBC + PostgreSQL Desktop Application

Description
Campus Scholar Manager is a desktop application built with JavaFX, JDBC, and PostgreSQL. It manages student/scholar records using a simple multi-view interface and stores database settings securely in an environment file.

Main Requirements Covered
- Desktop application using JavaFX
- Multiple views
- PostgreSQL database connection
- Environment settings stored securely using .env
- CRUD operations: create, read, update, delete

Application Views
1. Home View
   - First screen of the application.
   - Has a button that opens the Records view.

2. Records View
   - Shows the scholar form and TableView.
   - Allows the user to add, update, delete, and clear records.

Project Files
- src/MainApp.java: Starts the JavaFX app and switches screens.
- src/HomeController.java: Controls the Home view.
- src/ScholarController.java: Controls the Records view and CRUD operations.
- src/DatabaseConnection.java: Connects to PostgreSQL using .env values.
- src/Scholar.java: Model class for TableView binding.
- src/YearLevel.java: Enum for year level options.
- src/ScholarStatus.java: Enum for scholar status options.
- src/home.fxml: Home view layout.
- src/records.fxml: Records view layout.
- database.sql: SQL commands for creating the database and table.
- .env.example: Safe example environment file for GitHub.

Database Setup
Open psql and run:

CREATE DATABASE campusscholardb;

Connect to the new database:

\c campusscholardb

Create the table:

CREATE TABLE scholars (
    id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    program VARCHAR(80) NOT NULL,
    year_level VARCHAR(20) NOT NULL,
    status VARCHAR(20) NOT NULL
);

Environment Setup
Create a .env file in the project root:

DB_URL=jdbc:postgresql://localhost:5432/campusscholardb
DB_USER=postgres
DB_PASSWORD=your_password_here

Replace your_password_here with your PostgreSQL password.

Important
The real .env file should not be uploaded to GitHub. Only .env.example should be uploaded.

IntelliJ Setup
1. Open IntelliJ IDEA.
2. Open this project folder:
   CampusScholarManager
3. Add JavaFX SDK lib folder as a project library.
4. Add PostgreSQL JDBC jar as a project library.
5. Create an Application run configuration using MainApp.
6. Add VM options:

--module-path "C:\javafx-sdk-26.0.1\lib" --add-modules javafx.controls,javafx.fxml

How to Run
1. Start PostgreSQL.
2. Create the database and table using database.sql.
3. Make sure .env has the correct PostgreSQL password.
4. Run MainApp.java.
5. Use the Home view to open the Records view.
6. Add, update, delete, and clear records.

GitHub Submission
Upload the project to GitHub and submit the repository link.

Example:
https://github.com/your-username/CampusScholarManager
