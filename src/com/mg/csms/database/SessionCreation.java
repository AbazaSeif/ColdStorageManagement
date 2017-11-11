package com.mg.csms.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author Mohak Gupta
 *
 */
public class SessionCreation {

	private static SessionFactory sessionFactory;
	private static Session s;

	private SessionCreation() {
	}

	public static Session getSessionInstance() throws Exception {
		if (s == null)
			createSession();
		return s;
	}

	private static void createSession() throws Exception {
		try {
			sessionFactory = new Configuration().configure("./com/hibernate/config/hibernate.cfg.xml")
					.buildSessionFactory();
			s = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void closeSession() {
		if (s != null)
			s.close();
	}
}