package gui.controller;

import java.util.ArrayList;
import java.util.List;

import dados.controller.TblUsuariosController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Usuario;
import services.UserService;

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

		Usuario tempUser = new Usuario(null, null, user.getText(), pass.getText(), null, null, null, null, null);

		List tempList = TblUsuariosController.updateListaUsuarios();

		Integer validado = 0;

		for (Object x : tempList) {

			validado = UserService.validaLogin(tempList, tempUser);
		}

		if (validado == 1) {
			System.out.println("IT WORKS!!");
		} else {
			System.out.println("SOMETHING IS WRONG!");
		}
	}
}
