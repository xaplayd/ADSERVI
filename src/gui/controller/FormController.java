package gui.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import dao.DAO;
import dao.TblNiveisDAOImpl;
import dao.TblSetoresDAOImpl;
import dao.TblSituacaoDAOImpl;
import dao.TblUsuariosDAOImpl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.TabelaColuna;
import utils.TableColumnFormatter;

public class FormController implements Initializable {

	
	//  ------------ Estados para CRUD ------------  \\
	 
	private static final int ESTADO_NEUTRO = 0;
	private static final int ESTADO_NOVO = 1;
	private static final int ESTADO_EDICAO = 2;
	private int estadoAtual = ESTADO_NEUTRO;
    
	// --------------------------------------------- \\
	
	
	//  ------------- Estrutura base -------------  \\
	
	@FXML
	private VBox formContainer;

	@FXML
	private Button btnNovo, btnSalvar, btnExcluir, btnCancelar, btnEditar, btnFechar;

	private String tabela;
	private Integer idRegistro;
	private DAO<?> dao;
	
	private void adicionarIconesAosBotoes() {
		adicionarIconeComTexto(btnNovo, "/imgs/18x18/adicionar.png");
		adicionarIconeComTexto(btnSalvar, "/imgs/18x18/save--v1.png");
		adicionarIconeComTexto(btnExcluir, "/imgs/18x18/delete--v1.png");
		adicionarIconeComTexto(btnCancelar, "/imgs/18x18/erro.png");
		adicionarIconeComTexto(btnEditar, "/imgs/18x18/editar.png");
		adicionarIconeComTexto(btnFechar, "/imgs/18x18/sair.png");
	}

	private void adicionarIconeComTexto(Button botao, String caminhoImagem) {
		ImageView icone = new ImageView(new Image(getClass().getResourceAsStream(caminhoImagem)));
		icone.setFitWidth(16);
		icone.setFitHeight(16);
		botao.setGraphic(icone);
		botao.setContentDisplay(ContentDisplay.LEFT);
	}

	private ImageView imagem(String caminho) {
		ImageView iv = new ImageView(new Image(getClass().getResourceAsStream(caminho)));
		iv.setFitWidth(16);
		iv.setFitHeight(16);
		return iv;
	}
	
	// --------------------------------------------- \\
	
	//  ------------- Ações estrutura base -------------  \\
	
