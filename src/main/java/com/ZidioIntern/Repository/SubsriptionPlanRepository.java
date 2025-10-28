package com.ZidioIntern.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ZidioIntern.Entity.SubscriptionPlan;

@Repository

public interface SubsriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

}
