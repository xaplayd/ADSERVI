package gui.controller;

import dao.TblContratoGeralDAO;
import dao.TblContratoGeralDAOImpl;
import dao.TblFormatoContratoDAO;
import dao.TblFormatoContratoDAOImpl;
import dao.TblIndiceEscopoDAO;
import dao.TblIndiceEscopoDAOImpl;
import dao.TblNiveisDAO;
import dao.TblNiveisDAOImpl;
import dao.TblSetoresDAO;
import dao.TblSetoresDAOImpl;
import dao.TblSituacaoDAO;
import dao.TblSituacaoDAOImpl;
import dao.TblTagsDAO;
import dao.TblTagsDAOImpl;
import dao.TblTipoClienteDAO;
import dao.TblTipoClienteDAOImpl;
import dao.TblUniMedidasDAO;
import dao.TblUniMedidasDAOImpl;
import dao.TblUsuariosDAO;
import dao.TblUsuariosDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import models.Nivel;
import models.Setor;
import models.Situacao;
import models.Tag;
import models.Usuario;
import models.comercial.ContratoGeral;
import models.comercial.FormatoContrato;
import models.comercial.IndiceEscopo;
import models.comercial.TipoCliente;
import models.comercial.UniMedida;
import services.UserService;

public class PrincipalController {

	@FXML
	private MenuItem cadastroUsuario;
	@FXML
	private MenuItem cadastroSetor;
	@FXML
	private MenuItem cadastroPermissao;
	@FXML
	private MenuItem cadastroSituacao;
	@FXML
	private Label loggedUser;
	@FXML
	private Button logoutBtn;
	@FXML
	private Button changeUser;
	@FXML
	private MenuItem teste;
	@FXML
	private MenuItem miHtml;

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

