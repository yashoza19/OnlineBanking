package com.neu.onlinebanking.dao;

import com.neu.onlinebanking.pojo.SavingsAccount;

public interface SavingsAccountDao {

	SavingsAccount findByAccountNumber(int accountNumber);
	
	void save(SavingsAccount savingsAccount);
	
	
}
