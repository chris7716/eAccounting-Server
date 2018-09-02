package com.accsys.server.entity;

import java.util.List; 

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.accsys.server.models.Contact;
import com.accsys.server.util.HibernateUtil;
import com.accsys.server.util.PosException;

public class ContactsFactory {
	
	public List<Contact> getAllContacts(int firstResult, int limit, String contactType_like, String businessName_like, String contactName_like, String email_like, String telephone_like) throws PosException{
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();;
		List<Contact> contacts = null;
		Criteria criteria = session.createCriteria(Contact.class);
		
		try {
			
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(limit);
			if(contactType_like != null){
				criteria.add(Restrictions.like("contactType", contactType_like+"%"));
			}
			if(businessName_like != null){
				criteria.add(Restrictions.like("businessName", businessName_like+"%"));
			}
			if(contactName_like != null){
				criteria.add(Restrictions.like("contactName", contactName_like+"%"));
			}
			if(email_like != null){
				criteria.add(Restrictions.like("email", email_like+"%"));
			}
			if(telephone_like != null){
				criteria.add(Restrictions.like("telephone", telephone_like+"%"));
			}
			criteria.add(Restrictions.like("isDeleted", 0));
			contacts = criteria.list();
			tx.commit();
			
		}catch(Exception exception) {
			throw new PosException("AAA");
	    }finally {
	    	session.flush();
			session.close();
		}
		return contacts;
		
	}
	
	public void saveContact(Contact contact){
		
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Session session = factory.openSession();
		Transaction tx = null;
		
		try{
			tx = session.beginTransaction();
			session.saveOrUpdate(contact);
			tx.commit();
		}catch (HibernateException e) {
	         if (tx!=null) tx.rollback();
	         e.printStackTrace(); 
	    } finally{
			session.close();
		}
		
	}
	
	public int countContacts(String contactType_like, String businessName_like, String contactName_like, String email_like, String telephone_like){
		
		int noOfContacts = 0;
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(Contact.class);
		
		try {
			
			if(contactType_like != null){
				criteria.add(Restrictions.like("contactType", contactType_like+"%"));
			}
			if(businessName_like != null){
				criteria.add(Restrictions.like("businessName", businessName_like+"%"));
			}
			if(contactName_like != null){
				criteria.add(Restrictions.like("contactName", contactName_like+"%"));
			}
			if(email_like != null){
				criteria.add(Restrictions.like("email", email_like+"%"));
			}
			if(telephone_like != null){
				criteria.add(Restrictions.like("telephone", telephone_like+"%"));
			}
			criteria.add(Restrictions.like("isDeleted", 0));
			criteria.setProjection(Projections.rowCount());
			List rowCount = criteria.list();
			noOfContacts = ((Number) rowCount.get(0)).intValue();
			System.out.println(noOfContacts);
			tx.commit();
			
		}catch(Exception exception) {
			//
	    }finally {
	    	session.flush();
			session.close();
		}
		return noOfContacts;
		
	}

}
