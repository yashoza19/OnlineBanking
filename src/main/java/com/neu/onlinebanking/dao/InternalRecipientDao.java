package com.neu.onlinebanking.dao;

import java.util.List;
import com.neu.onlinebanking.pojo.InternalRecipient;

public interface InternalRecipientDao {
	
	List<InternalRecipient> findall();
	
	InternalRecipient findByName(String theName);
	
	
	
	void deleteByName(String theName);
	
	void save(InternalRecipient recipient);

}
