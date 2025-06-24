package com.course_registration.model;

public class Department {
	
	private int departmentId;
	private String departmentName;
	
	public Department(){
		
	}

	
	public Department(int departmentId, String departmentName) {
		super();
		this.departmentId = departmentId;
		this.departmentName = departmentName;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	public String getDepartmentName() {
		return departmentName;
	}


	@Override
	public String toString() {
		return "Department [departmentId=" + departmentId + ", departmentName=" + departmentName + "]";
	}	

}
