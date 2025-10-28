package com.ZidioIntern.Service;

import org.springframework.stereotype.Service;

import com.ZidioIntern.DTO.ApplicationStatusDTO;
import com.ZidioIntern.DTO.CourseStatusDTO;
import com.ZidioIntern.DTO.JobPostStatusDTO;
import com.ZidioIntern.DTO.SubscriptionStatusDTO;
import com.ZidioIntern.DTO.UserStatusDTO;

@Service

public class DashBoardService {
	public JobPostStatusDTO fetechJobStatus() {
		JobPostStatusDTO jobs = new JobPostStatusDTO();
		
		jobs.setTotalInternships(70);
		jobs.setTotalJobs(120);
		jobs.setTotalPartTimeJobs(70);
		jobs.setTotalFullTimeJobs(50);
		jobs.setTotalContractualJobs(50);
		jobs.setTotalFreelanceJobs(50);
		
		return jobs;
		
	}
	
	public ApplicationStatusDTO fetchApplicationStatus() {
		
		ApplicationStatusDTO applicant = new ApplicationStatusDTO();
		
		applicant.setTotalApplications(500);
		applicant.setTotalShortlisted(100);
		applicant.setTotalRejected(150);
		applicant.setTotalPending(200);
		
		return applicant;
		
	}
	
	public UserStatusDTO fetchUsersStatus() {
		
		UserStatusDTO users = new UserStatusDTO();
		
		users.setTotalJobseekers(10000);
		users.setTotalRecruiters(500);
		users.setTotalBlockUsers(1000);
		users.setTotalPaidUsers(500);
		
		return users;
	}
	
	public CourseStatusDTO fetchCourseStatus() {
		CourseStatusDTO dto = new CourseStatusDTO();
		
		dto.setTotalCourse(100);
		dto.setActiveCourse(70);
		dto.setInActiveCourses(30);
		
		return dto;
	}
	
	public SubscriptionStatusDTO fetchSubscriptionStatus() {
		SubscriptionStatusDTO sub = new SubscriptionStatusDTO();
		
		sub.setTotalPayments(250);
		sub.setPaidUsers(150);
		sub.setTotalRevenue(10000);
		sub.setAverageRevenuePerUser(sub.getTotalRevenue()/sub.getPaidUsers());
		sub.setActivePlans(100);
		
		return sub;
		
	}
	
}
