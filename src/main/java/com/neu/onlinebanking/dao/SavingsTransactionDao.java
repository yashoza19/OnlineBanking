package com.neu.onlinebanking.dao;

import java.util.List;

import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.SavingsTransaction;

public interface SavingsTransactionDao {

	List<SavingsTransaction> findAll();
	
	void save(SavingsTransaction savingsTransaction);
	
	SavingsTransaction findTransaction(long ID);
}
