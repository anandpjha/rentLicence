package com.namami.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "person")
//@SequenceGenerator(name = "PERSON_SEQUENCE", sequenceName = "PERSON_SEQUENCE", allocationSize = 1, initialValue = 0)
public class Person extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer personId;
	
	private String personName;
	
	private Integer personAge;
	
	private Integer age;
	
	private String occupation;
	
	private String aadharUid;
	
	private String panNo;
	
	private String mobNumber;

	private String emailAddress;
	
	private String gender;
	
	private String type;
	
	private Integer agreementId;
	
	private String contectAddress;
	
	/*@ManyToOne
	@JoinColumn(name = "agreementId")
	private Agreement agreement;
	
	public Agreement getAgreement() {
		return agreement;
	}

	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
*/
	//TODO add mapping here.
	//private PostalAddress permanentAddress;
	
	/**
	 * This field is applicable only for Owner. In case if owner has Power Of Attorney, 
	 * rental agreement is executed slightly different manner.
	 */
	private String isPoaHolder;

	public String getContectAddress() {
		return contectAddress;
	}

	public void setContectAddress(String contectAddress) {
		this.contectAddress = contectAddress;
	}

	public Integer getPersonId() {
		return personId;
	}

	public void setPersonId(Integer personId) {
		this.personId = personId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAgreementId() {
		return agreementId;
	}

	public void setAgreementId(Integer agreementId) {
		this.agreementId = agreementId;
	}

	


	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public Integer getPersonAge() {
		return personAge;
	}

	public void setPersonAge(Integer personAge) {
		this.personAge = personAge;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getAadharUid() {
		return aadharUid;
	}

	public void setAadharUid(String aadharUid) {
		this.aadharUid = aadharUid;
	}

	public String getPanNo() {
		return panNo;
	}

	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	public String getMobNumber() {
		return mobNumber;
	}

	public void setMobNumber(String mobNumber) {
		this.mobNumber = mobNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getIsPoaHolder() {
		return isPoaHolder;
	}

	public void setIsPoaHolder(String isPoaHolder) {
		this.isPoaHolder = isPoaHolder;
	}
	
	/*@OneToOne(cascade = CascadeType.ALL, optional = false, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
	private RegisteredUser user;
	*/
	

	/*public RegisteredUser getUser() {
		return user;
	}

	public void setUser(RegisteredUser user) {
		this.user = user;
	}
*/
	

}
