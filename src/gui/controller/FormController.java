package gui.controller;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.DAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.TabelaColuna;
import utils.TableColumnFormatter;

public class FormController <T> implements Initializable {


    //  ------------- Estrutura base -------------  \\
    
    private Integer estado = 0; // 0 - neutro, 1 - novo, 2 - edição

    @FXML
    private HBox topLinhaFixaContainer;
    private Label labelPrimeiraColuna;
    private TextField tfPrimeiraColuna;
    private Button btnPesquisaPrimeiraColuna;
    private HBox linhaFixaPrimeiraColuna;
    
    @FXML
    private VBox formContainer;

    @FXML
    private Button btnNovo, btnSalvar, btnExcluir, btnCancelar, btnEditar, btnFechar;
    private DAO<T> dao;

    private List<TabelaColuna> estrutura = null;

    private void adicionarBotoesRodape() {
        ImageView iconBtnNovo = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/adicionar.png")));
        iconBtnNovo.setFitWidth(16);
        iconBtnNovo.setFitHeight(16);
        btnNovo.setGraphic(iconBtnNovo);
        btnNovo.setContentDisplay(ContentDisplay.LEFT);
        ImageView iconBtnSalvar = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/save--v1.png")));
        iconBtnSalvar.setFitWidth(16);
        iconBtnSalvar.setFitHeight(16);
        btnSalvar.setGraphic(iconBtnSalvar);
        btnSalvar.setContentDisplay(ContentDisplay.LEFT);
        ImageView iconBtnExcluir = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/delete--v1.png")));
        iconBtnExcluir.setFitWidth(16);
        iconBtnExcluir.setFitHeight(16);
        btnExcluir.setGraphic(iconBtnExcluir);
        btnExcluir.setContentDisplay(ContentDisplay.LEFT);
        ImageView iconBtnCancelar = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/erro.png")));
        iconBtnCancelar.setFitWidth(16);
        iconBtnCancelar.setFitHeight(16);
        btnCancelar.setGraphic(iconBtnCancelar);
        btnCancelar.setContentDisplay(ContentDisplay.LEFT);
        ImageView iconBtnEditar = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/editar.png")));
        iconBtnEditar.setFitWidth(16);
        iconBtnEditar.setFitHeight(16);
        btnEditar.setGraphic(iconBtnEditar);
        btnEditar.setContentDisplay(ContentDisplay.LEFT);
        ImageView iconBtnFechar = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/sair.png")));
        iconBtnFechar.setFitWidth(16);
        iconBtnFechar.setFitHeight(16);
        btnFechar.setGraphic(iconBtnFechar);
        btnFechar.setContentDisplay(ContentDisplay.LEFT);

    }

    private Button adicionarBotaoPesquisa() {
        Button btnPesquisa = new Button();
        ImageView iconBtnPesquisa = new ImageView(new Image(getClass().getResourceAsStream("/imgs/18x18/lupa.png")));
        iconBtnPesquisa.setFitWidth(16);
        iconBtnPesquisa.setFitHeight(16);
        btnPesquisa.setGraphic(iconBtnPesquisa);
        return btnPesquisa;
    }

