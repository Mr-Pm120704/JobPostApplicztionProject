package com.ZidioIntern.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentRequestDTO {

	private Long userId;
	private Long planId;
	private Double amound;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getPlanId() {
		return planId;
	}
	public void setPlanId(Long planId) {
		this.planId = planId;
	}
	public Double getAmound() {
		return amound;
	}
	public void setAmound(Double amound) {
		this.amound = amound;
	}
	
}
