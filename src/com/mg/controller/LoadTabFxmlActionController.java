package com.mg.controller;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

/**
 * @author Mohak Gupta
 *
 */
public class LoadTabFxmlActionController {

	@FXML
	Pane secPane;

	public void loadFxmlFile() {
		try {
			System.out.println("loading File");
			Pane newLoadedPane = FXMLLoader.load(getClass().getResource("/application/fxml2.fxml"));
			secPane.getChildren().add(newLoadedPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
