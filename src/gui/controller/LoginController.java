package gui.controller;

import java.util.List;

import dados.controller.TblUsuariosController;
import javafx.application.Platform;
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

	private Parent parent;

	public void novaJanela() {

		Stage stageLogin = new Stage();

		try {
			Parent parent = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
			Scene scenePrincipal = new Scene(parent);
			stageLogin.setResizable(false);
			stageLogin.setTitle("DEMO - Pendências");
			stageLogin.getIcons().add(new Image("/imgs/logo2.png"));
			stageLogin.setScene(scenePrincipal);
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

	public void onButtonLoginAction(){
		Usuario tempUser = new Usuario(null, null, user.getText(), pass.getText(), null, null, null, null, null);
		List<Usuario> tempList = TblUsuariosController.updateListaUsuarios(); 
		Integer validado = 0;
		for (Usuario x : tempList) {
			validado = UserService.validaLogin(tempList, tempUser);
		}
		if (validado == 1) {
			System.out.println("O LOGIN FUNCIONOU!!");
			String tpus = user.getText();
			Stage stage = (Stage) close.getScene().getWindow();
			stage.close();
			try {
			 	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Principal.fxml"));
				parent = loader.load();
				PrincipalController pc = loader.getController();
				pc.setLoggedUser(tpus);
				Stage stagePrincipal = new Stage();
				Scene scene = new Scene(parent);
				stagePrincipal.setTitle("GRUPO ADSERVI");
				stagePrincipal.getIcons().add(new Image("/imgs/logo2.png"));
				stagePrincipal.setScene(scene);
				stagePrincipal.setOnHidden(e -> Platform.exit());
				stagePrincipal.show();
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
