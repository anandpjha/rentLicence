package com.namami.entity;
/**
 * @author Anand Jha
 * 
 * Base entity class - all persistence entity should extends this class
 */
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.namami.common.session.ContextUtil;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

	
	@CreatedBy
   // @NotNull
   // @Column(name = "created_by", nullable = false, length = 50, updatable = false)
    @JsonIgnore
    private String createdBy;

    @CreatedDate
   // @NotNull
    @Column(name = "created_date", nullable = false)
    @JsonIgnore
    private Date createdDate = new Date();

    @LastModifiedBy
    @Column(name = "last_modified_by", length = 50)
    @JsonIgnore
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @JsonIgnore
    private Date lastModifiedDate = new Date();

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
    
    
    
    @Transient
	String userID = "UNKNOWN";
	
	@PrePersist
	void onCreate() {

		this.setCreatedDate(new Date());
		
		if(null != ContextUtil.getUserContext()){
			userID = ContextUtil.getUserId();
	    }
		
		this.setCreatedBy(userID);

	}

	@PreUpdate
	void onPersist() {

		this.setLastModifiedDate(new Date());
		if(null != ContextUtil.getUserContext()){
			userID = ContextUtil.getUserId();
	    }
		this.setLastModifiedBy(userID);

	}


	
}
