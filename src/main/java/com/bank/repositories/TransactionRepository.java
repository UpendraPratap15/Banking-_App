package com.bank.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.entities.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction,Integer> 
{
	List<Transaction> findByAccountno(int accountno);
}
