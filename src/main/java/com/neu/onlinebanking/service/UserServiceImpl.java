package com.neu.onlinebanking.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neu.onlinebanking.dao.RoleDao;
import com.neu.onlinebanking.dao.UserDao;
import com.neu.onlinebanking.pojo.Role;
import com.neu.onlinebanking.pojo.User;
import com.neu.onlinebanking.users.Customer;
import com.neu.onlinebanking.users.Employee;

@Service
@Transactional
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleDao roleDao;
	


	@Override
	@Transactional
	public User findByUserName(String userName) {
		
		return userDao.findByUserName(userName);
	}
	
	

	@Override
	@Transactional
	public void save(Customer customer) {
		User user = new User();
		
		user.setUserName(customer.getUserName());
		user.setPassword(passwordEncoder.encode(customer.getPassword()));
		user.setFirstName(customer.getFirstName());
		user.setLastName(customer.getLastName());
		user.setEmail(customer.getEmail());
		
		user.setCheckingAccount(accountService.createCheckingAccount());
		user.setSavingsAccount(accountService.createSavingsAccount());
		
		user.setRoles(Arrays.asList(new Role("ROLE_CUSTOMER")));
		
		userDao.save(user);
		
	}
	
	@Transactional
	public void saveEmployee(Employee emp) {
		User user = new User();
		
		user.setUserName(emp.getUserName());
		user.setPassword(passwordEncoder.encode(emp.getPassword()));
		user.setFirstName(emp.getFirstName());
		user.setLastName(emp.getLastName());
		user.setEmail(emp.getEmail());
		user.setRoles(Arrays.asList(new Role("ROLE_EMPLOYEE")));
		//user.setRoles(Arrays.asList(roleDao.findRoleByName("ROLE_EMPLOYEE")));
		
		userDao.save(user);
		
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
		
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}



	@Override
	public List<User> findAllCustomers() {
		List<User> results = userDao.findAllCustomers();
		return results;
	}

	

}
