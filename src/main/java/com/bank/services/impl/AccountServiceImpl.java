package com.bank.services.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.AccountInfo;
import com.bank.entities.Transaction;
import com.bank.repositories.AccountRepository;
import com.bank.repositories.TransactionRepository;
import com.bank.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService 
{
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository; 

	public AccountInfo getAccount(String userid) 
	{
		AccountInfo account=accountRepository.findByUserid(userid);
		return account;
	}
	public AccountInfo getAccount(int an) 
	{
		AccountInfo account=accountRepository.findById(an).orElse(null);
		return account;
	}
	public void updateAmount(int amount, int accountno) 
	{
		//code to update amount in Account class
		accountRepository.updateUserAmount(amount, accountno);
		//code to create and persist object of Transaction class
		saveTransactionDetails(amount, accountno,"credit");
	} 
	private Transaction saveTransactionDetails(int amount,int accountno,String type)
	{
		Transaction transaction=new Transaction();
		transaction.setAccountno(accountno);
		transaction.setAmount(amount);
		transaction.setDate(getCurrentDate());
		transaction.setTime(getCurrentTime());
		transaction.setType(type);
		transactionRepository.save(transaction);
		return transaction;
	}
	public List<Transaction> getTransactionList(int accountno) 
	{
		return transactionRepository.findByAccountno(accountno);
	}
	public Transaction updateWithdrawAmount(int amount, int accountno) 
	{
		accountRepository.updateUserAmount(-amount, accountno);
		return saveTransactionDetails(amount, accountno,"debit");
	}
	private String getCurrentTime()
	{
		String y="AM";
		LocalTime lt=LocalTime.now();
		int h=lt.getHour();
		if(h>12)
		{
			h-=12;
			y="AM";
		}
		String time=lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond()+" "+y;
		return time;
	}
	private String getCurrentDate()
	{
		String dt=LocalDate.now().toString();
		String[] x=dt.split("-"); 
		String date=x[2]+"-"+x[1]+"-"+x[0];
		return date;
	}
}
