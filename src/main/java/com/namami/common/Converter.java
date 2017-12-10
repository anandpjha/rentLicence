package com.namami.common;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;



import org.springframework.beans.factory.annotation.Autowired;
/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.namami.bo.AddPersonRequest;
import com.namami.bo.AgreementDto;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.PersonDto;
import com.namami.bo.RegisterUserRequest;
import com.namami.bo.RentDto;
import com.namami.bo.UserDto;
import com.namami.bo.mapper.MiscellaneousMapper;
import com.namami.entity.Agreement;
import com.namami.entity.Miscellaneous;
import com.namami.entity.Person;
import com.namami.entity.RegisteredUser;
import com.namami.service.PersonType;

@Component("converter")
public class Converter {
	
	@Autowired
	MiscellaneousMapper miscellaneousMapper;

	public Person convertPerson(AddPersonRequest addPersonRequest) {
		
		Person person = new Person();
		
		person.setAadharUid(addPersonRequest.getAadharUid());
		person.setPanNo(addPersonRequest.getPanNo());
		person.setEmailAddress(addPersonRequest.getEmail());
		person.setMobNumber(addPersonRequest.getMobNumber());
		person.setPersonName(addPersonRequest.getPersonName());
		person.setPersonAge(addPersonRequest.getPersonAge());
		person.setOccupation(addPersonRequest.getOccupation());
		person.setGender(addPersonRequest.getGender());
		person.setContectAddress(addPersonRequest.getContectAddress());
		
		return person;
		
		
	}

	public PersonDto convertToPersonDto(Person person) {
		
		PersonDto  personDto = new PersonDto();
		personDto.setAadharUid(person.getAadharUid());
		personDto.setPersonName(person.getPersonName());
		personDto.setPersonAge(person.getPersonAge());
		personDto.setOccupation(person.getOccupation());
		personDto.setGender(person.getGender());
		personDto.setPanNo(person.getPanNo());
		personDto.setEmail(person.getEmailAddress());
		personDto.setPersonId(person.getPersonId());
		personDto.setMobNumber(person.getMobNumber());
		personDto.setAgreementId(String.valueOf(person.getAgreementId()));
		personDto.setContectAddress(person.getContectAddress());
		return personDto;
	}

	public RegisteredUser convertToUser(RegisterUserRequest userRequest) {
		
		RegisteredUser registeredUser = new RegisteredUser();
		
		
		registeredUser.setUserEmail(userRequest.getEmail());
		registeredUser.setUserMobile(userRequest.getMobile());
		registeredUser.setPassword(userRequest.getPassword());
		registeredUser.setRoleType(userRequest.getRoleType());
		
		return registeredUser;
	}

	public UserDto convertToUserDto(RegisteredUser user) {
		
		UserDto userDto = new UserDto();
		
		userDto.setUserId(user.getUserId());
		userDto.setName(user.getUserName());
		userDto.setCity(user.getCity());
		userDto.setUserEmail(user.getUserEmail());
		userDto.setUserMobile(user.getUserMobile());
		userDto.setPassword(user.getPassword());
		userDto.setUserRollType(user.getRoleType());
		
		return userDto;
	}

	
	public Agreement convertToAgreement(CreateAgreementReq createAgreementReq) {
	
		ObjectMapper mapper = new ObjectMapper();
	
		Agreement agreement = new Agreement();
		String varyingRent = null;
		
		if(createAgreementReq.isVaryingRent()) {
			try {
				varyingRent = mapper.writeValueAsString(createAgreementReq.getVaryingRents());
			} catch (JsonProcessingException e) {
				// TODO Throw error
				e.printStackTrace();
			}
		}

		agreement.setSecurityDeposit(createAgreementReq.getAdvanceDeposit());
		agreement.setIsSecurityDepositRefundable(createAgreementReq.getIsRefundable());
		agreement.setIsVaryingRent(createAgreementReq.getIsVaryingRent());
		agreement.setAgreementCity(createAgreementReq.getAgreementCity());
		agreement.setAgreementMonthPeriod(Integer.valueOf(createAgreementReq.getAgreementPeriodInMonths()));
		agreement.setAgreementStartDate(createAgreementReq.getAgreementStartDate());
		agreement.setAgreementState(createAgreementReq.getAgreementState());
		agreement.setRentPerMonth(createAgreementReq.getRentPerMonth());
		agreement.setVaryingRent(varyingRent);
		agreement.setLockInPeriod(createAgreementReq.getLockInPeriod());
		agreement.setNoticePeriod(createAgreementReq.getNoticePeriod());
		if(null != createAgreementReq.getAgreementId()){
			agreement.setAgreementId(createAgreementReq.getAgreementId());
		}
		
		agreement.setAgreementType(createAgreementReq.getAgreementType());
		agreement.setChequeNo(createAgreementReq.getChequeNo());
		agreement.setDateOfCheque(createAgreementReq.getDateOfCheque());
		agreement.setNameOfBank(createAgreementReq.getNameOfBank());
		agreement.setBranchName(createAgreementReq.getBranchName());
		
		return agreement;
	}

