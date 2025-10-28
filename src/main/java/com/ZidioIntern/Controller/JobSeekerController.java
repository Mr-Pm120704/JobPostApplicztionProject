package com.ZidioIntern.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.JobSeekerDTO;
import com.ZidioIntern.Service.JobSeekerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobseeker")
@RequiredArgsConstructor

public class JobSeekerController {
	@Autowired
	private JobSeekerService jobSeekerService;
	
	@PostMapping
	public ResponseEntity<JobSeekerDTO>createJobSeekerProfile(@RequestBody JobSeekerDTO dto){
		return ResponseEntity.ok(jobSeekerService.createJobseekerProfile(dto));
	}
	
	@GetMapping("/email/{email}")
	public ResponseEntity<Optional<JobSeekerDTO>> getJobSeekerByEmail(@PathVariable String email){
		return ResponseEntity.ok(jobSeekerService.getJobSeekerByEmail(email));
//		return jobSeekerService.getJobSeekerByEmail(email).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<JobSeekerDTO>> getJobSeekerById(@PathVariable Long id){
		return ResponseEntity.ok(jobSeekerService.getJobSeekerById(id));
//		return jobSeekerService.getJobSeekerById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}
}