	private void onNovo() {
		formContainer.getChildren().clear();
		try {
			List<TabelaColuna> dados = dao.getColunasDaTabela();
			gerarFormularioDinamico(dados);
			idRegistro = null;
			estadoAtual = ESTADO_NOVO;
			habilitarCamposDeEdicao(true);
			atualizarEstadoDosBotoes();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void onSalvar() {
		System.out.println("Salvar acionado. Estado atual: " + estadoAtual);
		estadoAtual = ESTADO_NEUTRO;
		habilitarCamposDeEdicao(false);
		atualizarEstadoDosBotoes();
	}

	private void onExcluir() {
		System.out.println("Excluir acionado");
		estadoAtual = ESTADO_NEUTRO;
		atualizarEstadoDosBotoes();
	}

	private void onCancelar() {
		estadoAtual = ESTADO_NEUTRO;
		initData(tabela, idRegistro, dao);
	}

	private void onEditar() {
		estadoAtual = ESTADO_EDICAO;
		habilitarCamposDeEdicao(true);
		atualizarEstadoDosBotoes();
	}

	private void onSair() {
		System.out.println("Sair acionado");
	}
	
	private void atualizarEstadoDosBotoes() {
		switch (estadoAtual) {
			case ESTADO_NEUTRO -> {
				btnNovo.setDisable(false);
				btnSalvar.setDisable(true);
				btnEditar.setDisable(idRegistro == null);
				btnExcluir.setDisable(idRegistro == null);
				btnCancelar.setDisable(true);
			}
			case ESTADO_NOVO, ESTADO_EDICAO -> {
				btnNovo.setDisable(true);
				btnSalvar.setDisable(false);
				btnEditar.setDisable(true);
				btnExcluir.setDisable(true);
				btnCancelar.setDisable(false);
			}
		}
	}
	
	private void habilitarCamposDeEdicao(boolean habilitar) {
		for (javafx.scene.Node node : formContainer.getChildren()) {
			if (node instanceof HBox hbox) {
				for (javafx.scene.Node subNode : hbox.getChildren()) {
					if (subNode instanceof Control control) {
						control.setDisable(!habilitar);
					}
				}
			}
		}
	}
	
	// --------------------------------------------- \\
	
	//  ------------- Estrutura variável -------------  \\
	
	private void gerarFormularioDinamico(List<TabelaColuna> colunas) {
		
		//referencia ao campo com chave primaria da tabela
		boolean primeiroCampoComId = true;

		for (TabelaColuna col : colunas) {
			String coluna = col.getNome();
			Object valor = col.getValor();
			int tipoSQL = col.getTipoSQL();

			String nomeFormatado = TableColumnFormatter.formatarNomeColunaAutomaticamente(coluna);
			Label label = new Label(nomeFormatado + ":");
			label.setMinWidth(150);

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
			if (coluna.toLowerCase().startsWith("id_")) {
				TextField tf = (TextField) campo;
				tf.setPrefWidth(80);

				Button btnPesquisa = new Button();
				btnPesquisa.setGraphic(imagem("/imgs/18x18/lupa.png"));

				Label labelDescricao = new Label();
				labelDescricao.setMinWidth(200);
				labelDescricao.setText(obterDescricao(coluna, tf.getText()));

				btnPesquisa.setOnAction(e -> abrirPesquisaParaCampo(coluna, tf, labelDescricao));

				if (primeiroCampoComId) {
					tf.setDisable(false);
					btnPesquisa.setDisable(false);
					labelDescricao.setDisable(false);
					primeiroCampoComId = false;
				} else {
					tf.setDisable(true);
					btnPesquisa.setDisable(true);
					labelDescricao.setDisable(true);
				}
				linha = new HBox(10, label, tf, btnPesquisa, labelDescricao);
			} else {
				campo.setDisable(true);
				linha = new HBox(10, label, campo);
			}

			formContainer.getChildren().add(linha);
		}

		atualizarEstadoDosBotoes();
	}
	
	

	private void abrirPesquisaParaCampo(String coluna, TextField campoDestino, Label labelDescricao) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Pesquisa.fxml"));
			Object controller;

			switch (coluna) {
				case "id_setor" -> controller = new PesquisaSetorController();
				case "id_usuario" -> controller = new PesquisaUsuarioController();
				case "id_nivel" -> controller = new PesquisaNivelController();
				case "id_situacao" -> controller = new PesquisaSituacaoController();
				default -> throw new IllegalArgumentException("Campo de pesquisa não suportado: " + coluna);
			}

			loader.setController(controller);
			Parent parent = loader.load();

			Stage stage = new Stage();
			stage.setTitle("Pesquisa - " + TableColumnFormatter.formatarNomeColunaAutomaticamente(coluna));
			stage.setScene(new Scene(parent));
			stage.setResizable(true);

			stage.setOnHidden(ev -> {
				String valorSelecionado = switch (coluna) {
					case "id_setor" -> ((PesquisaSetorController) controller).getSetorSelecionado();
					case "id_usuario" -> ((PesquisaUsuarioController) controller).getUsuarioSelecionado();
					case "id_nivel" -> ((PesquisaNivelController) controller).getNivelSelecionado();
					case "id_situacao" -> ((PesquisaSituacaoController) controller).getSituacaoSelecionado();
					default -> null;
				};

				if (valorSelecionado != null) {
					campoDestino.setText(valorSelecionado);
					labelDescricao.setText(obterDescricao(coluna, valorSelecionado));
				}
			});
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String obterDescricao(String coluna, String id) {
		try {
			LookupConfig config = lookupMap.get(coluna);
			if (config != null) {
				Object obj = config.dao.getById(Integer.parseInt(id));
				if (obj != null) {
					// Retorna toString() do objeto como descrição por padrão
					return obj.toString();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public Map<String, Object> capturarValoresDoFormulario() {
		Map<String, Object> valores = new LinkedHashMap<>();
		for (javafx.scene.Node node : formContainer.getChildren()) {
			if (node instanceof HBox hbox) {
				for (javafx.scene.Node subNode : hbox.getChildren()) {
					if (subNode instanceof TextInputControl input) {
						String id = input.getId().replace("campo_", "");
						valores.put(id, input.getText());
					} else if (subNode instanceof DatePicker dp) {
						String id = dp.getId().replace("campo_", "");
						valores.put(id, dp.getValue());
					} else if (subNode instanceof CheckBox cb) {
						String id = cb.getId().replace("campo_", "");
						valores.put(id, cb.isSelected());
					}
				}
			}
		}
		return valores;
	}
	
	// ---------- inicializador --------------- \\
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Inicialização será feita no initData
	}
	
	private static final Map<String, LookupConfig> lookupMap = new HashMap<>();

	static {
		lookupMap.put("id_usuario", new LookupConfig(new TblUsuariosDAOImpl()));
		lookupMap.put("id_nivel", new LookupConfig(new TblNiveisDAOImpl()));
		lookupMap.put("id_setor", new LookupConfig(new TblSetoresDAOImpl()));
		lookupMap.put("id_situacao", new LookupConfig(new TblSituacaoDAOImpl()));
	}

	public void initData(String tabela, Integer idRegistro, DAO<?> dao) {
		this.tabela = tabela;
		this.idRegistro = idRegistro;
		this.dao = dao;

		formContainer.getChildren().clear();

		try {
			List<TabelaColuna> dados = dao.getColunasDaTabela();

			gerarFormularioDinamico(dados);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		btnNovo.setOnAction(e -> onNovo());
		btnSalvar.setOnAction(e -> onSalvar());
		btnExcluir.setOnAction(e -> onExcluir());
		btnCancelar.setOnAction(e -> onCancelar());
		btnEditar.setOnAction(e -> onEditar());
		btnFechar.setOnAction(e -> onSair());

		adicionarIconesAosBotoes();
		atualizarEstadoDosBotoes();
	}

	
	// ------------------------------------------------- \\

	private static class LookupConfig {
		DAO<?> dao;

		LookupConfig(DAO<?> dao) {
			this.dao = dao;
		}
	}
}