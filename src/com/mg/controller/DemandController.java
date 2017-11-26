package com.mg.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mg.csms.beans.Demand;
import com.mg.csms.beans.InwardStockItem;
import com.mg.csms.database.SessionCreation;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class DemandController {

	@FXML
	private TextField demandDate;
	@FXML
	private TextField coldNo;
	@FXML
	private TextField quantity;
	@FXML
	private TableView<Demand> itemListTable;
	@FXML
	private TableColumn<Demand, String> tableColdNo;
	@FXML
	private TableColumn<Demand, String> tableQuantity;
	@FXML
	private Text successMessage;
	private Session session;
	List<Demand> addItemList;

	@FXML
	private void initialize() {
		try {
			session = SessionCreation.getSessionInstance();
		} catch (Exception e) {
			successMessage.setText("Database errors occoured");
			e.printStackTrace();
		}
		addItemList = new ArrayList<>();
		demandDate.setPromptText("dd/MM/yyyy");
		demandDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		itemListTable.setEditable(true);
		tableColdNo.setCellValueFactory(new PropertyValueFactory<Demand, String>("coldNo"));
		tableQuantity.setCellValueFactory(new PropertyValueFactory<Demand, String>("quantity"));
	}

	@FXML
	private void addDemandToList() {
		itemListTable.setItems(getDemandItem());
	}

	private ObservableList<Demand> getDemandItem() {
		addItemList.add(addDemandItem());
		clearUI();
		return FXCollections.observableList(addItemList);
	}

	private Demand addDemandItem() {
		Demand demand = new Demand();
		demand.setQuantity(Integer.parseInt(quantity.getText()));
		demand.setColdNo(Integer.parseInt(coldNo.getText()));
		return demand;
	}

	private void clearUI() {
		coldNo.clear();
		quantity.clear();
	}

	@FXML
	private void submitDemand() {
		try {
			successMessage.setText("");
			Transaction tx = session.beginTransaction();
			makeDemandItemsAndSave();
			tx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			successMessage.setText("Make sure you have entererd all fields correctly !");
		}
		if ("".equalsIgnoreCase(successMessage.getText()))
			successMessage.setText("Demand added successfully.");
		clearOverallUI();
	}

	private void makeDemandItemsAndSave() throws Exception {
		Date date = makeDate();
		itemListTable.getItems().stream().forEach(demandItem -> {
			Demand demand = new Demand();
			if (isValidColdNo(demandItem.getColdNo())) {
				demand.setColdNo(demandItem.getColdNo());
				demand.setDemandDate(date);
				demand.setQuantity(demandItem.getQuantity());
				session.save(demand);
			} else
				successMessage.setText("Cold Id " + demandItem.getColdNo()
						+ " is not correct ! Verify that you made an entry in stock earlier.");
		});
	}

	@SuppressWarnings("unchecked")
	private boolean isValidColdNo(Integer coldNo2) {
		List<InwardStockItem> inwardStockItemList = new ArrayList<>();
		String hql = "FROM InwardStockItem";
		Query query = session.createQuery(hql);
		inwardStockItemList = query.list();
		return inwardStockItemList.stream().anyMatch(item -> item.getColdNo().equals(coldNo2));
	}

	private Date makeDate() {
		LocalDate date = LocalDate.of(Integer.parseInt(demandDate.getText().substring(6, 10)),
				Integer.parseInt(demandDate.getText().substring(3, 5)),
				Integer.parseInt(demandDate.getText().substring(0, 2)));
		return Date.valueOf(date);
	}

	private void clearOverallUI() {
		itemListTable.setItems(null);
		addItemList.clear();
		clearUI();
	}
}
