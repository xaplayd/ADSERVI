package gui.controller;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import dados.controller.TblSetoresController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Setor;

public class PesquisaSetorController implements Initializable {

	private String idSelecionado;

	public String getSetorSelecionado() {
		return this.idSelecionado;
	}

	@FXML
	private TableView tabelita;

	@FXML
	private ComboBox<String> campoParaFiltro;

	@FXML
	private TextField valorParaFiltro;

	@FXML
	private Button adicionar;

	@FXML
	private TextField itemSelected;

	@FXML
	private Button okButton;

	@FXML
	public void onOkButtonAction() {
		ObservableList selectedRow = (ObservableList) tabelita.getSelectionModel().getSelectedItem();
		if (selectedRow != null && !selectedRow.isEmpty()) {
			String codigoSelecionado = selectedRow.get(0).toString();
			System.out.println(codigoSelecionado);
			okButton.getScene().getWindow().hide();
		}
	}

	@FXML
	private Button limpar;

	private final Map<String, String> filtrosAtivos = new HashMap<>();

	@FXML
	public void onLimparFiltrosAction() {

		filtrosAtivos.clear();

		// Restaura todos os itens da tabela (sem filtro)
		ObservableList<ObservableList> dadosOriginais = TblSetoresController.estruturaTblDeSetores().getItems();
		tabelita.setItems(dadosOriginais);

		valorParaFiltro.clear();
	}

	@FXML
	public void onAdicionarAction() {
		String colunaSelecionada = campoParaFiltro.getSelectionModel().getSelectedItem();
		String valorFiltro = valorParaFiltro.getText();

		// Verifica se uma coluna e valor para filtro foram fornecidos
		if (colunaSelecionada == null || valorFiltro.isEmpty()) {
			System.out.println("Selecione uma coluna e digite um valor para filtrar.");
			return;
		}

		// Adiciona o novo filtro ao mapa de filtros
		filtrosAtivos.put(colunaSelecionada, valorFiltro);

		// Pega os dados originais da tabela
		ObservableList<ObservableList> dadosOriginais = TblSetoresController.estruturaTblDeSetores().getItems();
		ObservableList<ObservableList> dadosFiltrados = FXCollections.observableArrayList();

		// Itera sobre as linhas dos dados
		for (ObservableList linha : dadosOriginais) {
			boolean correspondeTodos = true;

			// Itera sobre todos os filtros ativos
			for (Map.Entry<String, String> filtro : filtrosAtivos.entrySet()) {
				String nomeColuna = filtro.getKey();
				String valorEsperado = filtro.getValue();

				// Encontra o índice da coluna baseada no nome da coluna
				int colIndex = -1;
				for (int i = 0; i < tabelita.getColumns().size(); i++) {
					// Fazemos o cast para TableColumn<ObservableList, String>
					TableColumn<ObservableList, String> coluna = (TableColumn<ObservableList, String>) tabelita
							.getColumns().get(i);

					// Compara o nome da coluna com o nome da coluna selecionada no filtro
					if (coluna.getText().equals(nomeColuna)) {
						colIndex = i;
						break;
					}
				}

				// Se a coluna não foi encontrada, ignora essa iteração
				if (colIndex == -1)
					continue;

				// Obtém o valor da célula para o índice da coluna
				String valorCelula = linha.get(colIndex).toString();

				// Verifica se o valor da célula contém o valor do filtro (case insensitive)
				if (!valorCelula.toLowerCase().contains(valorEsperado.toLowerCase())) {
					correspondeTodos = false;
					break;
				}
			}

			// Se a linha corresponde a todos os filtros, adiciona ao resultado filtrado
			if (correspondeTodos) {
				dadosFiltrados.add(linha);
			}
		}

		// Atualiza a TableView com os dados filtrados
		tabelita.setItems(dadosFiltrados);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		TableView tbl = TblSetoresController.estruturaTblDeSetores();
		tabelita.getColumns().setAll(tbl.getColumns());
		tabelita.setItems(tbl.getItems());
		campoParaFiltro.setItems(TblSetoresController.obterNomesDasColunas());
		campoParaFiltro.setValue(TblSetoresController.obterNomesDasColunas().get(0));
		adicionar.setOnAction(event -> onAdicionarAction());
		limpar.setOnAction(event -> onLimparFiltrosAction());
		okButton.setOnAction(event -> onOkButtonAction());
		tabelita.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue != null) {
				// Supondo que o ID esteja na primeira coluna da linha
				ObservableList<String> linhaSelecionada = (ObservableList<String>) newValue;
				String idSelecionado = linhaSelecionada.get(0); // A primeira coluna, que é onde o ID deve estar
				itemSelected.setText(idSelecionado); // Atualizando o TextField com o ID
				this.idSelecionado = idSelecionado;
			}
		});
	}
}
