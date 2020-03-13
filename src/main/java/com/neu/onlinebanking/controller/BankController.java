package com.neu.onlinebanking.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.neu.onlinebanking.pojo.CheckingAccount;
import com.neu.onlinebanking.pojo.SavingsAccount;
import com.neu.onlinebanking.pojo.User;
import com.neu.onlinebanking.service.UserService;

@Controller
public class BankController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String showHomePage() {
		return "home";
	}
	
	@GetMapping("/employee")
	public String showEmployeePage() {
		
		return "employee";
	}
	
	@GetMapping("/customer-home")
	public String showCustomerHomePage(Model model, Principal principal) {
		
		User user = userService.findByUserName(principal.getName());
		
		CheckingAccount checkingAccount = user.getCheckingAccount();
		SavingsAccount savingsAccount = user.getSavingsAccount();
		
		model.addAttribute("checkingAccount", checkingAccount);
		model.addAttribute("savingsAccount", savingsAccount);
		
		return "customer-home";
	}
}
