package com.namami.repositories;
import java.util.List;

/**
 * @author Anand Jha
 * 
 * Person entity class 
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.namami.entity.RegisteredUser;


@Repository("userRepository")
public interface UserRepository extends JpaRepository<RegisteredUser, String> {
		
	RegisteredUser findByUserId(Integer userId); 
	
	RegisteredUser findByUserEmail(String emailId);
	
	List<RegisteredUser> findByRoleType(String roleType);

}
