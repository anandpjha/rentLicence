package com.namami.service;
/**
 * @author Anand Jha
 */
import java.util.List;

import com.namami.bo.AgreementTrackerDto;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.DeleteTrackerRequest;
import com.namami.bo.RetrieveTrackerRequest;
import com.namami.bo.UpdateTrackerRequest;

public interface AgreementTrackerService {

	public int createTracker(CreateTrackerRequest createTrackerRequest);
	
	public void updateTracker(UpdateTrackerRequest updateTrackerRequest);
	
	public void deleteTracker(DeleteTrackerRequest deleteTrackerRequest);
	
	public List<AgreementTrackerDto> retrieveTracker(RetrieveTrackerRequest retrieveTrackerRequest);
}
