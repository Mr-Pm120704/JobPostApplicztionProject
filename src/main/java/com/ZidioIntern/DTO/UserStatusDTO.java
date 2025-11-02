package com.ZidioIntern.DTO;

import java.util.List;

public class UserStatusDTO {
	public int totalJobseekers;
	public int totalRecruiters;
	public int totalBlockUsers;
	public int totalPaidUsers;
	private List<UserDTO> users;
	
	public int getTotalJobseekers() {
		return totalJobseekers;
	}
	public void setTotalJobseekers(int totalJobseekers) {
		this.totalJobseekers = totalJobseekers;
	}
	public int getTotalRecruiters() {
		return totalRecruiters;
	}
	public void setTotalRecruiters(int totalRecruiters) {
		this.totalRecruiters = totalRecruiters;
	}
	public int getTotalBlockUsers() {
		return totalBlockUsers;
	}
	public void setTotalBlockUsers(int totalBlockUsers) {
		this.totalBlockUsers = totalBlockUsers;
	}
	public int getTotalPaidUsers() {
		return totalPaidUsers;
	}
	public void setTotalPaidUsers(int totalPaidUsers) {
		this.totalPaidUsers = totalPaidUsers;
	}
    public List<UserDTO> getUsers() {
        return users;
    }
    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }
	
//	public UserStatusDTO() {}
//	public UserStatusDTO(int totalJobseekers,int totalRecruiters,int totalBlockUsers,int totalPaidUsers) {
//		this.totalJobseekers = totalJobseekers;
//		this.totalRecruiters = totalRecruiters;
//		this.totalBlockUsers = totalBlockUsers;
//		this.totalPaidUsers = totalPaidUsers;
//		
//		
//	}
	
	
}
