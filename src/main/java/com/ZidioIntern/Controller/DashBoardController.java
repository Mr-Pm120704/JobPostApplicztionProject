package com.ZidioIntern.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.ApplicationStatusDTO;
import com.ZidioIntern.DTO.CourseStatusDTO;
import com.ZidioIntern.DTO.JobPostStatusDTO;
import com.ZidioIntern.DTO.SubscriptionStatusDTO;
import com.ZidioIntern.DTO.UserStatusDTO;
import com.ZidioIntern.Service.DashBoardService;

@RestController
@RequestMapping("/api/dashboards")

public class DashBoardController {
	@Autowired
	private DashBoardService dashBoardService;
	
	
	@GetMapping("/jobs")
	public ResponseEntity<JobPostStatusDTO>fetchJobs(){
		return ResponseEntity.ok(dashBoardService.fetechJobStatus());
	}

	@GetMapping("/applications")
	public ResponseEntity<ApplicationStatusDTO>fetchApplication(){
		return ResponseEntity.ok(dashBoardService.fetchApplicationStatus());
	}
	
	@GetMapping("/users")
	public ResponseEntity<UserStatusDTO>fetchUsers(){
		return ResponseEntity.ok(dashBoardService.fetchUsersStatus());
	}
	
	@GetMapping("/courses")
	public ResponseEntity<CourseStatusDTO>getCourseStatus(){
		return ResponseEntity.ok(dashBoardService.fetchCourseStatus());
	}
	
	@GetMapping("/subscriptions")
	public ResponseEntity<SubscriptionStatusDTO>getSubscriptionStatus(){
		return ResponseEntity.ok(dashBoardService.fetchSubscriptionStatus());
	}
	
}
