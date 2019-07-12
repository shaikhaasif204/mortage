package com.hcl.mortageq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.mortageq.model.Property; 

public interface PropertyRepository extends JpaRepository<Property, Long> {

	public Property findByPincode(String pincode);
	
}
