package com.namami.dao;

import com.namami.bo.AddPostalAddressRequest;
import com.namami.bo.PostalAddressDto;

/**
 * @author Jagdish Kolhe
 * 
 */
public interface PostalAddressDao {

	Integer addPostalAddress(AddPostalAddressRequest addPostalAddressRequest);

	PostalAddressDto retriveProperty(Integer propertyId);
}
