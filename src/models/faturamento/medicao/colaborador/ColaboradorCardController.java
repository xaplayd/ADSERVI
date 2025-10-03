package models.faturamento.medicao.colaborador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.faturamento.medicao.Medicao;

public class ColaboradorCardController {
	
	Medicao md;

	@FXML
	private Label lblNome;
	@FXML
	private Label lblMatricula;

	public void setColaborador(Colaborador colab) {
		lblNome.setText(colab.getNome());
		lblMatricula.setText("Matrícula: " + colab.getMatricula());
	}
	
	
}
