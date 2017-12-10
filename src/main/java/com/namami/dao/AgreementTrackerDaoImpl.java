package com.namami.dao;
import java.util.ArrayList;
/**
 * @author Anand Jha
 */
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AgreementTrackerDto;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.DeleteTrackerRequest;
import com.namami.bo.RetrieveTrackerRequest;
import com.namami.bo.UpdateTrackerRequest;
import com.namami.bo.mapper.AgreementTrackerMapper;
import com.namami.entity.AgreementTracker;
import com.namami.exception.GlobalErrorConstants;
import com.namami.exception.GlobalExceptionHandler;
import com.namami.exception.NotFoundException;
import com.namami.exception.SystemFailureException;
import com.namami.repositories.AgreementTrackerRepository;

@Service
public class AgreementTrackerDaoImpl implements AgreementTrackerDao {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	

	@Autowired
	private AgreementTrackerMapper agreementTrackerMapper;
	
	@Autowired
	private AgreementTrackerRepository agreementTrackerRepository;


	@Override
	public int createTracker(CreateTrackerRequest createTrackerRequest) {
		
		AgreementTracker tracker = null;
		
		AgreementTracker agreementTracker = agreementTrackerMapper.reverseMap(createTrackerRequest);
		try{
			
			tracker = agreementTrackerRepository.saveAndFlush(agreementTracker);
			
			
		}catch (Exception e){
			
			SLF4JLOGGER.error("createTracker :: Error while saving Tracker in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while saving Tracker in DB" );
			
		}
		
		return tracker.getTrackerId();
	}

	@Override
	public void updateTracker(UpdateTrackerRequest updateTrackerRequest) {
		try{
			if(null != updateTrackerRequest.getTrackerId()){
				AgreementTracker tracker = agreementTrackerRepository.findByTrackerId(updateTrackerRequest.getTrackerId());
				if(null != updateTrackerRequest.getAgreementId()){
					tracker.setAgreementId(updateTrackerRequest.getAgreementId());
				}
				if(null != updateTrackerRequest.getAgreementStatusId()){
					tracker.setAgreementStatusId(updateTrackerRequest.getAgreementStatusId());
				}
				if(null != updateTrackerRequest.getAgreementTrackerStatusId()){
					tracker.setAgreementTrackerStatusId(updateTrackerRequest.getAgreementTrackerStatusId());
				}
				
				agreementTrackerRepository.saveAndFlush(tracker);
			}
			
		} catch (Exception e){
		
		SLF4JLOGGER.error("updateTracker :: Error while updating Tracker in DB", e.getMessage());
		throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while updating Tracker in DB" );
		
		}
		
	}

	@Override
	public void deleteTracker(DeleteTrackerRequest deleteTrackerRequest) {
	
	try{
	
		if(null != deleteTrackerRequest.getTrackerId()){
			
			agreementTrackerRepository.delete(String.valueOf(deleteTrackerRequest.getTrackerId()));
		}
		
		
	} catch (Exception e){
		
		SLF4JLOGGER.error("deleteTracker :: Error while deleting Tracker in DB", e.getMessage());
		throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while deleting Tracker in DB" );
		
		}	
	}

	@Override
	public List<AgreementTrackerDto> retrieveTracker(RetrieveTrackerRequest retrieveTrackerRequest) {
		
		List<AgreementTrackerDto> agreementTrackerDtos = null;
		
		try{
		
		List<AgreementTracker> agreementTrackerDetails = agreementTrackerRepository.findByAgreementId(retrieveTrackerRequest.getAgreementId());
		
		if(null != agreementTrackerDetails){
			
			agreementTrackerDtos = new ArrayList<AgreementTrackerDto>();
			
			for (AgreementTracker agreementTracker : agreementTrackerDetails) {
				
				AgreementTrackerDto trackerDto = new AgreementTrackerDto();
				
				trackerDto.setAgreementId(agreementTracker.getAgreementId());
				trackerDto.setTrackerId(agreementTracker.getTrackerId());
				//trackerDto.setAgreementStatusId(agreementTracker.getAgreementStatusId());
				trackerDto.setAgreementTrackerStatusId(agreementTracker.getAgreementTrackerStatusId());
				agreementTrackerDtos.add(trackerDto);
				
				
			}
		}
		
		}catch (Exception e){
			
			SLF4JLOGGER.error("retrieveTracker :: Error while retrieveTracker Tracker in DB", e.getMessage());
			throw new SystemFailureException(GlobalErrorConstants.SYSTEM_FAILURE_EXCEPTION.getValue() , "Error while retrieveTracker Tracker in DB" );
			
		}
		return agreementTrackerDtos;
	}

	
}
