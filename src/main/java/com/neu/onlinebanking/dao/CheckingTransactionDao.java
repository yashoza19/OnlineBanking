package com.neu.onlinebanking.dao;

import java.util.List;

import com.neu.onlinebanking.pojo.CheckingTransaction;

public interface CheckingTransactionDao {

	List<CheckingTransaction> findAll();
	
	void save(CheckingTransaction checkingTransaction);
	
	CheckingTransaction findTransaction(long ID);
}
