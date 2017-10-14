package com.mg.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Mohak Gupta
 *
 */
public class RegistrationController {

	@FXML
	private Text actiontarget;

	@FXML
	private PasswordField pfUserPassword;

	@FXML
	private Hyperlink handleSubmitButtonAction;

	@FXML
	protected void handleSubmitButtonAction(ActionEvent event) {
		System.out.println(pfUserPassword.getText());
		Parent root;
		try {
			root = (Parent) FXMLLoader.load(getClass().getResource("/view/Registration.fxml"));
			root.autosize();
			Scene scene = new Scene(root);
			Stage nStage = new Stage();
			nStage.setScene(scene);
			nStage.setMaximized(true);
			nStage.setTitle("Registration -StoreKeeper");
			nStage.show();

			Stage hlLoginStage = (Stage) this.handleSubmitButtonAction.getScene().getWindow();
			hlLoginStage.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@FXML
	protected void pfKeyTyped(ActionEvent event) {
		actiontarget.setText("Sign in button pressed");
	}

	@FXML
	protected void btnRegistration(ActionEvent event) {
		actiontarget.setText("Sign in button pressed");
	}

}
