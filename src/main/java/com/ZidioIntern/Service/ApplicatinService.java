package com.ZidioIntern.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ZidioIntern.DTO.ApplicationDTO;
import com.ZidioIntern.Entity.Application;
import com.ZidioIntern.Enum.JobType;
import com.ZidioIntern.Enum.Status;
import com.ZidioIntern.Repository.ApplicationRepository;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ApplicatinService {
	
	@Autowired
	private ApplicationRepository applicationRepo;
	
	public String apply(ApplicationDTO dto) {
		if(applicationRepo.findByJobSeekerEmailAndJobId(dto.getJobSeeekerEmail(),dto.getJobId()).isPresent()){
			throw new RuntimeException("you already have applied for this job");
		}
		
		Application app = new Application();
		app.setJobId(dto.getJobId());
		app.setJobSeekerName(dto.getJobSeekerName());
		app.setJobSeekerEmail(dto.getJobSeeekerEmail());
		app.setJobTitle(dto.getJobTitle());
		app.setJobType(dto.getJobType());
		app.setRecruiterEmail(dto.getRecruiterEmail());
		app.setStatus(dto.getStatus());
		app.setAppliedAt(dto.getAppliedAt());
		applicationRepo.save(app);
		
		return "you applied successfully for this job";
	}
	
	public List<ApplicationDTO>getByJobSeekerEmail(String jobSeekerEmail){
		return applicationRepo.findByJobSeekerEmail(jobSeekerEmail).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public List<ApplicationDTO>getByRecruiterEmail(String recruiterEmail){
		return applicationRepo.findByRecruiterEmail(recruiterEmail).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	public List<ApplicationDTO>getByJobTitle(String jobTitle){
		return applicationRepo.findByJobTitle(jobTitle).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	public List<ApplicationDTO>getByJobType(JobType jobType){
		return applicationRepo.findByJobType(jobType).stream().map(this::mapToDTO).collect(Collectors.toList());
	}
	
	public void updateStatus(Long id,Status status){
		Application app = applicationRepo.findById(id).orElseThrow(()-> new RuntimeException("Application not found !"));
		app.setStatus(status);
		applicationRepo.save(app);
	}
	
	
	private ApplicationDTO mapToDTO(Application app) {
		ApplicationDTO dto = new ApplicationDTO();
		app.setJobId(dto.getJobId());
		app.setJobSeekerEmail(dto.getJobSeeekerEmail());
		app.setJobSeekerName(dto.getJobSeekerName());
		app.setJobTitle(dto.getJobTitle());
		app.setJobType(dto.getJobType());
		app.setRecruiterEmail(dto.getRecruiterEmail());
		app.setStatus(dto.getStatus());
		app.setAppliedAt(dto.getAppliedAt());
		
		
		return dto;
	}
	
    public List<Application> getApplicationsByRecruiterEmail(String recruiterEmail) {
        return applicationRepo.findByRecruiterEmail(recruiterEmail);
    }

}
