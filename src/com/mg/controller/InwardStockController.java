package com.mg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.InwardStockItem;
import com.mg.csms.beans.Vyaapari;
import com.mg.csms.database.SessionCreation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class InwardStockController {

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
	private TextField stockInwardDate;
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

	private List<ColdStorage> coldStoreArrayList;
	private List<Vyaapari> vyaapariArrayList;
	private Session s;

	@FXML
	private Text successMessage;

	List<InwardStockItem> addItemList;

	@FXML
	protected void initialize() {
		try {
			s = SessionCreation.getSessionInstance();
		} catch (Exception e) {
			successMessage.setText("Database errors occoured");
			e.printStackTrace();
		}
		initializeItems();
		initializeTable();
	}

	private void initializeItems() {
		addItemList = new ArrayList<>();
		stockInwardDate.setPromptText("dd/MM/yyyy");
		stockInwardDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		coldStoreList.setItems(getColdStoreList());
		vyaapariList.setItems(getVyaapariList());
		itemList.setItems(getItemsList());
	}

	private void initializeTable() {
		itemListTable.setEditable(true);
		tableLotNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("lotNo"));
		tableColdNo.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("coldNo"));
		tableItem.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("item"));
		tableQuantity.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("quantity"));
		tableRate.setCellValueFactory(new PropertyValueFactory<InwardStockItem, String>("rate"));
		setTableEditableActions();
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
		coldStoreList.getValue();
		vyaapariList.getValue();
		totalQuantity.getText();
		gadiNo.getText();
		stockInwardDate.getText();
		itemListTable.getItems();

		clearOverallUI();
	}

	private void clearOverallUI() {
		// totalQuantity.clear();
		// gadiNo.clear();
		// itemListTable.setItems(null);
		// clearUI();
	}

	@SuppressWarnings("unchecked")
	private ObservableList<String> getColdStoreList() {
		coldStoreArrayList = new ArrayList<>();
		coldStoreArrayList = s.createQuery("FROM ColdStorage").list();
		List<String> coldStoreNameList = new ArrayList<>();
		coldStoreArrayList.forEach((cold) -> coldStoreNameList.add(cold.getColdName()));
		return FXCollections.observableList(coldStoreNameList);
	}

	@SuppressWarnings("unchecked")
	private ObservableList<String> getVyaapariList() {
		vyaapariArrayList = new ArrayList<>();
		vyaapariArrayList = s.createQuery("FROM Vyaapari").list();
		List<String> vyaapariNameList = new ArrayList<>();
		vyaapariArrayList.forEach((vyaapari) -> vyaapariNameList.add(vyaapari.getVyaapariName()));
		return FXCollections.observableList(vyaapariNameList);
	}

	private ObservableList<String> getItemsList() {
		List<String> itemsList = new ArrayList<>();
		itemsList.add("Dhaniya");
		itemsList.add("Ajwain");
		itemsList.add("Amchoor");
		itemsList.add("Methi");
		itemsList.add("Kaloonji");
		itemsList.add("Sarsoon");
		return FXCollections.observableList(itemsList);
	}

	private void setTableEditableActions() {
		tableLotNo.setOnEditCommit(new EventHandler<CellEditEvent<InwardStockItem, String>>() {
			@Override
			public void handle(CellEditEvent<InwardStockItem, String> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow())
						.setLotNo(Integer.parseInt(t.getNewValue()));
			}
		});

		tableColdNo.setOnEditCommit(new EventHandler<CellEditEvent<InwardStockItem, String>>() {
			@Override
			public void handle(CellEditEvent<InwardStockItem, String> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow())
						.setColdNo(Integer.parseInt(t.getNewValue()));
			}
		});

		tableItem.setOnEditCommit(new EventHandler<CellEditEvent<InwardStockItem, String>>() {
			@Override
			public void handle(CellEditEvent<InwardStockItem, String> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow()).setItem(t.getNewValue());
			}
		});

		tableQuantity.setOnEditCommit(new EventHandler<CellEditEvent<InwardStockItem, String>>() {
			@Override
			public void handle(CellEditEvent<InwardStockItem, String> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow())
						.setQuantity(Integer.parseInt(t.getNewValue()));
			}
		});

		tableRate.setOnEditCommit(new EventHandler<CellEditEvent<InwardStockItem, String>>() {
			@Override
			public void handle(CellEditEvent<InwardStockItem, String> t) {
				t.getTableView().getItems().get(t.getTablePosition().getRow())
						.setRate(Float.parseFloat(t.getNewValue()));
			}
		});
	}

}
