package gui.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dao.DAO;
import dao.TblFormatoContratoDAOImpl;
import dao.TblIndiceEscopoDAOImpl;
import dao.TblNiveisDAOImpl;
import dao.TblSetoresDAOImpl;
import dao.TblSituacaoDAOImpl;
import dao.TblTagsDAOImpl;
import dao.TblTipoClienteDAOImpl;
import dao.TblUniMedidasDAOImpl;
import dao.TblUsuariosDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
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

public class CadastroFormController <T> implements Initializable {


    //  ------------- Estrutura base -------------  \\
    
	//estados tela
    private Integer estado = 0; // 0 - neutro, 1 - novo, 2 - edição

    //estrutura fixa topo/id
    @FXML
    private HBox topLinhaFixaContainer;
    private Label labelPrimeiraColuna;
    private TextField tfPrimeiraColuna;
    private Button btnPesquisaPrimeiraColuna;
    private HBox linhaFixaPrimeiraColuna;
    
    //estrutura gerada confome campos tabela
    @FXML
    private VBox formContainer;

    //estrutura fixa rodapé/botoes
    @FXML
    private Button btnNovo, btnSalvar, btnExcluir, btnCancelar, btnEditar, btnFechar;
    
    //dao do model para uso
    private DAO<T> dao;

    //lista com estrutura de dados da tela
    private List<TabelaColuna> estrutura = null;

