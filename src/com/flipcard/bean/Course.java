package com.flipcard.bean;

public class Course {
	private long id;
	private String name;
	private String description; 
	private int courseFee;
	
	public Course() {	
	}
	
	public Course(long id, String name, String descripton, int courseFee) {
		this.setDescription(descripton);
		this.setId(id);
		this.setName(name);
		this.setCourseFee(courseFee);
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCourseFee() {
		return courseFee;
	}
	public void setCourseFee(int courseFee) {
		this.courseFee = courseFee;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String desciption) {
		this.description = desciption;
	}

}
