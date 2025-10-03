package models.faturamento.medicao.vaga;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.faturamento.medicao.Medicao;
import models.faturamento.medicao.alocacao.AlocacaoController;
import models.faturamento.medicao.alocacao.AlocacaoListController;

public class VagaCardController {
	
	Medicao md;
	
	AlocacaoController alocController = new AlocacaoController();

    @FXML
    private Label lblVaga;

    @FXML
    private Label lblPosTra;

    @FXML
    private Label lblExecucao;

    @FXML
    private Label lblExecutado;

    @FXML
    private Label lblAExecutar;

    @FXML
    private Button btnAlterar;

    private Vaga vaga;

    public void setVaga(Medicao md, Vaga vaga) {
    	this.md = md;
        this.vaga = vaga;
        lblVaga.setText("Posto: " + vaga.getDesPosTabVaga());
        lblPosTra.setText("Cod.: " + vaga.getPosTraTabVaga());
        lblExecucao.setText("Teto: " + vaga.getTotalExecucao());
        lblExecutado.setText("Executado: " + vaga.getExecutado());
        lblAExecutar.setText("A Executar: " + (vaga.getTotalExecucao() - vaga.getExecutado()));
    }
    
    
    @FXML
    private void onAlterar() {
        System.out.println("Alterar card: " + vaga.getId());
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/models/faturamento/medicao/alocacao/AlocacaoView.fxml"));
            Scene scene = new Scene(loader.load());

            // pega o controller da tela de alocação
            alocController = loader.getController();
            alocController.listaAlocacoes(md);

            Stage stage = new Stage();
            stage.setTitle("Alocações - " + vaga.getDesPosTabVaga());
            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    }
}
