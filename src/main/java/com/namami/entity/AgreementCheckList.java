package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * AgreementCheckList entity class 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "agreement_check_list")
//@SequenceGenerator(name = "AGREEMENTCHECKLIST_SEQUENCE", sequenceName = "AGREEMENTCHECKLIST_SEQUENCE", allocationSize = 1, initialValue = 0)
public class AgreementCheckList extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer agreementCheckListId;
	
		
}
