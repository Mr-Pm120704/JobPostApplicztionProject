package com.ZidioIntern.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.ApplicationDTO;
import com.ZidioIntern.Enum.JobType;
import com.ZidioIntern.Enum.Status;
import com.ZidioIntern.Service.ApplicatinService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor

public class ApplicationController {
	
	@Autowired
	private ApplicatinService appService;
	
	@PostMapping("/apply")
	public ResponseEntity<String>apply(@RequestBody ApplicationDTO dto){
		appService.apply(dto);
		return ResponseEntity.ok("Application got Submited Successfully !");
	}
	
	@GetMapping("/jobSeeker")
	public ResponseEntity<List<ApplicationDTO>>getJobSeekerApplication(@RequestParam String jobSeekerEmail){
		return ResponseEntity.ok(appService.getByJobSeekerEmail(jobSeekerEmail));
	}
	
	@GetMapping("/recruiter")
	public ResponseEntity<List<ApplicationDTO>>getRecruiterApplication(@RequestParam String recruiterEmail){
		return ResponseEntity.ok(appService.getByRecruiterEmail(recruiterEmail));
	}
	
	@GetMapping("/jobType")
	public ResponseEntity<List<ApplicationDTO>>getJobType(@RequestParam JobType jobType){
		return ResponseEntity.ok(appService.getByJobType(jobType));
	}
	
	@GetMapping("/jobTitle")
	public ResponseEntity<List<ApplicationDTO>>getJobTitle(@RequestParam String jobTitle){
		return ResponseEntity.ok(appService.getByJobTitle(jobTitle));
	}
	
	@PostMapping("/update-status")
	public ResponseEntity<String> updateStatus(@RequestParam Long id,@RequestParam Status status){
		appService.updateStatus(id, status);
		return ResponseEntity.ok("the status is updated");
	}

}
