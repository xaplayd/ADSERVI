package models.faturamento.medicao.colaborador;

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

public class ColaboradorListController implements Initializable{

    @FXML
    private ScrollPane root; // raiz do FXML

    @FXML
    private VBox container; // VBox dentro do ScrollPane

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getProperties().put("controller", this);
    }
    public void setColaboradores(List<Colaborador> colaboradores) {
        container.getChildren().clear();

        int indice = 1;
        for (Colaborador c : colaboradores) {
            try {
                FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/models/faturamento/medicao/colaborador/ColaboradorCard.fxml")
                );
                Node cardNode = loader.load();

                ColaboradorCardController controller = loader.getController();
                controller.setColaborador(c);
                controller.setIndice(indice++);

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
