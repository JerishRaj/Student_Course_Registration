package com.course_registration.dao;

import com.course_registration.model.Course;
import java.util.List;

public interface CourseDAO {
	
	List<Course> getAllCourses();
	
	Course getCourseById(int courseId);
	
    void addCourse(Course course);
    
    void updateCourse(Course course);
    
    void deleteCourse(int courseId);
    
    List<Course> getCoursesByDepartment(int departmentId);

   
    
}
