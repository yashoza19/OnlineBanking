package com.neu.onlinebanking.dao;

import java.util.List;

import com.neu.onlinebanking.pojo.User;

public interface UserDao {

	User findByUserName(String userName);
	
	 void save(User user);
	
	 List<User> findAllCustomers();
	 
}
