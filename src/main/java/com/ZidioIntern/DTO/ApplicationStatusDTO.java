package com.ZidioIntern.DTO;

public class ApplicationStatusDTO {
	public int totalApplications;
	public int totalShortlisted;
	public int totalRejected;
	public int totalPending;
	
	public int getTotalApplications() {
		return totalApplications;
	}
	public void setTotalApplications(int totalApplications) {
		this.totalApplications = totalApplications;
	}
	public int getTotalShortlisted() {
		return totalShortlisted;
	}
	public void setTotalShortlisted(int totalShortlisted) {
		this.totalShortlisted = totalShortlisted;
	}
	public int getTotalRejected() {
		return totalRejected;
	}
	public void setTotalRejected(int totalRejected) {
		this.totalRejected = totalRejected;
	}
	public int getTotalPending() {
		return totalPending;
	}
	public void setTotalPending(int totalPending) {
		this.totalPending = totalPending;
	}
	
	
//	public ApplicationStatusDTO() {}
//	public ApplicationStatusDTO(int totalApplications,int totalShortlisted,int totalRejected,int totalPending) {
//		
//		this.totalApplications=totalApplications;
//		this.totalShortlisted=totalShortlisted;
//		this.totalRejected=totalRejected;
//		this.totalPending=totalPending;
//	}
}
