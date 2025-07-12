package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dados.controller.ConnectionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import models.Tabela;

public class DicTabelasDAOImpl implements DicTabelasDAO {

	private String dicTabela = "tbls";

	@Override
	public Tabela getById(Integer id){
		Tabela tempTabela = null;
		String tbl = "tbls";
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String sql = "SELECT idtbl, nometbl FROM " + tbl + " WHERE idtbl = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer tempId = rs.getInt("idtbl");
				String tempName = rs.getString("nometbl");
				tempTabela = new Tabela(tempId, tempName);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempTabela;
	}

	@Override
	public List<Tabela> getAll(){
		Tabela tempTabela = null;
		String tbl = "tbls";
		Connection con = ConnectionController.getConexaoMySQL();
		List<Tabela> listaTabelas = new ArrayList<Tabela>();
		try {
			String sql = "SELECT * FROM " + tbl;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer tempId = rs.getInt("idtbl");
				String tempName = rs.getString("nometbl");
				tempTabela = new Tabela(tempId, tempName);
				listaTabelas.add(tempTabela);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaTabelas;
	}

	@Override
	public Integer insert(Tabela tabela){
		Connection con = ConnectionController.getConexaoMySQL();
		String tbl = "tbls";
		String sql = "INSERT INTO " + tbl + " (nometbl) VALUES (?)";
		Integer result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tabela.getNome());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o novo ID
	}

	@Override
	public Integer updateById(Tabela tabela){
		Connection con = ConnectionController.getConexaoMySQL();
		String tbl = "tbls";
		String sql = "UPDATE " + tbl + " SET nometbl = (?) WHERE idtbl = (?)";
		Integer result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tabela.getNome());
			ps.setInt(2, tabela.getCodigo());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o ID
	}

	@Override
	public Integer deleteById(Tabela tabela){
		Connection con = ConnectionController.getConexaoMySQL();
		String tbl = "tbls";
		String sql = "DELETE from " + tbl + " WHERE idtbl = (?)";
		Integer result = 0;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, tabela.getCodigo());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o ID
	}
	
	@Override
	public TableView estruturaTbl() throws SQLException {
		return null;
	}

	@Override
	public ObservableList<String> obterNomesDasColunas(){
		ObservableList<String> nomesDasColunas = FXCollections.observableArrayList();
		String tbl = "tbls";

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

	@Override
	public String getTblName(){
		return "tbls";
	}

	@Override
	public Tabela getByName(String name){
		Tabela tempTabela = null;
		String tbl = "tbls";
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String sql = "SELECT idtbl, nometbl FROM " + tbl + " WHERE nometbl = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer tempId = rs.getInt("idtbl");
				String tempName = rs.getString("nometbl");
				tempTabela = new Tabela(tempId, tempName);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempTabela;
	}
}
