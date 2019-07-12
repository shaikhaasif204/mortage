package com.hcl.mortageq.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.mortageq.model.LoanOffer;
import com.hcl.mortageq.model.OfferDetails;
import com.hcl.mortageq.model.Property;
import com.hcl.mortageq.model.User;
import com.hcl.mortageq.repository.LoanOfferRepository;
import com.hcl.mortageq.repository.PropertyRepository;

@Service
public class UserService {

	@Autowired
	PropertyRepository propertyRepository;

	@Autowired
	LoanOfferRepository loanOfferRepository;

	public OfferDetails searchForProperty(User user) {
		OfferDetails offerDetails = new OfferDetails();
		Property property = propertyRepository.findByPincode(user.getProperyPincode());
		List<LoanOffer> allLoanOffers = loanOfferRepository.findAll();
		double totalPrize = user.getPropertyAreaSize() * property.getPrizePerSqft();
		
		// prepare for offer details
		if(totalPrize < 500000) {
			offerDetails.setMsg("User is not eligiable for the loan, total loan amount should be greater that 5 lacks");
			
		}else {
		offerDetails.setPincode(property.getPincode());
		offerDetails.setPrizePerSqft(property.getPrizePerSqft());
		offerDetails.setTotalPrize(totalPrize);
		List<LoanOffer> eligibleOffers = allLoanOffers.stream().filter(lo -> lo.getLoanAmount() <= totalPrize * 0.8)
				.collect(Collectors.toList());
		offerDetails.setEligibleOffers(eligibleOffers);
		}
		return offerDetails;

	}
}
