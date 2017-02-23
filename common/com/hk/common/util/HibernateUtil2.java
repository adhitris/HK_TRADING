package com.hk.common.util;

import org.hibernate.SessionFactory;

public class HibernateUtil2 {
	
	public static SessionFactory sessionFactory2;

	public static SessionFactory getSessionFactory2() {
		return sessionFactory2;
	}

	public void setSessionFactory2(SessionFactory sessionFactory2) {
		HibernateUtil2.sessionFactory2 = sessionFactory2;
	}
	
}
