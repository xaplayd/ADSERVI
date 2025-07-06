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
import models.Nivel;
import models.Tabela;

public class TblNiveisController {
	private static Integer idTabela = 3;
	
	// Atualiza o nome da tabela de usuarios conforme o dicionário de tabelas, por
	// padrão, tabela niveis é a 3
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

	// Retorna lista com todos os niveis do DB
	public static List<Nivel> updateListaNivel() {
		String tbl = TblNiveisController.updateNomeTabela();
		Nivel tempNivel = null;
		List<Nivel> listaDeNivel = new ArrayList<Nivel>();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idnivel");
				String nome = rs.getString("nomenivel");
				tempNivel = new Nivel(id, nome);
				listaDeNivel.add(tempNivel);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaDeNivel;
	}

	// Insere um nivel na tabela
	public static Nivel insereNivelNaLista(String tempNome) {
		Nivel tempNivel = new Nivel();
		String tbl = TblNiveisController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO " + tbl + "(nomenivel) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tempNome);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			Integer id = 0;

			if (rs.next()) {
				id = Integer.parseInt(rs.getString(1));
			}
			tempNivel.setCodigo(id);
			tempNivel.setNome(tempNome);
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempNivel;
	}

	// Edita nivel na tabela
	public static void editaNivelNaLista(String id, String nome) {
		String tbl = TblNiveisController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE " + tbl + " SET nomenivel = (?) WHERE idnivel = (?)",
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

	// Deleta nivel na tabela
	public static String deletaNivelNaLista(String id) {
		String tbl = TblNiveisController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM " + tbl + " WHERE idnivel = (?)",
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
	public static ObservableList<Nivel> pesquisaDeNivel() {
		ObservableList<Nivel> observableList = FXCollections.observableArrayList();
		String tbl = TblNiveisController.updateNomeTabela();
		Nivel tempNivel = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idnivel");
				String nome = rs.getString("nomenivel");
				tempNivel = new Nivel(id, nome);
				observableList.add(tempNivel);
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
	public static TableView estruturaTblDeNivel() {

		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		String tbl = TblNiveisController.updateNomeTabela();
		Nivel tempNivel = null;
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
		String tbl = TblNiveisController.updateNomeTabela();

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
