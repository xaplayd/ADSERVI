package gui.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
	private String path = "/gui/CadastroUsuario.fxml";
	@FXML
	private AnchorPane scenePane;
	@FXML
	private Stage stageCadastroUsuario;
	@FXML
	private Parent parent;
	@FXML
	private Scene scene = new Scene(parent);
	
	public CadastroUsuarioController(Stage stageCadastroUsuario) {
			this.stageCadastroUsuario = stageCadastroUsuario;
	}

	public Stage getStageCadastroUsuario() {
		return this.stageCadastroUsuario;
	}

	public void setStageCadastroUsuario(Stage stageCadastroUsuario) {
		this.stageCadastroUsuario = stageCadastroUsuario;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void novaJanela() {
		try {
			getStageCadastroUsuario();
			parent = FXMLLoader.load(getClass().getResource("/gui/CadastroUsuario.fxml"));
			scene = new Scene(parent);
			getStageCadastroUsuario().setResizable(false);
			getStageCadastroUsuario().show();
		} catch (IOException e) {
			e.getMessage();
		}
	}
	
	public void onButtonFecharAction(ActionEvent event) {
		stageCadastroUsuario = (Stage) stageCadastroUsuario.getScene().getWindow();
		stageCadastroUsuario.close();
	}



	public void editaUsuarios() {
	}

	public void criaUsuarios() {

	}

	public void salvaUsuarios() {

	}

	public void buscaUsuarios() {

	}

}
