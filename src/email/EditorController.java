package email;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;

import java.io.File;

public class EditorController {

    @FXML private WebView webView;
    @FXML private Button insertImageBtn;
    @FXML private Button sendEmailBtn;

    private WebEngine engine;

    @FXML
    public void initialize() {
        engine = webView.getEngine();
        String url = getClass().getResource("/email/editor.html").toExternalForm();
        engine.load(url);
        
        engine.setOnAlert(event -> System.out.println("JS ALERT: " + event.getData()));
        engine.getLoadWorker().exceptionProperty().addListener((obs, oldExc, newExc) -> {
            if (newExc != null) {
                System.err.println("Load exception: " + newExc.getMessage());
            }
        });
        engine.setOnError(event -> System.err.println("JS error: " + event.getMessage()));
    }

    @FXML
    private void handleInsertImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolher Imagem");
        File file = fileChooser.showOpenDialog(null);
        if (file != null) {
            String imageUrl = file.toURI().toString();
            engine.executeScript("insertImage('" + imageUrl + "')");
        }
    }

    @FXML
    private void handleSendEmail() {
        String htmlContent = (String) engine.executeScript("getEditorContent()");
        EmailService.sendEmailWithHtml(htmlContent); // Defina este método como quiser
    }
}