package com.mg.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.hibernate.Transaction;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Vyaapari;
import com.mg.utils.DBQueriesUtils;
import com.mg.utils.DateUtils;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * @author Mohak Gupta
 *
 */
public class ColdVyaapariController {

	private DBQueriesUtils dbQueriesUtils;
	
	@FXML
	private TextField coldDate;
	@FXML
	private TextField coldName;
	@FXML
	private TextField coldAdd;
	@FXML
	private TextField coldPhone;
	@FXML
	private TextField vyaapariDate;
	@FXML
	private TextField vyaapariName;
	@FXML
	private TextField vyaapariAddress;
	@FXML
	private TextField vyaapariNumber;

	@FXML
	private TableView<ColdStorage> coldListView;
	@FXML
	private TableColumn<ColdStorage, String> listColdName;
	@FXML
	private TableColumn<ColdStorage, String> listColdPhone;
	@FXML
	private TableColumn<ColdStorage, String> listColdAddress;

	@FXML
	private TableView<Vyaapari> vyaapariListView;
	@FXML
	private TableColumn<Vyaapari, String> listVyaapariName;
	@FXML
	private TableColumn<Vyaapari, String> listVyaapariPhone;
	@FXML
	private TableColumn<Vyaapari, String> listVyaapariAddress;

	@FXML
	Tab addVyaapari;
	@FXML
	Tab addColdStorage;
	@FXML
	private Text successMessage;
	@FXML
	private Text successMessage1;

	@FXML
	protected void initialize() {
		try {
			dbQueriesUtils = DBQueriesUtils.getInstance();
		} catch (Exception e) {
			successMessage.setText("Database errors occoured");
		}
		initializeDate();
		initializeColdTable();
		initializeVyaapariTable();
	}

	private void initializeDate() {
		DateUtils.initializeDate(coldDate);
		DateUtils.initializeDate(vyaapariDate);
	}

	private void initializeColdTable() {
		coldListView.setEditable(true);
		listColdName.setCellValueFactory(new PropertyValueFactory<ColdStorage, String>("coldName"));
		listColdPhone.setCellValueFactory(new PropertyValueFactory<ColdStorage, String>("phoneNo"));
		listColdAddress.setCellValueFactory(new PropertyValueFactory<ColdStorage, String>("address"));
		dbQueriesUtils.makeColdStorageList();
		coldListView.setItems(FXCollections.observableList(dbQueriesUtils.getColdStorageList()));
	}

	private void initializeVyaapariTable() {
		vyaapariListView.setEditable(true);
		listVyaapariName.setCellValueFactory(new PropertyValueFactory<Vyaapari, String>("vyaapariName"));
		listVyaapariPhone.setCellValueFactory(new PropertyValueFactory<Vyaapari, String>("phoneNo"));
		listVyaapariAddress.setCellValueFactory(new PropertyValueFactory<Vyaapari, String>("address"));
		dbQueriesUtils.makeVyaapariList();
		vyaapariListView.setItems(FXCollections.observableList(dbQueriesUtils.getVyaapariArrayList()));
	}

	@FXML
	public void addColdStorage() {
		try {
			Transaction tx = dbQueriesUtils.getSession().beginTransaction();
			ColdStorage cold = makeColdStorage(new ColdStorage());
			dbQueriesUtils.getSession().save(cold);
			tx.commit();
			successMessage.setText("Cold added successfully. Cold Id : " + cold.getColdId());
		} catch (Exception e) {
			successMessage.setText("Make sure you have entererd all fields correctly !");
		}
	}

	private ColdStorage makeColdStorage(ColdStorage cold) {
		Date date = DateUtils.makeDate(coldDate);
		cold.setAddress(coldAdd.getText());
		cold.setPhoneNo(Long.parseLong(coldPhone.getText()));
		cold.setDate(date);
		cold.setColdName(coldName.getText());
		return cold;
	}

	@FXML
	protected void addVyaapari() {
		try {
			Transaction tx = dbQueriesUtils.getSession().beginTransaction();
			Vyaapari vyaapari = makeVyaapari(new Vyaapari());
			dbQueriesUtils.getSession().save(vyaapari);
			tx.commit();
			successMessage1.setText("Vyaapari added successfully. Vyaapari ID : " + vyaapari.getVyaapariId());
		} catch (Exception e) {
			successMessage1.setText("Make sure you have entererd all fields correctly !");
		}
	}

	private Vyaapari makeVyaapari(Vyaapari vyaapari) {
		Date date = DateUtils.makeDate(coldDate);
		vyaapari.setAddress(vyaapariAddress.getText());
		vyaapari.setPhoneNo(Long.parseLong(vyaapariNumber.getText()));
		vyaapari.setDate(date);
		vyaapari.setColdName(vyaapariName.getText());
		return vyaapari;
	}

}
