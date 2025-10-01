package models.faturamento.medicao;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import models.faturamento.medicao.colaborador.Colaborador;
import models.faturamento.medicao.colaborador.ColaboradorListController;
import models.faturamento.medicao.vaga.PostoVaga;
import models.faturamento.medicao.vaga.PostoVagaListController;

public class MedicaoController implements Initializable {

    // raiz dos includes (ScrollPane nos dois casos)
    @FXML
    private ScrollPane postoVagaListView;

    @FXML
    private ScrollPane colaboradorListView;

    // controladores reais (pegos via getProperties())
    private PostoVagaListController postoVagaListController;
    private ColaboradorListController colaboradorListController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Recupera os controllers dos includes
        postoVagaListController = (PostoVagaListController) 
                postoVagaListView.getProperties().get("controller");

        colaboradorListController = (ColaboradorListController) 
                colaboradorListView.getProperties().get("controller");
    }

    public void setPostos(List<PostoVaga> postos) {
        postoVagaListController.setPostos(postos);
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        colaboradorListController.setColaboradores(colaboradores);
    }
}
