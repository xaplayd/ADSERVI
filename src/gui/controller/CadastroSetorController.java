package gui.controller;

import java.util.List;
import dados.controller.TblSetoresController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.Setor;
import services.SetorService;

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

			Integer cod = Integer.parseInt(codigo.getText());
			List tempList = TblSetoresController.updateListaSetores();

			Setor puxa = SetorService.puxaSetor(tempList, cod);

			if (puxa != null) {
				nome.setText(puxa.getNome());
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
		}
	}

	public void novoSetor() {
		codigo.setDisable(true);
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
		String tempCodigo = codigo.getText();
		System.out.println("EXCLUSÃO DE DADOS ATIVA!!");
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
		alert.setTitle("Confirmação:");
		alert.setHeaderText("Realmente deseja excluir o setor selecionado?");
		alert.showAndWait();
		if (alert.getResult() == ButtonType.YES) {
			String processa = TblSetoresController.deletaSetorNaLista(tempCodigo);

			if (processa == "sucesso") {
				procuraSetor.setDisable(false);
				novoSetor.setDisable(false);
				editarSetor.setDisable(true);
				excluirSetor.setDisable(true);
				salvarSetor.setDisable(true);
				fecharSetor.setDisable(false);
				cancelarSetor.setDisable(true);
				codigo.setDisable(false);
				codigo.setText("");
				nome.setDisable(true);
				nome.setText("");
				this.modo = 0;
			}
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

		if (this.modo == 1) {
			String tempCodigo = codigo.getText();
			String tempNome = nome.getText();
			TblSetoresController.editaSetorNaLista(tempCodigo, tempNome);

		} else if (this.modo == 2) {
			String tempNome = nome.getText();
			String retornoNovaChave = TblSetoresController.insereSetorNaLista(tempNome);
			codigo.setText(retornoNovaChave);

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

}
