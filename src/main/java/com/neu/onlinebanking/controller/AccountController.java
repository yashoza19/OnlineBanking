package com.neu.onlinebanking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@Autowired
	private TransactionService transacionService;

	@Autowired
	private UserService userService;

	@GetMapping("/checkingAccountDetails")
	public String showCheckingAccountDetails(Model model, Principal principal) {

		User user = userService.findByUserName(principal.getName());

		CheckingAccount checkingAccount = user.getCheckingAccount();

		List<CheckingTransaction> checkingTransactionList = transacionService
				.findCheckingTransactionList(principal.getName());

		model.addAttribute("checkingAccount", checkingAccount);
		model.addAttribute("checkingTransactionList", checkingTransactionList);

		return "checkingAccount";

	}
	
	@GetMapping("/savingsAccountDetails")
	public String showSavingsAccountDetails(Model model, Principal principal) {
		
		User user = userService.findByUserName(principal.getName());
		
		SavingsAccount savingsAccount = user.getSavingsAccount();
		
		List<SavingsTransaction> savingsTransactionList = transacionService.findSavingsTransactionList(principal.getName());
		
		model.addAttribute("savingsAccount", savingsAccount);
		model.addAttribute("savingsTransactionList", savingsTransactionList);
		
		return "savingsAccount";
		
	}
	
	@GetMapping("/depositAmount")
	public String showDepositPage(Model model) {
		model.addAttribute("accountType", "");
		model.addAttribute("amount","");
		return "deposit";
	}
	
	@GetMapping("/withdrawAmount")
	public String showWithdrawPage(Model model) {
		model.addAttribute("accountType", "");
		model.addAttribute("amount","");
		return "withdraw";
	}
	
	@PostMapping("/depositAmount")
	public String depositPost(@ModelAttribute("amount")String amount, @ModelAttribute("accountType")String accountType, Principal principal) {
		
		accountService.deposit(accountType, Double.parseDouble(amount), principal);
		return "redirect:/customer-home";
	}
	
	@PostMapping("/withdrawAmount")
	public ModelAndView withdrawPost(@ModelAttribute("amount")String amount, @ModelAttribute("accountType")String accountType, Principal principal) {
		try {
		accountService.withdraw(accountType, Double.parseDouble(amount), principal);
		} catch (TransactionException e) {
			return new ModelAndView("error-transaction", "errorMessage", e.getMessage());
		}
		return new ModelAndView("redirect:/customer-home");
	}

}
