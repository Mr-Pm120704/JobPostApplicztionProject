package com.ZidioIntern.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/users/{userId}/block")
    public ResponseEntity<String> blockUser(@PathVariable Long userId) {
        dashBoardService.blockUser(userId);
        return ResponseEntity.ok("User blocked successfully");
    }

    @PostMapping("/users/{userId}/unblock")
    public ResponseEntity<String> unblockUser(@PathVariable Long userId) {
        dashBoardService.unblockUser(userId);
        return ResponseEntity.ok("User unblocked successfully");
    }
	
}
