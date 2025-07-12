package dados.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.Situacao;
import models.Tabela;

public class TblSituacaoController {
	
	private static Integer idTabela = 4;

	// Atualiza o nome da tabela de situacoes conforme o dicionário de tabelas, por
	// padrão, tabela situacoes é a 4
	public static String updateNomeTabela() {
		String nomeTabela = "";
		List<Tabela> tempListTab = DicTabelasController.updateListaTabela();
		for (Tabela x : tempListTab) {
			if (x.getCodigo() == idTabela) {
				nomeTabela = x.getNome();
			}
		}
		return nomeTabela.toString();
	}

	// Retorna lista com todos as situacoes do DB
	public static List<Situacao> updateListaSituacao() {
		String tbl = TblSituacaoController.updateNomeTabela();
		Situacao tempSituacao = null;
		List<Situacao> listaDeSituacao = new ArrayList<Situacao>();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idsituacao");
				String nome = rs.getString("nomesituacao");
				tempSituacao = new Situacao(id, nome);
				listaDeSituacao.add(tempSituacao);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaDeSituacao;
	}

	// Insere uma situacao na tabela
	public static Situacao insereSituacaoNaLista(String tempNome) {
		Situacao tempSituacao = new Situacao();
		String tbl = TblSituacaoController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO " + tbl + "(nomesituacao) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tempNome);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			Integer id = 0;

			if (rs.next()) {
				id = Integer.parseInt(rs.getString(1));
			}
			tempSituacao.setCodigo(id);
			tempSituacao.setNome(tempNome);
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempSituacao;
	}

	// Edita situacao na tabela
	public static void editaSituacaoNaLista(String id, String nome) {
		String tbl = TblSituacaoController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE " + tbl + " SET nomesituacao = (?) WHERE idsituacao = (?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nome);
			stmt.setString(2, id);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	// Deleta situacao na tabela
	public static String deletaSituacaoNaLista(String id) {
		String tbl = TblSituacaoController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM " + tbl + " WHERE idsituacao = (?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, id);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.close();
			stmt.close();
			con.close();
			return "sucesso";
		} catch (SQLException e) {
			e.getMessage();
		}
		return "sem sucesso";
	}

	// Observable list para pesquisa
	public static ObservableList<Situacao> pesquisaDeSituacao() {
		ObservableList<Situacao> observableList = FXCollections.observableArrayList();
		String tbl = TblSituacaoController.updateNomeTabela();
		Situacao tempSituacao = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idsituacao");
				String nome = rs.getString("nomesituacao");
				tempSituacao = new Situacao(id, nome);
				observableList.add(tempSituacao);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return observableList;
	}

	// Retorna colunas e quantidade
	public static TableView estruturaTblDeSituacao() {

		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		String tbl = TblSituacaoController.updateNomeTabela();
		Situacao tempSituacao = null;
		Connection con = ConnectionController.getConexaoMySQL();
		TableView tableview = new TableView<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);

			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));

				col.setCellValueFactory(
						new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {

							public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
								return new SimpleStringProperty(param.getValue().get(j).toString());
							}
						});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);
			}
			// FINALLY ADDED TO TableView
			tableview.setItems(data);
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}
		return tableview;
	}

	public static ObservableList<String> obterNomesDasColunas() {
		ObservableList<String> nomesDasColunas = FXCollections.observableArrayList();
		String tbl = TblSituacaoController.updateNomeTabela();

		try (Connection con = ConnectionController.getConexaoMySQL();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);) {
			int colCount = rs.getMetaData().getColumnCount();
			for (int i = 1; i <= colCount; i++) {
				nomesDasColunas.add(rs.getMetaData().getColumnName(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Erro ao obter nomes das colunas.");
		}

		return nomesDasColunas;
	}

}
