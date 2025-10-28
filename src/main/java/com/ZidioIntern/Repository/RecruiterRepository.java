package com.ZidioIntern.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ZidioIntern.Entity.Recruiter;

@Repository

public interface RecruiterRepository extends JpaRepository<Recruiter, Long> {
	
	Optional<Recruiter>findByRecruiteremail(String recruiteremail);
	Optional<Recruiter>findById(Long id);

}
