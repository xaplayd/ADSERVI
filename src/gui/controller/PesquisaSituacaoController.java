package gui.controller;

import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

import config.DatabaseConfig;
import connection.controller.ConnectionController;
import dao.TblSituacaoDAO;
import dao.TblSituacaoDAOImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import models.Filtro;
import services.ExpReporService;

public class PesquisaSituacaoController implements Initializable {

	private final ObservableList<Filtro> filtrosAtivos = FXCollections.observableArrayList();

	private String idSelecionado;

	public String getSituacaoSelecionado() {
		return this.idSelecionado;
	}

	@FXML
	private TableView<ObservableList<String>> tabelita;
	@FXML
	private ComboBox<String> campoParaFiltro;
	@FXML
	private ComboBox<String> condicaoFiltro;
	@FXML
	private TextField valorParaFiltro;
	@FXML
	private TextField filtroCorrente;
	@FXML
	private Button adicionar;
	@FXML
	private TextField itemSelected;
	@FXML
	private Button okButton;
	@FXML
	private Button btExportaCsv;
	@FXML
	private Button btExportaTxt;
	@FXML
	private Button btExportaXlsx;
	@FXML
	private Button btExportaPdf;
	@FXML
	private TextField tfNomeArquivo;
	@FXML
	private TextField tfCaminhoArquivo;
	@FXML
	private Button btDiretorioArquivo;
	@FXML
	private Button limpar;
	@FXML
	private Label lblEnderecoDatabase;
	@FXML
	private Label lblNomeDatabase;
	@FXML
	private Label lblNomeTabela;

	@FXML
	public void onExportarCsvAction() {
		String nomeArquivo = tfNomeArquivo.getText();
		String caminho = tfCaminhoArquivo.getText();
		String tipo = "csv";
		ExpReporService.expReportAs(tipo, caminho, nomeArquivo, tabelita);
	}

	@FXML
	public void onExportarTxtAction() {
		String nomeArquivo = tfNomeArquivo.getText();
		String caminho = tfCaminhoArquivo.getText();
		String tipo = "txt";
		ExpReporService.expReportAs(tipo, caminho, nomeArquivo, tabelita);
	}

	@FXML
	public void onExportarXlsxAction() {
		String nomeArquivo = tfNomeArquivo.getText();
		String caminho = tfCaminhoArquivo.getText();
		String tipo = "xlsx";
		ExpReporService.expReportAs(tipo, caminho, nomeArquivo, tabelita);
	}

	@FXML
	public void onExportarPdfAction() {
		String nomeArquivo = tfNomeArquivo.getText();
		String caminho = tfCaminhoArquivo.getText();
		String tipo = "pdf";
		ExpReporService.expReportAs(tipo, caminho, nomeArquivo, tabelita);
	}

