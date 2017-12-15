package com.namami.repositories;
import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.namami.entity.Agreement;
import com.namami.entity.ApprovalDeepLinking;

/**
 * 
 * @author Anand Jha
 *
 */


@Repository
public interface ApprovalDeepLinkingRepository extends JpaRepository<ApprovalDeepLinking, String> {
		
		
		ApprovalDeepLinking findById(Integer dpId);
		
		
		
}
