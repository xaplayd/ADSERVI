package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;

import dados.controller.TblSetoresController;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.Setor;

	public class PesquisaSetorController implements Initializable{
	
	@FXML
	private TableColumn<Setor, Integer> colunaCod;

	@FXML
	private TableColumn<Setor, String> colunaNome;

	@FXML
	private TableView<Setor> tabelita;

	@FXML
	private ObservableList<Setor> setores;

	@FXML
	private String itemSelecionado;
	
	@FXML
	private TextField itemSelected;
	
	@FXML
	public String onItemSelected() {	
		itemSelected.setText(String.valueOf(tabelita.getSelectionModel().getSelectedItem().getCodigo()));
		this.itemSelecionado = itemSelected.getText();
		return itemSelecionado;
	}
	
	@FXML
	private Button okButton;
	
	@FXML
	public void onOkButtonAction() {
		
	}
	public String retornaItem() {
		return itemSelecionado;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
	setores = TblSetoresController.pesquisaDeSetores();
	
	colunaCod.setCellValueFactory(new PropertyValueFactory<Setor, Integer>("codigo"));
	colunaNome.setCellValueFactory(new PropertyValueFactory<Setor, String>("nome"));
	
	tabelita.setItems(setores);
		
	
}
	}

