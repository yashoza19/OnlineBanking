package com.neu.onlinebanking.pojo;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="checking_account")
public class CheckingAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "accountNumber")
	private int accountNumber;
	
	@Column(name = "accountBalance")
	private BigDecimal accountBalance;
	
	@OneToMany(mappedBy = "checkingAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<CheckingTransaction> checkingTransactionList;
	
	@OneToOne(mappedBy="checkingAccount")
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public List<CheckingTransaction> getCheckingTransactionList() {
		return checkingTransactionList;
	}

	public void setCheckingTransactionList(List<CheckingTransaction> checkingTransactionList) {
		this.checkingTransactionList = checkingTransactionList;
	}
	
	
}
