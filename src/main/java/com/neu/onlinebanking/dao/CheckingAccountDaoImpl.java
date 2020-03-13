package com.neu.onlinebanking.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.onlinebanking.pojo.CheckingAccount;

@Repository
public class CheckingAccountDaoImpl implements CheckingAccountDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CheckingAccount findByAccountNumber(int accountNumber) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<CheckingAccount> theQuery = currentSession.createQuery("from CheckingAccount where accountNumber=:aNumber", CheckingAccount.class);
		theQuery.setParameter("aNumber", accountNumber);
		CheckingAccount checkingAccount = null;
		
		try {
			checkingAccount = theQuery.getSingleResult();
		}
		catch (Exception e) {
			checkingAccount = null;
		}
		return checkingAccount;
	}

	@Override
	public void save(CheckingAccount checkingAccount) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		
		currentSession.saveOrUpdate(checkingAccount);

	}

}
