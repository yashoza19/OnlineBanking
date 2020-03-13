package com.neu.onlinebanking.service;



import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.neu.onlinebanking.pojo.User;
import com.neu.onlinebanking.users.Customer;
import com.neu.onlinebanking.users.Employee;

public interface UserService extends UserDetailsService {

		User findByUserName(String userName);
		
		List<User> findAllCustomers();
		
		void save(Customer customer);
		void saveEmployee(Employee emp);
}
