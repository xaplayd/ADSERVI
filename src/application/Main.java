package application;

import gui.controller.LoginController;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {
		LoginController lg = new LoginController();
		lg.novaJanela(lg);
	
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
