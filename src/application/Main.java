package application;

import java.io.IOException;

import gui.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.stage.Stage;
import models.faturamento.medicao.MedicaoController;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		/*LoginController lg = new LoginController();
		lg.novaJanela(lg);*/
		
		try {
			Stage stageMedicaoTeste = new Stage();
	    	 FXMLLoader loader = new FXMLLoader(getClass().getResource("/models/faturamento/medicao/MedicaoView.fxml"));
	         SplitPane root = loader.load();

	         MedicaoController controller = loader.getController();
	         controller.novaMedicao("1943", "01/08/2025", "31/08/2025");

	         stageMedicaoTeste.setScene(new Scene(root, 1000, 600));
	         stageMedicaoTeste.setTitle("Medição - Postos");
	         stageMedicaoTeste.show();

	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
