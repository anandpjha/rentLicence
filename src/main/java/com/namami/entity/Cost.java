package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * Cost entity class 
 */
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "cost")
//@SequenceGenerator(name = "COST_SEQUENCE", sequenceName = "COST_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Cost extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer costId;
	
	}
