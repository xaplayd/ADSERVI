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

	private Integer janelaCadastroUsuarioSituacao = 0;

	@FXML
	private MenuItem cadastroUsuario;
	@FXML
	private Label loggedUser;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button changeUser;

	private Parent parent;

	public PrincipalController() {
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
		stage.close();
		LoginController lg = new LoginController();
		lg.novaJanela();
	}

	@FXML
	public void onMenuItemCadastroUsuarioAction() {

		Usuario tempUser = new Usuario(null, null, getLoggedUser(), null, null, null, null, null, null);

		List tempList = TblUsuariosController.updateListaUsuarios();

		Integer validado = 0;

		CadastroUsuarioController janela = null;

		for (Object x : tempList) {

			validado = UserService.validaPermissao(tempList, tempUser);
		}

		if (validado == 1) {
			System.out.println("PERMISSION WORKS!!");
			if (janelaCadastroUsuarioSituacao == 0) {
				janelaCadastroUsuarioSituacao = 1;
				Stage stageCadastroUsuario = new Stage();
				janela = new CadastroUsuarioController(stageCadastroUsuario);
				janela.novaJanela();
			} else {
				janela.getStageCadastroUsuario().toFront();
			}

		} else {
			System.out.println("SOMETHING IS WRONG WITH PERMISSION");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Permission Error");
			alert.setHeaderText("This user doesn't have permission for this!");
			alert.showAndWait();
		}

	}
}