package com.mg.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.jsonhandler.JSONParser;
import com.mg.jsonhandler.JSONWriter;
import com.mg.utils.DateUtils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

/**
 * @author Mohak Gupta
 *
 */
public class InwardStockController {

	private static Logger log = Logger.getLogger(InwardStockController.class);

	List<InwardStockItem> addItemList;

	@FXML
	private ComboBox<String> coldStoreList;
	@FXML
	private ComboBox<String> vyaapariList;
	@FXML
	private ComboBox<String> itemList;
	@FXML
	private TextField totalQuantity;
	@FXML
	private TextField gadiNo;
	@FXML
	private DatePicker stockInwardDate;
	@FXML
	private TextField lotNo;
	@FXML
	private TextField coldNo;
	@FXML
	private TextField quantity;
	@FXML
	private TextField rate;
	@FXML
	private TableView<InwardStockItem> itemListTable;

	@FXML
	private TableColumn<InwardStockItem, String> tableLotNo;
	@FXML
	private TableColumn<InwardStockItem, String> tableColdNo;
	@FXML
	private TableColumn<InwardStockItem, String> tableItem;
	@FXML
	private TableColumn<InwardStockItem, String> tableQuantity;
	@FXML
	private TableColumn<InwardStockItem, String> tableRate;

	@FXML
	private Button addButton;
	@FXML
	private Button submitButton;

	@FXML
	private Text successMessage;
	private JSONWriter jsonWriter;
	private JSONParser jsonParser;

	@FXML
	protected void initialize() {
		try {
			jsonWriter = new JSONWriter();
			jsonParser = new JSONParser();
		} catch (Exception e) {
			successMessage.setText("Database errors occoured");
		}
		initializeItems();
		initializeTable();
		initializeButtonKeyAction();
	}

	private void initializeItems() {
		addItemList = new ArrayList<>();
		DateUtils.initializeDate(stockInwardDate);
		coldStoreList.setItems(getColdStoreList());
		vyaapariList.setItems(getVyaapariList());
		itemList.setItems(makeItemsList());
	}

	private void initializeTable() {
		itemListTable.setEditable(true);
		tableLotNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("lotNo"));
		tableColdNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("coldNo"));
		tableItem.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("item"));
		tableQuantity.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
		tableRate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("rate"));
	}

	private void initializeButtonKeyAction() {
		addButton.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				addItemToList();
		});

		submitButton.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				submitStock();
		});
	}

	@FXML
	protected void addItemToList() {
		itemListTable.setItems(getInwardStockItem());
	}

	private ObservableList<InwardStockItem> getInwardStockItem() {
		addItemList.add(addInwardStockItem());
		clearUI();
		return FXCollections.observableList(addItemList);
	}

	private InwardStockItem addInwardStockItem() {
		InwardStockItem inwardStockItem = new InwardStockItem();
		inwardStockItem.setLotNo(Integer.parseInt(lotNo.getText()));
		inwardStockItem.setColdNo(Integer.parseInt(coldNo.getText()));
		inwardStockItem.setItem(itemList.getValue());
		inwardStockItem.setQuantity(Integer.parseInt(quantity.getText()));
		inwardStockItem.setRate(Float.parseFloat(rate.getText()));
		return inwardStockItem;
	}

	private void clearUI() {
		lotNo.clear();
		coldNo.clear();
		quantity.clear();
		rate.clear();
	}

	@FXML
	protected void submitStock() {
		try {
			InwardStock stock = makeInwardStock(new InwardStock());
			writeInwardStockToJson(stock);
			makeInwardStockItemsAndSave(stock);
			successMessage.setText("Lot added successfully.");
		} catch (Exception e) {
			successMessage.setText("Make sure you have entererd all fields correctly !");
		}
		clearOverallUI();
	}

	// TODO - CHECK THE WORKING
	public void writeInwardStockToJson(InwardStock stock) {
		Integer maxKey = 0;
		Map<Integer, Object> map = new HashMap<>();
		try {
			map = jsonParser.getObjectFromJsonFile("InwardStock");
			maxKey = Collections.max(map.keySet());
		} catch (IOException ex) {
			log.error(ex.getMessage());
		}
		stock.setStockId(maxKey + 1);
		map.put(stock.getStockId(), stock);
		jsonWriter.writeObjectToJson("InwardStock", map);
	}

	private void makeInwardStockItemsAndSave(InwardStock stock) {
		itemListTable.getItems().stream().forEach(itemStockItem -> {
			InwardStockItem item = new InwardStockItem();
			item.setColdNo(itemStockItem.getColdNo());
			item.setItem(itemStockItem.getItem());
			item.setQuantity(itemStockItem.getQuantity());
			item.setRate(itemStockItem.getRate());
			item.setLotNo(itemStockItem.getLotNo());
			item.setInwardStock(stock);
			item.setEntryDate(stock.getDate());
			item.setGadiNo(stock.getGadiNo());
			item.setBalance(itemStockItem.getQuantity());
			// dbQueriesUtils.getSession().save(item);
		});
	}

	private InwardStock makeInwardStock(InwardStock inwardStock) {
		inwardStock.setColdId(
				Integer.parseInt(coldStoreList.getValue().substring(0, coldStoreList.getValue().indexOf(':'))));
		inwardStock.setDate(DateUtils.makeDate(stockInwardDate));
		inwardStock.setGadiNo(gadiNo.getText());
		inwardStock.setVyaapariId(
				Integer.parseInt(vyaapariList.getValue().substring(0, vyaapariList.getValue().indexOf(':'))));
		inwardStock.setQty(Integer.parseInt(totalQuantity.getText()));
		return inwardStock;
	}

	private void clearOverallUI() {
		totalQuantity.clear();
		gadiNo.clear();
		itemListTable.setItems(null);
		coldStoreList.setValue("");
		vyaapariList.setValue("");
		itemList.setValue("");
		addItemList = new ArrayList<>();
		clearUI();
	}

	private ObservableList<String> getColdStoreList() {
		// dbQueriesUtils.makeColdStorageList();
		List<String> coldStoreNameList = new ArrayList<>();
		// dbQueriesUtils.getColdStorageList()
		// .forEach(cold -> coldStoreNameList.add(cold.getColdId() + ": " +
		// cold.getColdName()));
		return FXCollections.observableList(coldStoreNameList);
	}

	private ObservableList<String> getVyaapariList() {
		// dbQueriesUtils.makeVyaapariList();
		List<String> vyaapariNameList = new ArrayList<>();
		// dbQueriesUtils.getVyaapariArrayList().forEach(
		// vyaapari -> vyaapariNameList.add(vyaapari.getVyaapariId() + ": " +
		// vyaapari.getVyaapariName()));
		return FXCollections.observableList(vyaapariNameList);
	}

	private ObservableList<String> makeItemsList() {
		List<String> itemsList = new ArrayList<>();
		try (FileReader reader = new FileReader("./resources/itemList.properties")) {
			Properties p = new Properties();
			p.load(reader);
			p.entrySet().forEach(e -> itemsList.add((String) e.getValue()));
			Collections.sort(itemsList);
		} catch (IOException e) {
			log.debug(e.getMessage());
		}
		return FXCollections.observableList(itemsList);
	}

	@FXML
	public void deleteSelectedRow() {
		addItemList.remove(itemListTable.getSelectionModel().getSelectedItem());
		itemListTable.setItems(FXCollections.observableList(addItemList));
	}
}
