package com.namami.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AgreementDto;
import com.namami.bo.CreateAgreementReq;
import com.namami.bo.MiscellaneousTermsReq;
import com.namami.bo.PostalAddressDto;
import com.namami.bo.mapper.MiscellaneousMapper;
import com.namami.common.Converter;
import com.namami.entity.Agreement;
import com.namami.entity.AgreementAssociate;
import com.namami.entity.Miscellaneous;
import com.namami.entity.Person;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.AgreementAssociateRepository;
import com.namami.repositories.AgreementRepository;
import com.namami.repositories.AgreementTrackerRepository;
import com.namami.repositories.MiscellaneousRepository;
import com.namami.repositories.PersonRepository;

/**
 * @author Anand Jha
 * 
 */
@Service
public class AgreementDaoImpl implements AgreementDao {
	
	
	
	@Autowired
	private AgreementRepository agreementRepository;
	
	@Autowired
	private MiscellaneousRepository miscellaneousRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private PostalAddressDao postalAddressDao;
		
	@Autowired
	private Converter converter;
	
	@Autowired
	MiscellaneousMapper miscellaneousMapper;
	
	@Autowired
	AgreementTrackerRepository agreementTrackerRepository;
	
	@Autowired
	AgreementAssociateRepository agreementAssociateRepository;
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(AgreementDaoImpl.class);

	@Override
	public Agreement createBlankAgreement(String createdByUserId) {
		//TODO DAO should not return entity
		Agreement agreement = new Agreement();
		agreement.setCreatedBy(createdByUserId);
		
		return agreementRepository.saveAndFlush(agreement);
	}

	@Override
	public Integer createAgreement(CreateAgreementReq createAgreementReq, Integer userId) {
		Integer agreementId = 0;
		try{
			Agreement agreement = converter.convertToAgreement(createAgreementReq);
			agreement.setUserId(userId);
			agreementId = agreementRepository.saveAndFlush(agreement).getAgreementId();
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("createAgreement :: Error while create Agreement in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while create Agreement in DB" );
			
		}
		return agreementId;
		
	}


	@Override
	public AgreementDto retriveAgreement(String agreementId) {
		
		AgreementDto agreementDto = null;
		
		try{
		
			Agreement agreement = agreementRepository.findByAgreementId(new Integer(agreementId));
		
			agreementDto = converter.convertToAgreementDto(agreement);
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveAgreement :: Error while retrive Agreement in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrive Agreement in DB" );
			
		}
		return agreementDto;
	}
	
	@Override
	public AgreementDto retriveAgreementDetails(String agreementId) {
		
		AgreementDto agreementDto = null;
		
		try{
			Agreement agreement = agreementRepository.findByAgreementId(Integer.valueOf(agreementId));
		
			List<Person> persons = personRepository.findByAgreementId(Integer.valueOf(agreementId));
			
			Miscellaneous miscellaneous = miscellaneousRepository.findByAgreementId(Integer.valueOf(agreementId));
		
			agreementDto = converter.convertToAgreementDetailsDto(agreement, persons, miscellaneous);
			

			if( agreement.getPostalAddressId() != null ) {
				PostalAddressDto postalAddressDto = postalAddressDao.retriveProperty(agreement.getPostalAddressId());
				agreementDto.setProperty(postalAddressDto);
			}
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveAgreementDetails :: Error while retrive Agreement details in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrive Agreement Details in DB" );
			
		}
		return agreementDto;
		
	}

	@Override
	public List<AgreementDto> retriveUserAgreements(String userId) {
		
		List<AgreementDto> list = null;
		try{
			
			List<Agreement> agreements = agreementRepository.findByUserId(new Integer(userId));
			
			list = converter.convertToAgreementDtoList(agreements);
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveUserAgreements :: Error while retrive user Agreement details in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrive user Agreement Details in DB" );
			
		}
		return list;
	}

	@Override
	public void updateMiscellaneous(MiscellaneousTermsReq miscellaneousTermsReq, Integer userId) {
		try{
			
			Miscellaneous miscellaneous = miscellaneousRepository.findByAgreementId(Integer.valueOf(miscellaneousTermsReq.getAgreementId()));
			
			if(null != miscellaneous){
				
				Miscellaneous miscellaneousToUpdate = miscellaneousMapper.reverseMap(miscellaneousTermsReq);
				miscellaneousToUpdate.setMiscellaneousId(miscellaneous.getMiscellaneousId());
				
				miscellaneousRepository.saveAndFlush(miscellaneousToUpdate);
				
				
			}else{
				
				Miscellaneous newmiscellaneous = miscellaneousMapper.reverseMap(miscellaneousTermsReq);
				miscellaneousRepository.saveAndFlush(newmiscellaneous);
			}
			
			
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("updateMiscellaneous :: Error while updateMiscellaneous Agreement details in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while updateMiscellaneous Agreement Details in DB" );
			
		}
		
	}

	@Override
	public List<AgreementDto> retriveAdminAgreements() {
		List<AgreementDto> list = null;
		try{
			
			List<Agreement> agreements = agreementRepository.findAll();
			
			list = converter.convertToAgreementDtoList(agreements);
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveUserAgreements :: Error while retrive user Agreement details in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrive user Agreement Details in DB" );
			
		}
		return list;
	}

	@Override
	public List<AgreementDto> retriveAssociateAgreements(List<Integer> assignedAgreementsIds) {
		List<AgreementDto> list = null;
		try{
			
			List<Agreement> agreements = findAgreementsByAgreementsId(assignedAgreementsIds);
			
			list = converter.convertToAgreementDtoList(agreements);
		}catch (Exception e){
			
			SLF4JLOGGER.error("retriveUserAgreements :: Error while retrive user Agreement details in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrive user Agreement Details in DB" );
			
		}
		return list;
	}

	

	private List<Agreement> findAgreementsByAgreementsId(List<Integer> assignedAgreementsIds) {
		
		List<Agreement> agreementList = null;
		
		if(null != assignedAgreementsIds){
			agreementList = new ArrayList<Agreement>();
			for (Integer agreementId : assignedAgreementsIds) {
				
				Agreement agreement = agreementRepository.findByAgreementId(agreementId);
				agreementList.add(agreement);
				
			}
			
		}
		return agreementList;
	}

	@Override
	public void putEntryInAssociateAgreement(Integer agreementid) {
		
		try{
			AgreementAssociate agreementAssociate = new AgreementAssociate();
			agreementAssociate.setAgreementId(agreementid);
			agreementAssociate.setProgressStatus("UNASSIGNED");
			agreementAssociateRepository.saveAndFlush(agreementAssociate);
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("putEntryInAssociateAgreement :: Error while putEntryInAssociateAgreement in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while putEntryInAssociateAgreement in DB" );
			
		}
		
	}

	@Override
	public List<Integer> retrieveAssociateAgreementIds(Integer associateId) {
		List<Integer> agreementList = null;
		try{
		List<AgreementAssociate> list = agreementAssociateRepository.findByAssociateId(associateId);
		if(null != list){
			agreementList = new ArrayList<Integer>();
			for (AgreementAssociate agreementAssociate : list) {
				
				agreementList.add(agreementAssociate.getAgreementId());
			}
		}
		}catch (Exception e){
			
			SLF4JLOGGER.error("retrieveAssociateAgreement :: Error while retrieveAssociateAgreement in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrieveAssociateAgreement in DB" );
			
		}
		return agreementList;
	}

}
