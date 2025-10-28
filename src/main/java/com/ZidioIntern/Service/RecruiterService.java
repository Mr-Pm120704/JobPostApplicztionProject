package com.ZidioIntern.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ZidioIntern.DTO.RecruiterDTO;
import com.ZidioIntern.Entity.Recruiter;
import com.ZidioIntern.Repository.RecruiterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecruiterService {
	
	@Autowired
	private RecruiterRepository recruiterRepo;
	
	public RecruiterDTO createRecruiterProfile(RecruiterDTO dto) {
		Recruiter recruiter = new Recruiter();
		
		recruiter.setRecruitername(dto.getRecruitername());
		recruiter.setRecruiteremail(dto.getRecruiteremail());
		recruiter.setCompanyName(dto.getCompanyName());
		recruiter.setPhone(dto.getPhone());
		recruiter.setDesignation(dto.getDesignation());
		
		Recruiter saved=recruiterRepo.save(recruiter);
		
		
		return mapToDTO(saved);
	}
	public Optional<RecruiterDTO>getRecruiterByEmail(String recruiteremail){
		return recruiterRepo.findByRecruiteremail(recruiteremail).map(this::mapToDTO);
	}
	public Optional<RecruiterDTO>getRecruiterById(Long id){
		return recruiterRepo.findById(id).map(this::mapToDTO);
	}
	
	private RecruiterDTO mapToDTO (Recruiter req) {
		
		RecruiterDTO dto = new RecruiterDTO();
		dto.setRecruitername(req.getRecruitername());
		dto.setPhone(req.getPhone());
		dto.setRecruiteremail(req.getRecruiteremail());
		dto.setCompanyName(req.getCompanyName());
		dto.setDesignation(req.getDesignation());
		
		return dto;
	}
	

}

