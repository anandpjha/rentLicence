package com.namami.entity;
import javax.persistence.CascadeType;
/**
 * @author Anand Jha
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.namami.domain.types.LocatorTypeValues;

@Entity
@Table(name = "locator")
public class Locator extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long locatorId;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "personId")
	private Person person;

	private String value;

	@Column(name = "locatorType")
	@Enumerated(EnumType.STRING)
	private LocatorTypeValues locatorType;
	
	/*private Integer personId;
	

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}*/

	public long getLocatorId() {
		return locatorId;
	}

	public void setLocatorId(long locatorId) {
		this.locatorId = locatorId;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public LocatorTypeValues getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(LocatorTypeValues locatorType) {
		this.locatorType = locatorType;
	}

	
}