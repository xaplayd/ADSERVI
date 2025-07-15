package gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

	public void novaJanela(LoginController lg) {

		try {
			Stage stageLogin = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
			loader.setController(lg);
			Parent parent = loader.load();
			Scene sceneLogin = new Scene(parent);
			stageLogin.setTitle("Demo - Pendências");
			stageLogin.setResizable(false);
			stageLogin.getIcons().add(new Image("/imgs/logo2.png"));
			stageLogin.setScene(sceneLogin);
			stageLogin.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onButtonCloseAction() {
		Stage stage = (Stage) close.getScene().getWindow();
		stage.close();
	}

	public void onButtonForgotPassAction() {
		// colocar a ação de esquercer senha aqui
	}

	public void onButtonLoginAction() {
		Usuario tempUser = new Usuario(null, null, user.getText(), pass.getText(), null, null, null, null, null);
		
		Integer validado = 0;
		validado = UserService.validaLogin(tempUser);
		
		if (validado == 1) {
			System.out.println("O LOGIN FUNCIONOU!!");
			PrincipalController pc = new PrincipalController();
			Stage stage = (Stage) close.getScene().getWindow();
			stage.close();
			try {
				pc.novaJanela(pc, user.getText());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("ALGO ERRADO COM O LOGIN!!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro ao logar");
			alert.setHeaderText("Usuário ou senha errada! Por favor, tente novamente ou contate o administrador!");
			alert.showAndWait();
		}
	}
}