		Integer validado = UserService.validaPermissao(tempUser);

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			if (stageCadastroUser == null) {
				stageCadastroUser = new Stage();

			    try {
			    	//TblSetoresDAO dao = new TblSetoresDAOImpl();
			    	TblUsuariosDAO dao = new TblUsuariosDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <Usuario> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroUser.setTitle("Cadastro de Usuários");
			        stageCadastroUser.getIcons().add(new Image("/imgs/18x18/usuario.png"));
			        stageCadastroUser.setScene(new Scene(root));
			        stageCadastroUser.show();
			        stageCadastroUser.setOnHidden(we -> stageCadastroUser = null);
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

		Integer validado = UserService.validaPermissao(tempUser);

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			if (stageCadastroSetor == null) {
				stageCadastroSetor = new Stage();

			    try {
			    	TblSetoresDAO dao = new TblSetoresDAOImpl();
			    	//TblUsuariosDAO dao = new TblUsuariosDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <Setor> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroSetor.setTitle("Cadastro de Setores");
			        stageCadastroSetor.getIcons().add(new Image("/imgs/18x18/conferencia.png"));
			        stageCadastroSetor.setScene(new Scene(root));
			        stageCadastroSetor.show();
			        stageCadastroSetor.setOnHidden(we -> stageCadastroSetor = null);
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
	
	Stage stageCadastroPermissao = null;

	@FXML
	public void onMenuItemCadastroPermissaoAction() {

		Usuario tempUser = new Usuario(null, null, getLoggedUser(), null, null, null, null, null, null);

		Integer validado = UserService.validaPermissao(tempUser);

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			if (stageCadastroPermissao == null) {
				stageCadastroPermissao = new Stage();

			    try {
			    	TblNiveisDAO dao = new TblNiveisDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <Nivel> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroPermissao.setTitle("Cadastro de Permissões");
			        stageCadastroPermissao.getIcons().add(new Image("/imgs/18x18/password.png"));
			        stageCadastroPermissao.setScene(new Scene(root));
			        stageCadastroPermissao.show();
			        stageCadastroPermissao.setOnHidden(we -> stageCadastroPermissao = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroPermissao.toFront();
			}

		} else {
			System.out.println("ALGO ERRADO COM A PERMISSÃO!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Usuário sem permissão");
			alert.setHeaderText("Este usuário não tem permissão para esta rotina!");
			alert.showAndWait();
		}

	}
	
	Stage stageCadastroSituacao = null;
	
	@FXML
	public void onMenuItemCadastroSituacaoAction() {

		Usuario tempUser = new Usuario(null, null, getLoggedUser(), null, null, null, null, null, null);

		Integer validado = UserService.validaPermissao(tempUser);

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			if (stageCadastroSituacao == null) {
				stageCadastroSituacao = new Stage();

			    try {
			    	TblSituacaoDAO dao = new TblSituacaoDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <Situacao> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroSituacao.setTitle("Cadastro de Situações");
			        stageCadastroSituacao.getIcons().add(new Image("/imgs/18x18/erro.png"));
			        stageCadastroSituacao.setScene(new Scene(root));
			        stageCadastroSituacao.show();
			        stageCadastroSituacao.setOnHidden(we -> stageCadastroSituacao = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroSituacao.toFront();
			}

		} else {
			System.out.println("ALGO ERRADO COM A PERMISSÃO!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Usuário sem permissão");
			alert.setHeaderText("Este usuário não tem permissão para esta rotina!");
			alert.showAndWait();
		}

	}

	Stage stageCadastroTag = null;
	
	@FXML
	public void onMenuItemCadastroTagAction() {

		Usuario tempUser = new Usuario(null, null, getLoggedUser(), null, null, null, null, null, null);

		Integer validado = UserService.validaPermissao(tempUser);

		if (validado == 1) {
			System.out.println("PERMISSÃO FUNCIONOU!");

			if (stageCadastroTag == null) {
				stageCadastroTag = new Stage();

			    try {
			    	TblTagsDAO dao = new TblTagsDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <Tag> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroTag.setTitle("Cadastro de Tags");
			        stageCadastroTag.getIcons().add(new Image("/imgs/18x18/lista.png"));
			        stageCadastroTag.setScene(new Scene(root));
			        stageCadastroTag.show();
			        stageCadastroTag.setOnHidden(we -> stageCadastroTag = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroTag.toFront();
			}

		} else {
			System.out.println("ALGO ERRADO COM A PERMISSÃO!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Usuário sem permissão");
			alert.setHeaderText("Este usuário não tem permissão para esta rotina!");
			alert.showAndWait();
		}

	}
	
	Stage stageCadastroUniMedida = null;
	
	@FXML
	public void onMenuItemCadastroUniMedidaAction() {
			if (stageCadastroUniMedida == null) {
				stageCadastroUniMedida = new Stage();

			    try {
			    	TblUniMedidasDAO dao = new TblUniMedidasDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <UniMedida> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroUniMedida.setTitle("Cadastro de Unidades de Medida");
			        stageCadastroUniMedida.getIcons().add(new Image("/imgs/18x18/lista.png"));
			        stageCadastroUniMedida.setScene(new Scene(root));
			        stageCadastroUniMedida.show();
			        stageCadastroUniMedida.setOnHidden(we -> stageCadastroUniMedida = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroUniMedida.toFront();
			}

	}
	
	Stage stageCadastroFormatoContrato = null;
	
	@FXML
	public void onMenuItemCadastroFormatoContratoAction() {
			if (stageCadastroFormatoContrato == null) {
				stageCadastroFormatoContrato = new Stage();

			    try {
			    	TblFormatoContratoDAO dao = new TblFormatoContratoDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <FormatoContrato> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroFormatoContrato.setTitle("Cadastro Formato Contrato");
			        stageCadastroFormatoContrato.getIcons().add(new Image("/imgs/18x18/lista.png"));
			        stageCadastroFormatoContrato.setScene(new Scene(root));
			        stageCadastroFormatoContrato.show();
			        stageCadastroFormatoContrato.setOnHidden(we -> stageCadastroFormatoContrato = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroFormatoContrato.toFront();
			}
	}
	
	Stage stageCadastroTipoCliente = null;
	
	@FXML
	public void onMenuItemCadastroTipoClienteAction() {
			if (stageCadastroTipoCliente == null) {
				stageCadastroTipoCliente = new Stage();

			    try {
			    	TblTipoClienteDAO dao = new TblTipoClienteDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <TipoCliente> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroTipoCliente.setTitle("Cadastro Tipo Cliente");
			        stageCadastroTipoCliente.getIcons().add(new Image("/imgs/18x18/lista.png"));
			        stageCadastroTipoCliente.setScene(new Scene(root));
			        stageCadastroTipoCliente.show();
			        stageCadastroTipoCliente.setOnHidden(we -> stageCadastroTipoCliente = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroTipoCliente.toFront();
			}
	}
	
	Stage stageCadastroIndiceEscopo = null;
	
	@FXML
	public void onMenuItemCadastroIndiceEscopoAction() {
			if (stageCadastroIndiceEscopo == null) {
				stageCadastroIndiceEscopo = new Stage();

			    try {
			    	TblIndiceEscopoDAO dao = new TblIndiceEscopoDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <IndiceEscopo> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroIndiceEscopo.setTitle("Cadastro Indice Escopo");
			        stageCadastroIndiceEscopo.getIcons().add(new Image("/imgs/18x18/lista.png"));
			        stageCadastroIndiceEscopo.setScene(new Scene(root));
			        stageCadastroIndiceEscopo.show();
			        stageCadastroIndiceEscopo.setOnHidden(we -> stageCadastroIndiceEscopo = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroIndiceEscopo.toFront();
			}
	}
	
	Stage stageCadastroContratoGeral = null;
	
	@FXML
	public void onMenuItemCadastroContratoGeralAction() {
			if (stageCadastroContratoGeral == null) {
				stageCadastroContratoGeral = new Stage();

			    try {
			    	TblContratoGeralDAO dao = new TblContratoGeralDAOImpl();
			        FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/CadastroForm.fxml"));
			        Parent root = loader.load();

			        CadastroFormController <ContratoGeral> controller = loader.getController();
			        controller.initData(1, dao); // passando a tabela e o id
			        
			        stageCadastroContratoGeral.setTitle("Cadastro Contrato Geral");
			        stageCadastroContratoGeral.getIcons().add(new Image("/imgs/18x18/lista.png"));
			        stageCadastroContratoGeral.setScene(new Scene(root));
			        stageCadastroContratoGeral.show();
			        stageCadastroContratoGeral.setOnHidden(we -> stageCadastroContratoGeral = null);
			    } catch (Exception e) {
			        e.printStackTrace();
			    }
			} else {
				stageCadastroContratoGeral.toFront();
			}
	}
	
	@FXML
	public void onMenuItemPendencia() {

		 try {
			 	Stage primaryStage = new Stage();
	            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pendencia.fxml"));
	            AnchorPane root = loader.load();
	            Scene scene = new Scene(root, 900, 600);
	            primaryStage.setTitle("Editor de E-mail HTML");
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	
}