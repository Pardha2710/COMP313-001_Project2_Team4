package com.college;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Courses")
public class Courses {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "course_id")
	public int courseId;
	@NotNull
	@Column(name = "course_code")
	public String courseCode;
	@NotNull
	@Column(name = "course_name")
	public String courseName;
	@NotNull
	@Column(name = "course_availability")
	public String courseAvailability;

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseAvailability() {
		return courseAvailability;
	}

	public void setCourseAvailability(String courseAvailability) {
		this.courseAvailability = courseAvailability;
	}

	public Courses() {
		super();

	}

	public Courses(int courseId, String courseCode, String courseName, String courseAvailability) {
		super();
		this.courseId = courseId;
		this.courseCode = courseCode;
		this.courseName = courseName;
		this.courseAvailability = courseAvailability;
	}

}
