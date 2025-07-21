package gui.controller;

import java.sql.SQLException;

import dao.TblNiveisDAO;
import dao.TblNiveisDAOImpl;
import dao.TblSetoresDAO;
import dao.TblSetoresDAOImpl;
import dao.TblSituacaoDAO;
import dao.TblSituacaoDAOImpl;
import dao.TblUsuariosDAO;
import dao.TblUsuariosDAOImpl;
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
import models.Nivel;
import models.Setor;
import models.Situacao;
import models.Usuario;

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
				TblUsuariosDAO usuariodao = new TblUsuariosDAOImpl();
				try {
					Usuario tempUser = usuariodao.getById(codUser);
					if (tempUser != null) {
						codigo.setDisable(true);
						procuraUsuario.setDisable(true);
						novoUsuario.setDisable(true);
						editarUsuario.setDisable(false);
						excluirUsuario.setDisable(false);
						cancelar.setDisable(false);

						nome.setText(tempUser.getNome());
						login.setText(tempUser.getLogin());
						senha.setText(tempUser.getSenha());
						permissoes.setText(tempUser.getPermissoes().toString());
						email.setText(tempUser.getEmail());
						emailGerencia.setText(tempUser.getEmailGerencia());
						setor.setText(tempUser.getSetor().toString());
						situacao.setText(tempUser.getSituacao().toString());
						TblSetoresDAO setordao = new TblSetoresDAOImpl();
						Setor setor = setordao.getById(tempUser.getSetor());
						setorDesc.setText(setor.getNome());

					} else {
						System.out.println("NAO ACHOU USUARIO!!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Usuário não encontrado");
						alert.setHeaderText("Não foi possível achar nenhum usuário pelo código informado!");
						alert.showAndWait();
					}
				} catch (SQLException exception) {
					exception.getMessage();
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

	public void salvaUsuario() {
		TblUsuariosDAOImpl usuariodao = new TblUsuariosDAOImpl();
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
			Usuario tempUser = new Usuario(tempCodigo, tempNome, tempLogin, tempSenha, tempPermissoes, tempEmail,
					tempEmailGerencia, tempSetor, tempSituacao);
			usuariodao.updateById(tempUser);
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
		} else if (this.modo == 2) {
			String tempLogin = login.getText();
			Usuario usuarioTest = usuariodao.getByLogin(tempLogin);
			if (usuarioTest == null) {
				String tempNome = nome.getText();
				String tempSenha = senha.getText();
				Integer tempPermissoes = Integer.parseInt(permissoes.getText());
				String tempEmail = email.getText();
				String tempEmailGerencia = emailGerencia.getText();
				Integer tempSetor = Integer.parseInt(setor.getText());
				Integer tempSituacao = Integer.parseInt(situacao.getText());
				Usuario tempUser = new Usuario(null, tempNome, tempLogin, tempSenha, tempPermissoes, tempEmail,
						tempEmailGerencia, tempSetor, tempSituacao);
				usuariodao.insert(tempUser);
				tempUser = usuariodao.getByName(tempNome);
				codigo.setText(tempUser.getCodigo().toString());
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
			} else {
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Usuário já cadastrado!");
				alert.setHeaderText("Já existe um usuário com este login cadastrado!");
				alert.showAndWait();
			}
		}
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
		TblUsuariosDAO usuariodao = new TblUsuariosDAOImpl();
		Integer result = 0;
		try {
			System.out.println("EXCLUSÃO DE DADOS ATIVA!!");
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Confirmação:");
			alert.setHeaderText("Realmente deseja excluir o usuário selecionado?");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				result = usuariodao.deleteById(new Usuario(Integer.parseInt(codigo.getText()), null, null, null, null,
						null, null, null, null));
				if (result > 0) {
					cancelaUsuario();
					this.modo = 0;
				}
			}
		} catch (SQLException exception) {
			exception.getMessage();
		}
	}

	Stage stagePesquisaUsuario = null;

	@FXML
	public void onButtonPesquisaUsuarioAction() {

		if (stagePesquisaUsuario == null) {
			stagePesquisaUsuario = new Stage();

			try {	
				TblUsuariosDAO dao = new TblUsuariosDAOImpl();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
				
				PesquisaFormController <Usuario> psc = new PesquisaFormController();
				psc.initData(dao);
				loader.setController(psc);
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
					String usuarioSelecionado = psc.getIdItemSelecionado();
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

	Stage stagePesquisaNivel = null;

	@FXML
	public void onButtonPesquisaNivelAction() {

		if (stagePesquisaNivel == null) {
			stagePesquisaNivel = new Stage();

			try {
				TblNiveisDAO dao = new TblNiveisDAOImpl();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
				
				PesquisaFormController <Nivel> psc = new PesquisaFormController();
				psc.initData(dao);
				loader.setController(psc);
				Parent parent = loader.load();

				// Passa a referência do controller para atualizar a string após a seleção
				Scene scenePesquisaNivel = new Scene(parent);
				stagePesquisaNivel.setTitle("Pesquisa Nivel");
				stagePesquisaNivel.setResizable(true);
				stagePesquisaNivel.getIcons().add(new Image("/imgs/18x18/conferencia.png"));
				stagePesquisaNivel.setScene(scenePesquisaNivel);

				// Defina o que acontece quando a janela for fechada
				stagePesquisaNivel.setOnHidden(event -> {
					// Aqui você pega a string da nova janela
					String nivelSelecionado = psc.getIdItemSelecionado();
					System.out.println("Usuario Selecionado: " + nivelSelecionado);

					// Agora você pode fazer o que quiser com essa string
					if (nivelSelecionado != null) {
						permissoes.setText(nivelSelecionado);
					}
					stagePesquisaNivel = null;
					permissoes.requestFocus();
				});

				stagePesquisaNivel.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			stagePesquisaNivel.toFront();
		}
	}

	Stage stagePesquisaSituacao = null;

	@FXML
	public void onButtonPesquisaSituacaoAction() {

		if (stagePesquisaSituacao == null) {
			stagePesquisaSituacao = new Stage();

			try {
				TblSituacaoDAO dao = new TblSituacaoDAOImpl();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
				
				PesquisaFormController <Situacao> psc = new PesquisaFormController();
				psc.initData(dao);
				loader.setController(psc);
				Parent parent = loader.load();

				// Passa a referência do controller para atualizar a string após a seleção
				Scene scenePesquisaSituacao = new Scene(parent);
				stagePesquisaSituacao.setTitle("Pesquisa Situação");
				stagePesquisaSituacao.setResizable(true);
				stagePesquisaSituacao.getIcons().add(new Image("/imgs/18x18/conferencia.png"));
				stagePesquisaSituacao.setScene(scenePesquisaSituacao);

				// Defina o que acontece quando a janela for fechada
				stagePesquisaSituacao.setOnHidden(event -> {
					// Aqui você pega a string da nova janela
					String situacaoSelecionado = psc.getIdItemSelecionado();
					System.out.println("Usuario Selecionado: " + situacaoSelecionado);

					// Agora você pode fazer o que quiser com essa string
					if (situacaoSelecionado != null) {
						situacao.setText(situacaoSelecionado);
					}
					stagePesquisaSituacao = null;
					situacao.requestFocus();
				});

				stagePesquisaSituacao.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			stagePesquisaSituacao.toFront();
		}
	}

	Stage stagePesquisaSetor = null;

	@FXML
	public void onButtonPesquisaSetorAction() {

		if (stagePesquisaSetor == null) {
			stagePesquisaSetor = new Stage();

			try {
				TblSetoresDAO dao = new TblSetoresDAOImpl();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
				
				PesquisaFormController <Setor> psc = new PesquisaFormController();
				psc.initData(dao);
				loader.setController(psc);
				Parent parent = loader.load();

				// Passa a referência do controller para atualizar a string após a seleção
				Scene scenePesquisaSetor = new Scene(parent);
				stagePesquisaSetor.setTitle("Pesquisa Setores");
				stagePesquisaSetor.setResizable(true);
				stagePesquisaSetor.getIcons().add(new Image("/imgs/18x18/conferencia.png"));
				stagePesquisaSetor.setScene(scenePesquisaSetor);

				// Defina o que acontece quando a janela for fechada
				stagePesquisaSetor.setOnHidden(event -> {
					// Aqui você pega a string da nova janela
					String setorSelecionado = psc.getIdItemSelecionado();
					System.out.println("Setor Selecionado: " + setorSelecionado);

					// Agora você pode fazer o que quiser com essa string
					if (setorSelecionado != null) {
						setor.setText(setorSelecionado);

					}
					stagePesquisaSetor = null;
					setor.requestFocus();
				});

				stagePesquisaSetor.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			stagePesquisaSetor.toFront();
		}
	}

	public void onSetorTxtFieldKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)) {
			if (setor.getText() == "") {
			} else {
				Integer cod = Integer.parseInt(setor.getText());
				TblSetoresDAO setordao = new TblSetoresDAOImpl();
				try {
					Setor tempSetor = setordao.getById(cod);
					if (tempSetor != null) {
						setorDesc.setText(tempSetor.getNome());
					} else {
						System.out.println("NAO ACHOU SETOR!!");
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Setor não encontrado");
						alert.setHeaderText("Não foi possível achar nenhum setor pelo código informado!");
						alert.showAndWait();
					}
				} catch (SQLException exception) {
					exception.getMessage();
				}
			}
		}
	}
}
