package models.faturamento.medicao.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.faturamento.medicao.colaborador.Colaborador;

public class AlocacaoController {

    @FXML private Button btnAlocar;
    @FXML private Button btnRemover;

    @FXML private TableView<Alocacao> tblAlocacoes;
    @FXML private TableColumn<Alocacao, Integer> colId;
    @FXML private TableColumn<Alocacao, String> colVaga;
    @FXML private TableColumn<Alocacao, String> colMatriculaColab;
    @FXML private TableColumn<Alocacao, String> colNomeColab;
    @FXML private TableColumn<Alocacao, LocalDate> colDataInicio;
    @FXML private TableColumn<Alocacao, LocalTime> colHoraInicio;
    @FXML private TableColumn<Alocacao, LocalDate> colDataFim;
    @FXML private TableColumn<Alocacao, LocalTime> colHoraFim;

    private ObservableList<Alocacao> alocacoes = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // === Bind de colunas ===
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));

        colVaga.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getVaga() != null ? cellData.getValue().getVaga().getDesPosTabVaga() : ""
            )
        );

        colMatriculaColab.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                (cellData.getValue().getColaborador() != null && cellData.getValue().getColaborador().getMatricula() != null)
                        ? cellData.getValue().getColaborador().getMatricula().toString()
                        : ""
            )
        );

        colNomeColab.setCellValueFactory(cellData ->
            new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getColaborador() != null ? cellData.getValue().getColaborador().getNome() : ""
            )
        );

        colDataInicio.setCellValueFactory(new PropertyValueFactory<>("dataInicio"));
        colHoraInicio.setCellValueFactory(new PropertyValueFactory<>("horaInicio"));
        colDataFim.setCellValueFactory(new PropertyValueFactory<>("dataFim"));
        colHoraFim.setCellValueFactory(new PropertyValueFactory<>("horaFim"));

        // === Formatação ===
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter hf = DateTimeFormatter.ofPattern("HH:mm");

        colDataInicio.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : df.format(item));
            }
        });

        colDataFim.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : df.format(item));
            }
        });

        colHoraInicio.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(LocalTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : hf.format(item));
            }
        });

        colHoraFim.setCellFactory(col -> new TableCell<>() {
            @Override protected void updateItem(LocalTime item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? null : hf.format(item));
            }
        });

       

        tblAlocacoes.setItems(alocacoes);
    }

    @FXML
    private void onAlocar(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelecionarColaboradorView.fxml"));
            Parent root = loader.load();

            SelecionarColaboradorController controller = loader.getController();

            Stage stage = new Stage();
            stage.setTitle("Selecionar Colaborador");
            stage.setScene(new Scene(root));
            stage.showAndWait(); // aguarda fechar

            Colaborador escolhido = controller.getSelecionado();
            if (escolhido != null) {
                System.out.println("Colaborador selecionado: " + escolhido.getNome());

                // Criar nova alocação (mock datas por enquanto)
               /* Alocacao nova = new Alocacao(
                    alocacoes.size() + 1,
                    vaga, // a vaga que já está setada no controller
                    escolhido,
                    java.time.LocalDate.now(),
                    java.time.LocalTime.of(8, 0),
                    java.time.LocalDate.now(),
                    java.time.LocalTime.of(12, 0)
                );

                alocacoes.add(nova);
                escolhido.addAlocacao(nova);*/
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onRemover(ActionEvent event) {
        Alocacao selecionada = tblAlocacoes.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            alocacoes.remove(selecionada);
            System.out.println("Removida alocação ID=" + selecionada.getId());
        }
    }
}
