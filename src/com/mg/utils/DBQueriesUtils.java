package com.mg.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.csms.beans.Vyaapari;
import com.mg.csms.database.SessionCreation;

@SuppressWarnings("unchecked")
public class DBQueriesUtils {

	private static Logger log = Logger.getLogger(DBQueriesUtils.class);

	private Session session;
	private List<Vyaapari> vyaapariArrayList;
	private List<ColdStorage> coldStorageList;
	private List<InwardStock> stockListFromDB;
	private List<InwardStockItem> stockItemList;
	private List<Demand> demandList;

	public DBQueriesUtils() throws Exception{
		session = SessionCreation.getSessionInstance();
		try{
			makeColdStorageList();
			makeVyaapariStockList();
			makeCompleteStockList();
			makeDemandTableList();
		}catch(Exception e){
			log.debug(e);
		}
	}

	public void makeVyaapariList() {
		vyaapariArrayList = new ArrayList<>();
		vyaapariArrayList = session.createQuery("FROM Vyaapari").list();
	}

	public void makeColdStorageList() {
		coldStorageList = new ArrayList<>();
		coldStorageList = session.createQuery("FROM ColdStorage").list();
	}

	public void makeVyaapariStockList() {
		stockListFromDB = new ArrayList<>();
		stockListFromDB = session.createQuery("FROM InwardStock").list();
	}

	public void makeCompleteStockList() {
		stockItemList = new ArrayList<>();
		stockItemList = session.createQuery("FROM InwardStockItem").list();
	}

	public void makeDemandTableList() {
		demandList = new ArrayList<>();
		demandList = session.createQuery("FROM Demand").list();
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

	public List<InwardStock> getStockListFromDB() {
		return stockListFromDB;
	}

	public List<InwardStockItem> getStockItemList() {
		return stockItemList;
	}

	public List<Demand> getDemandList() {
		return demandList;
	}

}
