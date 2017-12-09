package com.mg.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mg.controller.InwardStockController;
import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.csms.beans.Vyaapari;
import com.mg.jsonhandler.JSONParser;
import com.mg.jsonhandler.JSONWriter;

/**
 * @author Mohak Gupta
 *
 */
public class JsonUtils {

	private static Logger log = Logger.getLogger(InwardStockController.class);

	private static JsonUtils jsonUtils;
	private JSONWriter jsonWriter;
	private JSONParser jsonParser;

	List<ColdStorage> coldStoreList;
	List<Vyaapari> vyaapariList;
	Map<Integer, Object> coldStoreMap;
	Map<Integer, Object> vyaapariMap;

	List<InwardStock> stockList;
	Map<Integer, Object> stockMap;
	List<InwardStockItem> stockItemList;
	Map<Integer, Object> stockItemMap;
	List<Demand> demandList;
	Map<Integer, Object> demandMap;

	private JsonUtils() {
		try {
			jsonWriter = new JSONWriter();
			jsonParser = new JSONParser();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public static JsonUtils getInstance() {
		if (jsonUtils == null)
			return new JsonUtils();
		return jsonUtils;
	}

	public void makeColdStoreList() {
		coldStoreMap = new HashMap<>();
		try {
			coldStoreMap = jsonParser.getObjectFromJsonFile("ColdStorage");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		coldStoreList = new ArrayList(coldStoreMap.values());
	}

	public void makeVyaapariList() {
		vyaapariMap = new HashMap<>();
		try {
			vyaapariMap = jsonParser.getObjectFromJsonFile("Vyaapari");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		vyaapariList = new ArrayList(vyaapariMap.values());
	}

	public void makeStockList() {
		stockMap = new HashMap<>();
		try {
			stockMap = jsonParser.getObjectFromJsonFile("InwardStock");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		stockList = new ArrayList(stockMap.values());
	}

	public void makeStockItemList() {
		stockItemMap = new HashMap<>();
		try {
			stockItemMap = jsonParser.getObjectFromJsonFile("InwardStockItem");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		stockItemList = new ArrayList(stockItemMap.values());
	}

	public void makeDemandList() {
		demandMap = new HashMap<>();
		try {
			demandMap = jsonParser.getObjectFromJsonFile("Demand");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		demandList = new ArrayList(demandMap.values());
	}

	public void writeObjectToJson(String fileName, Map<Integer, Object> jsonObject) {
		jsonWriter.writeObjectToJson(fileName, jsonObject);
	}

	public List<ColdStorage> getColdStoreList() {
		return coldStoreList;
	}

	public List<Vyaapari> getVyaapariList() {
		return vyaapariList;
	}

	public Map<Integer, Object> getColdStoreMap() {
		return coldStoreMap;
	}

	public Map<Integer, Object> getVyaapariMap() {
		return vyaapariMap;
	}

	public void setColdStoreMap(ColdStorage cold) {
		this.coldStoreMap.put(cold.getColdId(), cold);
	}

	public void setVyaapariMap(Vyaapari vyaapari) {
		this.vyaapariMap.put(vyaapari.getVyaapariId(), vyaapari);
	}

	public List<InwardStock> getStockList() {
		return stockList;
	}

	public void setStockList(List<InwardStock> stockList) {
		this.stockList = stockList;
	}

	public Map<Integer, Object> getStockMap() {
		return stockMap;
	}

	public void setStockMap(InwardStock stock) {
		this.stockMap.put(stock.getStockId(), stock);
	}

	public List<InwardStockItem> getStockItemList() {
		return stockItemList;
	}

	public void setStockItemList(List<InwardStockItem> stockItemList) {
		this.stockItemList = stockItemList;
	}

	public Map<Integer, Object> getStockItemMap() {
		return stockItemMap;
	}

	public void setStockItemMap(InwardStockItem stockItem) {
		this.stockItemMap.put(stockItem.getRecordId(), stockItem);
	}

	public List<Demand> getDemandList() {
		return demandList;
	}

	public Map<Integer, Object> getDemandMap() {
		return demandMap;
	}

	public void setDemandMap(Demand demand) {
		this.demandMap.put(demand.getDemandId(), demand);
	}

}
