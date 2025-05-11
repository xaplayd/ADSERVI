package dados.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import config.TabelaConfig;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.Setor;

public class TblSetoresController {

	// Atualiza o nome da tabela de usuarios conforme o dicionário de tabelas, por
	// padrão, tabela usuário é a 1
	public static String updateNomeTabela() {
		Integer idTabela = 2;
		String nomeTabela = "";
		List<TabelaConfig> tempListTab = DicTabelasController.updateListaTabela();
		for (TabelaConfig x : tempListTab) {
			if (x.getNomeid() == idTabela) {
				nomeTabela = x.getNomeTabela();
			}
		}
		return nomeTabela.toString();
	}

	// Retorna lista com todos os usuarios do DB
	public static List<Setor> updateListaSetores() {
		String tbl = TblSetoresController.updateNomeTabela();
		Setor tempSetor = null;
		List<Setor> listaDeSetores = new ArrayList<Setor>();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idsetor");
				String nome = rs.getString("nomesetor");
				tempSetor = new Setor(id, nome);
				listaDeSetores.add(tempSetor);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaDeSetores;
	}

	// Insere um setor na tabela
	public static Setor insereSetorNaLista(String tempNome) {
		Setor tempSetor = new Setor();
		String tbl = TblSetoresController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO " + tbl + "(nomesetor) VALUES (?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tempNome);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			Integer id = 0;

			if (rs.next()) {
				id = Integer.parseInt(rs.getString(1));
			}
			tempSetor.setCodigo(id);
			tempSetor.setNome(tempNome);
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempSetor;
	}

	// Edita setor na tabela
	public static void editaSetorNaLista(String id, String nome) {
		String tbl = TblSetoresController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE " + tbl + " SET nomesetor = (?) WHERE idsetor = (?)",
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

	// Deleta setor na tabela
	public static String deletaSetorNaLista(String id) {
		String tbl = TblSetoresController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM " + tbl + " WHERE idsetor = (?)",
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
	public static ObservableList<Setor> pesquisaDeSetores() {
		ObservableList<Setor> observableList = FXCollections.observableArrayList();
		String tbl = TblSetoresController.updateNomeTabela();
		Setor tempSetor = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idsetor");
				String nome = rs.getString("nomesetor");
				tempSetor = new Setor(id, nome);
				observableList.add(tempSetor);
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
	public static TableView estruturaTblDeSetores() {

		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		String tbl = TblSetoresController.updateNomeTabela();
		Setor tempSetor = null;
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
	    String tbl = TblSetoresController.updateNomeTabela();
	    
	    try (
	        Connection con = ConnectionController.getConexaoMySQL();
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
	    ) {
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
