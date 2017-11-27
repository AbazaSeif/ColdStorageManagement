package com.mg.csms.database;

public class FetchDatabaseRecords {

	private FetchDatabaseRecords fetchDBRecords;
	
	private FetchDatabaseRecords(){
		
	}
	
	public FetchDatabaseRecords getInstance(){
		synchronized (fetchDBRecords) {
			if(fetchDBRecords == null)
				fetchDBRecords = new FetchDatabaseRecords();
			return fetchDBRecords;
		}
	}
}
