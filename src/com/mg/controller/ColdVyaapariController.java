package com.mg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Vyaapari;
import com.mg.csms.database.SessionCreation;

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

	@FXML
	private TextField cold_date;
	@FXML
	private TextField cold_name;
	@FXML
	private TextField cold_add;
	@FXML
	private TextField cold_phone;
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

	private List<Vyaapari> vyaapariArrayList;
	private List<ColdStorage> coldStorageList;

	@FXML
	Tab addVyaapari;

	@FXML
	Tab addColdStorage;

	@FXML
	private Text successMessage;

	@FXML
	private Text successMessage1;

	private Session s;

	@FXML
	protected void initialize() {
		try {
			s = SessionCreation.getSessionInstance();
		} catch (Exception e) {
			successMessage.setText("Database errors occoured");
		}
		cold_date.setPromptText("dd/MM/yyyy");
		cold_date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		vyaapariDate.setPromptText("dd/MM/yyyy");
		vyaapariDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		initializeColdTable();
		initializeVyaapariTable();
	}

	private void initializeColdTable() {
		makeColdStorageList();
		coldListView.setEditable(true);
		listColdName.setCellValueFactory(new PropertyValueFactory<ColdStorage, String>("coldName"));
		listColdPhone.setCellValueFactory(new PropertyValueFactory<ColdStorage, String>("phoneNo"));
		listColdAddress.setCellValueFactory(new PropertyValueFactory<ColdStorage, String>("address"));
		coldListView.setItems(FXCollections.observableList(coldStorageList));
	}

	private void initializeVyaapariTable() {
		getVyaapariList();
		vyaapariListView.setEditable(true);
		listVyaapariName.setCellValueFactory(new PropertyValueFactory<Vyaapari, String>("vyaapariName"));
		listVyaapariPhone.setCellValueFactory(new PropertyValueFactory<Vyaapari, String>("phoneNo"));
		listVyaapariAddress.setCellValueFactory(new PropertyValueFactory<Vyaapari, String>("address"));
		vyaapariListView.setItems(FXCollections.observableList(vyaapariArrayList));
	}

	@SuppressWarnings("unchecked")
	private void getVyaapariList() {
		vyaapariArrayList = new ArrayList<>();
		vyaapariArrayList = s.createQuery("FROM Vyaapari").list();
	}

	@SuppressWarnings("unchecked")
	private void makeColdStorageList() {
		coldStorageList = new ArrayList<>();
		String hql = "FROM ColdStorage";
		coldStorageList = s.createQuery(hql).list();
	}

	@FXML
	public void addColdStorage() {
		try {
			Transaction tx = s.beginTransaction();
			ColdStorage cold = makeColdStorage(new ColdStorage());
			s.save(cold);
			tx.commit();
			successMessage.setText("Cold added successfully. Cold Id : " + cold.getColdId());
		} catch (Exception e) {
			successMessage.setText("Make sure you have entererd all fields correctly !");
		}
	}

	private ColdStorage makeColdStorage(ColdStorage cold) {
		Date date = makeDate();
		cold.setAddress(cold_add.getText());
		cold.setPhoneNo(Long.parseLong(cold_phone.getText()));
		cold.setDate(date);
		cold.setColdName(cold_name.getText());
		return cold;
	}

	@SuppressWarnings({ "deprecation" })
	private Date makeDate() {
		Date date = new Date(Integer.parseInt(cold_date.getText().substring(0, 2)),
				Integer.parseInt(cold_date.getText().substring(3, 5)),
				Integer.parseInt(cold_date.getText().substring(6, 10)));
		return date;
	}

	@FXML
	protected void addVyaapari() {
		try {
			Transaction tx = s.beginTransaction();
			Vyaapari vyaapari = makeVyaapari(new Vyaapari());
			s.save(vyaapari);
			tx.commit();
			successMessage1.setText("Vyaapari added successfully. Vyaapari ID : " + vyaapari.getVyaapariId());
		} catch (Exception e) {
			successMessage1.setText("Make sure you have entererd all fields correctly !");
		}
	}

	private Vyaapari makeVyaapari(Vyaapari vyaapari) {
		Date date = makeDate();
		vyaapari.setAddress(vyaapariAddress.getText());
		vyaapari.setPhoneNo(Long.parseLong(vyaapariNumber.getText()));
		vyaapari.setDate(date);
		vyaapari.setColdName(vyaapariName.getText());
		return vyaapari;
	}

}
