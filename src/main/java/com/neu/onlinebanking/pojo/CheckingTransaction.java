package com.neu.onlinebanking.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.neu.onlinebanking.validator.AmountConstraint;


@Entity
@Table(name="checking_transaction")
public class CheckingTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    private String description;
    private String type;
    private String status;
    private double amount;
    private BigDecimal availableBalance;
    private String transactionType;
    private String receiversAccountNumber;
    
    public CheckingTransaction() {}


    public CheckingTransaction(Date date, String description, String type, String status, double amount, BigDecimal availableBalance, CheckingAccount checkingAccount, String transactionType, String receiversAccountNumber) {
        this.date = date;
        this.description = description;
        this.type = type;
        this.status = status;
        this.amount = amount;
        this.availableBalance = availableBalance;
        this.checkingAccount = checkingAccount;
        this.transactionType = transactionType;
        this.receiversAccountNumber = receiversAccountNumber;
    }

    @ManyToOne
    @JoinColumn(name = "checking_account_id")
    private CheckingAccount checkingAccount;

	public Long getId() {
		return id;
	}

	
	public String getTransactionType() {
		return transactionType;
	}


	public String getReceiversAccountNumber() {
		return receiversAccountNumber;
	}


	public void setReceiversAccountNumber(String receiversAccountNumber) {
		this.receiversAccountNumber = receiversAccountNumber;
	}


	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}


	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}


	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}


	public CheckingAccount getCheckingAccount() {
		return checkingAccount;
	}


	public void setCheckingAccount(CheckingAccount checkingAccount) {
		this.checkingAccount = checkingAccount;
	}
    
    
}
