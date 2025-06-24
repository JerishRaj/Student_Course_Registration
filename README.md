# 🎓 Course Registration System

A robust and scalable **Java + MySQL** course registration system with a clean separation of concerns using the **DAO design pattern**. It supports multi-role access for Admins, Students, and Instructors, offering real-time student enrollments, course management, and academic record viewing—all from a command-line interface!


## 🚀 Quick Glance

> 🔒 **Role-Based Access**  
> 💾 **MySQL Database Integration**  
> 🧱 **Modular Architecture (DAO, Models, Utils)**  
> 🎛️ **Interactive Command-Line Interface**


## 📌 Use Cases

This project is ideal for:

- 👨‍💼 **University administrators** managing student/course data
- 👨‍🎓 **Students** enrolling in semester-wise courses
- 👨‍🏫 **Instructors** viewing courses and registered students
- 🧑‍💻 **Java beginners** learning real-world JDBC architecture
- 🎓 Final year academic projects


## 🧠 Tech Stack

| Layer           | Technology               |
|---------------- |--------------------------|  
| Language        | Java (JDK 8/11/17+)      |
| Database        | MySQL                    |
| Design Pattern  | DAO (Data Access Object) |
| Connection      | JDBC                     |
| UI              | Console (CLI)            |
| IDE             |  Eclipse                 |



## 🏗️ Architecture

com.course_registration/
├── dao/ # DAO interfaces
├── daoimpl/ # JDBC implementations
├── model/ # Data models (POJOs)
├── util/ # DBConnection utility
└── main/ # MainApp.java (Entry point)


## 🔐 Role-Based Access

| Role       | Permissions                                                                 |
|------------|------------------------------------------------------------------------------|
| 👨‍💼 Admin    | Manage students, instructors, courses, departments, enrollments             |
| 👩‍🎓 Student  | View & enroll in courses, view enrolled courses                             |
| 👨‍🏫 Instructor | (Extensible) View assigned courses, enrolled students                     |



## 🗃️ Database Schema

Tables:  
- `students`  
- `courses`  
- `departments`  
- `instructors`  
- `enrollments`

Relational structure using foreign keys ensures **referential integrity**.


## 🧪 Test Flow

🎓 Welcome to Course Registration System

Login as:
1. Admin
2. Student
3. Instructor
4. Exit
   
Sample Admin Action:
Add Student > Name: Praveen | Email: praveen@gmail.com | Dept ID: 1
✅ Student added!

Sample Student Action:
Enroll in Course > Course ID: 101 | Semester: Fall 2024
✅ Enrollment successful!
📷 Screenshots / Demo (Optional)


🧾 SQL Dump

CREATE TABLE students (
  studentId INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  departmentId INT,
  FOREIGN KEY (departmentId) REFERENCES departments(departmentId)
);


🔐 Security Notes
❌ Plain password inputs (Consider using hashed passwords in future)

✅ PreparedStatements used (prevents SQL Injection)

🔒 Role-based access control enforced in MainApp.java

🛠️ Setup Instructions

Clone Repository
git clone https://github.com/your-username/course-registration-system.git
cd course-registration-system

Create MySQL DB
CREATE DATABASE course_registration;
-- Run schema.sql here
Update DB Credentials
Edit DBConnection.java with your DB username/password.

Compile & Run

javac com/course_registration/main/MainApp.java
java com.course_registration.main.MainApp

🧱 Future Roadmap
✅ DAO Layer
✅ Role-specific login
✅ Modular code

🔜 Coming Soon:

 JavaFX / Swing GUI

 Instructor dashboard

 Enrollment status tracking

 Email notifications

 RESTful API (Spring Boot)

💡 Learning Outcomes
JDBC CRUD operations

MySQL foreign key usage

Multi-class CLI interaction

MVC + DAO layering

Reusable code principles

🤝 Contributing
Pull requests are welcome!
Open issues or suggestions to improve features or add enhancements.

📢 You can contribute by:

Adding GUI with JavaFX

Extending instructor features

Writing unit tests with JUnit

🧑‍💻 Author
Developed with ❤️ by [D A JERISH MARY SAHANA]
📧 Email: jerishraj25@gmail.com
🌐 GitHub: JerishRaj

🌟 Star the Repo
If this project helped you, please consider starring 🌟 the repository. It helps others discover it and shows your support!
