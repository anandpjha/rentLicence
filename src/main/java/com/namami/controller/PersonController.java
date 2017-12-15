package com.namami.controller;
/**
 * @author Anand Jha
 * 
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.namami.bo.AddPersonRequest;
import com.namami.bo.PersonDto;
import com.namami.common.RestURIConstants;
import com.namami.service.PersonService;
import com.namami.service.PersonType;


@RestController
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * This method is used to add owner 
	 * @param userId
	 * @param addPersonRequest
	 */
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = RestURIConstants.ADD_OWNER, method = RequestMethod.POST)
	public Integer addOwner(@RequestBody final AddPersonRequest addPersonRequest) {
		
		PersonDto person = personService.addPerson(addPersonRequest, PersonType.OWNER);
		
		return person.getPersonId(); 
	}

	/**
	 * This method is used to add owner 
	 * @param userId
	 * @param addPersonRequest
	 */
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = RestURIConstants.ADD_TENANT, method = RequestMethod.POST)
	public Integer addTenant(@RequestBody final AddPersonRequest addPersonRequest) {
		
		return personService.addPerson(addPersonRequest, PersonType.TENANT).getPersonId(); 
	}

	/**
	 * This method is used to add owner 
	 * @param userId
	 * @param addPersonRequest
	 */
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = RestURIConstants.ADD_WITNESS, method = RequestMethod.POST)
	public Integer addWitness(@RequestBody final AddPersonRequest addPersonRequest) {
		
		return personService.addPerson(addPersonRequest, PersonType.WITNESS).getPersonId(); 
	}


	@CrossOrigin(origins = "*")
	@RequestMapping(value = RestURIConstants.GET_PERSON, method = RequestMethod.GET)
	public PersonDto retrivePerson(@PathVariable final String personId){
		
		return personService.retrivePerson(personId);
		
	}
	
	
	/**
	 * This method is used to add owner 
	 * @param userId
	 * @param addPersonRequest
	 */
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = RestURIConstants.UPDATE_PERSON, method = RequestMethod.POST)
	public Integer updatePerson(@RequestBody final AddPersonRequest addPersonRequest) {
		
		PersonDto person = personService.updatePerson(addPersonRequest); 
		return person.getPersonId();
	}

	/**
	 * This method is used to delete person 
	 * @param userId
	 * @param addPersonRequest
	 */
	@CrossOrigin(origins = "*")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	@RequestMapping(value = RestURIConstants.DELETE_PERSON, method = RequestMethod.POST)
	public void deletePerson(@RequestBody final AddPersonRequest addPersonRequest) {
		
		personService.deletePerson(addPersonRequest); 
	}
	

}
