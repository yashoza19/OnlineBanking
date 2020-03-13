package com.neu.onlinebanking.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.neu.onlinebanking.exception.AccountNotFoundException;
import com.neu.onlinebanking.exception.TransactionException;
import com.neu.onlinebanking.pojo.InternalRecipient;
import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.User;
import com.neu.onlinebanking.service.TransactionService;
import com.neu.onlinebanking.service.UserService;

@Controller
@RequestMapping("/transfer")
public class TransferController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionService transactionService;
	
	@GetMapping("/betweenAccounts")
	public String betweenAccounts(Model model) {
		model.addAttribute("transferFrom", "");
		model.addAttribute("transferTo", "");
		model.addAttribute("amount", "");
		
		return "betweenAccounts";
	}
	
	@PostMapping("/betweenAccounts")
	public ModelAndView betweenAccountsPost(
			@ModelAttribute("transferFrom") String transferFrom,
			@ModelAttribute("transferTo")String transferTo,
			@ModelAttribute("amount")String amount,
			Principal principal) throws Exception{
		
		User user = userService.findByUserName(principal.getName());
		
		CheckingAccount checkingAccount = user.getCheckingAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
		try {
		transactionService.betweenAccountsTransfer(transferFrom, transferTo, amount, checkingAccount, savingsAccount);
		}
		catch (TransactionException e) {
			return new ModelAndView("error-transaction","errorMessage",e.getMessage());
		}
		
		return new ModelAndView("redirect:/customer-home");
		
		
		
	}
	
	@GetMapping("/internalrecipient/save")
	public String showInternalRecipientAddPage(Model model, Principal principal) {
		
		List<InternalRecipient> recipientList = transactionService.findInternalRecipientList(principal);
		
		InternalRecipient recipient = new InternalRecipient();
		
		model.addAttribute("recipientList",recipientList);
		model.addAttribute("recipient",recipient);
		
		return "internal-recipient";
	}
	
	@PostMapping("/internalrecipient/save")
	public ModelAndView processInternalRecipient(@ModelAttribute("recipient")InternalRecipient recipient, Principal principal) {
		try {
			transactionService.findInternalRecipientByAccountNumber(recipient.getName(), recipient.getAccountNumber());
		} catch(AccountNotFoundException e){
			return new ModelAndView("error-transaction","errorMessage",e.getMessage());
		}
		
		User user = userService.findByUserName(principal.getName());
		recipient.setUser(user);
		transactionService.saveInternalRecipient(recipient);
		
		return new ModelAndView("redirect:/transfer/internalrecipient/save");
		
	}
	
	@GetMapping("/deleteinternal")
	@Transactional
	public String internalRecipientDelete(@RequestParam(value="recipientName")String recipientName,Model model, Principal principal ) {
		transactionService.deleteInternalRecipientByName(recipientName);
		
		List<InternalRecipient> recipientList = transactionService.findInternalRecipientList(principal);
		
		InternalRecipient recipient = new InternalRecipient();
		
		model.addAttribute("recipientList",recipientList);
		model.addAttribute("recipient",recipient);
		
		
		
		return "redirect:/transfer/internalrecipient/save";
	}
	
	@GetMapping("/toSomeoneElseWithin")
	public String toSomeoneElseWithinPage(Model model, Principal principal) {
		
		List<InternalRecipient> recipientList = transactionService.findInternalRecipientList(principal);
		
		InternalRecipient recipient = new InternalRecipient();
		
		for(InternalRecipient ex : recipientList) {
			System.out.println(ex.getName());
		}
		
		model.addAttribute("recipientList",recipientList);
		model.addAttribute("recipient",recipient);
		return "toSomeoneElseWithin";
	}
	
	@PostMapping("/toSomeoneElseWithin")
	public ModelAndView processToSomeoneElseWithinPage(@ModelAttribute("recipientName")String recipientName, 
			@ModelAttribute("accountType")String accountType, @ModelAttribute("amount")String amount, Principal principal) {
		
		User user = userService.findByUserName(principal.getName());
		InternalRecipient recipient = transactionService.findInternalRecipientByName(recipientName);
		try {
		transactionService.toSomeoneElseTransferWithin(recipient, accountType, amount, user.getCheckingAccount(), user.getSavingsAccount());
		}
		catch (TransactionException e) {
			return new ModelAndView("error-transaction","errorMessage",e.getMessage());
		}
		
		return new ModelAndView("redirect:/customer-home");
	}
	
	
}
