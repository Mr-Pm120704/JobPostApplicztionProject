package com.ZidioIntern.Controller;


import java.util.Base64;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ZidioIntern.DTO.EmailRequestDTO;
import com.ZidioIntern.Service.EmailService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor

public class EmailController {


	@Autowired
	private EmailService emailService;
	
	@PostMapping("/send")
	public ResponseEntity<String>sendEmail(@RequestBody EmailRequestDTO dto ){
		emailService.sendEmail(dto, null);
		return ResponseEntity.ok("Email sent successfully");
	}
	
//	@PostMapping("/send-invoice")
//	public ResponseEntity<String>sendInvoice(@RequestParam String to, @RequestParam String subject, @RequestParam String body, @RequestParam String filePath){
//		emailService.sendEmail(new EmailRequestDTO(to,subject,body), new file(filePath));
//		return ResponseEntity.ok("Invoice Mail Send Successfully");
//	}
	
	@PostMapping("/send-invoice")
	public String sendInvoice(@RequestBody Map<String, String> payLoad) {
		String to = payLoad.get("to");
		String subject = payLoad.get("subject");
		String body = payLoad.get("body");
		String pdfBase64 = payLoad.get("pdfBytes");
		
		byte[] pdfBytes = Base64.getDecoder().decode(pdfBase64);
		
		EmailRequestDTO request =new EmailRequestDTO(to,subject,body);
		emailService.sendEmail(request, pdfBytes);
		
		return "Invoice Mail Send Successfully";
	}
	
}
