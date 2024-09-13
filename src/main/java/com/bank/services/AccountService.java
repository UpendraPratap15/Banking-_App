package com.bank.services;

import java.util.List;

import com.bank.entities.AccountInfo;
import com.bank.entities.Transaction;

public interface AccountService 
{
	AccountInfo getAccount(String userid);
	AccountInfo getAccount(int an);
	void updateAmount(int amount, int accountno);
	List<Transaction> getTransactionList(int accountno);
	Transaction updateWithdrawAmount(int amount, int accountno);
}
