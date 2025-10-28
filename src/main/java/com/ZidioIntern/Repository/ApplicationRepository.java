package com.ZidioIntern.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ZidioIntern.Entity.Application;
import com.ZidioIntern.Enum.JobType;

@Repository

public interface ApplicationRepository extends JpaRepository<Application, Long>{
	
	List<Application>findByJobSeekerEmail(String jobSeekerEmail);
	List<Application>findByRecruiterEmail(String recruiterEmail);
	Optional<Application>findByJobSeekerEmailAndJobId(String jobSeekerEmail, Long jobId);
	Optional<Application>findByJobTitle(String jobTilte);
	Optional<Application>findByJobType(JobType jobType);
	

}
