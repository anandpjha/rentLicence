package com.namami.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AddPostalAddressRequest;
import com.namami.bo.PostalAddressDto;
import com.namami.bo.mapper.PostalAddressMapper;
import com.namami.entity.Agreement;
import com.namami.entity.PostalAddress;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.AgreementRepository;
import com.namami.repositories.PostalAddressRepository;

/**
 * @author Jagdish Kolhe
 * 
 */
@Service
public class PostalAddressDaoImpl implements PostalAddressDao {

	@Autowired
	PostalAddressRepository postalAddressRepository;

	@Autowired
	AgreementRepository agreementRepository;

	@Autowired
    private PostalAddressMapper mapper = null;
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public Integer addPostalAddress(AddPostalAddressRequest addPostalAddressRequest) {
		Integer postalAddressId = 0;
		try{
		
			PostalAddress postalAddress = mapper.reverseMap(addPostalAddressRequest);
		
			postalAddressId = postalAddressRepository.saveAndFlush(postalAddress).getPostalAddressId();
		
		
			Agreement agreement = agreementRepository.findOne(addPostalAddressRequest.getAgreementId().toString());
			agreement.setPostalAddressId(postalAddressId);
			agreementRepository.saveAndFlush(agreement);
		}catch (Exception e){
			e.printStackTrace();
			SLF4JLOGGER.error("addPostalAddress :: Error while saving postal adress in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while saving postal Adress in DB" );
			
		}
		
		return postalAddressId;
	}
	
	@Override
	public PostalAddressDto retriveProperty(Integer propertyId) {
		PostalAddressDto dto = null;
		try{
			
		PostalAddress postalAddress = postalAddressRepository.findOne(propertyId);
		
		if(null == postalAddress){
			
			throw new NotFoundException(GlobalErrorConstants.NOT_FOUND_EXCEPTION.getValue() , "Error while retrieve postal Adress in DB" );
			
		}
		
		dto = new PostalAddressDto();
		dto.setBuildingName(postalAddress.getBuildingName());
		dto.setBuiltUpArea(postalAddress.getBuiltUpArea());
		dto.setCity(postalAddress.getCity());
		dto.setDistrict(postalAddress.getDistrict());
		dto.setFloorNo(postalAddress.getFloorNo());
		dto.setPinCode(postalAddress.getPinCode());
		dto.setPostalAddressId(postalAddress.getPostalAddressId());
		dto.setPropertyIdentity(postalAddress.getPropertyIdentity());
		dto.setPropertyIdentityType(postalAddress.getPropertyIdentityType());
		dto.setPropertyNumber(postalAddress.getPropertyNumber());
		dto.setPropertyType(postalAddress.getPropertyType());
		dto.setRoad(postalAddress.getRoad());
		dto.setState(postalAddress.getState());
		dto.setTahasil(postalAddress.getTahasil());
		dto.setMunicipal(postalAddress.getMunicipal());
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveProperty :: Error while retrieve postal adress in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrieve postal Adress in DB" );
			
		}
		return dto;
	}

}
