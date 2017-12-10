package com.namami.bo.mapper;

import org.springframework.stereotype.Component;

import com.namami.bo.PaymentRequest;
import com.namami.entity.Payment;

@Component
public class PaymentMapper implements BaseMapper<PaymentRequest, Payment>{

	@Override
	public PaymentRequest map(Payment payment) {
		return null;
	}

	@Override
	public Payment reverseMap(PaymentRequest PaymentRequest) {
		
		return null;
	}


}
