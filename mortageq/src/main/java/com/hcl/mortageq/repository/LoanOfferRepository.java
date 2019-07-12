package com.hcl.mortageq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.mortageq.model.LoanOffer;

public interface LoanOfferRepository extends JpaRepository<LoanOffer, Long> {

}
