package com.namami.repositories;

import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.PostalAddress;

/**
 * 
 * @author Jagdish Kolhe
 *
 */


@Repository
public interface PostalAddressRepository extends JpaRepository<PostalAddress, Integer> {
		
		List<PostalAddress> findBypostalAddressId(Integer postalAddressId);
	 

}
