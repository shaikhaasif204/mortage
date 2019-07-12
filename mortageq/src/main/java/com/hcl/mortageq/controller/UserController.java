package com.hcl.mortageq.controller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.mortageq.model.OfferDetails;
import com.hcl.mortageq.model.User;
import com.hcl.mortageq.service.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/search")
	public ResponseEntity<OfferDetails> searchOffer(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		HttpStatus httpStatus = HttpStatus.OK;
		OfferDetails offerDetails = null;
		LocalDate today = LocalDate.now(); // Today's date
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		// convert String to LocalDate
		LocalDate dateOfbirth = LocalDate.parse(user.getDob(), formatter);
		Period p = Period.between(dateOfbirth, today);
		double age = p.getYears();
		if (age < 25) {
			offerDetails = new OfferDetails();
			offerDetails.setMsg("User is not eligiable for the loan, age should be equal or greater than 25 yrs");
		} else if (user.getSalary() < 10000) {
			offerDetails = new OfferDetails();
			offerDetails.setMsg("User is not eligiable for the loan, salary should be equal or greater than 10000 rs");
		} else {
			offerDetails = userService.searchForProperty(user);
		}
		return new ResponseEntity<>(offerDetails, headers, httpStatus);
	}

}
