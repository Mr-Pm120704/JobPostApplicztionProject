package com.ZidioIntern.DTO;

import com.ZidioIntern.Enum.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class PaymentResponseDTO {

	private String transactionId;
	private PaymentStatus paymentStatus;
	private Double amound;
	
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public PaymentStatus getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(PaymentStatus paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public Double getAmound() {
		return amound;
	}
	public void setAmound(Double amound) {
		this.amound = amound;
	}
	
}
