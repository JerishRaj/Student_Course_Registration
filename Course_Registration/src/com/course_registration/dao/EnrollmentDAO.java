package com.course_registration.dao;

import com.course_registration.model.Enrollment;
import java.util.List;

public interface EnrollmentDAO {

    List<Enrollment> getAllEnrollments();

    Enrollment getEnrollmentById(int enrollmentId);

    void addEnrollment(Enrollment enrollment);

    void updateEnrollment(Enrollment enrollment);

    void deleteEnrollment(int enrollmentId);

    
    void enrollStudent(Enrollment enrollment);

    
    List<Enrollment> getEnrollmentsByStudentId(int studentId);
}