    //  ------------- Gera a view -------------  \\
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
                    T temp = null;
                    try {
						temp = dao.getById(Integer.parseInt(tfPrimeiraColuna.getText()));
					} catch (NumberFormatException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    if(temp != null) {

			                    if (valor != null && !valor.isBlank()) {
			                        try {
			                            int id = Integer.parseInt(valor);
			                            this.estrutura = dao.mapperEntityToView(id, this.estrutura);
			                            if (estrutura == null || estrutura.isEmpty()) {
			                                throw new SQLException("ID não encontrado.");
			                            }
			
			                            formContainer.getChildren().clear();
			                            for (TabelaColuna col : estrutura.subList(1, estrutura.size())) {
			                                HBox linha = construirLinhaFormulario(col, false);
			                                formContainer.getChildren().add(linha);
			                            }
			
			                            btnEditar.setDisable(false);
			                            btnExcluir.setDisable(false);
			                            btnCancelar.setDisable(false);
			                            btnNovo.setDisable(true);
			
			                        } catch (NumberFormatException | SQLException e) {
			                            tfPrimeiraColuna.clear();
			                            Alert alert = new Alert(Alert.AlertType.WARNING);
			                            alert.setTitle("ID inválido");
			                            alert.setHeaderText("Esta chave ID não existe!");
			                            alert.showAndWait();
			                            tfPrimeiraColuna.requestFocus();
			                            event.consume(); // Impede o foco de sair do campo
			                        }
			                    }
                    } else {
                    	Alert alert = new Alert(Alert.AlertType.WARNING);
						alert.setTitle("ID não encontrado");
						alert.setHeaderText("Esta chave ID não existe para esta tabela!");
                        alert.showAndWait();
                        tfPrimeiraColuna.requestFocus();
                        event.consume(); // Impede o foco de sair do campo
                    	
                    }
                }
            }
        });
    }
    
    private void gerarFormularioDinamico(List<TabelaColuna> colunas) {
        formContainer.setAlignment(Pos.BASELINE_CENTER);
        for (TabelaColuna col : colunas) {
            HBox linha = construirLinhaFormulario(col, false); // inicia desabilitado
            formContainer.getChildren().add(linha);
        }
    }

    //  ------------- Ações  -------------  \\
    
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
    	
		  if (!validarCamposObrigatorios()) {
		        return; // Interrompe o salvamento se houver campos em branco
		    }
	    	  
        try {
            List<TabelaColuna> dados = coletarValoresDoFormulario();
            T tempObj = dao.mapperViewToEntity(dados);

            if (estado == 1) {
                // Supondo que o método insert agora retorna o ID gerado (Integer ou Object)
                Object idGerado = dao.insert(tempObj);

                System.out.println("Entidade inserida com sucesso. ID gerado: " + idGerado);

                // Preencher o campo de ID (tfPrimeiraColuna) com o ID gerado
                tfPrimeiraColuna.setText(idGerado != null ? idGerado.toString() : null);
                for (Node node : formContainer.getChildren()) {
                    if (node instanceof HBox hbox) {
                        for (Node subNode : hbox.getChildren()) {
                            if (subNode instanceof Control control) {
                                control.setDisable(true);
                            }
                        }
                    }
                }
                btnNovo.setDisable(true);
                btnSalvar.setDisable(true);
                btnExcluir.setDisable(false);
                btnCancelar.setDisable(false);
                btnEditar.setDisable(false);
                btnFechar.setDisable(false);
            } else if (estado == 2) {
                int resultado = dao.updateById(tempObj);
                System.out.println("Entidade atualizada com sucesso. Linhas afetadas: " + resultado);
                for (Node node : formContainer.getChildren()) {
                    if (node instanceof HBox hbox) {
                        for (Node subNode : hbox.getChildren()) {
                            if (subNode instanceof Control control) {
                                control.setDisable(true);
                            }
                        }
                    }
                }
                btnNovo.setDisable(true);
                btnSalvar.setDisable(true);
                btnExcluir.setDisable(false);
                btnCancelar.setDisable(false);
                btnEditar.setDisable(false);
                btnFechar.setDisable(false);
            } else {
                System.out.println("Estado da tela inválido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private boolean validarCamposObrigatorios() {
        StringBuilder camposInvalidos = new StringBuilder();

        for (Node node : formContainer.getChildren()) {
            if (node instanceof HBox hbox) {
                for (Node subNode : hbox.getChildren()) {
                    if (subNode instanceof TextField tf && tf.getId() != null && tf.getId().startsWith("campo_")) {
                        if (tf.getText() == null || tf.getText().isBlank()) {
                            String nomeCampo = tf.getId().replace("campo_", "");
                            camposInvalidos.append("- ").append(TableColumnFormatter.formatarNomeColunaAutomaticamente(nomeCampo)).append("\n");
                        }
                    }
                    if (subNode instanceof PasswordField pf && pf.getId() != null && pf.getId().startsWith("campo_")) {
                        if (pf.getText() == null || pf.getText().isBlank()) {
                            String nomeCampo = pf.getId().replace("campo_", "");
                            camposInvalidos.append("- ").append(TableColumnFormatter.formatarNomeColunaAutomaticamente(nomeCampo)).append("\n");
                        }
                    }
                }
            }
        }

        if (!camposInvalidos.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Campos obrigatórios");
            alert.setHeaderText("Por favor, preencha os seguintes campos:");
            alert.setContentText(camposInvalidos.toString());
            alert.showAndWait();
            return false;
        }

        return true;
    }
    
    private void onExcluir() {
  		try {
  			T tempObj = dao.mapperViewToEntity(coletarValoresDoFormulario());
			System.out.println("EXCLUSÃO DE DADOS ATIVA!!");
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "", ButtonType.YES, ButtonType.NO);
			alert.setTitle("Confirmação:");
			alert.setHeaderText("Realmente deseja excluir o usuário selecionado?");
			alert.showAndWait();
			if (alert.getResult() == ButtonType.YES) {
				int result = dao.deleteById(tempObj);
				if (result > 0) {
					onCancelar();
					this.estado = 0;
				}
			}
		} catch (SQLException exception) {
			exception.getMessage();
		}  
    }

    private void onCancelar() {
    	topLinhaFixaContainer.getChildren().clear();
    	formContainer.getChildren().clear();
    	initData(null, dao);
    }

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
    
    private HBox construirLinhaFormulario(TabelaColuna col, boolean camposEditaveis) {
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
            tf.setMinWidth(80);
            tf.setPrefWidth(80);
            tf.setMaxWidth(80);

            Button btnPesquisa = adicionarBotaoPesquisa();
            Label labelDescricao = new Label(obterDescricao(coluna, tf.getText()));
            labelDescricao.setId("descricao_" + coluna); // <-- Adiciona ID único
            labelDescricao.setMinWidth(200);
            labelDescricao.setText(obterDescricaoComDao(coluna, tf.getText()));
            btnPesquisa.setOnAction(e -> abrirPesquisaParaCampo(coluna, tf));

            tf.setDisable(!camposEditaveis);
            btnPesquisa.setDisable(!camposEditaveis);
            labelDescricao.setDisable(!camposEditaveis);
            tf.setOnKeyPressed(event -> {
                switch (event.getCode()) {
                    case ENTER, TAB -> {
                        String idDigitado = tf.getText();
                        if (idDigitado != null && !idDigitado.isBlank()) {
                            String descricao = obterDescricaoComDao(coluna, idDigitado);
                            if (descricao != null && !descricao.isBlank()) {
                                labelDescricao.setText(descricao);
                            } else {
                                tf.clear();
                                tf.requestFocus();
        						Alert alert = new Alert(Alert.AlertType.WARNING);
        						alert.setTitle("ID não encontrado");
        						alert.setHeaderText("Esta chave ID não existe para esta tabela!");
        						alert.showAndWait();
        						tf.requestFocus(); 
                            }
                        } else {
                            labelDescricao.setText("");
                        }
                    }
                }
            });

            linha = new HBox(10, label, tf, btnPesquisa, labelDescricao);
        } else {
            campo.setPrefWidth(250);
            campo.setMaxWidth(350);
            campo.setDisable(!camposEditaveis);
            linha = new HBox(10, label, campo);
        }

        linha.setAlignment(Pos.CENTER_LEFT);
        linha.setPadding(new Insets(5));
        HBox.setHgrow(campo, Priority.NEVER);

        return linha;
    }
    
    private void carregarRegistroPorId() {
        try {
            this.estrutura = dao.mapperEntityToView(Integer.parseInt(tfPrimeiraColuna.getText()), this.estrutura);
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            return;
        }

        formContainer.getChildren().clear();
        formContainer.setAlignment(Pos.BASELINE_CENTER);

        for (TabelaColuna col : this.estrutura.subList(1, this.estrutura.size())) {
            HBox linha = construirLinhaFormulario(col, false); // inicia desabilitado

            // Se a coluna for de ID e tiver botão de pesquisa, atualiza o label de descrição
            if (col.getNome().toLowerCase().startsWith("id_")) {
                for (Node node : linha.getChildren()) {
                    if (node instanceof TextField tf && tf.getId() != null && tf.getId().startsWith("campo_")) {
                        String coluna = col.getNome();
                        String valor = tf.getText();
                        for (Node sub : linha.getChildren()) {
                        	Label labelDescricao = (Label) linha.lookup("#descricao_" + coluna);
                        	if (labelDescricao != null) {
                        	    labelDescricao.setText(obterDescricaoComDao(coluna, valor));
                        	}
                        }
                    }
                }
            }

            formContainer.getChildren().add(linha);
        }

        btnEditar.setDisable(false);
        btnExcluir.setDisable(false);
        btnCancelar.setDisable(false);
    }

    // Simulação do método para pegar descrição do campo
    private String obterDescricao(String coluna, String valor) {
        if (valor == null || valor.isBlank()) {
            return "";
        }
        return "Descrição para " + coluna + " = " + valor;
    }

    Stage stagePesquisa = null;
    
    private void abrirPesquisaParaCampo(String coluna, TextField tf) {
        DAO<T> daoPesquisa = obterDaoParaColuna(coluna);
        if (daoPesquisa == null) {
            System.out.println("Nenhum DAO disponível para a coluna: " + coluna);
            return;
        }
        if (stagePesquisa == null) {
        	stagePesquisa = new Stage();
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
            PesquisaFormController<T> psc = new PesquisaFormController<>();
            psc.initData(daoPesquisa);
            loader.setController(psc);
            Parent parent = loader.load();

            Scene scenePesquisa = new Scene(parent);
            stagePesquisa.setTitle("Pesquisa para " + coluna);
            stagePesquisa.setScene(scenePesquisa);
            stagePesquisa.setResizable(true);
            stagePesquisa.getIcons().add(new Image(getClass().getResourceAsStream("/imgs/18x18/lupa.png")));
            stagePesquisa.setOnHidden(event -> {
                String resultado = psc.getIdItemSelecionado();
                if (resultado != null) {
                    tf.setText(resultado);
                    tf.requestFocus();

                    // ⚠️ Atualizar o Label ao lado com a descrição do nome
                    HBox parenti = (HBox) tf.getParent();
                    for (Node node : parenti.getChildren()) {
                        if (node instanceof Label label && !label.getText().endsWith(":")) {
                            // Chama o método para obter a descrição a partir do DAO e ID
                            String descricao = obterDescricaoComDao(coluna, resultado);
                            label.setText(descricao);
                        }
                    }
                }
            });
            stagePesquisa.setOnHidden(we -> stagePesquisa = null);

            stagePesquisa.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        } else {
        	stagePesquisa.toFront();
		}
    }

    private String obterDescricaoComDao(String coluna, String id) {
        DAO<?> daoPesquisa = obterDaoParaColuna(coluna);
        if (daoPesquisa == null || id == null || id.isBlank()) {
            return "";
        }

        try {
            Object entidade = daoPesquisa.getById(Integer.parseInt(id));
            if (entidade != null) {
                var metodoGetNome = entidade.getClass().getMethod("getNome");
                Object valorNome = metodoGetNome.invoke(entidade);
                return valorNome != null ? valorNome.toString() : "";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "";
    }
    private <T> DAO<T> obterDaoParaColuna(String coluna) {
        return (DAO<T>) switch (coluna.toLowerCase()) {
            case "id_setor" -> new TblSetoresDAOImpl();
            case "id_usuario" -> new TblUsuariosDAOImpl();
            case "id_nivel" -> new TblNiveisDAOImpl();
            case "id_situacao" -> new TblSituacaoDAOImpl();
            case "id_tag" -> new TblTagsDAOImpl();
            case "id_unimedida" -> new TblUniMedidasDAOImpl();
            case "id_formatocontrato" -> new TblFormatoContratoDAOImpl();
            case "id_tipocliente" -> new TblTipoClienteDAOImpl();
            case "id_escopo" -> new TblIndiceEscopoDAOImpl();
            default -> null;
        };
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