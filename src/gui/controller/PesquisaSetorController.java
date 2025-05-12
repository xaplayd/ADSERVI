package gui.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.itextpdf.text.Document;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import config.DatabaseConfig;
import dados.controller.ConnectionController;
import dados.controller.TblSetoresController;
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

public class PesquisaSetorController implements Initializable {

    private String idSelecionado;

    public String getSetorSelecionado() {
        return this.idSelecionado;
    }

    @FXML private TableView<ObservableList<String>> tabelita;
    @FXML private ComboBox<String> campoParaFiltro;
    @FXML private ComboBox<String> condicaoFiltro;
    @FXML private TextField valorParaFiltro;
    @FXML private TextField filtroCorrente;
    @FXML private Button adicionar;
    @FXML private TextField itemSelected;
    @FXML private Button okButton;
    @FXML private Button btExportaCsv;
    @FXML private Button btExportaTxt;
    @FXML private Button btExportaXlsx;
    @FXML private Button btExportaPdf;
    @FXML private TextField tfNomeArquivo;
    @FXML private TextField tfCaminhoArquivo;
    @FXML private Button btDiretorioArquivo;
    @FXML private Button limpar;
    @FXML private Label lblEnderecoDatabase;
    @FXML private Label lblNomeDatabase;
    @FXML private Label lblNomeTabela;

    private final ObservableList<Filtro> filtrosAtivos = FXCollections.observableArrayList();

    @FXML
    public void onExportarCsvAction() {
        exportarTexto("csv", ";");
    }

    @FXML
    public void onExportarTxtAction() {
        exportarTexto("txt", "\t");
    }

    @FXML
    public void onExportarXlsxAction() {
        String nomeArquivo = tfNomeArquivo.getText();
        String caminho = tfCaminhoArquivo.getText();

        if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
            System.out.println("Nome do arquivo ou caminho não preenchido.");
            return;
        }

        File arquivo = Paths.get(caminho, nomeArquivo + ".xlsx").toFile();

        try (Workbook workbook = new XSSFWorkbook()) {
        	Sheet sheet = workbook.createSheet("Setores");

            // Cabeçalho
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < tabelita.getColumns().size(); i++) {
                TableColumn<ObservableList<String>, ?> col = tabelita.getColumns().get(i);
                String header = col.getText() != null ? col.getText() : "Coluna " + i;
                headerRow.createCell(i).setCellValue(header);
            }

            // Dados
            for (int i = 0; i < tabelita.getItems().size(); i++) {
                ObservableList<String> linha = tabelita.getItems().get(i);
                Row row = sheet.createRow(i + 1);
                for (int j = 0; j < linha.size(); j++) {
                    Object valor = linha.get(j);
                    Cell cell = row.createCell(j);
                    if (valor == null) {
                        cell.setCellValue("");
                    } else if (valor instanceof Number) {
                        cell.setCellValue(((Number) valor).doubleValue());
                    } else if (valor instanceof Boolean) {
                        cell.setCellValue((Boolean) valor);
                    } else {
                        cell.setCellValue(valor.toString());
                    }
                }
            }

            try (FileOutputStream fileOut = new FileOutputStream(arquivo)) {
                workbook.write(fileOut);
            }

            System.out.println("Arquivo XLSX exportado com sucesso.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        	
        	
    }

    @FXML
    public void onExportarPdfAction() {
        String nomeArquivo = tfNomeArquivo.getText();
        String caminho = tfCaminhoArquivo.getText();

        if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
            System.out.println("Nome do arquivo ou caminho não preenchido.");
            return;
        }

        File arquivo = Paths.get(caminho, nomeArquivo + ".pdf").toFile();

        Document document = new Document();

        try {
            PdfWriter.getInstance(document, new FileOutputStream(arquivo));
            document.open();

            PdfPTable table = new PdfPTable(tabelita.getColumns().size());

            // Cabeçalhos
            for (TableColumn<ObservableList<String>, ?> col : tabelita.getColumns()) {
                String header = col.getText() != null ? col.getText() : "";
                table.addCell(new PdfPCell(new Phrase(header)));
            }

            // Dados
            for (ObservableList<String> linha : tabelita.getItems()) {
                for (String celula : linha) {
                    table.addCell(new PdfPCell(new Phrase(celula != null ? celula : "")));
                }
            }

            document.add(table);
            System.out.println("Arquivo PDF exportado com sucesso.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
    }

    private void exportarTexto(String extensao, String separador) {
        String nomeArquivo = tfNomeArquivo.getText();
        String caminho = tfCaminhoArquivo.getText();

        if (nomeArquivo.isEmpty() || caminho.isEmpty()) {
            System.out.println("Nome do arquivo ou caminho não preenchido.");
            return;
        }

        File arquivo = Paths.get(caminho, nomeArquivo + "." + extensao).toFile();

        try (PrintWriter writer = new PrintWriter(arquivo)) {
            // Cabeçalhos
            List<String> headers = tabelita.getColumns().stream()
                .map(col -> col.getText() != null ? col.getText() : "")
                .collect(Collectors.toList());
            writer.println(String.join(separador, headers));

            // Dados
            for (ObservableList<String> linha : tabelita.getItems()) {
                List<String> dados = linha.stream()
                    .map(val -> val != null ? val : "")
                    .collect(Collectors.toList());
                writer.println(String.join(separador, dados));
            }

            System.out.println("Arquivo exportado com sucesso: " + arquivo.getAbsolutePath());

        } catch (IOException e) {
            e.printStackTrace();
        }
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
        filtrosAtivos.clear();
        filtroCorrente.clear();
        tabelita.setItems(TblSetoresController.estruturaTblDeSetores().getItems());
        valorParaFiltro.clear();
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

        ObservableList<ObservableList<String>> dadosOriginais = TblSetoresController.estruturaTblDeSetores().getItems();
        ObservableList<ObservableList<String>> dadosFiltrados = FXCollections.observableArrayList();

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

                if (colIndex == -1) continue;

                String valorCelula = linha.get(colIndex).toLowerCase();
                String valorFiltroLower = filtro.getValor().toLowerCase();

                switch (filtro.getCondicao()) {
                    case "Contém":
                        if (!valorCelula.contains(valorFiltroLower)) correspondeTodos = false;
                        break;
                    case "Igual":
                        if (!valorCelula.equals(valorFiltroLower)) correspondeTodos = false;
                        break;
                    case "Diferente":
                        if (valorCelula.contains(valorFiltroLower)) correspondeTodos = false;
                        break;
                }

                if (!correspondeTodos) break;
            }

            if (correspondeTodos) {
                dadosFiltrados.add(linha);
            }
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
        TableView<ObservableList<String>> tbl = TblSetoresController.estruturaTblDeSetores();
        tabelita.getColumns().setAll(tbl.getColumns());
        tabelita.setItems(tbl.getItems());

        campoParaFiltro.setItems(TblSetoresController.obterNomesDasColunas());
        campoParaFiltro.setValue(TblSetoresController.obterNomesDasColunas().get(0));
        condicaoFiltro.setItems(FXCollections.observableArrayList("Contém", "Igual", "Diferente"));
        condicaoFiltro.setValue("Contém");

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
        lblNomeTabela.setText(TblSetoresController.updateNomeTabela());

        btDiretorioArquivo.setOnAction(e -> onDiretorioArquivoAction());
        btExportaCsv.setOnAction(e -> onExportarCsvAction());
        btExportaTxt.setOnAction(e -> onExportarTxtAction());
        btExportaXlsx.setOnAction(e -> onExportarXlsxAction());
        btExportaPdf.setOnAction(e -> onExportarPdfAction());
    }
}