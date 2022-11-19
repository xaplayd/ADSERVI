package application;

import gui.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) {

		LoginController lg = new LoginController();

		lg.novaJanela();

	}

	public static void main(String[] args) {
		launch(args);
	}
}
