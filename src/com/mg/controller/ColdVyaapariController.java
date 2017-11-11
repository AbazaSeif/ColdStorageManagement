package com.mg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mg.csms.beans.ColdStorage;
import com.mg.csms.beans.Vyaapari;
import com.mg.csms.database.SessionCreation;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
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
	Tab addVyaapari;

	@FXML
	Tab addColdStorage;

	@FXML
	private Text successMessage;

	@FXML
	private Text successMessage1;

	private Session s;

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

	}

	/*
	 * private void initializePartyTable() { partyList =
	 * s.createQuery("FROM Party").list(); tablePartyId.setCellValueFactory(new
	 * PropertyValueFactory<Party, String>("partyId"));
	 * tablePartyName.setCellValueFactory(new PropertyValueFactory<Party,
	 * String>("partyName")); tablePartyGstn.setCellValueFactory(new
	 * PropertyValueFactory<Party, String>("gstNo"));
	 * tablePartyAddress.setCellValueFactory(new PropertyValueFactory<Party,
	 * String>("address")); tablePartyPhone.setCellValueFactory(new
	 * PropertyValueFactory<Party, String>("phoneNo"));
	 * partyTable.getItems().setAll(partyList); }
	 */

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
		//
		// String partySearch = searchPartyName.getText();
		// List<Party> searchList = partyList.stream()
		// .filter((party) ->
		// party.getPartyName().toLowerCase().contains(partySearch.toLowerCase()))
		// .collect(Collectors.toList());
		// if (!"".equalsIgnoreCase(partySearch) || partySearch != null)
		// partyTable.getItems().setAll(searchList);
		// else
		// partyTable.getItems().setAll(partyList);

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
