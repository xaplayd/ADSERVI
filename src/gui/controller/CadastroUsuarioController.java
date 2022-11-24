package gui.controller;

import java.util.List;

import dados.controller.TblUsuariosController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Usuario;
import services.UserService;

public class CadastroUsuarioController {

	@FXML
	private TextField codigo;
	@FXML
	private Button procuraUsuario;
	@FXML
	private TextField nome;
	@FXML
	private TextField login;
	@FXML
	private PasswordField senha;
	@FXML
	private TextField permissoes;
	@FXML
	private Button procuraPermissoes;
	@FXML
	private TextField email;
	@FXML
	private TextField emailGerencia;
	@FXML
	private TextField setor;
	@FXML
	private Button procuraSetor;
	@FXML
	private Button novoUsuario;
	@FXML
	private Button editarUsuario;
	@FXML
	private Button excluirUsuario;
	@FXML
	private Button salvarUsuario;
	@FXML
	private Button fechar;
	@FXML
	private Button cancelar;

	public void onCodigoTxtFieldKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER)) {

			Integer cod = Integer.parseInt(codigo.getText());
			List tempList = TblUsuariosController.updateListaUsuarios();

			Usuario puxa = UserService.puxaUser(tempList, cod);

			if (puxa != null) {
				codigo.setDisable(true);
				procuraUsuario.setDisable(true);
				novoUsuario.setDisable(true);
				editarUsuario.setDisable(false);
				excluirUsuario.setDisable(false);
				cancelar.setDisable(false);

				nome.setText(puxa.getNome());
				login.setText(puxa.getLogin());
				senha.setText(puxa.getSenha());
				permissoes.setText(puxa.getPermissoes().toString());
				email.setText(puxa.getEmail());
				emailGerencia.setText(puxa.getEmailGerencia());
				setor.setText(puxa.getSetor());
			} else {
				System.out.println("NAO ACHOU USUARIO!!");
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Usuário não encontrado");
				alert.setHeaderText("Não foi possível achar nenhum usuário pelo código informado!");
				alert.showAndWait();
			}

		}

	}

	public void novoUser() {
		codigo.setDisable(true);
		procuraUsuario.setDisable(true);
		procuraPermissoes.setDisable(false);
		permissoes.setEditable(false);
		procuraSetor.setDisable(false);
		nome.setDisable(false);
		login.setDisable(false);
		senha.setDisable(false);
		permissoes.setDisable(false);
		email.setDisable(false);
		emailGerencia.setDisable(false);
		setor.setDisable(false);
		setor.setEditable(false);
		excluirUsuario.setDisable(false);
		salvarUsuario.setDisable(false);
		novoUsuario.setDisable(true);
		excluirUsuario.setDisable(true);
		cancelar.setDisable(false);
	}

	public void fechaJanela() {
		Stage stage = (Stage) fechar.getScene().getWindow();
		stage.close();
	}

	public void editaUsuarios() {
	}

	public void criaUsuarios() {
	}

	public void salvaUsuarios() {
	}

	public void buscaUsuarios() {
	}
	public void cancelaUsuario() {
		codigo.setDisable(false);
		procuraUsuario.setDisable(false);
		procuraPermissoes.setDisable(true);
		permissoes.setEditable(true);
		procuraSetor.setDisable(true);
		nome.setDisable(true);
		login.setDisable(true);
		senha.setDisable(true);
		permissoes.setDisable(true);
		email.setDisable(true);
		emailGerencia.setDisable(true);
		setor.setDisable(true);
		excluirUsuario.setDisable(true);
		salvarUsuario.setDisable(true);
		novoUsuario.setDisable(false);
		excluirUsuario.setDisable(true);
	}
	
}
