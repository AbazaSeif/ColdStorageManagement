package com.mg.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.mg.csms.beans.InwardInventory;
import com.mg.csms.database.SessionCreation;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class InwardInventoryController {

	@FXML
	private Pane inwardPane;
	@FXML
	private TextField inwardDate;
	@FXML
	private TextField inwardHSNCode;
	@FXML
	private TextField inwardItemName;
	@FXML
	private TextField inwardPartyId;
	@FXML
	private TextField inwardColdNo;
	@FXML
	private TextField inwardLotNo;
	@FXML
	private TextField inwardQTY;
	@FXML
	private TextField inwardBardana;

	@FXML
	private Text successMessage;

	@FXML
	public void addInwardInventory() {
		try {
			Session s = SessionCreation.getSessionInstance();
			Transaction tx = s.beginTransaction();
			s.save(makeInventory());
			tx.commit();
			successMessage.setText("Inward Entry added successfully.");
			if (s != null)
				SessionCreation.closeSession();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private InwardInventory makeInventory() {
		InwardInventory inwardInventory = new InwardInventory();
		Date date = new Date(Integer.parseInt(inwardDate.getText().substring(0, 2)),
				Integer.parseInt(inwardDate.getText().substring(3, 5)),
				Integer.parseInt(inwardDate.getText().substring(6, 10)));
		inwardInventory.setDate(date);
		inwardInventory.setHsnCode(Integer.parseInt(inwardHSNCode.getText()));
		inwardInventory.setItemName(inwardItemName.getText());
		inwardInventory.setPartyId(inwardPartyId.getText());
		inwardInventory.setLotNo(Integer.parseInt(inwardLotNo.getText()));
		inwardInventory.setQty(Integer.parseInt(inwardQTY.getText()));
		inwardInventory.setBardana(Integer.parseInt(inwardBardana.getText()));
		return inwardInventory;
	}

	@FXML
	protected void initialize() {
		inwardDate.setPromptText("dd/MM/yyyy");
		inwardDate.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
	}
}
