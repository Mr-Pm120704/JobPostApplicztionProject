package com.ZidioIntern.Entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="recruiter")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Recruiter {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String recruitername;
	@Column(unique=true)
	private String recruiteremail;
	private String phone;
	private String companyName;
	private String designation;
	
	private boolean active;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRecruitername() {
		return recruitername;
	}

	public void setRecruitername(String recruitername) {
		this.recruitername = recruitername;
	}

	public String getRecruiteremail() {
		return recruiteremail;
	}

	public void setRecruiteremail(String recruiteremail) {
		this.recruiteremail = recruiteremail;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
	
	
	

}
