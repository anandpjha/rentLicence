package com.namami.bo.mapper;

import org.springframework.stereotype.Component;

import com.namami.bo.AddPostalAddressRequest;
import com.namami.bo.PostalAddressDto;
import com.namami.entity.PostalAddress;

@Component
public class PostalAddressMapper implements BaseMapper<AddPostalAddressRequest, PostalAddress>{

	@Override
	public AddPostalAddressRequest map(PostalAddress postalAddress) {
		AddPostalAddressRequest dto = new AddPostalAddressRequest();
		dto.setBuildingName(postalAddress.getBuildingName());
		dto.setBuiltUpArea(String.valueOf(postalAddress.getBuiltUpArea()));
		dto.setCity(postalAddress.getCity());
		dto.setDistrict(postalAddress.getDistrict());
		dto.setFloorNo(String.valueOf(postalAddress.getFloorNo()));
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
		
		return dto;
	}

	@Override
	public PostalAddress reverseMap(AddPostalAddressRequest postalAddressDto) {
		PostalAddress postalAddress = new PostalAddress();
		postalAddress.setBuildingName(postalAddressDto.getBuildingName());
		postalAddress.setBuiltUpArea(Integer.valueOf(postalAddressDto.getBuiltUpArea()));
		postalAddress.setCity(postalAddressDto.getCity());
		postalAddress.setDistrict(postalAddressDto.getDistrict());
		postalAddress.setFloorNo(Integer.valueOf(postalAddressDto.getFloorNo()));
		postalAddress.setPinCode(postalAddressDto.getPinCode());
		postalAddress.setPostalAddressId(postalAddressDto.getPostalAddressId());
		postalAddress.setPropertyIdentity(postalAddressDto.getPropertyIdentity());
		postalAddress.setPropertyIdentityType(postalAddressDto.getPropertyIdentityType());
		postalAddress.setPropertyNumber(postalAddressDto.getPropertyNumber());
		postalAddress.setPropertyType(postalAddressDto.getPropertyType());
		postalAddress.setRoad(postalAddressDto.getRoad());
		postalAddress.setState(postalAddressDto.getState());
		postalAddress.setTahasil(postalAddressDto.getTahasil());
		postalAddress.setMunicipal(postalAddressDto.getMunicipal());
		
		return postalAddress;
	}


}
