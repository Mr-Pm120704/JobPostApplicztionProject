package com.ZidioIntern.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ZidioIntern.Entity.Payment;

@Repository

public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	List<Payment>findByuserId(Long userId);

}
