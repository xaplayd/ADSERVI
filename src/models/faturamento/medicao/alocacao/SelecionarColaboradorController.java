package models.faturamento.medicao.alocacao;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import models.faturamento.medicao.colaborador.Colaborador;

public class SelecionarColaboradorController {

    @FXML private TableView<Colaborador> tblColaboradores;
    @FXML private TableColumn<Colaborador, Integer> colMatricula;
    @FXML private TableColumn<Colaborador, String> colNome;
    @FXML private Button btnConfirmar;
    @FXML private Button btnCancelar;

    private ObservableList<Colaborador> colaboradores = FXCollections.observableArrayList();
    private Colaborador selecionado;

    @FXML
    private void initialize() {
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        // Mock: em produção, essa lista viria do banco ou serviço
        colaboradores.add(new Colaborador(1001, "João Silva", "POS1", "VT1"));
        colaboradores.add(new Colaborador(1002, "Maria Souza", "POS2", "VT2"));
        colaboradores.add(new Colaborador(1003, "Carlos Oliveira", "POS3", "VT3"));

        tblColaboradores.setItems(colaboradores);
    }

    @FXML
    private void onConfirmar() {
        selecionado = tblColaboradores.getSelectionModel().getSelectedItem();
        Stage stage = (Stage) btnConfirmar.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onCancelar() {
        selecionado = null;
        Stage stage = (Stage) btnCancelar.getScene().getWindow();
        stage.close();
    }

    public Colaborador getSelecionado() {
        return selecionado;
    }
}
