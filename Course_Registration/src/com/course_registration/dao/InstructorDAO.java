package com.course_registration.dao;

import com.course_registration.model.Instructor;
import java.util.List;

public interface InstructorDAO {
	
	List<Instructor> getAllInstructors();
	
	Instructor getInstructorById(int instructorId);
	
    void addInstructor(Instructor instructor);
    
    void updateInstructor(Instructor instructor);
    
    void deleteInstructor(int instructorId);
    
}
