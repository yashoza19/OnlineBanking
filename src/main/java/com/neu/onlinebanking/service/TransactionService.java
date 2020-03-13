package com.neu.onlinebanking.service;

import java.security.Principal;
import java.util.List;

import com.neu.onlinebanking.exception.AccountNotFoundException;
import com.neu.onlinebanking.exception.TransactionException;
import com.neu.onlinebanking.pojo.InternalRecipient;
import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.CheckingTransaction;
import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.SavingsTransaction;
import com.neu.onlinebanking.pojo.User;



public interface TransactionService {

	List<CheckingTransaction> findCheckingTransactionList(String username);

    List<SavingsTransaction> findSavingsTransactionList(String username);

    void saveCheckingDepositTransaction(CheckingTransaction checkingTransaction);
    
    CheckingAccount findCheckingAccountByAccountNumber(String accountNumber);

    void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction);
    
    void saveCheckingWithdrawTransaction(CheckingTransaction checkingTransaction);
    void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction);
    
    void betweenAccountsTransfer(String transferFrom, String transferTo, String amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount) throws TransactionException;
    
    
    
    List<InternalRecipient> findInternalRecipientList(Principal principal);

    InternalRecipient saveInternalRecipient(InternalRecipient recipient);

    InternalRecipient findInternalRecipientByName(String recipientName);
    
    void findInternalRecipientByAccountNumber(String recipientName, String accountNumber) throws AccountNotFoundException;; 

    void deleteInternalRecipientByName(String recipientName);
    
    
    void toSomeoneElseTransferWithin(InternalRecipient recipient, String accountType, String amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount) throws TransactionException;
    void approveToSomeoneElseTransferWithin(CheckingAccount receiversCheckingAccount, String description, String accountType, String amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount, long ID) throws TransactionException;
    
    void approveToSomeoneElseTransfer(String description, String accountType, String amount,CheckingAccount checkingAccount, SavingsAccount savingsAccount, long ID) throws TransactionException;
    
    
}
