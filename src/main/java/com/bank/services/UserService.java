package com.bank.services;

import com.bank.entities.User;

public interface UserService 
{
	int createAccount(User user);
	User getUser(String userid);
}
