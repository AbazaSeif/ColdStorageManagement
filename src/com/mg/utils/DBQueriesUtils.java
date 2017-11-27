package com.mg.utils;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Vyaapari;
import com.mg.csms.database.SessionCreation;

public class DBQueriesUtils {

	private Session session;
	private static DBQueriesUtils dbQueriesUtils;
	private List<Vyaapari> vyaapariArrayList;
	private List<ColdStorage> coldStorageList;

	private DBQueriesUtils() throws Exception{
		session = SessionCreation.getSessionInstance();
	}

	@SuppressWarnings("unchecked")
	public void makeVyaapariList() {
		vyaapariArrayList = new ArrayList<>();
		vyaapariArrayList = session.createQuery("FROM Vyaapari").list();
	}

	@SuppressWarnings("unchecked")
	public void makeColdStorageList() {
		coldStorageList = new ArrayList<>();
		coldStorageList = session.createQuery("FROM ColdStorage").list();
	}

	public static DBQueriesUtils getInstance() throws Exception{
		if(dbQueriesUtils == null)
			dbQueriesUtils = new DBQueriesUtils();
		return dbQueriesUtils;
	}

	public List<Vyaapari> getVyaapariArrayList() {
		return vyaapariArrayList;
	}

	public List<ColdStorage> getColdStorageList() {
		return coldStorageList;
	}

	public Session getSession() {
		return session;
	}
	
}
