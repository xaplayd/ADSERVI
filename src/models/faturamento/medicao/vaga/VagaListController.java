package models.faturamento.medicao.vaga;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import models.faturamento.medicao.Medicao;

public class VagaListController implements Initializable {
	
	Medicao md;

    @FXML
    private ScrollPane root;

    @FXML
    private VBox container;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    	root.getProperties().put("controller", this);
    }
    
    public void initData(Medicao md) {
    	this.md = md;
    	setVagasCard(md.getVagas());
    }

    public void setVagasCard(List<Vaga> vagas) {
        container.getChildren().clear();

        for (Vaga vaga : vagas) {
            try {
                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/models/faturamento/medicao/vaga/VagaCard.fxml"));
                Node cardNode = loader.load();

                VagaCardController controller = loader.getController();
                controller.setVaga(md, vaga);

                container.getChildren().add(cardNode);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ScrollPane getRoot() {
        return root;
    }
}
