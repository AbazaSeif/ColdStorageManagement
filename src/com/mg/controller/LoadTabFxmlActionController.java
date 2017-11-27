package com.mg.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Mohak Gupta
 *
 */
public class LoadTabFxmlActionController {

	@FXML
	private Pane secPane;
	@FXML
	private Tab stockEntryTab;
	@FXML
	private Tab stockinHandTab;
	@FXML
	private Tab billingTab;
	@FXML
	private Tab demandTab;
	@FXML
	private Tab coldVyaapariTab;

	@FXML
	protected void initialize() {
		stockEntryTab.setOnSelectionChanged(event-> loadTabBasedFXML("/view/StockEntry.fxml"));
		stockinHandTab.setOnSelectionChanged(event-> loadTabBasedFXML("/view/StockInHand.fxml"));
		demandTab.setOnSelectionChanged(event-> loadTabBasedFXML("/view/Demand.fxml"));
		billingTab.setOnSelectionChanged(event-> loadTabBasedFXML("/view/Billing.fxml"));
		coldVyaapariTab.setOnSelectionChanged(event-> loadTabBasedFXML("/view/ColdStorage.fxml"));
	}

	private void loadTabBasedFXML(String fxmlPath) {
		try {
			AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource(fxmlPath));
			stockEntryTab.setContent(anchorPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
