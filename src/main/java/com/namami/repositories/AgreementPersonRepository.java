package com.namami.repositories;
import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.AgreementPerson;


@Repository
public interface AgreementPersonRepository extends JpaRepository<AgreementPerson, String> {
		
	List<AgreementPerson> findByPersonAgreementId(Integer personAgreementId);

}
