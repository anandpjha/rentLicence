package com.namami.repositories;
import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.AgreementTracker;


@Repository("agreementTrackerRepository")
public interface AgreementTrackerRepository extends JpaRepository<AgreementTracker, String> {
		
	List<AgreementTracker> findByAgreementId(Integer agreementId); 
	
	AgreementTracker findByTrackerId(Integer trackerId);
	
	List<AgreementTracker> findByAgreementTrackerStatusId(Integer agreementTrackerStatusId);

}
