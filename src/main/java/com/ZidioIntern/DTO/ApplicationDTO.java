package com.ZidioIntern.DTO;

import java.time.LocalDateTime;

import com.ZidioIntern.Enum.JobType;
import com.ZidioIntern.Enum.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApplicationDTO {
	
	private String jobSeekerName;
	private String jobSeeekerEmail;
	private String recruiterEmail;
	private Long jobId;
	private String jobTitle;
	private JobType jobType;
	private Status status;
	private LocalDateTime appliedAt;
	
	
	public String getJobSeekerName() {
		return jobSeekerName;
	}
	public void setJobSeekerName(String jobSeekerName) {
		this.jobSeekerName = jobSeekerName;
	}
	public String getJobSeeekerEmail() {
		return jobSeeekerEmail;
	}
	public void setJobSeeekerEmail(String jobSeeekerEmail) {
		this.jobSeeekerEmail = jobSeeekerEmail;
	}
	public String getRecruiterEmail() {
		return recruiterEmail;
	}
	public void setRecruiterEmail(String recruiterEmail) {
		this.recruiterEmail = recruiterEmail;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public String getJobTitle() {
		return jobTitle;
	}
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	public JobType getJobType() {
		return jobType;
	}
	public void setJobType(JobType jobType) {
		this.jobType = jobType;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public LocalDateTime getAppliedAt() {
		return appliedAt;
	}
	public void setAppliedAt(LocalDateTime appliedAt) {
		this.appliedAt = appliedAt;
	}	

}