    private void configurarLinhaFixaPrimeiraColuna(TabelaColuna primeiraColuna) {
        labelPrimeiraColuna = new Label(TableColumnFormatter.formatarNomeColunaAutomaticamente(primeiraColuna.getNome()) + ":");
        labelPrimeiraColuna.setMinWidth(150);
        labelPrimeiraColuna.setAlignment(Pos.CENTER_RIGHT);

        tfPrimeiraColuna = new TextField(primeiraColuna.getValor() != null ? primeiraColuna.getValor().toString() : "");
        tfPrimeiraColuna.setId("campo_" + primeiraColuna.getNome());
        tfPrimeiraColuna.setMinWidth(80);
        tfPrimeiraColuna.setPrefWidth(80);
        tfPrimeiraColuna.setMaxWidth(80);

        btnPesquisaPrimeiraColuna = adicionarBotaoPesquisa();
        btnPesquisaPrimeiraColuna.setOnAction(e -> abrirPesquisaParaCampo(primeiraColuna.getNome(), tfPrimeiraColuna));

        linhaFixaPrimeiraColuna = new HBox(10, labelPrimeiraColuna, tfPrimeiraColuna, btnPesquisaPrimeiraColuna);
        linhaFixaPrimeiraColuna.setAlignment(Pos.CENTER_LEFT);
        linhaFixaPrimeiraColuna.setPadding(new Insets(5));

        topLinhaFixaContainer.getChildren().add(linhaFixaPrimeiraColuna);
        tfPrimeiraColuna.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case ENTER, TAB -> {
                    String valor = tfPrimeiraColuna.getText();
                    if (valor != null && !valor.isBlank()) {
                        carregarRegistroPorId();
                        btnNovo.setDisable(true);
                    }
                }
            }
        });
    }

    //  ------------- Ações estrutura base -------------  \\

    private void onNovo() {
    	estado = 1;
        tfPrimeiraColuna.setDisable(true);
        btnPesquisaPrimeiraColuna.setDisable(true);

        for (Node node : formContainer.getChildren()) {
            if (node instanceof HBox hbox) {
                if (hbox == linhaFixaPrimeiraColuna) {
                    continue;
                }
                for (Node subNode : hbox.getChildren()) {
                    if (subNode instanceof Control control) {
                        control.setDisable(false);
                    }
                }
            }
        }
        btnNovo.setDisable(true);
        btnSalvar.setDisable(false);
        btnExcluir.setDisable(true);
        btnCancelar.setDisable(false);
        btnEditar.setDisable(true);
        btnFechar.setDisable(false);
    }

    public void onSalvar() {
        try {
            T tempObj = dao.mapperViewToEntity(coletarValoresDoFormulario());
            

            int resultado = 0;

            if (estado == 1) {
                resultado = dao.insert(tempObj);
                
                System.out.println("Entidade inserida com sucesso. Linhas afetadas: " + resultado);
            } else if (estado == 2) {

                resultado = dao.updateById(tempObj);
                System.out.println("Entidade atualizada com sucesso. Linhas afetadas: " + resultado);
            } else {
                System.out.println("Estado da tela inválido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void onExcluir() {}

    private void onCancelar() {}

    private void onEditar() {
    	estado = 2;
        tfPrimeiraColuna.setDisable(true);
        btnPesquisaPrimeiraColuna.setDisable(true);

        for (Node node : formContainer.getChildren()) {
            if (node instanceof HBox hbox) {
                if (hbox == linhaFixaPrimeiraColuna) {
                    continue;
                }
                for (Node subNode : hbox.getChildren()) {
                    if (subNode instanceof Control control) {
                        control.setDisable(false);
                    }
                }
            }
        }
        btnNovo.setDisable(true);
        btnSalvar.setDisable(false);
        btnExcluir.setDisable(false);
        btnCancelar.setDisable(false);
        btnEditar.setDisable(true);
        btnFechar.setDisable(false);
        
    }

    private void onSair() {
		Stage stage = (Stage) btnFechar.getScene().getWindow();
		stage.close();
	}
    

    private void carregarRegistroPorId() {
    	try {
			this.estrutura = dao.mapperEntityToView(Integer.parseInt(tfPrimeiraColuna.getText()), this.estrutura);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    formContainer.getChildren().clear();
		    
	        formContainer.setAlignment(Pos.BASELINE_CENTER); // centralizar VBox

	        for (TabelaColuna col : this.estrutura.subList(1, this.estrutura.size())){
	            String coluna = col.getNome();
	            Object valor = col.getValor();
	            int tipoSQL = col.getTipoSQL();

	            String nomeFormatado = TableColumnFormatter.formatarNomeColunaAutomaticamente(coluna);
	            Label label = new Label(nomeFormatado + ":");
	            label.setMinWidth(150);
	            label.setAlignment(Pos.CENTER_RIGHT);

	            Control campo;
	            boolean isSenha = coluna.toLowerCase().contains("senha");

	            if (isSenha) {
	                PasswordField pf = new PasswordField();
	                pf.setText(valor != null ? valor.toString() : "");
	                pf.setId("campo_" + coluna);
	                campo = pf;
	            } else {
	                switch (tipoSQL) {
	                    case Types.BOOLEAN, Types.BIT -> {
	                        CheckBox cb = new CheckBox();
	                        cb.setSelected(valor != null && (Boolean.TRUE.equals(valor) || (valor instanceof Number && ((Number) valor).intValue() == 1)));
	                        cb.setId("campo_" + coluna);
	                        campo = cb;
	                    }
	                    case Types.DATE, Types.TIMESTAMP -> {
	                        DatePicker dp = new DatePicker();
	                        if (valor instanceof Date date) {
	                            dp.setValue(date.toLocalDate());
	                        } else if (valor instanceof Timestamp ts) {
	                            dp.setValue(ts.toLocalDateTime().toLocalDate());
	                        }
	                        dp.setId("campo_" + coluna);
	                        campo = dp;
	                    }
	                    case Types.INTEGER, Types.SMALLINT, Types.TINYINT, Types.BIGINT -> {
	                        TextField tf = new TextField(valor != null ? valor.toString() : "");
	                        tf.setId("campo_" + coluna);
	                        tf.setTextFormatter(TableColumnFormatter.numericIntegerFormatter());
	                        campo = tf;
	                    }
	                    case Types.FLOAT, Types.REAL, Types.DOUBLE, Types.NUMERIC, Types.DECIMAL -> {
	                        TextField tf = new TextField(valor != null ? valor.toString() : "");
	                        tf.setId("campo_" + coluna);
	                        tf.setTextFormatter(TableColumnFormatter.numericDecimalFormatter());
	                        campo = tf;
	                    }
	                    default -> {
	                        TextField tf = new TextField(valor != null ? valor.toString() : "");
	                        tf.setId("campo_" + coluna);
	                        campo = tf;
	                    }
	                }
	            }

	            HBox linha;
	            if (coluna.toLowerCase().contains("id")) {
	                TextField tf = (TextField) campo;
	                tf.setMinWidth(80);    // tamanho mínimo
	                tf.setPrefWidth(80);   // tamanho preferido
	                tf.setMaxWidth(80);    // tamanho máximo

	                Button btnPesquisa = adicionarBotaoPesquisa();

	                Label labelDescricao = new Label();
	                labelDescricao.setMinWidth(200);
	                labelDescricao.setText(obterDescricao(coluna, tf.getText()));

	                btnPesquisa.setOnAction(e -> abrirPesquisaParaCampo(coluna, tf));

	                tf.setDisable(true);
	                btnPesquisa.setDisable(true);
	                labelDescricao.setDisable(true);

	                linha = new HBox(10, label, tf, btnPesquisa, labelDescricao);
	            } else {
	                // Definir tamanho máximo para campos que não são IDs
	                campo.setPrefWidth(250);
	                campo.setMaxWidth(350);
	                campo.setDisable(true);

	                linha = new HBox(10, label, campo);
	            }

	            // Centralizar a linha e adicionar padding
	            linha.setAlignment(Pos.CENTER_LEFT);
	            linha.setPadding(new Insets(5));

	            // Impedir que o campo cresça além do maxWidth
	            HBox.setHgrow(campo, Priority.NEVER);

	            formContainer.getChildren().add(linha);
	        }

		    btnEditar.setDisable(false);
		    btnExcluir.setDisable(false);
		    btnCancelar.setDisable(false);
		    
		    
    }

    //  ------------- Estrutura variável -------------  \\

    private void gerarFormularioDinamico(List<TabelaColuna> colunas) {

        formContainer.setAlignment(Pos.BASELINE_CENTER); // centralizar VBox

        for (TabelaColuna col : colunas) {
            String coluna = col.getNome();
            Object valor = col.getValor();
            int tipoSQL = col.getTipoSQL();

            String nomeFormatado = TableColumnFormatter.formatarNomeColunaAutomaticamente(coluna);
            Label label = new Label(nomeFormatado + ":");
            label.setMinWidth(150);
            label.setAlignment(Pos.CENTER_RIGHT);

            Control campo;
            boolean isSenha = coluna.toLowerCase().contains("senha");

            if (isSenha) {
                PasswordField pf = new PasswordField();
                pf.setText(valor != null ? valor.toString() : "");
                pf.setId("campo_" + coluna);
                campo = pf;
            } else {
                switch (tipoSQL) {
                    case Types.BOOLEAN, Types.BIT -> {
                        CheckBox cb = new CheckBox();
                        cb.setSelected(valor != null && (Boolean.TRUE.equals(valor) || (valor instanceof Number && ((Number) valor).intValue() == 1)));
                        cb.setId("campo_" + coluna);
                        campo = cb;
                    }
                    case Types.DATE, Types.TIMESTAMP -> {
                        DatePicker dp = new DatePicker();
                        if (valor instanceof Date date) {
                            dp.setValue(date.toLocalDate());
                        } else if (valor instanceof Timestamp ts) {
                            dp.setValue(ts.toLocalDateTime().toLocalDate());
                        }
                        dp.setId("campo_" + coluna);
                        campo = dp;
                    }
                    case Types.INTEGER, Types.SMALLINT, Types.TINYINT, Types.BIGINT -> {
                        TextField tf = new TextField(valor != null ? valor.toString() : "");
                        tf.setId("campo_" + coluna);
                        tf.setTextFormatter(TableColumnFormatter.numericIntegerFormatter());
                        campo = tf;
                    }
                    case Types.FLOAT, Types.REAL, Types.DOUBLE, Types.NUMERIC, Types.DECIMAL -> {
                        TextField tf = new TextField(valor != null ? valor.toString() : "");
                        tf.setId("campo_" + coluna);
                        tf.setTextFormatter(TableColumnFormatter.numericDecimalFormatter());
                        campo = tf;
                    }
                    default -> {
                        TextField tf = new TextField(valor != null ? valor.toString() : "");
                        tf.setId("campo_" + coluna);
                        campo = tf;
                    }
                }
            }

            HBox linha;
            if (coluna.toLowerCase().contains("id")) {
                TextField tf = (TextField) campo;
                tf.setMinWidth(80);    // tamanho mínimo
                tf.setPrefWidth(80);   // tamanho preferido
                tf.setMaxWidth(80);    // tamanho máximo

                Button btnPesquisa = adicionarBotaoPesquisa();

                Label labelDescricao = new Label();
                labelDescricao.setMinWidth(200);
                labelDescricao.setText(obterDescricao(coluna, tf.getText()));

                btnPesquisa.setOnAction(e -> abrirPesquisaParaCampo(coluna, tf));

                tf.setDisable(true);
                btnPesquisa.setDisable(true);
                labelDescricao.setDisable(true);

                linha = new HBox(10, label, tf, btnPesquisa, labelDescricao);
            } else {
                // Definir tamanho máximo para campos que não são IDs
                campo.setPrefWidth(250);
                campo.setMaxWidth(350);
                campo.setDisable(true);

                linha = new HBox(10, label, campo);
            }

            // Centralizar a linha e adicionar padding
            linha.setAlignment(Pos.CENTER_LEFT);
            linha.setPadding(new Insets(5));

            // Impedir que o campo cresça além do maxWidth
            HBox.setHgrow(campo, Priority.NEVER);

            formContainer.getChildren().add(linha);
        }

    }

    // --------------------------------------------------------- \\

    // Simulação do método para pegar descrição do campo
    private String obterDescricao(String coluna, String valor) {
        if (valor == null || valor.isBlank()) {
            return "";
        }
        return "Descrição para " + coluna + " = " + valor;
    }

    private void abrirPesquisaParaCampo(String coluna, TextField tf) {
        System.out.println("Abrindo pesquisa para campo: " + coluna);
        // Exemplo simulado:
        String resultadoPesquisa = "123"; // Exemplo fixo de resultado da pesquisa
        tf.setText(resultadoPesquisa);

    }

    private Object construirObjeto(List<TabelaColuna> colunas, Class<?> clazz) {
        try {
            Object instancia = clazz.getDeclaredConstructor().newInstance();
            for (TabelaColuna col : colunas) {
                try {
                    Field field = clazz.getDeclaredField(col.getNome());
                    field.setAccessible(true);
                    Object valor = col.getValor();

                    // Conversão rudimentar, idealmente use utilitário genérico
                    if (field.getType() == Integer.class || field.getType() == int.class) {
                        field.set(instancia, valor != null ? Integer.parseInt(valor.toString()) : null);
                    } else if (field.getType() == String.class) {
                        field.set(instancia, valor != null ? valor.toString() : null);
                    } else if (field.getType() == Boolean.class || field.getType() == boolean.class) {
                        field.set(instancia, valor instanceof Boolean b ? b : Boolean.parseBoolean(valor.toString()));
                    } else if (field.getType() == java.sql.Date.class && valor instanceof java.sql.Date) {
                        field.set(instancia, valor);
                    } else {
                        field.set(instancia, valor); // fallback
                    }

                } catch (NoSuchFieldException ignore) {}
            }
            return instancia;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private List<TabelaColuna> coletarValoresDoFormulario() {
        List<TabelaColuna> valores = new ArrayList<>();

        // Coletar valores do formContainer
        for (Node node : formContainer.getChildren()) {
            if (node instanceof HBox hbox) {
                for (Node subNode : hbox.getChildren()) {
                    if (subNode instanceof Control control && control.getId() != null && control.getId().startsWith("campo_")) {
                        String nomeColuna = control.getId().replace("campo_", "");
                        Object valor = null;

                        if (control instanceof TextField tf) {
                            valor = tf.getText().isBlank() ? null : tf.getText();
                        } else if (control instanceof PasswordField pf) {
                            valor = pf.getText();
                        } else if (control instanceof DatePicker dp) {
                            valor = dp.getValue() != null ? java.sql.Date.valueOf(dp.getValue()) : null;
                        } else if (control instanceof CheckBox cb) {
                            valor = cb.isSelected();
                        }

                        int tipoSQL = estrutura.stream()
                            .filter(c -> c.getNome().equalsIgnoreCase(nomeColuna))
                            .map(TabelaColuna::getTipoSQL)
                            .findFirst()
                            .orElse(Types.VARCHAR);

                        valores.add(new TabelaColuna(nomeColuna, valor, tipoSQL));
                    }
                }
            }
        }

        // Coletar valores do topLinhaFixaContainer
        for (Node node : topLinhaFixaContainer.getChildren()) {
            if (node instanceof HBox hbox) {
                for (Node subNode : hbox.getChildren()) {
                    if (subNode instanceof Control control && control.getId() != null && control.getId().startsWith("campo_")) {
                        String nomeColuna = control.getId().replace("campo_", "");
                        Object valor = null;

                        if (control instanceof TextField tf) {
                            valor = tf.getText().isBlank() ? null : tf.getText();
                        } else if (control instanceof PasswordField pf) {
                            valor = pf.getText();
                        } else if (control instanceof DatePicker dp) {
                            valor = dp.getValue() != null ? java.sql.Date.valueOf(dp.getValue()) : null;
                        } else if (control instanceof CheckBox cb) {
                            valor = cb.isSelected();
                        }

                        int tipoSQL = estrutura.stream()
                            .filter(c -> c.getNome().equalsIgnoreCase(nomeColuna))
                            .map(TabelaColuna::getTipoSQL)
                            .findFirst()
                            .orElse(Types.VARCHAR);

                        valores.add(new TabelaColuna(nomeColuna, valor, tipoSQL));
                    }
                }
            }
        }

        return valores;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {}

    public void initData(Integer idRegistro, DAO<T> dao) {
        this.dao = dao;
    	try {
    		this.estrutura = dao.getColunasDaTabela();
    		configurarLinhaFixaPrimeiraColuna(estrutura.get(0));
    		gerarFormularioDinamico(estrutura.subList(1, estrutura.size()));
    		}catch (SQLException exception) {
    		exception.getMessage();    	
    		}
        adicionarBotoesRodape();
        btnNovo.setDisable(false);
        btnSalvar.setDisable(true);
        btnExcluir.setDisable(true);
        btnCancelar.setDisable(true);
        btnEditar.setDisable(true);
        btnFechar.setDisable(false);
        btnNovo.setOnAction(e -> onNovo());
        btnSalvar.setOnAction(e -> onSalvar());
        btnExcluir.setOnAction(e -> onExcluir());
        btnCancelar.setOnAction(e -> onCancelar());
        btnEditar.setOnAction(e -> onEditar());
        btnFechar.setOnAction(e -> onSair());
    }
}