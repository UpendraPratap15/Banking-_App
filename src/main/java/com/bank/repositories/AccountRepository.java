package com.bank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.entities.AccountInfo;

import jakarta.transaction.Transactional;

public interface AccountRepository extends JpaRepository<AccountInfo,Integer> 
{
	AccountInfo findByUserid(String userid);
	
	@Transactional
	@Modifying
	@Query("update AccountInfo set amount=amount+:arg1 where accountno=:arg2")
	void updateUserAmount(@Param("arg1") int amount,@Param("arg2") int accountno);
}
