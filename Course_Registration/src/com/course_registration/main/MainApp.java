package com.course_registration.main;

import java.util.*;

import com.course_registration.daoimpl.*;
import com.course_registration.model.*;

public class MainApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        StudentDAOImpl studentDAO = new StudentDAOImpl();
        CourseDAOImpl courseDAO = new CourseDAOImpl();
        InstructorDAOImpl instructorDAO = new InstructorDAOImpl();
        DepartmentDAOImpl departmentDAO = new DepartmentDAOImpl();
        EnrollmentDAOImpl enrollmentDAO = new EnrollmentDAOImpl();

        System.out.println("🎓 Welcome to Course Registration System");

        while (true) {
            System.out.println("\nLogin as:\n1. Admin\n2. Student\n3. Instructor\n4. Exit");
            System.out.println("Choose One Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (adminLogin(scanner)) {
                        adminMenu(scanner, studentDAO, courseDAO, instructorDAO, departmentDAO, enrollmentDAO);
                    } else {
                        System.out.println("❌ Invalid admin credentials.");
                    }
                    break;
                case 2:
                    Student student = studentLogin(scanner, studentDAO);
                    if (student != null) {
                        studentMenu(scanner, courseDAO, enrollmentDAO, student);
                    } else {
                        System.out.println("❌ Student not found.");
                    }
                    break;
                case 3:
                    Instructor instructor = instructorLogin(scanner, instructorDAO);
                    if (instructor != null) {
                        instructorMenu(scanner, courseDAO, enrollmentDAO, instructor);
                    } else {
                        System.out.println("❌ Instructor not found.");
                    }
                    break;
                case 4:
                    System.out.println("👋 Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }

    // Admin Login
    private static boolean adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String user = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String pass = scanner.nextLine();
        return user.equals("admin") && pass.equals("admin123");
    }

    // Student Login
    private static Student studentLogin(Scanner scanner, StudentDAOImpl studentDAO) {
        System.out.print("Enter your Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return studentDAO.getStudentById(id);
    }

    // Instructor Login
    private static Instructor instructorLogin(Scanner scanner, InstructorDAOImpl instructorDAO) {
        System.out.print("Enter your Instructor ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        return instructorDAO.getInstructorById(id); // Ensure this method exists in InstructorDAOImpl
    }

    // Admin Menu
    private static void adminMenu(Scanner scanner, StudentDAOImpl studentDAO, CourseDAOImpl courseDAO,
                                  InstructorDAOImpl instructorDAO, DepartmentDAOImpl departmentDAO,
                                  EnrollmentDAOImpl enrollmentDAO) {
        while (true) {
            System.out.println("\n📋 Admin Menu:");
            System.out.println("1. Add Student\n2. View Students\n3. Delete Student");
            System.out.println("4. Add Course\n5. View Courses\n6. View Courses by Department");
            System.out.println("7. Add Instructor\n8. View Instructors");
            System.out.println("9. Add Department\n10. View Departments");
            System.out.println("11. View All Enrollments\n12. Back");
            
            System.out.println("Choose One Option: ");

            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1:
                    System.out.print("Name: ");
                    String sname = scanner.nextLine();
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Department ID: ");
                    int deptId = scanner.nextInt();
                    studentDAO.addStudent(new Student(0, sname, email, deptId));
                    break;
                case 2:
                    studentDAO.getAllStudents().forEach(System.out::println);
                    break;
                case 3:
                    System.out.print("Student ID to delete: ");
                    studentDAO.deleteStudent(scanner.nextInt());
                    break;
                case 4:
                    System.out.print("Course name: ");
                    String cname = scanner.nextLine();
                    System.out.print("Credits: ");
                    int credits = scanner.nextInt();
                    System.out.print("Department ID: ");
                    deptId = scanner.nextInt();
                    courseDAO.addCourse(new Course(0, cname, credits, deptId));
                    break;
                case 5:
                    courseDAO.getAllCourses().forEach(System.out::println);
                    break;
                case 6:
                    System.out.print("Enter department ID: ");
                    courseDAO.getCoursesByDepartment(scanner.nextInt()).forEach(System.out::println);
                    break;
                case 7:
                    System.out.print("Instructor name: ");
                    String iname = scanner.nextLine();
                    System.out.print("Course ID: ");
                    int cid = scanner.nextInt();
                    instructorDAO.addInstructor(new Instructor(0, iname, cid));
                    break;
                case 8:
                    instructorDAO.getAllInstructors().forEach(System.out::println);
                    break;
                case 9:
                    System.out.print("Department name: ");
                    departmentDAO.addDepartment(new Department(0, scanner.nextLine()));
                    break;
                case 10:
                    departmentDAO.getAllDepartments().forEach(System.out::println);
                    break;
                case 11:
                    enrollmentDAO.getAllEnrollments().forEach(System.out::println);
                    break;
                case 12:
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }

    // Student Menu
    private static void studentMenu(Scanner scanner, CourseDAOImpl courseDAO, EnrollmentDAOImpl enrollmentDAO, Student student) {
        while (true) {
            System.out.println("\n📚 Student Menu (" + student.getStudentName() + "):");
            System.out.println("1. View Available Courses\n2. Enroll in Course\n3. View My Enrollments\n4. Back");
            System.out.println("Choose One Option: ");
            int opt = scanner.nextInt();
            scanner.nextLine();

            switch (opt) {
                case 1:
                    courseDAO.getAllCourses().forEach(System.out::println);
                    break;
                case 2:
                    System.out.print("Course ID: ");
                    int cid = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Semester: ");
                    String sem = scanner.nextLine();
                    enrollmentDAO.enrollStudent(new Enrollment(0, student.getStudentId(), cid, sem));
                    break;
                case 3:
                    enrollmentDAO.getEnrollmentsByStudentId(student.getStudentId()).forEach(System.out::println);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }

    // Instructor Menu
    private static void instructorMenu(Scanner scanner, CourseDAOImpl courseDAO, EnrollmentDAOImpl enrollmentDAO, Instructor instructor) {
        System.out.println("\n📘 Instructor Menu (" + instructor.getName() + "):");

        while (true) {
            System.out.println("1. View My Course\n2. View Enrolled Students\n3. Back");
            System.out.println("Choose One Option: ");
            int opt = scanner.nextInt();

            switch (opt) {
                case 1:
                    Course c = courseDAO.getCourseById(instructor.getCourseId());
                    System.out.println(c != null ? c : "❌ Course not found.");
                    break;
                case 2:
                    List<Enrollment> enrollments = enrollmentDAO.getAllEnrollments();
                    enrollments.stream()
                        .filter(e -> e.getCourseId() == instructor.getCourseId())
                        .forEach(System.out::println);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }
}
