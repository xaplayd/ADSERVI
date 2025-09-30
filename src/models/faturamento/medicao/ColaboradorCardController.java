package models.faturamento.medicao;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ColaboradorCardController {

	@FXML
	private Label lblIdView;
	
    @FXML
    private Label lblMatricula;

    @FXML
    private Label lblNome;

    @FXML
    private Label lblPosTra;

    @FXML
    private Label lblDataAlt;

    @FXML
    private Button btnEditar;

    private Colaborador colaborador;
    
    private Integer idView;
    
    @FXML
    private Label lblDisponiveis;

    @FXML
    private Label lblAlocados;

    public void setColaborador(Colaborador colab) {
        this.colaborador = colab;
        lblMatricula.setText("Matrícula: " + colab.getMatricula());
        lblNome.setText("Nome: " + colab.getNome());
        lblPosTra.setText("Função: " + colab.getPosTra());
        lblDataAlt.setText("Última alteração: " + colab.getDataAlt());

        String disponiveis = colab.getPeriodosDisponiveis().stream()
                .map(Periodo::getDescricao)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Nenhum");
        lblDisponiveis.setText("Disponíveis: " + disponiveis);

        String alocados = colab.getPeriodosAlocados().stream()
                .map(Periodo::getDescricao)
                .reduce((a, b) -> a + ", " + b)
                .orElse("Nenhum");
        lblAlocados.setText("Alocados: " + alocados);
    }

    public void setIndice(Integer id) {
        this.idView = id;
        lblIdView.setText("ID: " + idView);
    }

    @FXML
    private void onEditar() {
    	System.out.println("Editar card de índice colaborador: " + this.idView);
    }
}