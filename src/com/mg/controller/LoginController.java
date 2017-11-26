package com.mg.controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

/**
 * @author Mohak Gupta
 *
 */
public class LoginController {
	@FXML
	private AnchorPane apDesignPane;
	@FXML
	private Hyperlink createAccount;
	@FXML
	private TextField tfUserName;
	@FXML
	private PasswordField pfUserPassword;

	@FXML
	private Button btnLogin;

	@FXML
	public void createAccount() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Registration.fxml"));
			Pane cmdPane = (Pane) fxmlLoader.load();
			apDesignPane.getChildren().clear();
			apDesignPane.getChildren().add(cmdPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void btnLogin() {
		FileReader reader;
		try {
			reader = new FileReader("./resources/login.properties");
			Properties p = new Properties();
			p.load(reader);

			String userName = p.getProperty("username");
			String password = p.getProperty("password");

			// if (tfUserName.getText().equalsIgnoreCase(userName) &&
			// pfUserPassword.getText().equalsIgnoreCase(password))
			loadMainPage();
			// else
			// createAccount();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void loadMainPage() {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/MainMenu.fxml"));
			Pane cmdPane = (Pane) fxmlLoader.load();
			apDesignPane.getChildren().clear();
			apDesignPane.getChildren().add(cmdPane);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
