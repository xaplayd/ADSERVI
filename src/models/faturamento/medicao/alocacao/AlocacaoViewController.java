package models.faturamento.medicao.alocacao;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import models.faturamento.medicao.colaborador.Colaborador;
import models.faturamento.medicao.vaga.PostoVaga;

public class AlocacaoViewController {

    @FXML private Label lblTitulo;

    // Tabelas
    @FXML private TableView<Alocacao> tblAlocados;
    @FXML private TableColumn<Alocacao, Integer> colMatriculaAlocado;
    @FXML private TableColumn<Alocacao, String> colNomeAlocado;
    @FXML private TableColumn<Alocacao, Double> colHorasAlocado;

    @FXML private TableView<Colaborador> tblDisponiveis;
    @FXML private TableColumn<Colaborador, Integer> colMatriculaDisp;
    @FXML private TableColumn<Colaborador, String> colNomeDisp;

    // Botões
    @FXML private Button btnAdicionar;
    @FXML private Button btnRemover;
    @FXML private Button btnSalvar;
    @FXML private Button btnCancelar;

    private PostoVaga posto; // posto em edição

    public void setPosto(PostoVaga posto) {
        this.posto = posto;
        lblTitulo.setText("Alocações do Posto: " + posto.getPosTraTabVaga());

        // carrega os já alocados
        tblAlocados.getItems().setAll(posto.getAlocacoes());

        // mock de colaboradores disponíveis
        List<Colaborador> disponiveis = List.of(
            new Colaborador(101, "Maria Silva", "1.1943.0001.105", "2025-09-01"),
            new Colaborador(102, "João Santos", "1.1943.0001.224", "2025-09-10"),
            new Colaborador(103, "Ana Costa", "1.1943.0001.105", "2025-09-20")
        );

        // remove da lista os que já estão alocados
        disponiveis = disponiveis.stream()
                .filter(c -> posto.getAlocacoes().stream()
                        .noneMatch(a -> a.getColaborador().equals(c)))
                .toList();

        tblDisponiveis.getItems().setAll(disponiveis);
    }

    @FXML
    private void initialize() {
        // Configurar colunas
        colMatriculaAlocado.setCellValueFactory(c -> 
            new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getColaborador().getMatricula()));
        colNomeAlocado.setCellValueFactory(c -> 
            new javafx.beans.property.SimpleStringProperty(c.getValue().getColaborador().getNome()));
        colHorasAlocado.setCellValueFactory(c -> 
            new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getHorasAlocadas()));

        colMatriculaDisp.setCellValueFactory(c -> 
            new javafx.beans.property.SimpleObjectProperty<>(c.getValue().getMatricula()));
        colNomeDisp.setCellValueFactory(c -> 
            new javafx.beans.property.SimpleStringProperty(c.getValue().getNome()));
    }

    @FXML
    private void onAdicionar() {
        Colaborador selecionado = tblDisponiveis.getSelectionModel().getSelectedItem();
        if (selecionado != null) {
            Alocacao nova = new Alocacao(
                posto.getAlocacoes().size() + 1,
                posto,
                selecionado,
                220.0, // horas padrão fictícia
                "2025-09-30"
            );
            posto.adicionarAlocacao(nova);
            tblAlocados.getItems().add(nova);
            tblDisponiveis.getItems().remove(selecionado);
        }
    }

    @FXML
    private void onRemover() {
        Alocacao selecionada = tblAlocados.getSelectionModel().getSelectedItem();
        if (selecionada != null) {
            posto.removerAlocacao(selecionada);
            tblAlocados.getItems().remove(selecionada);
            tblDisponiveis.getItems().add(selecionada.getColaborador());
        }
    }

    @FXML
    private void onSalvar() {
        ((Stage) lblTitulo.getScene().getWindow()).close();
    }

    @FXML
    private void onCancelar() {
        ((Stage) lblTitulo.getScene().getWindow()).close();
    }
}
