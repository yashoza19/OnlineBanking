package com.neu.onlinebanking.service;

import com.neu.onlinebanking.dao.CheckingAccountDao;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.onlinebanking.dao.SavingsAccountDao;
import com.neu.onlinebanking.exception.TransactionException;
import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.CheckingTransaction;
import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.SavingsTransaction;
import com.neu.onlinebanking.pojo.User;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	private static int nextAccountNumber = 112233;

	@Autowired
	private CheckingAccountDao checkingAccountDao;

	@Autowired
	private SavingsAccountDao savingsAccountDao;

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Override
	public CheckingAccount createCheckingAccount() {
		CheckingAccount checkingAccount = new CheckingAccount();
		checkingAccount.setAccountBalance(new BigDecimal(0.0));
		checkingAccount.setAccountNumber(accountGen());

		checkingAccountDao.save(checkingAccount);

		return checkingAccountDao.findByAccountNumber(checkingAccount.getAccountNumber());
	}

	@Override
	public SavingsAccount createSavingsAccount() {
		SavingsAccount savingsAccount = new SavingsAccount();
		savingsAccount.setAccountBalance(new BigDecimal(0.0));
		savingsAccount.setAccountNumber(accountGen());

		savingsAccountDao.save(savingsAccount);

		return savingsAccountDao.findByAccountNumber(savingsAccount.getAccountNumber());
	}

	@Override
	public void deposit(String accountType, double amount, Principal principal) {
		User user = userService.findByUserName(principal.getName());

		if (accountType.equalsIgnoreCase("Checking")) {
			CheckingAccount checkingAccount = user.getCheckingAccount();
			checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().add(new BigDecimal(amount)));
			checkingAccountDao.save(checkingAccount);

			Date date = new Date();

			CheckingTransaction checkingTransaction = new CheckingTransaction(date, "Deposit to Checking Account",
					"Account", "Finished", amount, checkingAccount.getAccountBalance(), checkingAccount, "Between Accounts","");
			transactionService.saveCheckingDepositTransaction(checkingTransaction);

		} else if (accountType.equalsIgnoreCase("Savings")) {
			SavingsAccount savingsAccount = user.getSavingsAccount();
			savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().add(new BigDecimal(amount)));
			savingsAccountDao.save(savingsAccount);

			Date date = new Date();
			SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Deposit to savings Account",
					"Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount, "Between Accounts","");
			transactionService.saveSavingsDepositTransaction(savingsTransaction);
		}

	}

	@Override
	public void withdraw(String accountType, double amount, Principal principal) throws TransactionException {
		User user = userService.findByUserName(principal.getName());

		if (accountType.equalsIgnoreCase("Checking")) {
			CheckingAccount checkingAccount = user.getCheckingAccount();
			if (checkingAccount.getAccountBalance().doubleValue() < amount) {
				throw new TransactionException("Not Enough balance in Checking Account!!");
			} else {
				checkingAccount.setAccountBalance(checkingAccount.getAccountBalance().subtract(new BigDecimal(amount)));
				checkingAccountDao.save(checkingAccount);

				Date date = new Date();

				CheckingTransaction checkingTransaction = new CheckingTransaction(date, "Withdraw from Checking Account",
						"Account", "Finished", amount, checkingAccount.getAccountBalance(), checkingAccount, "Between Accounts","");
				transactionService.saveCheckingWithdrawTransaction(checkingTransaction);
			}
		} else if (accountType.equalsIgnoreCase("Savings")) {

			SavingsAccount savingsAccount = user.getSavingsAccount();
			if (savingsAccount.getAccountBalance().doubleValue() < amount) {
				throw new TransactionException("Not enough balance in Savings account!!");
			} else {
				savingsAccount.setAccountBalance(savingsAccount.getAccountBalance().subtract(new BigDecimal(amount)));
				savingsAccountDao.save(savingsAccount);

				Date date = new Date();
				SavingsTransaction savingsTransaction = new SavingsTransaction(date, "Withdraw from savings Account",
						"Account", "Finished", amount, savingsAccount.getAccountBalance(), savingsAccount, "Between Accounts","");
				transactionService.saveSavingsWithdrawTransaction(savingsTransaction);
			}
		}

	}

	private int accountGen() {
		return ++nextAccountNumber;
	}

}
