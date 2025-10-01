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
import models.faturamento.documentacao.Tag;
import netscape.javascript.JSObject;
import services.GMailer;

import java.io.File;
import java.util.ArrayList;
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
    private final List<File> insertedImages = new ArrayList<>();
    private final List<String> editorImages = new ArrayList<>();
    private ObservableList<String> allTags;
    private FilteredList<String> filteredTags;

    private final ObservableMap<String, BooleanProperty> tagSelections = FXCollections.observableHashMap();
    private final TblTagsDAOImpl tagsDAO = new TblTagsDAOImpl();

    @FXML
    public void initialize() {
        // Carrega tags do banco
        List<Tag> tagsFromDB = tagsDAO.getAll();
        allTags = FXCollections.observableArrayList(
            tagsFromDB.stream().map(Tag::getNome).collect(Collectors.toList())
        );

        for (String tag : allTags) {
            tagSelections.put(tag, new SimpleBooleanProperty(false));
        }

        filteredTags = new FilteredList<>(allTags, s -> true);
        tagListView.setItems(filteredTags);

        tagListView.setCellFactory(CheckBoxListCell.forListView(
            tag -> tagSelections.get(tag),
            new StringConverter<>() {
                @Override public String toString(String object) { return object; }
                @Override public String fromString(String string) { return string; }
            }
        ));

        filterTextField.textProperty().addListener((obs, oldVal, newVal) -> {
            String filter = newVal == null ? "" : newVal.toLowerCase().trim();
            filteredTags.setPredicate(tag -> tag.toLowerCase().contains(filter));
        });

        clearFilterBtn.setOnAction(e -> filterTextField.clear());

        initializeWebView();

        insertImageBtn.setOnAction(e -> handleInsertImage());
        sendEmailBtn.setOnAction(e -> handleSendEmail());
    }

    private void initializeWebView() {
        engine = webView.getEngine();
        String url = getClass().getResource("/resources/tinymce_pendencia.html").toExternalForm();
        engine.load(url);

        engine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState.toString().equals("SUCCEEDED")) webView.setZoom(0.8);
        });

        engine.setOnAlert(event -> System.out.println("JS ALERT: " + event.getData()));
        engine.getLoadWorker().exceptionProperty().addListener((obs, oldExc, newExc) -> {
            if (newExc != null) System.err.println("Load exception: " + newExc.getMessage());
        });
        engine.setOnError(event -> System.err.println("JS error: " + event.getMessage()));
    }

    private void handleInsertImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Escolher Imagem");
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            try {
                // lê bytes do arquivo e transforma em base64
                byte[] bytes = java.nio.file.Files.readAllBytes(file.toPath());
                String base64 = java.util.Base64.getEncoder().encodeToString(bytes);

                // detecta MIME
                String mimeType = java.nio.file.Files.probeContentType(file.toPath());
                if (mimeType == null || !mimeType.startsWith("image/")) {
                    mimeType = "image/png";
                }

                // monta a tag <img>
                String imgTag = "<img src='data:" + mimeType + ";base64," + base64 + "' />";
                engine.executeScript(
                    "tinymce.activeEditor.execCommand('mceInsertContent', false, `" + imgTag + "`);"
                );

                // adiciona o arquivo escolhido na lista local
                insertedImages.add(file);

                // agora pega TODAS as imagens que estão no TinyMCE
                JSObject result = (JSObject) engine.executeScript(
                    "Array.from(tinymce.activeEditor.dom.select('img')).map(img => img.src)"
                );

                editorImages.clear();
                for (int i = 0; i < (int) result.getMember("length"); i++) {
                    editorImages.add((String) result.getSlot(i));
                }

                System.out.println("Arquivos escolhidos: " + insertedImages.size());
                System.out.println("Imagens no editor: " + editorImages.size());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleSendEmail() {
        try {
            // 1️⃣ Conteúdo HTML do TinyMCE
            String htmlContent = (String) engine.executeScript("getEditorContent()");

            // 2️⃣ Tags selecionadas (para assunto)
            List<String> selectedTags = tagSelections.entrySet().stream()
                    .filter(entry -> entry.getValue().get())
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            GMailer mailer = new GMailer();

            // 3️⃣ Cria lista de arquivos a enviar como anexos
            List<File> attachments = new ArrayList<>(insertedImages);

            // 4️⃣ Padrão inline + anexos separados
            // Criar Multipart mixed
            // HTML + inline primeiro (related)
            // Anexos depois

            // Adaptando a lógica do mini-exemplo
            mailer.sendMail(
                    "Pendência - " + String.join(", ", selectedTags),
                    htmlContent,
                    insertedImages // GMailer já vai criar inline + anexos separados
            );

            System.out.println("Email enviado com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}