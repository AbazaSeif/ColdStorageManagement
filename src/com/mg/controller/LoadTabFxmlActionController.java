package com.mg.controller;

import java.io.IOException;

import javafx.event.Event;
import javafx.event.EventHandler;
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
	Pane secPane;
	@FXML
	Tab stockEntryTab;
	@FXML
	Tab stockinHandTab;
	@FXML
	Tab billingTab;
	@FXML
	Tab demandTab;
	@FXML
	Tab coldVyaapariTab;

	public void loadFxmlFile() {

		try {
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/application/fxml2.fxml"));
			secPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void initialize() {
		stockEntryTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/StockEntry.fxml"));
					stockEntryTab.setContent(anchorPane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		stockinHandTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/StockInHand.fxml"));
					stockinHandTab.setContent(anchorPane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		demandTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/Demand.fxml"));
					demandTab.setContent(anchorPane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		billingTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/Billing.fxml"));
					billingTab.setContent(anchorPane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});

		coldVyaapariTab.setOnSelectionChanged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				try {
					AnchorPane anchorPane = FXMLLoader.load(this.getClass().getResource("/view/ColdStorage.fxml"));
					coldVyaapariTab.setContent(anchorPane);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
