package com.ZidioIntern.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ZidioIntern.DTO.JobSeekerDTO;
import com.ZidioIntern.Entity.JobSeeker;
import com.ZidioIntern.Repository.JobSeekerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class JobSeekerService {
	
	@Autowired
	private JobSeekerRepository jobSeekerRepository;
		
	public JobSeekerDTO createJobseekerProfile(JobSeekerDTO dto) {
		JobSeeker jobseeker = new JobSeeker();
		
		jobseeker.setFullname(dto.getFullname());
		jobseeker.setEmail(dto.getEmail());
		jobseeker.setPhone(dto.getPhone());
		jobseeker.setCollagename(dto.getCollagename());
		jobseeker.setUniversityname(dto.getUniversityname());
		jobseeker.setDegree(dto.getDegree());
		jobseeker.setPassingyear(dto.getPassingyear());
		jobseeker.setResumeURL(dto.getResumeURL());
		
		jobSeekerRepository.save(jobseeker);
		return dto;
	}
	
	public Optional<JobSeekerDTO>getJobSeekerByEmail(String email){
		return jobSeekerRepository.findByEmail(email).map(JobSeek ->{
			JobSeekerDTO dto = new JobSeekerDTO();
			dto.setFullname(JobSeek.getFullname());
			dto.setEmail(JobSeek.getEmail());
			dto.setPhone(JobSeek.getPhone());
			dto.setCollagename(JobSeek.getCollagename());
			dto.setUniversityname(JobSeek.getUniversityname());
			dto.setDegree(JobSeek.getDegree());
			dto.setPassingyear(JobSeek.getPassingyear());
			dto.setResumeURL(JobSeek.getResumeURL());
			return dto;
			
		});
	}
	
	public Optional<JobSeekerDTO>getJobSeekerById(Long id){
		return  jobSeekerRepository.findById(id).map(Jobseek ->{
			JobSeekerDTO dto = new JobSeekerDTO();
			dto.setFullname(Jobseek.getFullname());
			dto.setEmail(Jobseek.getEmail());
			dto.setPhone(Jobseek.getPhone());
			dto.setCollagename(Jobseek.getCollagename());
			dto.setUniversityname(Jobseek.getUniversityname());
			dto.setDegree(Jobseek.getDegree());
			dto.setPassingyear(Jobseek.getPassingyear());
			dto.setResumeURL(Jobseek.getResumeURL());
			return dto;
			
		});
	}
}
