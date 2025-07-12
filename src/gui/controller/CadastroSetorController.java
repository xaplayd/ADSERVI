package gui.controller;

import java.sql.SQLException;

import dao.TblSetoresDAO;
import dao.TblSetoresDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Setor;

public class CadastroSetorController {

	@FXML
	private Button procuraSetor;
	@FXML
	private Button novoSetor;
	@FXML
	private Button editarSetor;
	@FXML
	private Button excluirSetor;
	@FXML
	private Button salvarSetor;
	@FXML
	private Button fecharSetor;
	@FXML
	private Button cancelarSetor;
	@FXML
	private TextField codigo;
	@FXML
	private TextField nome;
	@FXML
	private Integer modo = 0; // modo 0 = neutro || modo 1 = em edição || modo 2 = novo

	@FXML
	public void onCodigoTxtFieldKeyPressed(KeyEvent e) {
		if (e.getCode().equals(KeyCode.ENTER) || e.getCode().equals(KeyCode.TAB)) {
			if (codigo.getText() == "") {
			} else {
				Integer cod = Integer.parseInt(codigo.getText());
				TblSetoresDAO setordao = new TblSetoresDAOImpl();
				try {
					Setor tempSetor = setordao.getById(cod);
					if (tempSetor != null) {
						nome.setText(tempSetor.getNome());
						codigo.setDisable(true);
						procuraSetor.setDisable(true);
						novoSetor.setDisable(true);
						editarSetor.setDisable(false);
						excluirSetor.setDisable(false);
						cancelarSetor.setDisable(false);
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

	public void novoSetor() {
		codigo.setDisable(true);
		codigo.setText(null);
		procuraSetor.setDisable(true);
		novoSetor.setDisable(true);
		editarSetor.setDisable(true);
		excluirSetor.setDisable(true);
		salvarSetor.setDisable(false);
		cancelarSetor.setDisable(false);
		nome.setDisable(false);
		this.modo = 2;
	}

	public void editarSetor() {
		nome.setDisable(false);
		salvarSetor.setDisable(false);
		editarSetor.setDisable(true);
		this.modo = 1;
	}

	public void excluirSetor() {
		TblSetoresDAO setordao = new TblSetoresDAOImpl();
		Integer result = 0;
		try {
			System.out.println("EXCLUSÃO DE DADOS ATIVA!!");
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Confirmação:");
			alert.setHeaderText("Realmente deseja excluir o setor selecionado?");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				result = setordao.deleteById(new Setor(Integer.parseInt(codigo.getText()),null));
			}
			if (result > 0) {
					cancelarSetor();
					this.modo = 0;
				}
			} catch (SQLException exception) {
				exception.getMessage();
			}
	}

	public void cancelarSetor() {
		codigo.setDisable(false);
		procuraSetor.setDisable(false);
		nome.setDisable(true);
		novoSetor.setDisable(false);
		editarSetor.setDisable(true);
		excluirSetor.setDisable(true);
		cancelarSetor.setDisable(true);
		salvarSetor.setDisable(true);
		codigo.setText(null);
		nome.setText(null);
		this.modo = 0;
	}

	public void salvarSetor() {
		try {
			TblSetoresDAO setordao = new TblSetoresDAOImpl();
			if (this.modo == 1) {
				Integer tempCodigo = Integer.parseInt(codigo.getText());
				String tempNome = nome.getText();
				Setor tempSetor = new Setor(tempCodigo, tempNome);
				setordao.updateById(tempSetor);
			} else if (this.modo == 2) {
				String tempNome = nome.getText();
				Setor tempSetor = new Setor(null, tempNome);
				setordao.insert(tempSetor);
				tempSetor = setordao.getByName(tempNome);
				codigo.setText(tempSetor.getCodigo().toString());
			}
		} catch (SQLException exception) {
			exception.getMessage();
		}
		this.modo = 0;
		codigo.setDisable(true);
		procuraSetor.setDisable(true);
		nome.setDisable(true);
		novoSetor.setDisable(true);
		editarSetor.setDisable(false);
		excluirSetor.setDisable(false);
		cancelarSetor.setDisable(false);
		salvarSetor.setDisable(true);
	}

	public void fecharSetor() {

		Stage stage = (Stage) fecharSetor.getScene().getWindow();
		stage.close();
	}

	Stage stagePesquisaSetor = null;

	@FXML
	public void onButtonPesquisaSetorAction() {

		if (stagePesquisaSetor == null) {
			stagePesquisaSetor = new Stage();

			try {
				PesquisaSetorController psc = new PesquisaSetorController();
				FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
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
					String setorSelecionado = psc.getSetorSelecionado();
					System.out.println("Setor Selecionado: " + setorSelecionado);

					// Agora você pode fazer o que quiser com essa string
					if (setorSelecionado != null) {
						codigo.setText(setorSelecionado);
					}
					stagePesquisaSetor = null;
					codigo.requestFocus();
				});

				stagePesquisaSetor.show();

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			stagePesquisaSetor.toFront();
		}
	}
}
