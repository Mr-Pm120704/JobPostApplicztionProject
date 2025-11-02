package com.ZidioIntern.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ZidioIntern.DTO.ApplicationStatusDTO;
import com.ZidioIntern.DTO.CourseStatusDTO;
import com.ZidioIntern.DTO.JobPostStatusDTO;
import com.ZidioIntern.DTO.SubscriptionStatusDTO;
import com.ZidioIntern.DTO.UserDTO;
import com.ZidioIntern.DTO.UserStatusDTO;
import com.ZidioIntern.Entity.User;
import com.ZidioIntern.Enum.Role;
import com.ZidioIntern.Repository.UserRepository;

@Service

public class DashBoardService {
	
    @Autowired
    private UserRepository userRepository;
	
	
public JobPostStatusDTO fetechJobStatus() {
JobPostStatusDTO jobs = new JobPostStatusDTO();

jobs.setTotalInternships(70);
jobs.setTotalJobs(120);
jobs.setTotalPartTimeJobs(70);
jobs.setTotalFullTimeJobs(50);
jobs.setTotalContractualJobs(50);
jobs.setTotalFreelanceJobs(50);

return jobs;

}

public ApplicationStatusDTO fetchApplicationStatus() {

ApplicationStatusDTO applicant = new ApplicationStatusDTO();

applicant.setTotalApplications(500);
applicant.setTotalShortlisted(100);
applicant.setTotalRejected(150);
applicant.setTotalPending(200);

return applicant;

}

//public UserStatusDTO fetchUsersStatus() {
//
//UserStatusDTO users = new UserStatusDTO();
//
//users.setTotalJobseekers(10000);
//users.setTotalRecruiters(500);
//users.setTotalBlockUsers(1000);
//users.setTotalPaidUsers(500);
//
//return users;
//}


public UserStatusDTO fetchUsersStatus() {
    List<User> allUsers = userRepository.findAll();

    int totalJobseekers = (int) allUsers.stream().filter(u -> u.getRole() == Role.JOBSEEKER).count();
    int totalRecruiters = (int) allUsers.stream().filter(u -> u.getRole() == Role.RECRUITER).count();
    int totalBlockUsers = (int) allUsers.stream().filter(u -> !u.isActive()).count();
    int totalPaidUsers = 0; // or calculate if you have payment info

    List<UserDTO> userDTOs = allUsers.stream()
        .map(UserDTO::fromEntity)
        .collect(Collectors.toList());

    UserStatusDTO dto = new UserStatusDTO();
    dto.setTotalJobseekers(totalJobseekers);
    dto.setTotalRecruiters(totalRecruiters);
    dto.setTotalBlockUsers(totalBlockUsers);
    dto.setTotalPaidUsers(totalPaidUsers);
    dto.setUsers(userDTOs); // ✅ attach list here

    return dto;
}



public CourseStatusDTO fetchCourseStatus() {
CourseStatusDTO dto = new CourseStatusDTO();

dto.setTotalCourse(100);
dto.setActiveCourse(70);
dto.setInActiveCourses(30);

return dto;
}

public SubscriptionStatusDTO fetchSubscriptionStatus() {
SubscriptionStatusDTO sub = new SubscriptionStatusDTO();

sub.setTotalPayments(250);
sub.setPaidUsers(150);
sub.setTotalRevenue(10000);
sub.setAverageRevenuePerUser(sub.getTotalRevenue()/sub.getPaidUsers());
sub.setActivePlans(100);

return sub;

}

public void blockUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setActive(false);
    userRepository.save(user);
}

public void unblockUser(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new RuntimeException("User not found"));
    user.setActive(true);
    userRepository.save(user);
}

}