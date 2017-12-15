package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * Payment entity class 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "payment")
//@SequenceGenerator(name = "PAYMENT_SEQUENCE", sequenceName = "PAYMENT_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Payment extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer paymentId;
	
		
}
