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

		// prepare for offer details
		offerDetails.setPincode(property.getPincode());
		offerDetails.setPrizePerSqft(property.getPrizePerSqft());
		double totalPrize = user.getPropertyAreaSize() * property.getPrizePerSqft();
		offerDetails.setTotalPrize(totalPrize);
		List<LoanOffer> eligibleOffers = allLoanOffers.stream().filter(lo -> lo.getLoanAmount() <= totalPrize * 0.8)
				.collect(Collectors.toList());
		offerDetails.setEligibleOffers(eligibleOffers);
		return offerDetails;

	}
}
