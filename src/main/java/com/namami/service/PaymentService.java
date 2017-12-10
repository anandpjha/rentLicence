package com.namami.service;

import com.namami.bo.PaymentRequest;
import com.namami.bo.PaymentResponse;


/**
 * @author Anand Jha
 */

public interface PaymentService {

	
	PaymentResponse calculatePaymentDetail(PaymentRequest paymentRequest);

	PaymentResponse retrievePaymentDetail(String agreementId);

	String updatePaymentStatus(String agreementId);
	
}
