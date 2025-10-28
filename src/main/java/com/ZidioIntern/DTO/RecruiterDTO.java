package com.ZidioIntern.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class RecruiterDTO {
	
	private String recruitername;
	private String recruiteremail;
	private String phone;
	private String companyName;
	private String designation;
	
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
	
}
