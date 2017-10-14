package com.mg.csms.database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionCreation {

	private static SessionFactory sessionFactory;
	private static Session s;

	public static Session getSessionInstance() throws Exception {
		if (s != null)
			return s;
		return getSession();
	}

	public static Session getSession() throws Exception {
		try {
			sessionFactory = new Configuration().configure("./com/hibernate/config/hibernate.cfg.xml")
					.buildSessionFactory();
			s = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return s;
	}

	public static void closeSession() {
		s.close();
	}
}