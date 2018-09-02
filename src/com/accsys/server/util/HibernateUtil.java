package com.accsys.server.util;

import org.hibernate.SessionFactory;   

import org.hibernate.cfg.AnnotationConfiguration;

@SuppressWarnings("deprecation")
public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new AnnotationConfiguration().configure()
					.buildSessionFactory();
			
			/*Configuration configuration = new Configuration().configure();
			StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
			applySettings(configuration.getProperties());
			sessionFactory = configuration.buildSessionFactory(builder.build());*/
		} catch (Exception ex) {
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() throws Exception {
		SessionFactory sessionFactorynew = null;
		try{
			sessionFactorynew = sessionFactory;
		}
		catch(Exception e){
			throw new CustomException(e.getMessage(), 444);
		}
		return sessionFactorynew;
		
	}
}