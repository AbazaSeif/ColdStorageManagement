package com.mg.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStock;
import com.mg.csms.beans.InwardStockItem;
import com.mg.json.controller.JsonHandlerInterface;
import com.mg.json.model.ColdStorageJsonModel;
import com.mg.json.model.DemandJsonModel;
import com.mg.json.model.InwardStockItemJsonModel;
import com.mg.json.model.InwardStockJsonModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class BillingController {

	@FXML
	private TableView<InwardStockItem> stockListView;
	@FXML
	private TableColumn<InwardStockItem, String> itemColdNo;
	@FXML
	private TableColumn<InwardStockItem, String> itemQuantity;
	@FXML
	private TableColumn<InwardStockItem, Date> entryDate;
	@FXML
	private TableColumn<InwardStockItem, String> itemLotNo;
	@FXML
	private TableColumn<InwardStockItem, String> itemColdStore;
	@FXML
	private TableColumn<InwardStockItem, String> itemBhada;
	@FXML
	private TableColumn<InwardStockItem, String> itemBillAmount;

	@FXML
	private TableView<Demand> demandListTable;
	@FXML
	private TableColumn<Demand, Date> demandTableDate;
	@FXML
	private TableColumn<Demand, String> demandTableQuantity;
	@FXML
	private TableColumn<Demand, String> demandTableChalanNumber;
	@FXML
	private TableColumn<Demand, String> demandBillAmount;
	@FXML
	private Button calculateBillButton;
	@FXML
	private Button isBillPaid;
	@FXML
	private TextField coldBhada;
	@FXML
	private Text successMessage;

	private JsonHandlerInterface jsonStockModel;
	private JsonHandlerInterface jsonStockItemModel;
	private JsonHandlerInterface jsonColdHandler;
	private JsonHandlerInterface jsonDemandHandler;
	private List<InwardStockItem> stockItemList1;
	private List<InwardStockItem> stockListUpdated;
	private List<Demand> demandListToShow;

	@FXML
	public void initialize(){
		jsonStockModel = new InwardStockJsonModel();
		jsonStockItemModel = new InwardStockItemJsonModel();
		jsonColdHandler = new ColdStorageJsonModel();
		jsonDemandHandler = new DemandJsonModel();
		demandListToShow = new ArrayList<>();
		stockItemList1 = new ArrayList<>();
		stockListUpdated = new ArrayList<>();
		initializeTableViews();
		addTableRowAction();

		jsonStockItemModel.makeListAndMapFromJson();
		stockItemList1 = ((InwardStockItemJsonModel) jsonStockItemModel).getStockItemList();
		stockListUpdated.addAll(stockItemList1.stream().filter(element -> filterStockItemCondition(element))
				.collect(Collectors.toList()));
		stockListView.setItems(FXCollections.observableList(stockListUpdated));
	}

	protected boolean filterStockItemCondition(InwardStockItem element) {
		return element.getBalance() == 0;
	}

	@FXML
	private void billPaidAction(){
		successMessage.setText("Bill Paid. Please check Paid Entries Tab for Paid Bills.");
	}

	@FXML
	private void calculateBill(){
		successMessage.setText("Bill Generated. Please check respective entry for bill amount.");
	}

	private void initializeTableViews() {
		initializeStockListTableView();
		initializeDemandListTableView();
	}

	private void addTableRowAction() {
		stockListView.getSelectionModel().selectedIndexProperty().addListener(num -> {
			if (!stockListView.getSelectionModel().isEmpty())
				populateDemandTable(stockListView.getSelectionModel().selectedItemProperty().get().getColdNo());
		});
	}

	private void populateDemandTable(Integer coldNo) {
		jsonDemandHandler.makeListAndMapFromJson();
		demandListToShow = ((DemandJsonModel) jsonDemandHandler).getDemandList().stream()
				.filter(demand -> demand.getColdNo().toString().equalsIgnoreCase(coldNo.toString()))
				.collect(Collectors.toList());
		demandListTable.setItems(FXCollections.observableList(demandListToShow));
	}

	private void initializeStockListTableView() {
		stockListView.setEditable(true);
		entryDate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, Date>("entryDate"));
		itemColdNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("coldNo"));
		itemQuantity.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
		itemLotNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("lotNo"));

		itemColdStore.setCellValueFactory((CellDataFeatures<InwardStockItem, String> data) -> {
			jsonStockModel.makeListAndMapFromJson();
			Optional<InwardStock> stock = ((InwardStockJsonModel) jsonStockModel).getStockList().stream()
					.filter(stockObject -> stockObject.getStockId() == data.getValue().getStockId()
							|| stockObject.getStockId().equals(data.getValue().getStockId()))
					.findAny();
			Optional<ColdStorage> coldObject = getColdStoreList().stream()
					.filter(cold -> stock.get().getColdId() == cold.getColdId()
							|| stock.get().getColdId().equals(cold.getColdId()))
					.findAny();

			return new SimpleStringProperty(coldObject.get().getColdName());
		});
		
		itemBhada.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
		itemBillAmount.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
	}

	private List<ColdStorage> getColdStoreList() {
		jsonColdHandler.makeListAndMapFromJson();
		return ((ColdStorageJsonModel) jsonColdHandler).getColdStoreList();
	}

	private void initializeDemandListTableView() {
		demandListTable.setEditable(true);
		demandTableDate.setCellValueFactory(new PropertyValueFactory<Demand, Date>("demandDate"));
		demandTableQuantity.setCellValueFactory(new PropertyValueFactory<Demand, String>("quantity"));
		demandTableChalanNumber.setCellValueFactory(new PropertyValueFactory<Demand, String>("chalanNumber"));
		demandBillAmount.setCellValueFactory(new PropertyValueFactory<Demand, String>("billAmount"));
	}
}
