package gui.controller;

import java.util.List;

import dados.controller.TblSetoresController;
import dados.controller.TblUsuariosController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
	@FXML
	private TextField situacao;
	@FXML
	private Button procuraSituacao;
	@FXML
	private Integer modo = 0; // modo 0 = neutro || modo 1 = em edição || modo 2 = novo

	public void onCodigoTxtFieldKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)) {

			if (codigo.getText() == "") {

			} else {

				Integer codUser = Integer.parseInt(codigo.getText());
				List<Usuario> tempListUser = TblUsuariosController.updateListaUsuarios();
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
					situacao.setText(puxaUser.getSituacao().toString());
					Integer codSetor = Integer.parseInt(setor.getText());
					List<Setor> tempListSetor = TblSetoresController.updateListaSetores();

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

	}

	public void novoUser() {
		codigo.setDisable(true);
		codigo.setText(null);
		procuraUsuario.setDisable(true);
		procuraPermissoes.setDisable(false);
		permissoes.setEditable(true);
		procuraSetor.setDisable(false);
		nome.setDisable(false);
		login.setDisable(false);
		senha.setDisable(false);
		permissoes.setDisable(false);
		email.setDisable(false);
		emailGerencia.setDisable(false);
		setor.setDisable(false);
		setor.setEditable(true);
		excluirUsuario.setDisable(false);
		salvarUsuario.setDisable(false);
		novoUsuario.setDisable(true);
		excluirUsuario.setDisable(true);
		cancelar.setDisable(false);
		setorDesc.setText(null);
		situacao.setDisable(false);
		situacao.setText(null);
		procuraSituacao.setDisable(false);
		situacao.setEditable(true);
		this.modo = 2;
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
		procuraSituacao.setDisable(false);
		situacao.setEditable(true);
		situacao.setDisable(false);
		this.modo = 1;
	}

	public void criaUsuarios() {
	}

	public void salvaUsuario() {
		if (this.modo == 1) {
			Integer tempCodigo = Integer.parseInt(codigo.getText());
			String tempNome = nome.getText();
			String tempLogin = login.getText();
			String tempSenha = senha.getText();
			Integer tempPermissoes = Integer.parseInt(permissoes.getText());
			String tempEmail = email.getText();
			String tempEmailGerencia = emailGerencia.getText();
			Integer tempSetor = Integer.parseInt(setor.getText());
			Integer tempSituacao = Integer.parseInt(situacao.getText());
			TblUsuariosController.editaUsuarioNaLista(tempCodigo, tempNome, tempLogin, tempSenha, tempPermissoes,
					tempEmail, tempEmailGerencia, tempSetor, tempSituacao);
		} else if (this.modo == 2) {
			String tempNome = nome.getText();
			String tempLogin = login.getText();
			String tempSenha = senha.getText();
			Integer tempPermissoes = Integer.parseInt(permissoes.getText());
			String tempEmail = email.getText();
			String tempEmailGerencia = emailGerencia.getText();
			Integer tempSetor = Integer.parseInt(setor.getText());
			Integer tempSituacao = Integer.parseInt(situacao.getText());
			Usuario novoUsuario = TblUsuariosController.insereUsuarioNaLista(tempNome, tempLogin, tempSenha,
					tempPermissoes, tempEmail, tempEmailGerencia, tempSetor, tempSituacao);
			codigo.setText(novoUsuario.getCodigo().toString());
		}
		this.modo = 0;
		excluirUsuario.setDisable(false);
		editarUsuario.setDisable(false);
		codigo.setDisable(true);
		procuraUsuario.setDisable(true);
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
		salvarUsuario.setDisable(true);
		novoUsuario.setDisable(true);
		excluirUsuario.setDisable(false);
		cancelar.setDisable(false);
		situacao.setDisable(true);
		procuraSituacao.setDisable(true);

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
		situacao.setText(null);
		situacao.setDisable(true);
		procuraSituacao.setDisable(true);
	}

	public void excluirUsuario() {
		String tempCodigo = codigo.getText();
		System.out.println("EXCLUSÃO DE DADOS ATIVA!!");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Confirmação:");
		alert.setHeaderText("Realmente deseja excluir o usuário selecionado?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			String processa = TblUsuariosController.deletaUsuarioNaLista(tempCodigo);

			if (processa == "sucesso") {
				cancelaUsuario();
				this.modo = 0;
			}
		}
	}

	Stage stagePesquisaUsuario = null;
	
	@FXML
	public void onButtonPesquisaUsuarioAction() {
		
		if (stagePesquisaUsuario == null) {
			stagePesquisaUsuario = new Stage();

			try {
				PesquisaUsuarioController psu = new PesquisaUsuarioController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
				loader.setController(psu);
				
				Parent parent = loader.load();

				// Passa a referência do controller para atualizar a string após a seleção
				Scene scenePesquisaUsuario = new Scene(parent);
				stagePesquisaUsuario.setTitle("Pesquisa Usuarios");
				stagePesquisaUsuario.setResizable(true);
				stagePesquisaUsuario.getIcons().add(new Image("/imgs/18x18/conferencia.png"));
				stagePesquisaUsuario.setScene(scenePesquisaUsuario);

				// Defina o que acontece quando a janela for fechada
				stagePesquisaUsuario.setOnHidden(event -> {
					// Aqui você pega a string da nova janela
					String usuarioSelecionado = psu.getUsuarioSelecionado();
					System.out.println("Usuario Selecionado: " + usuarioSelecionado);

					// Agora você pode fazer o que quiser com essa string
					if (usuarioSelecionado != null) {
						codigo.setText(usuarioSelecionado);
					}
					stagePesquisaUsuario = null;
					codigo.requestFocus();
				});

				stagePesquisaUsuario.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			stagePesquisaUsuario.toFront();
		}
	}
}