	public CreateAgreementReq convertToAgreementTerms(Agreement agreement) {
		ObjectMapper mapper = new ObjectMapper();
		
		
		CreateAgreementReq req = new CreateAgreementReq();
		req.setAdvanceDeposit(agreement.getSecurityDeposit());
		req.setAgreementCity(agreement.getAgreementCity());
		req.setAgreementId(agreement.getAgreementId());
		req.setAgreementPeriodInMonths(agreement.getAgreementMonthPeriod());
		//TODO req.setAgreementStartDate(agreementStartDate);
		req.setAgreementState(agreement.getAgreementState());
		req.setIsRefundable(agreement.getIsSecurityDepositRefundable());
		req.setIsVaryingRent(agreement.getIsVaryingRent());
		req.setRentPerMonth(agreement.getRentPerMonth());
		req.setLockInPeriod(agreement.getLockInPeriod());
		req.setAgreementStartDate(agreement.getAgreementStartDate());
		
		req.setAgreementType(agreement.getAgreementType());
		req.setChequeNo(agreement.getChequeNo());
		req.setDateOfCheque(agreement.getDateOfCheque());
		req.setNameOfBank(agreement.getNameOfBank());
		req.setBranchName(agreement.getBranchName());
		
		List<RentDto> varyingRents = null;
		if(agreement.getVaryingRent()!=null) {
			try {
				varyingRents = (List<RentDto>)mapper.readValue(agreement.getVaryingRent(),  new TypeReference<List<RentDto>>(){});
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setVaryingRents(varyingRents);
		}
		
		return req;
	}
	
	public AgreementDto convertToAgreementDto(Agreement agreement) {
		
		AgreementDto agreementDto = new AgreementDto();
		
		agreementDto.setAdvanceDeposit(agreement.getSecurityDeposit());
		agreementDto.setAgreementCity(agreement.getAgreementCity());
		agreementDto.setAgreementId(agreement.getAgreementId());
		agreementDto.setAgreementPeriodInMonths(agreement.getAgreementMonthPeriod());
		agreementDto.setAgreementStartDate(agreement.getAgreementStartDate());
		agreementDto.setAgreementState(agreement.getAgreementState());
		agreementDto.setRentPerMonth(agreement.getRentPerMonth());
		agreementDto.setLockInPeriod(agreement.getLockInPeriod());
		// TODO to set persons & property details
		
		return agreementDto;
	}
	
	public AgreementDto convertToAgreementDetailsDto(Agreement agreement, List<Person> persons, Miscellaneous miscellaneous) {
		
		AgreementDto agreementDto = new AgreementDto();
		
		agreementDto.setAdvanceDeposit(agreement.getSecurityDeposit());
		agreementDto.setAgreementCity(agreement.getAgreementCity());
		agreementDto.setAgreementId(agreement.getAgreementId());
		agreementDto.setAgreementPeriodInMonths(agreement.getAgreementMonthPeriod());
		agreementDto.setAgreementStartDate(agreement.getAgreementStartDate());
		agreementDto.setAgreementState(agreement.getAgreementState());
		agreementDto.setRentPerMonth(agreement.getRentPerMonth());
		agreementDto.setLockInPeriod(agreement.getLockInPeriod());
		agreementDto.setAgreementType(agreement.getAgreementType());
		agreementDto.setChequeNo(agreement.getChequeNo());
		agreementDto.setDateOfCheque(agreement.getDateOfCheque());
		agreementDto.setNameOfBank(agreement.getNameOfBank());
		agreementDto.setBranchName(agreement.getBranchName());
		
		agreementDto.setTerms(convertToAgreementTerms(agreement));
		
		if(null != miscellaneous){
			
			agreementDto.setMiscellaneousTerms(miscellaneousMapper.map(miscellaneous));
		}
		
		//List<Person> persons = agreement.getPersons();
		List<PersonDto> owners = new ArrayList<PersonDto>();
		List<PersonDto> tenets = new ArrayList<PersonDto>();
		List<PersonDto> witness = new ArrayList<PersonDto>();
		
		if(null != persons && persons.size()> 0){
				for (Person person : persons) {
					
					PersonDto personDto = new PersonDto();
					personDto.setAadharUid(person.getAadharUid());
					personDto.setPersonName(person.getPersonName());
					personDto.setPersonAge(person.getPersonAge());
					personDto.setOccupation(person.getOccupation());
					personDto.setGender(person.getGender());
					personDto.setPanNo(person.getPanNo());
					personDto.setEmail(person.getEmailAddress());
					personDto.setPersonId(person.getPersonId());
					personDto.setMobNumber(person.getMobNumber());
					personDto.setAgreementId(String.valueOf(person.getAgreementId()));
					personDto.setContectAddress(person.getContectAddress());
					personDto.setType(person.getType());
					
					if(person.getType().equals("OWNER")){
						owners.add(personDto);
						
					}else if(person.getType().equals("TENANT")){
						
						tenets.add(personDto);
						
					}else if(person.getType().equals("WITNESS")){
						
						witness.add(personDto);
						
					}
					
				}
				agreementDto.setOwners(owners);
				agreementDto.setTenants(tenets);
				agreementDto.setWitnesses(witness);
		}
		return agreementDto;
	}

	public List<AgreementDto> convertToAgreementDtoList(List<Agreement> agreements) {
		List<AgreementDto> agreementDtos = null;
		if(null != agreements){
		agreementDtos = new ArrayList<AgreementDto>();
		
		for (Agreement agreement : agreements) {
			
			AgreementDto agreementDto = new AgreementDto();
			
			agreementDto.setAdvanceDeposit(agreement.getSecurityDeposit());
			agreementDto.setAgreementCity(agreement.getAgreementCity());
			agreementDto.setAgreementId(agreement.getAgreementId());
			agreementDto.setAgreementPeriodInMonths(agreement.getAgreementMonthPeriod());
			agreementDto.setAgreementStartDate(agreement.getAgreementStartDate());
			agreementDto.setAgreementState(agreement.getAgreementState());
			agreementDto.setRentPerMonth(agreement.getRentPerMonth());
			agreementDto.setLockInPeriod(agreement.getLockInPeriod());
			agreementDto.setAgreementType(agreement.getAgreementType());
			agreementDto.setChequeNo(agreement.getChequeNo());
			agreementDto.setDateOfCheque(agreement.getDateOfCheque());
			agreementDto.setNameOfBank(agreement.getNameOfBank());
			agreementDto.setBranchName(agreement.getBranchName());
			agreementDtos.add(agreementDto);
			}
		}
		return agreementDtos;
	}
	
	public Person preparePerson(AddPersonRequest request, PersonType personType ) {
		Person person = new Person();
		person.setPersonId(request.getPersonId());
		person.setPersonName(request.getPersonName());
		person.setPersonAge(request.getPersonAge());
		person.setOccupation(request.getOccupation());
		person.setGender(request.getGender());
		person.setContectAddress(request.getContectAddress());
		
		person.setEmailAddress(request.getEmail());
		person.setMobNumber(request.getMobNumber());
		person.setPanNo(request.getPanNo());
		person.setAadharUid(request.getAadharUid());
		
		person.setAgreementId(request.getAgreementId());
		
		if(null != personType){
		person.setType(personType.toString());
		}
		
		return person;
	}

	
}
