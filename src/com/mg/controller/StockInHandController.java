package com.mg.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.csms.beans.Vyaapari;
import com.mg.jsonhandler.JSONParser;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * @author Mohak Gupta
 *
 */
public class StockInHandController {

	private static Logger log = Logger.getLogger(StockInHandController.class);

	private List<InwardStock> vyaapariStockList;
	private List<InwardStockItem> stockItemList1;
	private List<InwardStockItem> stockListUpdated;
	private List<Demand> demandListToShow;

	private Integer vyaapariIdFromChoiceBox;
	protected Integer gadiNoFromChoiceBox = 0;

	@FXML
	private ChoiceBox<String> vyaapari;
	@FXML
	private ChoiceBox<String> gadiNo;
	@FXML
	private TableView<InwardStockItem> stockListView;
	@FXML
	private TableColumn<InwardStockItem, String> itemColdNo;
	@FXML
	private TableColumn<InwardStockItem, String> itemName;
	@FXML
	private TableColumn<InwardStockItem, String> itemQuantity;
	@FXML
	private TableColumn<InwardStockItem, String> itemGadiNo;
	@FXML
	private TableColumn<InwardStockItem, Date> entryDate;
	@FXML
	private TableColumn<InwardStockItem, String> itemExpectedRate;
	@FXML
	private TableColumn<InwardStockItem, String> itemLotNo;
	@FXML
	private TableColumn<InwardStockItem, String> itemColdStore;
	@FXML
	private TableColumn<InwardStockItem, String> itemBalance;

	@FXML
	private TableView<Demand> demandListTable;
	@FXML
	private TableColumn<Demand, Date> demandTableDate;
	@FXML
	private TableColumn<Demand, String> demandTableQuantity;

	private JSONParser jsonParser;

	@FXML
	protected void initialize() {
		try {
			jsonParser = new JSONParser();
		} catch (Exception e) {
			log.debug(e);
		}
		vyaapari.setItems(getVyaapariList());
		demandListToShow = new ArrayList<>();
		makeVyaapariChangeAction();
		makeGadiNoChangeAction();
		initializeTableViews();
		addTableRowAction();
	}

	private void addTableRowAction() {
		stockListView.getSelectionModel().selectedIndexProperty().addListener(num -> {
			if (!stockListView.getSelectionModel().isEmpty())
				populateDemandTable(stockListView.getSelectionModel().selectedItemProperty().get().getColdNo());
		});
	}

	private void populateDemandTable(Integer coldNo) {

		demandListToShow = getDemandList().stream()
				.filter(demand -> demand.getColdNo().toString().equalsIgnoreCase(coldNo.toString()))
				.collect(Collectors.toList());

		demandListTable.setItems(FXCollections.observableList(demandListToShow));
	}

