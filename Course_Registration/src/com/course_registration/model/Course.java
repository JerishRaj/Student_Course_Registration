package com.course_registration.model;

public class Course {
	
	private int courseId;
    private String courseName;
    private int credits;
    private int departmentId;
    
    public Course() {
    	
    }
    
    public Course(int courseId, String courseName, int credits, int departmentId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.departmentId = departmentId;
    }

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}

	public int getCourseId() {
		return courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public int getCredits() {
		return credits;
	}

	public int getDepartmentId() {
		return departmentId;
	}

	@Override
	public String toString() {
		return "Course [courseId=" + courseId + ", courseName=" + courseName + ", credits=" + credits
				+ ", departmentId=" + departmentId + "]";
	}

    
    


}
