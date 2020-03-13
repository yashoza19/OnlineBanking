package com.neu.onlinebanking.service;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.onlinebanking.dao.InternalRecipientDao;
import com.neu.onlinebanking.dao.SavingsAccountDao;
import com.neu.onlinebanking.dao.SavingsTransactionDao;
import com.neu.onlinebanking.exception.AccountNotFoundException;
import com.neu.onlinebanking.exception.TransactionException;
import com.neu.onlinebanking.pojo.InternalRecipient;
import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.CheckingTransaction;
import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.SavingsTransaction;
import com.neu.onlinebanking.pojo.User;
import com.neu.onlinebanking.dao.CheckingAccountDao;
import com.neu.onlinebanking.dao.CheckingTransactionDao;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private UserService userService;

	@Autowired
	private SavingsAccountDao savingsAccountDao;

	@Autowired
	private CheckingAccountDao checkingAccountDao;

	@Autowired
	private SavingsTransactionDao savingsTransactionDao;

	@Autowired
	private CheckingTransactionDao checkingTransactionDao;

	
	
	@Autowired
	private InternalRecipientDao internalRecipientDao;

	@Override
	public List<CheckingTransaction> findCheckingTransactionList(String username) {
		User user = userService.findByUserName(username);
		List<CheckingTransaction> checkingTransactionList = user.getCheckingAccount().getCheckingTransactionList();
		return checkingTransactionList;
	}

	@Override
	public List<SavingsTransaction> findSavingsTransactionList(String username) {
		User user = userService.findByUserName(username);
		List<SavingsTransaction> savingsTransactionList = user.getSavingsAccount().getSavingsTransactionList();
		return savingsTransactionList;
	}

	@Override
	public void saveCheckingDepositTransaction(CheckingTransaction checkingTransaction) {
		checkingTransactionDao.save(checkingTransaction);

	}

	@Override
	public void saveSavingsDepositTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);

	}

	@Override
	public void saveCheckingWithdrawTransaction(CheckingTransaction checkingTransaction) {
		checkingTransactionDao.save(checkingTransaction);

	}

	@Override
	public void saveSavingsWithdrawTransaction(SavingsTransaction savingsTransaction) {
		savingsTransactionDao.save(savingsTransaction);

	}

	@Override
	public void betweenAccountsTransfer(String transferFrom, String transferTo, String amount,
			CheckingAccount checkingAccount, SavingsAccount savingsAccount) throws TransactionException {

		if (transferFrom.equalsIgnoreCase("Checking") && transferTo.equalsIgnoreCase("Savings")) {
			if (checkingAccount.getAccountBalance().doubleValue() < (Double.parseDouble(amount))) {
				throw new TransactionException("Not enough Balance in Checking Account!!");
			} else {
				checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
				savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
				checkingAccountDao.save(checkingAccount);
				savingsAccountDao.save(savingsAccount);

				Date date = new Date();

				CheckingTransaction checkingTransaction = new CheckingTransaction(date,
						"Between accounts, Transfer From: " + transferFrom + " Transfer To: " + transferTo, "Account",
						"Finished", Double.parseDouble(amount), checkingAccount.getAccountBalance(), checkingAccount, "Between Accounts Transfer","");
				checkingTransactionDao.save(checkingTransaction);
			}

		} else if (transferFrom.equalsIgnoreCase("Savings") && transferTo.equalsIgnoreCase("Checking")) {
			if (savingsAccount.getAccountBalance().doubleValue() < (Double.parseDouble(amount))) {
				throw new TransactionException("Not enough Balance in Savings Account!!");
			} else {
				checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().add(new BigDecimal(amount)));
				savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
				checkingAccountDao.save(checkingAccount);
				savingsAccountDao.save(savingsAccount);

				Date date = new Date();

				SavingsTransaction savingsTransaction = new SavingsTransaction(date,
						"Between accounts, Tranfer From: " + transferFrom + " Transfer To: " + transferTo, "Account",
						"Finished", Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount, "Between Accounts Transfer","");
				savingsTransactionDao.save(savingsTransaction);
			}
		}

	}


	@Override
	public void approveToSomeoneElseTransfer(String description, String accountType, String amount,
			CheckingAccount checkingAccount, SavingsAccount savingsAccount, long ID) {
		if (accountType.equalsIgnoreCase("Checking")) {

			checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			checkingAccountDao.save(checkingAccount);

			Date date = new Date();

			
			CheckingTransaction checkingTransaction = checkingTransactionDao.findTransaction(ID);
			checkingTransaction.setStatus("Finished");
			checkingTransaction.setDate(date);
			checkingTransaction.setAvailableBalance(checkingAccount.getAccountBalance());
			checkingTransactionDao.save(checkingTransaction);
		} else if (accountType.equalsIgnoreCase("Savings")) {
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();

			// SavingsTransaction savingsTransaction = new SavingsTransaction(date,
			// description, "Transfer", "Finished", Double.parseDouble(amount),
			// savingsAccount.getAccountBalance(), savingsAccount);
			SavingsTransaction savingsTransaction = savingsTransactionDao.findTransaction(ID);
			savingsTransaction.setStatus("Finished");
			savingsTransaction.setDate(date);
			savingsTransactionDao.save(savingsTransaction);
		}

	}

	

	@Override
	public List<InternalRecipient> findInternalRecipientList(Principal principal) {
		String username = principal.getName();
		List<InternalRecipient> recipientList = internalRecipientDao.findall().stream()
				.filter(recipient -> username.equals(recipient.getUser().getUserName())).collect(Collectors.toList());
		return recipientList;
	}

	@Override
	public InternalRecipient saveInternalRecipient(InternalRecipient recipient) {
		internalRecipientDao.save(recipient);
		return recipient;
	}

	@Override
	public InternalRecipient findInternalRecipientByName(String recipientName) {
		
		return internalRecipientDao.findByName(recipientName);
	}
	
	@Override
	public void findInternalRecipientByAccountNumber(String recipientName, String accountNumber) throws AccountNotFoundException{
		//InternalRecipient recipient = null;
		CheckingAccount account = null;
		
		
		
		account = checkingAccountDao.findByAccountNumber(Integer.parseInt(accountNumber));
		if(account==null) {
			throw new AccountNotFoundException("Account not found. Please enter the checking account number of the recipient");
		}
		
		//recipient=internalRecipientDao.findByName(recipientName);
		
		
		
		//return recipient;
	}

	@Override
	public void deleteInternalRecipientByName(String recipientName) {
		internalRecipientDao.deleteByName(recipientName);
		
	}

	@Override
	public void toSomeoneElseTransferWithin(InternalRecipient recipient, String accountType, String amount,
			CheckingAccount checkingAccount, SavingsAccount savingsAccount) throws TransactionException {
		
		if (accountType.equalsIgnoreCase("Checking")) {
			
			if (checkingAccount.getAccountBalance().doubleValue() < (Double.parseDouble(amount))) {
				throw new TransactionException("Not enough Balance in Checking account!!");
			} else {

				Date date = new Date();

				CheckingTransaction checkingTransaction = new CheckingTransaction(date,
						"Transfer to recipient " + recipient.getName(), "Transfer", "Pending",
						Double.parseDouble(amount), checkingAccount.getAccountBalance(), checkingAccount, "Internal", recipient.getAccountNumber());
				checkingTransactionDao.save(checkingTransaction);
			}
		} else if (accountType.equalsIgnoreCase("Savings")) {
			
			if (savingsAccount.getAccountBalance().doubleValue() < (Double.parseDouble(amount))) {
				throw new TransactionException("Not enough Balance in Savings account!!");
			} else {

				Date date = new Date();

				SavingsTransaction savingsTransaction = new SavingsTransaction(date,
						"Transfer to recipient " + recipient.getName(), "Transfer", "Pending",
						Double.parseDouble(amount), savingsAccount.getAccountBalance(), savingsAccount, "Internal", recipient.getAccountNumber());
				savingsTransactionDao.save(savingsTransaction);
			}
		}
		
	}

	@Override
	public void approveToSomeoneElseTransferWithin(CheckingAccount receiversCheckingAccount, String description,
			String accountType, String amount, CheckingAccount checkingAccount, SavingsAccount savingsAccount, long ID)
			throws TransactionException {
		
		if (accountType.equalsIgnoreCase("Checking")) {

			checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			receiversCheckingAccount.setAccountBalance(receiversCheckingAccount.getAccountBalance().add(new BigDecimal(amount)));
			
			checkingAccountDao.save(checkingAccount);
			checkingAccountDao.save(receiversCheckingAccount);

			Date date = new Date();

			
			CheckingTransaction checkingTransaction = checkingTransactionDao.findTransaction(ID);
			checkingTransaction.setStatus("Finished");
			checkingTransaction.setDate(date);
			checkingTransaction.setAvailableBalance(checkingAccount.getAccountBalance());
			checkingTransactionDao.save(checkingTransaction);
		} else if (accountType.equalsIgnoreCase("Savings")) {
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
			receiversCheckingAccount.setAccountBalance(receiversCheckingAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);
			checkingAccountDao.save(receiversCheckingAccount);

			Date date = new Date();

		
			SavingsTransaction savingsTransaction = savingsTransactionDao.findTransaction(ID);
			savingsTransaction.setStatus("Finished");
			savingsTransaction.setDate(date);
			savingsTransactionDao.save(savingsTransaction);
		}

	
		
	}

	@Override
	public CheckingAccount findCheckingAccountByAccountNumber(String accountNumber) {
		CheckingAccount checkingAccount = checkingAccountDao.findByAccountNumber(Integer.parseInt(accountNumber));
		return checkingAccount;
	}

	




}
