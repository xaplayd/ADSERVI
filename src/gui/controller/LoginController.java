package gui.controller;

import java.util.List;

import dados.controller.TblUsuariosController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	@FXML
	private ImageView logo;

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
			System.out.println("LOGIN WORKS!!");

			Stage stage = (Stage) close.getScene().getWindow();
			stage.close();
			
			Stage stagePrincipal = new Stage();
			
			try {
				Parent parent = FXMLLoader.load(getClass().getResource("/gui/Principal.fxml"));
				Scene scene = new Scene(parent);
				stagePrincipal.setTitle("DEMO");
				stagePrincipal.getIcons().add(new Image("/imgs/logo2.png"));
				stagePrincipal.setScene(scene);
				stagePrincipal.show();
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			System.out.println("SOMETHING IS WRONG ON LOGIN!");
		}
	}

}
