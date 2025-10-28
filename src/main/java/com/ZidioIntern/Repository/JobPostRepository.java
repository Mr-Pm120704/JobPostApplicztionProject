package com.ZidioIntern.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ZidioIntern.Entity.JobPost;
import com.ZidioIntern.Enum.JobType;

@Repository

public interface JobPostRepository extends JpaRepository<JobPost, Long> {

	Optional<JobPost>findByCompanyName(String companyName);
	Optional<JobPost>findByPostedBy(String postedBy);
	Optional<JobPost>findByJobTitle(String jobTitle);
	Optional<JobPost>findByJobType(JobType jobType);
	Optional<JobPost>findByJobLocation(String jobLocation);
	Optional<JobPost>findByRemote(String remote);
	
}
