package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.controller.ConnectionController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.Situacao;

public class TblSituacaoDAOImpl implements TblSituacaoDAO {

	private Integer idTabela = 4;;

	@Override
	public Situacao getById(Integer id){
		Situacao tempSituacao = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT idsituacao, nomesituacao FROM " + tbl + " WHERE idsituacao = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer tempId = rs.getInt("idsituacao");
				String tempName = rs.getString("nomesituacao");
				tempSituacao = new Situacao(tempId, tempName);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempSituacao;
	}

	@Override
	public List<Situacao> getAll(){
		Situacao tempSituacao = null;
		Connection con = ConnectionController.getConexaoMySQL();
		List<Situacao> listaSituacoes = new ArrayList<Situacao>();
		try {
			String tbl = getTblName();
			String sql = "SELECT * FROM " + tbl;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer tempId = rs.getInt("idsituacao");
				String tempName = rs.getString("nomesituacao");
				tempSituacao = new Situacao(tempId, tempName);
				listaSituacoes.add(tempSituacao);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaSituacoes;
	}

	@Override
	public Integer insert(Situacao situacao){
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "INSERT INTO " + tbl + " (nomesituacao) VALUES (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, situacao.getNome());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o novo ID
	}

	@Override
	public Integer updateById(Situacao situacao){
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "UPDATE " + tbl + " SET nomesituacao = (?) WHERE idsituacao = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, situacao.getNome());
			ps.setInt(2, situacao.getCodigo());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o ID
	}

	@Override
	public Integer deleteById(Situacao situacao){
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "DELETE from " + tbl + " WHERE idsituacao = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, situacao.getCodigo());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o ID
	}
	@Override
	public TableView estruturaTbl(){
		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		Connection con = ConnectionController.getConexaoMySQL();
		TableView tableview = new TableView<>();
		try {
			String tbl = getTblName();
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

	@Override
	public ObservableList<String> obterNomesDasColunas(){
		ObservableList<String> nomesDasColunas = FXCollections.observableArrayList();

		try (Connection con = ConnectionController.getConexaoMySQL(); Statement stmt = con.createStatement();) {
			String tbl = getTblName();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
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

	@Override
	public String getTblName(){
		Connection con = ConnectionController.getConexaoMySQL();
		String nameCurrentTbl = "";
		try {
			String tbls = "";
			DicTabelasDAO tblsdao = new DicTabelasDAOImpl();
			tbls = tblsdao.getTblName();
			String sql = "SELECT nometbl FROM " + tbls + " WHERE idtbl = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, this.idTabela);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nameCurrentTbl = rs.getString("nometbl");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return nameCurrentTbl;
	}

	@Override
	public Situacao getByName(String name) {
		Situacao tempSituacao = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT idsituacao, nomesituacao FROM " + tbl + " WHERE nomesituacao = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer tempId = rs.getInt("idsituacao");
				String tempName = rs.getString("nomesituacao");
				tempSituacao = new Situacao(tempId, tempName);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempSituacao;
	}
}
