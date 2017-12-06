package com.mg.jsonhandler;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.csms.beans.Vyaapari;
import com.mg.stock.constant.StockConstants;

/**
 * @author Mohak Gupta
 *
 */
public class JSONParser {

	private static Logger log = Logger.getLogger(JSONWriter.class);

	public Map<Integer, Object> getObjectFromJsonFile(String fileName) throws IOException {
		Map<Integer, Object> objectMap = new HashMap<>();
		ObjectMapper mapper = new ObjectMapper();
		File file = new File(StockConstants.DIRECTORY_PATH + fileName + StockConstants.JSON_SUFFIX);
		try {
			if (file.exists())
				switch (fileName) {
				case "ColdStorage":
					objectMap = mapper.readValue(file, new TypeReference<Map<Integer, ColdStorage>>() {
					});
					break;
				case "Vyaapari":
					objectMap = mapper.readValue(file, new TypeReference<Map<Integer, Vyaapari>>() {
					});
					break;
				case "InwardStock":
					objectMap = mapper.readValue(file, new TypeReference<Map<Integer, InwardStock>>() {
					});
					break;
				case "LotEntry":
					objectMap = mapper.readValue(file, new TypeReference<Map<Integer, InwardStockItem>>() {
					});
					break;
				case "Demand":
					objectMap = mapper.readValue(file, new TypeReference<Map<Integer, Demand>>() {
					});
					break;
				default:
					break;
				}
			else
				throw new IOException("File " + fileName + " Does Not Exists ! ");
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new IOException("File " + fileName + " Does Not Exists ! ");
		}
		return objectMap;
	}
}
