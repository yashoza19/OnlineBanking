package com.neu.onlinebanking.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.neu.onlinebanking.pojo.User;

@Repository
public class UserDaoImpl implements UserDao{
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User findByUserName(String theUserName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser = null;
		
		try {
			theUser = theQuery.getSingleResult();
		}
		catch (Exception e) {
			theUser = null;
		}
		
		return theUser;
	}

	@Override
	public void save(User theUser) {
		

		Session currentSession = sessionFactory.getCurrentSession();


		currentSession.saveOrUpdate(theUser);
		
	}

	@Override
	public List<User> findAllCustomers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> theQuery = currentSession.createQuery("from User u where u.checkingAccount is not null");
		List<User> results = theQuery.list();
		return results;
	}

}