	private List<Demand> getDemandList() {
		Map<Integer, Object> demandMap = new HashMap<>();
		try {
			demandMap = jsonParser.getObjectFromJsonFile("Demand");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return new ArrayList(demandMap.values());
	}

	private void makeGadiNoChangeAction() {
		gadiNo.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
					gadiNoFromChoiceBox = 0;
					if (number2.intValue() >= 0)
						gadiNoFromChoiceBox = Integer.parseInt(gadiNo.getItems().get((Integer) number2));
					populateListView();
				});
	}

	private void makeVyaapariChangeAction() {
		vyaapari.getSelectionModel().selectedIndexProperty()
				.addListener((ObservableValue<? extends Number> observableValue, Number number, Number number2) -> {
					vyaapariIdFromChoiceBox = Integer.parseInt(vyaapari.getItems().get((Integer) number2).substring(0,
							vyaapari.getItems().get((Integer) number2).indexOf(':')));
					populateListView();
					gadiNo.setItems(getGadiNoBasedOnVyaapari());
				});
	}

	private void initializeTableViews() {
		initializeStockListTableView();
		initializeDemandListTableView();
	}

	private void initializeStockListTableView() {
		stockListView.setEditable(true);
		entryDate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, Date>("entryDate"));
		itemColdNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("coldNo"));
		itemName.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("item"));
		itemQuantity.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
		itemGadiNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("gadiNo"));
		itemExpectedRate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("rate"));
		itemLotNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("lotNo"));
		itemBalance.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("balance"));

		itemColdStore.setCellValueFactory((CellDataFeatures<InwardStockItem, String> data) -> {
			Optional<InwardStock> stock = getStockList().stream()
					.filter(stockObject -> stockObject.getStockId() == data.getValue().getStockId()
							|| stockObject.getStockId().equals(data.getValue().getStockId()))
					.findAny();
			Optional<ColdStorage> coldObject = getColdStoreList().stream()
					.filter(cold -> stock.get().getColdId() == cold.getColdId()
							|| stock.get().getColdId().equals(cold.getColdId()))
					.findAny();

			return new SimpleStringProperty(coldObject.get().getColdName());
		});
	}

	private List<ColdStorage> getColdStoreList() {
		Map<Integer, Object> coldStoreMap = new HashMap<>();
		try {
			coldStoreMap = jsonParser.getObjectFromJsonFile("ColdStorage");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		List<ColdStorage> coldStoreList = new ArrayList(coldStoreMap.values());
		return coldStoreList;
	}

	private void initializeDemandListTableView() {
		demandListTable.setEditable(true);
		demandTableDate.setCellValueFactory(new PropertyValueFactory<Demand, Date>("demandDate"));
		demandTableQuantity.setCellValueFactory(new PropertyValueFactory<Demand, String>("quantity"));
	}

	private ObservableList<String> getGadiNoBasedOnVyaapari() {
		List<String> gadiNoList = new ArrayList<>();
		getVyaapariStockList();
		vyaapariStockList.forEach(listItem -> gadiNoList.add(listItem.getGadiNo()));
		return FXCollections.observableList(gadiNoList);
	}

	private void populateListView() {
		stockListView.setItems(null);
		demandListTable.setItems(null);
		stockItemList1 = new ArrayList<>();
		stockListUpdated = new ArrayList<>();
		getVyaapariStockList();
		vyaapariStockList.forEach(vyaapariStock -> makeItemStockListForVyaapari(vyaapariStock));
	}

	private void getVyaapariStockList() {
		vyaapariStockList = getStockList().stream()
				.filter(element -> element.getVyaapariId() == vyaapariIdFromChoiceBox).collect(Collectors.toList());
	}

	private ArrayList<InwardStock> getStockList() {
		Map<Integer, Object> stockList = new HashMap<>();
		try {
			stockList = jsonParser.getObjectFromJsonFile("InwardStock");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return new ArrayList(stockList.values());
	}

	private void makeItemStockListForVyaapari(InwardStock vyaapariStock) {
		stockItemList1 = getStockItemList().stream()
				.filter(element -> element.getStockId() == vyaapariStock.getStockId()).collect(Collectors.toList());
		stockListUpdated.addAll(stockItemList1.stream().filter(element -> filterStockItemCondition(element))
				.collect(Collectors.toList()));
		if (gadiNoFromChoiceBox != 0)
			stockListUpdated = stockListUpdated.stream()
					.filter(element -> Integer.parseInt(element.getGadiNo()) == gadiNoFromChoiceBox)
					.collect(Collectors.toList());

		stockListView.setItems(FXCollections.observableList(stockListUpdated));
	}

	protected boolean filterStockItemCondition(InwardStockItem element) {
		return element.getBalance() > 0;
	}

	private ObservableList<String> getVyaapariList() {
		List<String> vyaapariNameList = new ArrayList<>();
		Map<Integer, Object> vyaapariStoreMap = new HashMap<>();
		try {
			vyaapariStoreMap = jsonParser.getObjectFromJsonFile("Vyaapari");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		List<Vyaapari> vyaapariList = new ArrayList(vyaapariStoreMap.values());
		vyaapariList.forEach(
				vyaapari -> vyaapariNameList.add(vyaapari.getVyaapariId() + ": " + vyaapari.getVyaapariName()));
		return FXCollections.observableList(vyaapariNameList);
	}

	private List<InwardStockItem> getStockItemList() {
		Map<Integer, Object> stockItemList = new HashMap<>();
		try {
			stockItemList = jsonParser.getObjectFromJsonFile("InwardStockItem");
		} catch (IOException e) {
			log.error(e.getMessage());
		}
		return new ArrayList(stockItemList.values());
	}
}
