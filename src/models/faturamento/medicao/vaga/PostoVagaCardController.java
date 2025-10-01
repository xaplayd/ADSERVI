package models.faturamento.medicao.vaga;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.faturamento.medicao.alocacao.AlocacaoViewController;

public class PostoVagaCardController {

	@FXML
	private Label lblIdView;

	@FXML
	private Label lblPosto;

	@FXML
	private Label lblPosTra;

	@FXML
	private Label lblTeto;

	@FXML
	private Label lblExecutado;

	@FXML
	private Label lblAExecutar;

	@FXML
	private Button btnAlterar;

	private PostoVaga postoVaga;

	private Integer idView;

	public void setPostoVaga(PostoVaga posto) {
		this.postoVaga = posto;
		lblPosto.setText("Posto: " + posto.getDesPosTabVaga());
		lblPosTra.setText("Cod.: " + posto.getPosTraTabVaga());
		lblTeto.setText("Teto: " + posto.getTeto());
		lblTeto.setStyle("-fx-font-weight: bold;");
		lblExecutado.setText("Executado: " + posto.getExecutado());
		lblAExecutar.setText("A Executar: " + posto.getaExecutar());
		
		Double valorAexecutar = posto.getaExecutar() != null ? posto.getaExecutar() : 0;

	    String statusAexecutar;
	    if (valorAexecutar > 0) {
	    	statusAexecutar = "positivo";
	    } else if (valorAexecutar < 0) {
	    	statusAexecutar = "negativo";
	    } else {
	    	statusAexecutar = "zero";
	    }

	    switch (statusAexecutar) {
	        case "positivo" -> lblAExecutar.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
	        case "negativo" -> lblAExecutar.setStyle("-fx-text-fill: orange; -fx-font-weight: bold;");
	        case "zero"     -> lblAExecutar.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	    }
	    
	    Double valorExecutado = posto.getExecutado() != null ? posto.getExecutado() : 0;
	    
	    String statusExecutado;
	    if (valorExecutado < posto.getTeto()) {
	    	statusExecutado = "negativo";
	    } else if (valorExecutado >= posto.getTeto()) {
	    	statusExecutado = "positivo";
	    } else {
	    	statusExecutado = "zero";
	    }

	    switch (statusExecutado) {
	        case "positivo" -> lblExecutado.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
	        case "negativo" -> lblExecutado.setStyle("-fx-text-fill: orange;-fx-font-weight: bold;");
	        case "zero"     -> lblExecutado.setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
	    }
	    
	    
	}

	@FXML
	private void onAlterar() {
		 try {
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/models/faturamento/medicao/alocacao/AlocacaoView.fxml"));
		        Parent root = loader.load();

		        AlocacaoViewController controller = loader.getController();
		        controller.setPosto(this.postoVaga); // passa o posto em edição

		        Stage stage = new Stage();
		        stage.setTitle("Gerenciar Alocações");
		        stage.setScene(new Scene(root, 900, 600));
		        stage.showAndWait();

		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		System.out.println("Alterar card de índice vaga: " + this.idView);
	}

	public void setIndice(Integer id) {
		this.idView = id;
		lblIdView.setText("ID: " + idView); // exibe no label
	}
}