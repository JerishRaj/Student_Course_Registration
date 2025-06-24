package com.course_registration.dao;

import com.course_registration.model.Student;
import java.util.List;

public interface StudentDAO {

    List<Student> getAllStudents();

    Student getStudentById(int studentId);

    void addStudent(Student student);

    void updateStudent(Student student);

    void deleteStudent(int studentId);
}
