package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class PrincipalController {
	
	private Integer janelaCadastroUsuarioSituacao = 0;
	

	@FXML
	private MenuItem cadastroUsuario;

	@FXML
	public void onMenuItemCadastroUsuarioAction() {
		CadastroUsuarioController janela = null;
		
		if(janelaCadastroUsuarioSituacao == 0) {
				janelaCadastroUsuarioSituacao = 1;
				Stage stageCadastroUsuario = new Stage();
				janela = new CadastroUsuarioController(stageCadastroUsuario);
				janela.novaJanela();
		}
		else {
			janela.getStageCadastroUsuario().toFront();
		}
	}
	
}