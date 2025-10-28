package com.ZidioIntern.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ZidioIntern.Entity.Admin;

@Repository

public interface AdminRepository extends JpaRepository<Admin,Long>{
	
	List<Admin>findByAdminId(Long adminId);
	List<Admin>findByUserId(Long userId);
	
}
