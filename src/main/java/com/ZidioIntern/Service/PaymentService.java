package com.ZidioIntern.Service;

//import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.ZidioIntern.DTO.PaymentRequestDTO;
import com.ZidioIntern.DTO.PaymentResponseDTO;
import com.ZidioIntern.Entity.Payment;
import com.ZidioIntern.Enum.PaymentStatus;
import com.ZidioIntern.Repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private InvoiceService invoiceServ;
	
	public PaymentResponseDTO processPayment(PaymentRequestDTO dto) {
		Payment pay = new Payment();
		pay.setUserId(dto.getUserId());
		pay.setPlanId(dto.getPlanId());
		pay.setAmound(dto.getAmound());
		pay.setPaymentStatus(PaymentStatus.SUCCESS);
		pay.setTransactionId(UUID.randomUUID().toString());
		pay.setTimeStamp(LocalDateTime.now());
		
		paymentRepository.save(pay);
		
		byte[]pdfbytes = invoiceServ.generateInvoice(pay);
		
//		String filePath = "invoices/invoice-"+pay.getTransactionId()+".pdf";
//		try(FileOutputStream fos = new FileOutputStream(filePath)){
//			fos.write(pdfbytes);
//		}catch(Exception e) {
//			throw new RuntimeException("ERROR IN SAVING THE INVOICE PDF",e);
//		}
//		
////		call notification service
//		RestTemplate restTemplate=new RestTemplate();
//		String notifyUrl = "http://localhost:6060/api/notify/send-invoice";
//		restTemplate.postForObject(notifyUrl+"?to=user@gmail.com&subject=your Invoice&body=please find by attached ", null,String.class);
//		
		MultiValueMap<String, Object> payload = new LinkedMultiValueMap<>();
		payload.add("to","user@gmail.com");
		payload.add("subject","Your ZIDIOConnect Invoice");
		payload.add("body", "Dear User \n\n Thank you For Your Payment Please Find Your Invoice");
		payload.add("pdfBytes", Base64.getEncoder().encodeToString(pdfbytes));
		
		HttpHeaders header = new HttpHeaders();
		header.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<MultiValueMap<String, Object>>requestEntity = new HttpEntity<>(payload,header);
		
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.postForObject("http://localhost:6060/api/notify/send-invoice", requestEntity, String.class);
		
		
		
		PaymentResponseDTO response = new PaymentResponseDTO();
		response.setTransactionId(pay.getTransactionId());
		response.setPaymentStatus(pay.getPaymentStatus());
		response.setAmound(pay.getAmound());
		return response;
	}

}
