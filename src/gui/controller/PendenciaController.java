package gui.controller;

import dao.TblTagsDAOImpl;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import models.Tag;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PendenciaController {

    @FXML private WebView webView;
    @FXML private Button insertImageBtn;
    @FXML private Button sendEmailBtn;
    @FXML private ListView<String> tagListView;
    @FXML private TextField filterTextField;
    @FXML private Button clearFilterBtn;

    private WebEngine engine;

    private ObservableList<String> allTags;
    private FilteredList<String> filteredTags;

    private final ObservableMap<String, BooleanProperty> tagSelections = FXCollections.observableHashMap();

    private final TblTagsDAOImpl tagsDAO = new TblTagsDAOImpl();

    @FXML
    public void initialize() {
        // 1. Carrega tags do banco
        List<Tag> tagsFromDB = tagsDAO.getAll();
        allTags = FXCollections.observableArrayList(
            tagsFromDB.stream().map(Tag::getNome).collect(Collectors.toList())
        );

        // 2. Cria mapa de seleção de tags
        for (String tag : allTags) {
            tagSelections.put(tag, new SimpleBooleanProperty(false));
        }

        // 3. Lista filtrável
        filteredTags = new FilteredList<>(allTags, s -> true);
        tagListView.setItems(filteredTags);

        // 4. Células com checkbox
        tagListView.setCellFactory(CheckBoxListCell.forListView(
            tag -> tagSelections.get(tag),
            new StringConverter<>() {
                @Override public String toString(String object) { return object; }
                @Override public String fromString(String string) { return string; }
            }
        ));

        // 5. Filtro por texto
        filterTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            String filter = newVal == null ? "" : newVal.toLowerCase().trim();
            filteredTags.setPredicate(tag -> tag.toLowerCase().contains(filter));
        });

        // 6. Botão limpar filtro
        clearFilterBtn.setOnAction(e -> filterTextField.clear());

        // 7. Carrega o WebView com TinyMCE
        initializeWebView();

        // 8. Ação botão inserir imagem
        insertImageBtn.setOnAction(e -> handleInsertImage());
    }

    private void initializeWebView() {
        engine = webView.getEngine();
        String url = getClass().getResource("/resources/tinymce_pendencia.html").toExternalForm();
        engine.load(url);

        // Aplica zoom menor (para reduzir tamanho visual dos botões do editor)
        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState.toString().equals("SUCCEEDED")) {
                webView.setZoom(0.8); // ajustável
            }
        });

        engine.setOnAlert(event -> System.out.println("JS ALERT: " + event.getData()));
        engine.getLoadWorker().exceptionProperty().addListener((obs, oldExc, newExc) -> {
            if (newExc != null) {
                System.err.println("Load exception: " + newExc.getMessage());
            }
        });
        engine.setOnError(event -> System.err.println("JS error: " + event.getMessage()));
    }

    private void handleInsertImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolher Imagem");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            String imageUrl = file.toURI().toString();
            // Chama JS no editor para inserir imagem
            engine.executeScript("insertImage('" + imageUrl + "')");
        }
    }

    @FXML
    private void handleSendEmail() {
        // Captura conteúdo HTML
        String htmlContent = (String) engine.executeScript("getEditorContent()");

        // Coleta tags marcadas
        List<String> selectedTags = tagSelections.entrySet().stream()
            .filter(entry -> entry.getValue().get())
            .map(Map.Entry::getKey)
            .collect(Collectors.toList());

        System.out.println("TAGS selecionadas: " + selectedTags);
        System.out.println("HTML gerado:\n" + htmlContent);

        // Aqui você pode disparar o envio do e-mail
    }
}