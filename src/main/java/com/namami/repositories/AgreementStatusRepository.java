package com.namami.repositories;
/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.AgreementStatus;


@Repository("agreementStatusRepository")
public interface AgreementStatusRepository extends JpaRepository<AgreementStatus, String> {
		
	AgreementStatus findByAgreementId(Integer agreementId); 
	
	AgreementStatus findByStatusId(Integer statusId);
	
	
	
}
