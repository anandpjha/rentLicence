package com.namami.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.namami.aspect.SecureCustomer;
import com.namami.bo.AddPostalAddressRequest;
import com.namami.common.RestURIConstants;
import com.namami.service.PostalAddressService;

/**
 * @author Anand Jha
 * 
 */

@RestController
public class PostalAddressController {
	
	@Autowired
	private PostalAddressService postalAddressService;
	
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.ADD_POSTAL_ADDRESS, method = RequestMethod.POST)
	@SecureCustomer
	public void addPostalAddressToAgreement(@RequestBody final AddPostalAddressRequest addPostalAddressRequest){
		
		postalAddressService.addPostalAddressToAgreement(addPostalAddressRequest);
	}
	
}
