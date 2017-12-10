package com.namami.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "registered_user")
//@SequenceGenerator(name = "USER_SEQUENCE", sequenceName = "USER_SEQUENCE", allocationSize = 1, initialValue = 0)
public class RegisteredUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	private String userName;
	
	private String city;
	
	@Column(unique=true)
	private String userEmail;
	
	private String userMobile;
	
	private String password;
	
	private String roleType;
	
	private String status;

	/*@OneToOne(cascade = CascadeType.ALL, optional = true, fetch = FetchType.EAGER, orphanRemoval = true)
    @PrimaryKeyJoinColumn
	private Agreement agreement;

	public Agreement getAgreement() {
		return agreement;
	}


	public void setAgreement(Agreement agreement) {
		this.agreement = agreement;
	}
*/

	public String getRoleType() {
		return roleType;
	}


	

	public String getUserName() {
		return userName;
	}




	public void setUserName(String userName) {
		this.userName = userName;
	}




	public String getCity() {
		return city;
	}




	public void setCity(String city) {
		this.city = city;
	}




	public String getUserEmail() {
		return userEmail;
	}


	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}


	public String getUserMobile() {
		return userMobile;
	}


	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}


	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}


	public Integer getUserId() {
		return userId;
	}

	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	
	public void setStatus(String status) {
		this.status = status;
	}
		
}
