package com.course_registration.model;

public class Instructor {
	
	private int instructorId;
    private String name;
    private int courseId;

    public Instructor() {
    	
    }

    public Instructor(int instructorId, String name, int courseId) {
        this.instructorId = instructorId;
        this.name = name;
        this.courseId = courseId;
    }


	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getInstructorId() {
		return instructorId;
	}

	public String getName() {
		return name;
	}

	public int getCourseId() {
		return courseId;
	}

	@Override
	public String toString() {
		return "Instructor [instructorId=" + instructorId + ", name=" + name + ", courseId=" + courseId + "]";
	}
	
	
	
	
}
