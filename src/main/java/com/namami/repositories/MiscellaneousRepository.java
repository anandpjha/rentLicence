package com.namami.repositories;
import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.Agreement;
import com.namami.entity.Miscellaneous;

/**
 * 
 * @author Anand Jha
 *
 */


@Repository
public interface MiscellaneousRepository extends JpaRepository<Miscellaneous, String> {
		
		Miscellaneous findByAgreementId(Integer agreementId);
	
		
}
