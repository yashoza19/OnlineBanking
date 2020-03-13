package com.neu.onlinebanking.dao;

import com.neu.onlinebanking.pojo.Role;

public interface RoleDao {
	
	public Role findRoleByName(String theRoleName);

}
