package com.bank.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.entities.AccountInfo;
import com.bank.entities.User;
import com.bank.repositories.AccountRepository;
import com.bank.repositories.UserRepository;
import com.bank.services.UserService;

@Service
public class UserServiceImpl implements UserService 
{
	@Autowired private UserRepository userRepository;
	@Autowired private AccountRepository accountRepository;

	public int createAccount(User user) 
	{
		userRepository.save(user);
		AccountInfo account=new AccountInfo();
		account.setUserid(user.getUserid());
		accountRepository.save(account);
		return account.getAccountno();
	}

	public User getUser(String userid) 
	{
		User user=userRepository.findById(userid).orElse(null);
		return user;
	}
}
