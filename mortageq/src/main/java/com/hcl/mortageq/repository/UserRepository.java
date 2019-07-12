package com.hcl.mortageq.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hcl.mortageq.model.Property; 

public interface UserRepository extends JpaRepository<Property, Long> {

	
	
}
