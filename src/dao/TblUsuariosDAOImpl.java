package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dados.controller.ConnectionController;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.Usuario;

public class TblUsuariosDAOImpl implements TblUsuariosDAO {

	private Integer idTabela = 1;

	@Override
	public Usuario getById(Integer id){
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT idusuario, nome, login, senha, nivel, email, emailgerente, idsetor, idsituacao FROM " + tbl + " WHERE idusuario = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer iduser = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("emailgerente");
				Integer idsetor = rs.getInt("idsetor");
				Integer situacao = rs.getInt("idsituacao");
				tempUsuario = new Usuario(iduser, nome, login, senha, nivel, email, idemailgerente, idsetor, situacao);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempUsuario;
	}

	public Integer getNivelByLogin(String login){
		Integer nivel = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT login, nivel FROM " + tbl + " WHERE login = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nivel = rs.getInt("nivel");
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return nivel;
	}
	
	@Override
	public List<Usuario> getAll(){
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			String tbl = getTblName();
			String sql = "SELECT * FROM " + tbl;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer iduser = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("emailgerente");
				Integer idsetor = rs.getInt("idsetor");
				Integer situacao = rs.getInt("idsituacao");
				tempUsuario = new Usuario(iduser, nome, login, senha, nivel, email, idemailgerente, idsetor, situacao);
				listaUsuarios.add(tempUsuario);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaUsuarios;
	}

	@Override
	public Integer insert(Usuario usuario){
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "INSERT INTO " + tbl + " (nome, login, senha, nivel, email, emailgerente, idsetor, situacao) VALUES (?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getPermissoes());
			ps.setString(5, usuario.getEmail());
			ps.setString(6, usuario.getEmailGerencia());
			ps.setInt(7, usuario.getSetor());
			ps.setInt(8, usuario.getSituacao());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o novo ID
	}

	@Override
	public Integer updateById(Usuario usuario){
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "UPDATE " + tbl + " SET nome = (?), login = (?), senha = (?), nivel = (?), email = (?), emailgerente = (?), idsetor = (?), situacao = (?) WHERE idusuario = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, usuario.getNome());
			ps.setString(2, usuario.getLogin());
			ps.setString(3, usuario.getSenha());
			ps.setInt(4, usuario.getPermissoes());
			ps.setString(5, usuario.getEmail());
			ps.setString(6, usuario.getEmailGerencia());
			ps.setInt(7, usuario.getSetor());
			ps.setInt(8, usuario.getSituacao());
			ps.setInt(9, usuario.getCodigo());
			
			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; //retorna a quantidade de linhas afetadas, nao o ID
	}

	@Override
	public Integer deleteById(Usuario usuario){
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "DELETE from " + tbl + " WHERE idusuario = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, usuario.getCodigo());
			
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
	public ObservableList<String> obterNomesDasColunas() throws SQLException {
		ObservableList<String> nomesDasColunas = FXCollections.observableArrayList();

		try (Connection con = ConnectionController.getConexaoMySQL();Statement stmt = con.createStatement();) {
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
	public Usuario getByName(String name){
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT idusuario, nome, login, senha, nivel, email, emailgerente, idsetor, idsituacao FROM " + tbl + " WHERE nome = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer iduser = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("emailgerente");
				Integer idsetor = rs.getInt("idsetor");
				Integer situacao = rs.getInt("idsituacao");
				tempUsuario = new Usuario(iduser, nome, login, senha, nivel, email, idemailgerente, idsetor, situacao);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempUsuario;
	}
}
