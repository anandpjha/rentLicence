package com.namami.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.namami.bo.AgreementDto;
import com.namami.bo.CreateTrackerRequest;
import com.namami.bo.PaymentRequest;
import com.namami.bo.PaymentResponse;
import com.namami.domain.types.AgreementStatusType;
import com.namami.domain.types.AgreementTrackerStatus;
import com.namami.exception.GlobalExceptionHandler;
import com.namami.exception.NotFoundException;
/**
 * @author Anand Jha
 */

@Service
public class PaymentServiceImpl implements PaymentService {
	
	private static final Logger SLF4JLOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	AgreementService agreementService; 
	
	@Autowired
	AgreementTrackerService agreementTrackerService;
	
	@Autowired
	AgreementStatusService agreementStatusService;

	@Override
	public PaymentResponse calculatePaymentDetail(PaymentRequest paymentRequest) {
		
		 Integer no_months = Integer.valueOf(paymentRequest.getNoOfMonths());
		 Double rent = Double.valueOf(paymentRequest.getRentPerMonth());
		 String type = paymentRequest.getAgreementType();
		 Double deposit = Double.valueOf(paymentRequest.getDepositAmount());
         
         Integer nyear = (no_months)/12;
         
         Integer nyear2 = 0;
         Integer total = 0;
         Integer stampduty = 0;
         
         if (nyear <= 1)
              nyear2 = 1;            
         else if (nyear > 1 && nyear <= 2)
              nyear2 = 2;
         else if (nyear > 2 && nyear <= 3)
              nyear2 = 3;
         else if (nyear > 3 && nyear <= 4)
              nyear2 = 4;
         else if (nyear > 4 && nyear <= 5)
              nyear2 = 5;
              
         
         Double totalamt = (rent * no_months) + (deposit * 0.10 * nyear2);
         Double stamp = (totalamt * 0.25)/100;
         
         Double rno = stamp % 100;
         Double dno = Math.abs((stamp / 100));
         Integer no = (dno.intValue() * 100);
         
         if(rno >= 50)
         {
             stampduty = no + 100;//-------------2
         }
         else if(rno < 50)
         {
             stampduty = no;
         }
         
         if (stampduty < 100)
         {
             stampduty = 100;
         }
         
         Integer anulom_charges_r = 1000 + 300 + 99;
         Integer anulom_charges_nr = 1500 + 300 + 99;
         Integer regcharg =  stampduty + 1000;//-------------1
         Double tax = 0.0;
         Integer otherCharges = 0;
        /* $("#l_month").html(no_months);
         $("#l_stamp").html(stampduty);*/
         if (type.equals("Residential"))
         {
             otherCharges = 399;//Other  charges (Software fee, Printing, Advisory, Courier etc
             tax =  Math.ceil((anulom_charges_r * 14.5)/100);
             tax = tax - 1;
             total =  regcharg + anulom_charges_r;
             total = total + tax.intValue();
         }
         else if(type.equals("Commercial"))
         {
        	 otherCharges = 899; //Other charges (Software fee, Printing, Advisory, Courier etc
             tax = Math.ceil((anulom_charges_nr * 14.5)/100);
             tax = tax - 1;
             total =  regcharg + anulom_charges_nr;
             total = total + tax.intValue();
         }
     /*$("#l_tax").html(tax);          
     $("#l_total").html(anulomcharg);
     $("#price").show();*/
		
         String registrationFee_to_govt = "1000";
         String governmentAuthorizationFee_to_govt = "150";
         String governmentApprovedERegistrationFee = "550";
         String singleVisitGovernmentApprovedFee = "300"; 
         
         PaymentResponse paymentResponse = new PaymentResponse();
         
         paymentResponse.seteRegistrationFee(governmentApprovedERegistrationFee);
         paymentResponse.setGovernmentAuthorizationFee(governmentAuthorizationFee_to_govt);
         paymentResponse.setOtherCharges(otherCharges.toString());
         paymentResponse.setRegistrationFee(registrationFee_to_govt);
         paymentResponse.setServiceTax( String.valueOf(tax.intValue()));
         paymentResponse.setSinglevisitFee(singleVisitGovernmentApprovedFee);
         paymentResponse.setStampDuty(stampduty.toString());
         paymentResponse.setTotalRegistrationCharges(total.toString());
         
		return paymentResponse;
	}

	@Override
	public PaymentResponse retrievePaymentDetail(String agreementId) {
		
		//TODO to validate if agreement belongs to the correct user
		
		AgreementDto agreementDto = agreementService.retriveAgreementDetails(agreementId);
		PaymentRequest paymentRequest = null;
		if(null != agreementDto){
			paymentRequest = new PaymentRequest();
			paymentRequest.setAgreementType(agreementDto.getAgreementType());
			if(null != agreementDto.getAdvanceDeposit()){
				paymentRequest.setDepositAmount(agreementDto.getAdvanceDeposit().toString());
			}else{
				paymentRequest.setDepositAmount("0");
			}
			paymentRequest.setNoOfMonths(agreementDto.getAgreementPeriodInMonths().toString());
			paymentRequest.setRentPerMonth(agreementDto.getRentPerMonth().toString());
		}else{
			throw new NotFoundException("Agreement not found : Agreement Id::"+agreementId);
		}
		
		PaymentResponse paymentResponse = calculatePaymentDetail(paymentRequest);
		
		return paymentResponse;
	}

	@Override
	public String updatePaymentStatus(String agreementId) {
		
		//TODO to validate that the agreement belogs to the user
		
		agreementStatusService.updateAgreementStatus(Integer.valueOf(agreementId), AgreementStatusType.PAYMENT_DONE.getValue());
		
		CreateTrackerRequest createTrackerRequest = new CreateTrackerRequest();
		createTrackerRequest.setAgreementId(Integer.valueOf(agreementId));
		//createTrackerRequest.setAgreementStatusId(AgreementStatusType.PAYMENT_DONE.getValue());
		createTrackerRequest.setAgreementTrackerStatusId(AgreementTrackerStatus.USR_PYMENT_DONE.getValue());
		
		agreementTrackerService.createTracker(createTrackerRequest);
		
		return "success";
	}
	
}
