package com.namami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.namami.aspect.SecureCustomer;
import com.namami.bo.PaymentRequest;
import com.namami.bo.PaymentResponse;
import com.namami.common.RestURIConstants;
import com.namami.service.PaymentService;
/**
 * @author Anand Jha
 * 
 */

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;

	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_PAYMENT_DETAIL, method = RequestMethod.GET)
	@SecureCustomer
	public PaymentResponse retrievePaymentDetail(@PathVariable final String agreementId){
		
		return paymentService.retrievePaymentDetail(agreementId);
		
	}
	
	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.UPDATE_PAYMENT_STATUS, method = RequestMethod.GET)
	@SecureCustomer
	public String updatePaymentStatus(@PathVariable final String agreementId){
		
		return paymentService.updatePaymentStatus(agreementId);
		
	}
	
	

}