	@FXML
	public void onDiretorioArquivoAction() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		File selectedDirectory = directoryChooser.showDialog(btDiretorioArquivo.getScene().getWindow());
		if (selectedDirectory != null) {
			tfCaminhoArquivo.setText(selectedDirectory.getAbsolutePath());
		}
	}

	@FXML
	public void onOkButtonAction() {
		ObservableList<String> selectedRow = tabelita.getSelectionModel().getSelectedItem();
		if (selectedRow != null && !selectedRow.isEmpty()) {
			String codigoSelecionado = selectedRow.get(0);
			System.out.println(codigoSelecionado);
			okButton.getScene().getWindow().hide();
		}
	}

	@FXML
	public void onLimparFiltrosAction() {
		try {
			TblSituacaoDAO situacaodao = new TblSituacaoDAOImpl();
			filtrosAtivos.clear();
			filtroCorrente.clear();
			tabelita.setItems(situacaodao.estruturaTbl().getItems());
			valorParaFiltro.clear();
			}catch (Exception exception) {
				exception.getMessage();
		}
	}

	@FXML
	public void onAdicionarAction() {
		String colunaSelecionada = campoParaFiltro.getSelectionModel().getSelectedItem();
		String valorFiltro = valorParaFiltro.getText();
		String condicaoSelecionada = condicaoFiltro.getSelectionModel().getSelectedItem();

		if (colunaSelecionada == null || valorFiltro.isEmpty() || condicaoSelecionada == null) {
			System.out.println("Selecione uma coluna, condição e digite um valor para filtrar.");
			return;
		}

		filtrosAtivos.add(new Filtro(colunaSelecionada, valorFiltro, condicaoSelecionada));

		ObservableList<ObservableList<String>> dadosFiltrados = FXCollections.observableArrayList();
		try {
			TblSituacaoDAO situacaodao = new TblSituacaoDAOImpl();
			ObservableList<ObservableList<String>> dadosOriginais = situacaodao.estruturaTbl().getItems();

			for (ObservableList<String> linha : dadosOriginais) {
				boolean correspondeTodos = true;
	
				for (Filtro filtro : filtrosAtivos) {
					int colIndex = -1;
					for (int i = 0; i < tabelita.getColumns().size(); i++) {
						TableColumn<ObservableList<String>, ?> coluna = tabelita.getColumns().get(i);
						if (coluna.getText().equals(filtro.getColuna())) {
							colIndex = i;
							break;
						}
					}
	
					if (colIndex == -1)
						continue;
	
					String valorCelula = linha.get(colIndex).toLowerCase();
					String valorFiltroLower = filtro.getValor().toLowerCase();
	
					switch (filtro.getCondicao()) {
					case "Contém":
						if (!valorCelula.contains(valorFiltroLower))
							correspondeTodos = false;
						break;
					case "Igual":
						if (!valorCelula.equals(valorFiltroLower))
							correspondeTodos = false;
						break;
					case "Diferente":
						if (valorCelula.contains(valorFiltroLower))
							correspondeTodos = false;
						break;
					}
	
					if (!correspondeTodos)
						break;
				}
	
				if (correspondeTodos) {
					dadosFiltrados.add(linha);
				}
			}
		}catch(SQLException exception) {
			exception.getMessage();
		}
		tabelita.setItems(dadosFiltrados);

		StringBuilder sb = new StringBuilder();
		for (Filtro f : filtrosAtivos) {
			sb.append(f.getColuna()).append(" ").append(f.getCondicao()).append(" ").append(f.getValor()).append(" | ");
		}
		filtroCorrente.setText(sb.toString());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TblSituacaoDAO situacaodao = new TblSituacaoDAOImpl();
		try {
			TableView<ObservableList<String>> tbl = situacaodao.estruturaTbl();
			tabelita.getColumns().setAll(tbl.getColumns());
			tabelita.setItems(tbl.getItems());
		} catch(SQLException exception) {
			exception.getMessage();
		}
		
		try {
			campoParaFiltro.setItems(situacaodao.obterNomesDasColunas());
			campoParaFiltro.setValue(situacaodao.obterNomesDasColunas().get(0));
			condicaoFiltro.setItems(FXCollections.observableArrayList("Contém", "Igual", "Diferente"));
			condicaoFiltro.setValue("Contém");
		} catch(SQLException exception) {
			exception.getMessage();
		}
		adicionar.setOnAction(e -> onAdicionarAction());
		limpar.setOnAction(e -> onLimparFiltrosAction());
		okButton.setOnAction(e -> onOkButtonAction());

		tabelita.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
			if (newVal != null) {
				String idSelecionado = newVal.get(0);
				itemSelected.setText(idSelecionado);
				this.idSelecionado = idSelecionado;
			}
		});

		List<DatabaseConfig> parametros = ConnectionController.getParametrosDeConexao();
		lblEnderecoDatabase.setText(parametros.get(3).getParametro());
		lblNomeDatabase.setText(parametros.get(5).getParametro());
		
		try {
			lblNomeTabela.setText(situacaodao.getTblName());
			}catch(SQLException exception) {
				exception.getMessage();
			}

		btDiretorioArquivo.setOnAction(e -> onDiretorioArquivoAction());
		btExportaCsv.setOnAction(e -> onExportarCsvAction());
		btExportaTxt.setOnAction(e -> onExportarTxtAction());
		btExportaXlsx.setOnAction(e -> onExportarXlsxAction());
		btExportaPdf.setOnAction(e -> onExportarPdfAction());
	}
}