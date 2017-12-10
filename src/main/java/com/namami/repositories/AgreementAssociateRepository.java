package com.namami.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.AgreementAssociate;

@Repository
public interface AgreementAssociateRepository extends JpaRepository<AgreementAssociate, String> {
	
	AgreementAssociate findByAgreementId(Integer agreementId);
	
	List<AgreementAssociate> findByProgressStatus(String status);
	
	List<AgreementAssociate> findByAssociateId(Integer associateId);

}
