package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connection.controller.ConnectionController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import models.Tabela;
import models.TabelaColuna;

public class DicTabelasDAOImpl implements DicTabelasDAO {

	private String dicTabela = "tbls";
	
	private String chavePrimaria = "id_tbl";

	@Override
	public Tabela getById(Integer id){
		Tabela tempTabela = null;
		String tbl = "tbls";
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String sql = "SELECT id_tbl, nome_tbl FROM " + tbl + " WHERE id_tbl = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer tempId = rs.getInt("id_tbl");
				String tempName = rs.getString("nome_tbl");
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
				Integer tempId = rs.getInt("id_tbl");
				String tempName = rs.getString("nome_tbl");
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
		String sql = "INSERT INTO " + tbl + " (nome_tbl) VALUES (?)";
		Integer idGerado = null;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tabela.getNome());
			
			ps.executeUpdate();
			
			  // Pega o ID gerado
	        ResultSet rs = ps.getGeneratedKeys();
	        if (rs.next()) {
	            idGerado = rs.getInt(1); // ou rs.getObject(1) se quiser genérico
	        }

	        rs.close();
	        ps.close();
	        con.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return idGerado;
	}

	@Override
	public Integer updateById(Tabela tabela){
		Connection con = ConnectionController.getConexaoMySQL();
		String tbl = "tbls";
		String sql = "UPDATE " + tbl + " SET nome_tbl = (?) WHERE id_tbl = (?)";
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
		String sql = "DELETE from " + tbl + " WHERE id_tbl = (?)";
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
			String sql = "SELECT id_tbl, nome_tbl FROM " + tbl + " WHERE nome_tbl = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer tempId = rs.getInt("id_tbl");
				String tempName = rs.getString("nome_tbl");
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
	public List getColunasDaTabela(){
		List<TabelaColuna> colunas = new ArrayList<>();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String sql = "SELECT * FROM " + getTblName() + " LIMIT 0";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				String nome = meta.getColumnName(i);
				int tipo = meta.getColumnType(i);
				colunas.add(new TabelaColuna(nome, null, tipo));
			}
		}catch (SQLException exception) {
			exception.getMessage();
		}
		return colunas;
	}

	@Override
	public String getChavePrimaria(){
		return this.chavePrimaria;
	}

	@Override
	public List<TabelaColuna> mapperEntityToView(Integer id, List<TabelaColuna> estrutura) {
		Tabela tempTab = getById(id);
		for (TabelaColuna x : estrutura) {
			if (x.getNome().compareTo("id_tbl") == 0) {
				x.setValor(tempTab.getCodigo());
			}else if (x.getNome().compareTo("nome_tbl") == 0) {
				x.setValor(tempTab.getNome());
			}
		}
		return estrutura;
	
	}

	@Override
	public Tabela mapperViewToEntity(List<TabelaColuna> estrutura) {
		Tabela tempTabela = new Tabela();
		for (TabelaColuna x : estrutura) {
			 if(x.getNome().compareTo("id_tbl") == 0) {
		            if (x.getValor() != null && !x.getValor().toString().isBlank()) {
		            	tempTabela.setCodigo(Integer.parseInt(x.getValor().toString()));
		            }
			}else if (x.getNome().compareTo("nome_tbl") == 0) {
				tempTabela.setNome(x.getValor().toString());
			}
		}
		return tempTabela;
	
	}

}
