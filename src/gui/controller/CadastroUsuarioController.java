package gui.controller;

import java.util.List;

import dados.controller.TblSetoresController;
import dados.controller.TblUsuariosController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Setor;
import models.Usuario;
import services.SetorService;
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
	@FXML
	private Label setorDesc;

	public void onCodigoTxtFieldKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)) {

			Integer codUser = Integer.parseInt(codigo.getText());
			List tempListUser = TblUsuariosController.updateListaUsuarios();

			Usuario puxaUser = UserService.puxaUser(tempListUser, codUser);
			

			if (puxaUser != null) {
				codigo.setDisable(true);
				procuraUsuario.setDisable(true);
				novoUsuario.setDisable(true);
				editarUsuario.setDisable(false);
				excluirUsuario.setDisable(false);
				cancelar.setDisable(false);

				nome.setText(puxaUser.getNome());
				login.setText(puxaUser.getLogin());
				senha.setText(puxaUser.getSenha());
				permissoes.setText(puxaUser.getPermissoes().toString());
				email.setText(puxaUser.getEmail());
				emailGerencia.setText(puxaUser.getEmailGerencia());
				setor.setText(puxaUser.getSetor().toString());
				
				Integer codSetor = Integer.parseInt(setor.getText());
				List tempListSetor = TblSetoresController.updateListaSetores();

				Setor puxaSetor = SetorService.puxaSetor(tempListSetor, codSetor);
				
				setorDesc.setText(puxaSetor.getNome());
				
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
		setorDesc.setText(null);
	}

	public void fechaJanela() {
		Stage stage = (Stage) fechar.getScene().getWindow();
		stage.close();
	}

	public void editaUsuarios() {
		nome.setDisable(false);
		login.setDisable(false);
		senha.setDisable(false);
		permissoes.setDisable(false);
		procuraPermissoes.setDisable(false);
		email.setDisable(false);
		emailGerencia.setDisable(false);
		setor.setDisable(false);
		procuraSetor.setDisable(false);
		salvarUsuario.setDisable(false);
		editarUsuario.setDisable(true);
	}

	public void criaUsuarios() {
	}

	public void salvaUsuarios() {
	}

	public void buscaUsuarios() {
	}
	public void cancelaUsuario() {
		excluirUsuario.setDisable(true);
		editarUsuario.setDisable(true);
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
		codigo.setText(null);
		nome.setText(null);
		login.setText(null);
		senha.setText(null);
		permissoes.setText(null);
		email.setText(null);
		emailGerencia.setText(null);
		setor.setText(null);
		cancelar.setDisable(true);
		setorDesc.setText(null);
	}
	
}
