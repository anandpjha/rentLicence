package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * PaymentInvoice entity class 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "payment_invoice")
//@SequenceGenerator(name = "PAYMENTINVOICE_SEQUENCE", sequenceName = "PAYMENTINVOICE_SEQUENCE", allocationSize = 1, initialValue = 0)
public class PaymentInvoice extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer paymentInvoiceId;
	
	}
