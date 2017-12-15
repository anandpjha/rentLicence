package com.namami.service;
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
import com.namami.dao.AgreementTrackerDao;
import com.namami.exception.GlobalExceptionHandler;

@Service
public class AgreementTrackerServiceImpl implements AgreementTrackerService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private AgreementTrackerDao agreementTrackerdao;
	

	@Override
	public int createTracker(CreateTrackerRequest createTrackerRequest) {
		
		return agreementTrackerdao.createTracker(createTrackerRequest);
		
	}

	@Override
	public void updateTracker(UpdateTrackerRequest updateTrackerRequest) {
		
		agreementTrackerdao.updateTracker(updateTrackerRequest);
		
	}

	@Override
	public void deleteTracker(DeleteTrackerRequest deleteTrackerRequest) {
		
		agreementTrackerdao.deleteTracker(deleteTrackerRequest);
		
	}

	@Override
	public List<AgreementTrackerDto> retrieveTracker(RetrieveTrackerRequest retrieveTrackerRequest) {
		
		return agreementTrackerdao.retrieveTracker(retrieveTrackerRequest);
	}

	
}
