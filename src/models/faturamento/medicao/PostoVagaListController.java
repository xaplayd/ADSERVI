package models.faturamento.medicao;

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

public class PostoVagaListController implements Initializable {

    @FXML
    private ScrollPane root; // raiz do FXML (ScrollPane)

    @FXML
    private VBox container; // VBox dentro do ScrollPane

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        root.getProperties().put("controller", this);
    }
    
    public void setPostos(List<PostoVaga> postos) {
        container.getChildren().clear();

        int indice = 1; // contador único para todos os cards na tela

        for (PostoVaga posto : postos) {
            for (int j = 0; j < posto.getQtdQpoTabVaga(); j++) {
                try {
                    FXMLLoader loader = new FXMLLoader(
                        getClass().getResource("/models/faturamento/medicao/PostoVagaCard.fxml")
                    );
                    Node cardNode = loader.load();

                    PostoVagaCardController controller = loader.getController();
                    controller.setPostoVaga(posto);
                    controller.setIndice(indice++); // vai numerando de 1 em diante

                    container.getChildren().add(cardNode);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ScrollPane getRoot() {
        return root;
    }
}