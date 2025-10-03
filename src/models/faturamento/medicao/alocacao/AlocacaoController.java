package models.faturamento.medicao.alocacao;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.Pane;
import models.faturamento.medicao.Medicao;

public class AlocacaoController {
	
	private Medicao md;
	
    private AlocacaoListController alocListController;
    
    @FXML
    private ScrollPane alocacaoListView;
        
    @FXML
    private SplitPane root;

    public void listaAlocacoes(Medicao md) {
    	this.md = md;


        try {
            FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/models/faturamento/medicao/alocacao/AlocacaoListView.fxml")
            );
            ScrollPane alocListRoot = loader.load();

            // pega o controller já com @FXML injetado
            alocListController = loader.getController();
            alocListController.initData(md);

            // adiciona na ScrollPane
            alocacaoListView.setContent(alocListRoot);

        } catch (IOException e) {
            e.printStackTrace();
        }
        
     
    }

    public SplitPane getRoot() {
        return root;
    }
}
