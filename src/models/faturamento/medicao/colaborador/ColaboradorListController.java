package models.faturamento.medicao.colaborador;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class ColaboradorListController {

    @FXML
    private TableView<Colaborador> tblColaboradores;

    private ObservableList<Colaborador> colaboradores = FXCollections.observableArrayList();

    public void setColaboradores(List<Colaborador> lista) {
        colaboradores.setAll(lista);
        tblColaboradores.setItems(colaboradores);
    }
}