package com.neu.onlinebanking.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.onlinebanking.exception.TransactionException;
import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.CheckingTransaction;
import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.SavingsTransaction;
import com.neu.onlinebanking.pojo.User;
import com.neu.onlinebanking.service.AccountService;
import com.neu.onlinebanking.service.TransactionService;
import com.neu.onlinebanking.service.UserService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private UserService userService;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private AccountService accountService;

	@GetMapping("/emp-useraccounts")
	public String showUserAccountsPage(Model model) {

		List<User> userAccountList = userService.findAllCustomers();

		model.addAttribute("userAccountList", userAccountList);

		return "emp-useraccounts";
	}

	@GetMapping("/emp-checkingtransactionlist")
	public String showCheckingTransactionList(@RequestParam(value = "checkingAccountUser") String userName,
			@RequestParam(value = "accountType") String accountType, Model model) {
		List<CheckingTransaction> transactionList = transactionService.findCheckingTransactionList(userName);

		model.addAttribute("transactionList", transactionList);
		model.addAttribute("accountType", accountType);
		model.addAttribute("userName", userName);
		model.addAttribute("description", "");
		model.addAttribute("amount", "");
		model.addAttribute("id", "");
		model.addAttribute("transactionType", "");
		model.addAttribute("receiversAccountNumber", "");

		return "emp-transactionlist";
	}

	@GetMapping("/emp-savingstransactionlist")
	public String showSavingsTransactionList(@RequestParam(value = "savingsAccountUser") String userName,
			@RequestParam(value = "accountType") String accountType, Model model) {
		List<SavingsTransaction> transactionList = transactionService.findSavingsTransactionList(userName);

		model.addAttribute("transactionList", transactionList);
		model.addAttribute("accountType", accountType);
		model.addAttribute("userName", userName);
		model.addAttribute("description", "");
		model.addAttribute("amount", "");
		model.addAttribute("id", "");
		model.addAttribute("transactionType", "");
		model.addAttribute("receiversAccountNumber", "");

		return "emp-transactionlist";
	}

	@PostMapping("/approve-transaction")
	public ModelAndView approveTransaction(@ModelAttribute("description") String description,
			@ModelAttribute("amount") String amount, @ModelAttribute("accountType") String accountType,
			@ModelAttribute("userName") String userName, @ModelAttribute("id") long id,
			@ModelAttribute("transactionType") String transactionType,
			@ModelAttribute("receiversAccountNumber") String receiversAccountNumber) {

		User user = userService.findByUserName(userName);

		System.out.println(user.getUserName());
		System.out.println(user.getCheckingAccount().getAccountBalance());

		CheckingAccount checkingAccount = user.getCheckingAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();

		if (transactionType.equalsIgnoreCase("Internal")) {
			CheckingAccount receiversCheckingAccount = transactionService
					.findCheckingAccountByAccountNumber(receiversAccountNumber);
			try {
				transactionService.approveToSomeoneElseTransferWithin(receiversCheckingAccount, description, accountType,
						amount, checkingAccount, savingsAccount, id);
			} catch (TransactionException e) {
				return new ModelAndView("error-transaction", "errorMessage", e.getMessage());
			}
		}

		return new ModelAndView("redirect:/employee/emp-useraccounts");
	}

	

}
