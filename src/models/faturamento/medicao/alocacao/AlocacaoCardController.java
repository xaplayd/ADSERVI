package models.faturamento.medicao.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.faturamento.medicao.Medicao;
import models.faturamento.medicao.colaborador.Colaborador;
import models.faturamento.medicao.vaga.Vaga;

public class AlocacaoCardController {
	
	Medicao md;

    @FXML
    private Label lblId;

    @FXML
    private Label lblMatricula;
    
    @FXML
    private Label lblNome;
    
    @FXML
    private Label lblDataInicio;
    
    @FXML
    private Label lblHrInicio;
    
    @FXML
    private Label lblDataFim;
    
    @FXML
    private Label lblHrFim;
    
    @FXML
    private Button btnAlterar;
    
    @FXML
    private Button btnExcluir;

    private Alocacao aloc;

    public void setAlocacao(Medicao md, Alocacao aloc) {
    	this.md = md;
        this.aloc = aloc;
        
        lblId.setText("Id: " + aloc.getId());
        lblMatricula.setText("Matrícula:" + aloc.getColaborador().getMatricula());
        lblNome.setText("Nome: " + aloc.getColaborador().getNome());
        lblDataInicio.setText("Data Inicio: " + aloc.getDataInicio());
        lblHrInicio.setText("Hora Inicio: " + aloc.getHoraInicio());
        lblDataFim.setText("Data Fim: " + aloc.getDataFim());
        lblHrFim.setText("Hora Fim: " + aloc.getHoraFim());
        
    }

    @FXML
    private void onAlterar() {
        System.out.println("Alterar card alocacao: ");    
    
    }

    @FXML
    private void onExcluir() {
        System.out.println("Excluir card alocacao: ");
    }
    
    
}
