package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

	@FXML
	private Button login;
	@FXML
	private Button forgotPass;
	@FXML
	private Button close;
	@FXML
	private TextField user;
	@FXML
	private PasswordField pass;

	public void onButtonCloseAction() {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	public void onButtonForgotPassAction() {
		// colocar a ação de esquercer senha aqui
	}

	public void onButtonLoginAction() {
		
	}
}
