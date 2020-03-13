package com.neu.onlinebanking.service;

import java.security.Principal;

import com.neu.onlinebanking.exception.TransactionException;
import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.SavingsAccount;



public interface AccountService {
	
    CheckingAccount createCheckingAccount();
    SavingsAccount createSavingsAccount();
    void deposit(String accountType, double amount, Principal principal);
    void withdraw(String accountType, double amount, Principal principal) throws TransactionException;

}
