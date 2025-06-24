package com.course_registration.dao;

import com.course_registration.model.Department;
import java.util.List;

public interface DepartmentDAO {
	
	List<Department> getAllDepartments();
	
	Department getDepartmentById(int departmentId);
	
	void addDepartment(Department department);
	
	void updateDepartment(Department department);
	
	void deleteDepartment(int departmentId);

}
