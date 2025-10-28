package com.ZidioIntern.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="course")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long id;
	
	public String courseTitle;
	public String courseDescription;
	public String courCatogory;
	public String adminId;
	private LocalDateTime createdAt;
	private boolean active;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseDescription() {
		return courseDescription;
	}
	public void setCourseDescription(String courseDescription) {
		this.courseDescription = courseDescription;
	}
	public String getCourCatogory() {
		return courCatogory;
	}
	public void setCourCatogory(String courCatogory) {
		this.courCatogory = courCatogory;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
