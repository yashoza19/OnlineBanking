package com.neu.onlinebanking.dao;

import com.neu.onlinebanking.pojo.CheckingAccount;

public interface CheckingAccountDao {

	CheckingAccount findByAccountNumber (int accountNumber);
	
	void save( CheckingAccount checkingAccount);
}
