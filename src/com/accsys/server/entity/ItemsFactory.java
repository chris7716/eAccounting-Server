package com.accsys.server.entity;

import java.util.List; 

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.accsys.server.models.Item;
import com.accsys.server.util.HibernateUtil;
import com.accsys.server.util.PosException;

public class ItemsFactory {

	public void saveItem(Item item) {

		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Session session = factory.openSession();
		Transaction tx = null;

		try {
			// int i = 1 / 0;
			tx = session.beginTransaction();
			session.saveOrUpdate(item);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}
	
	public List<Item> getAllItems(int firstResult, int limit, String itemCode_like, String itemDescription_like, String sellingPrice_like, String currentStock_like, String stockPerDate_like) throws PosException{
		SessionFactory factory = null;
		try {
			factory = HibernateUtil.getSessionFactory();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Session session = factory.openSession();
		Transaction tx = session.beginTransaction();;
		List<Item> items = null;
		Criteria criteria = session.createCriteria(Item.class);
		
		try {
			
			criteria.setFirstResult(firstResult);
			criteria.setMaxResults(limit);
			if(itemCode_like != null){
				criteria.add(Restrictions.like("itemCode", itemCode_like+"%"));
			}
			if(itemDescription_like != null){
				criteria.add(Restrictions.like("itemDescription", itemDescription_like+"%"));
			}
			if(sellingPrice_like != null){
				criteria.add(Restrictions.like("sellingPrice", sellingPrice_like+"%"));
			}
			if(currentStock_like != null){
				criteria.add(Restrictions.like("currentStock", currentStock_like+"%"));
			}
			if(stockPerDate_like != null){
				criteria.add(Restrictions.like("stockPerDate", stockPerDate_like+"%"));
			}
			criteria.add(Restrictions.like("isDeleted", 0));
			items = criteria.list();
			tx.commit();
			
		}catch(Exception exception) {
			throw new PosException("AAA");
	    }finally {
	    	session.flush();
			session.close();
		}
		return items;
		
	}

}
