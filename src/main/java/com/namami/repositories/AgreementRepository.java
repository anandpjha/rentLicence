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

/**
 * 
 * @author Anand Jha
 *
 */


@Repository
public interface AgreementRepository extends JpaRepository<Agreement, String> {
		
		Agreement findByAgreementId(Integer agreementId);
		List<Agreement> findByUserId(Integer userId);
		
		/*@Query("select f from Foo f where f.name like %?1% or f.alias like %?1% or ...")
		public List<Foo> findByAnyColumnContaining(String text, Pageable pageable);*/
		
		//List<Agreement> findAdminAgreements(Integer userId);
		
		
}
