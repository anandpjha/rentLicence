package com.namami.repositories;
import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.namami.entity.Person;


@Repository("personRepository")
public interface PersonRepository extends JpaRepository<Person, Integer>, JpaSpecificationExecutor<Person> {
		
		List<Person> findByPersonId(Integer personid);
		
		List<Person> findByAgreementId(Integer agreementId);
	
	 

}
