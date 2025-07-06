package gui.controller;

import java.util.List;

import dados.controller.TblUsuariosController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import models.Usuario;
import services.UserService;

public class PrincipalController {

	@FXML
	private MenuItem cadastroUsuario;
	@FXML
	private MenuItem cadastroSetor;
	@FXML
	private Label loggedUser;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button changeUser;

	public PrincipalController() {
	}

	public void novaJanela(PrincipalController pc, String loggedUser) {
		try {
			Stage stagePrincipal = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Principal.fxml"));
			loader.setController(pc);
			Parent parent = loader.load();
			pc.setLoggedUser(loggedUser);
			Scene scene = new Scene(parent);
			stagePrincipal.setTitle("GRUPO ADSERVI");
			stagePrincipal.getIcons().add(new Image("/imgs/logo2.png"));
			stagePrincipal.setScene(scene);
			stagePrincipal.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setLoggedUser(String loggedUserTxt) {
		this.loggedUser.setText(loggedUserTxt);
	}

	public String getLoggedUser() {
		return this.loggedUser.getText();
	}

	@FXML
	public void onButtonLogoutAction() {
		Stage stage = (Stage) logoutBtn.getScene().getWindow();
		stage.close();
	}

	@FXML
	public void onButtonChangeUserAction() {

		Stage stage = (Stage) logoutBtn.getScene().getWindow();
		stage.hide();

		LoginController lg = new LoginController();
		lg.novaJanela(lg);

	}

	Stage stageCadastroUser = null;

	@FXML
	public void onMenuItemCadastroUsuarioAction() {

		Usuario tempUser = new Usuario(null, null, getLoggedUser(), null, null, null, null, null, null);

		List tempList = TblUsuariosController.updateListaUsuarios();

		Integer validado = 0;

		for (Object x : tempList) {

			validado = UserService.validaPermissao(tempList, tempUser);
		}

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			CadastroUsuarioController cuc = new CadastroUsuarioController();

			if (stageCadastroUser == null) {
				stageCadastroUser = new Stage();

				try {
					Parent parent = FXMLLoader.load(getClass().getResource("/gui/CadastroUsuario.fxml"));
					Scene sceneCadastroUser = new Scene(parent);
					stageCadastroUser.setTitle("Usuarios");
					stageCadastroUser.setResizable(false);
					stageCadastroUser.getIcons().add(new Image("/imgs/18x18/usuario.png"));
					stageCadastroUser.setScene(sceneCadastroUser);
					stageCadastroUser.setOnHidden(we -> stageCadastroUser = null);
					stageCadastroUser.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				stageCadastroUser.toFront();
			}

		} else {
			System.out.println("ALGO ERRADO COM A PERMISSÃO!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Usuário sem permissão");
			alert.setHeaderText("Este usuário não tem permissão para esta rotina!");
			alert.showAndWait();
		}

	}

	Stage stageCadastroSetor = null;

	@FXML
	public void onMenuItemCadastroSetorAction() {

		Usuario tempUser = new Usuario(null, null, getLoggedUser(), null, null, null, null, null, null);

		List tempList = TblUsuariosController.updateListaUsuarios();

		Integer validado = 0;

		for (Object x : tempList) {

			validado = UserService.validaPermissao(tempList, tempUser);
		}

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			CadastroUsuarioController cuc = new CadastroUsuarioController();
			;

			if (stageCadastroSetor == null) {
				stageCadastroSetor = new Stage();

				try {
					Parent parent = FXMLLoader.load(getClass().getResource("/gui/CadastroSetores.fxml"));
					Scene sceneCadastroSetor = new Scene(parent);
					stageCadastroSetor.setTitle("Setores");
					stageCadastroSetor.setResizable(false);
					stageCadastroSetor.getIcons().add(new Image("/imgs/18x18/conferencia.png"));
					stageCadastroSetor.setScene(sceneCadastroSetor);
					stageCadastroSetor.setOnHidden(we -> stageCadastroSetor = null);
					stageCadastroSetor.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				stageCadastroSetor.toFront();
			}

		} else {
			System.out.println("ALGO ERRADO COM A PERMISSÃO!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Usuário sem permissão");
			alert.setHeaderText("Este usuário não tem permissão para esta rotina!");
			alert.showAndWait();
		}

	}
}