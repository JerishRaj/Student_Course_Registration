package com.course_registration.model;

public class Student {
	
	private int studentId;
	private String studentName;
	private String studentEmail;
	private int departmentId;
	
	public Student(){
		
	}


	public Student(int studentId, String studentName, String studentEmail, int departmentId) {
		super();
		this.studentId = studentId;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.departmentId = departmentId;
	}
	
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}


	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}


	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}


	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}


	public int getStudentId() {
		return studentId;
	}


	public String getStudentName() {
		return studentName;
	}


	public String getStudentEmail() {
		return studentEmail;
	}


	public int getDepartmentId() {
		return departmentId;
	}


	@Override
	public String toString() {
		return "Students [studentId=" + studentId + ", studentName=" + studentName + ", studentEmail=" + studentEmail
				+ ", departmentId=" + departmentId + "]";
	}

}
