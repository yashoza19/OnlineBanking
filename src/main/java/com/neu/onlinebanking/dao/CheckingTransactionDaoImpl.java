package com.neu.onlinebanking.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.onlinebanking.pojo.CheckingTransaction;

@Repository
public class CheckingTransactionDaoImpl implements CheckingTransactionDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<CheckingTransaction> findAll() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<CheckingTransaction> theQuery = currentSession.createQuery("from CheckingTransaction", CheckingTransaction.class);
		List<CheckingTransaction> results = theQuery.list();
		return results;
	}

	@Override
	public void save(CheckingTransaction checkingTransaction) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(checkingTransaction);
		
	}

	@Override
	public CheckingTransaction findTransaction(long ID) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<CheckingTransaction> theQuery = currentSession.createQuery("from CheckingTransaction p where p.id=:ID", CheckingTransaction.class);
		theQuery.setParameter("ID", ID);
		CheckingTransaction checkingTransaction = theQuery.getSingleResult();
		return checkingTransaction;
	}

}
