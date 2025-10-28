package com.ZidioIntern.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.PaymentRequestDTO;
import com.ZidioIntern.DTO.PaymentResponseDTO;
import com.ZidioIntern.Entity.Payment;
import com.ZidioIntern.Entity.SubscriptionPlan;
import com.ZidioIntern.Repository.PaymentRepository;
import com.ZidioIntern.Repository.SubsriptionPlanRepository;
import com.ZidioIntern.Service.InvoiceService;
import com.ZidioIntern.Service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor

public class PaymentController {

	@Autowired
	private PaymentService paymentServ;
	
	@Autowired
	private SubsriptionPlanRepository subPlanRepo;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Autowired
	private InvoiceService invoiceService;
	
	@PostMapping("/process")
	public ResponseEntity<PaymentResponseDTO>process(@RequestBody PaymentRequestDTO dto){
		return ResponseEntity.ok(paymentServ.processPayment(dto));
	}
	
	@GetMapping("/plans")
	public ResponseEntity<List<SubscriptionPlan>>getPlans(){
		return ResponseEntity.ok(subPlanRepo.findAll());
	}
	@PostMapping ResponseEntity<SubscriptionPlan>createPlans(@RequestBody SubscriptionPlan plan){
		return ResponseEntity.ok(subPlanRepo.save(plan));
	}
	
	@GetMapping("/history/{userId}")
	public ResponseEntity<List<Payment>> history(@PathVariable Long userId){
		return ResponseEntity.ok(paymentRepo.findByuserId(userId));
	}
	
	 @GetMapping("/invoice/{paymentId}")
		public ResponseEntity<byte[]>downloadInvoice(@PathVariable Long paymentId){
			
			Payment pay = paymentRepo.findById(paymentId).orElseThrow(()-> new RuntimeException("Payment not found"));
			byte[] pdfBytes = invoiceService.generateInvoice(pay);
			
			return ResponseEntity.ok()
					.header("Content-Diposition", "Attachment; fileNameInvoice-" +pay.getTransactionId() + ".pdf")
					.contentType(org.springframework.http.MediaType.APPLICATION_PDF).body(pdfBytes);
		} 
	
}
