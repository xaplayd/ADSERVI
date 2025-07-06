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
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;
import models.Setor;
import models.Usuario;

public class TblUsuariosController {

	// Atualiza o nome da tabela de setores conforme o dicionário de tabelas, por
	// padrão, tabela setores é a 2
	public static String updateNomeTabela() {
		Integer idTabela = 1;
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
	public static List<Usuario> updateListaUsuarios() {
		String tbl = TblUsuariosController.updateNomeTabela();
		Usuario tempUser = null;
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("emailgerente");
				Integer idsetor = rs.getInt("idsetor");
				Integer situacao = rs.getInt("situacao");
				tempUser = new Usuario(id, nome, login, senha, nivel, email, idemailgerente, idsetor, situacao);
				listaDeUsuarios.add(tempUser);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaDeUsuarios;
	}

	// insere um usuario na tabela
	public static Usuario insereUsuarioNaLista(String tempNome, String tempLogin, String tempSenha, Integer tempNivel, String tempEmail, String tempEmailGerente, Integer tempiIdsetor, Integer tempSituacao) {
		Usuario tempUsuario = new Usuario();
		String tbl = TblUsuariosController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("INSERT INTO " + tbl + "(nome, login, senha, nivel, email, emailgerente, idsetor, situacao) VALUES (?,?,?,?,?,?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, tempNome);
			stmt.setString(2, tempLogin);
			stmt.setString(3, tempSenha);
			stmt.setInt(4, tempNivel);
			stmt.setString(5, tempEmail);
			stmt.setString(6, tempEmailGerente);
			stmt.setInt(7, tempiIdsetor);
			stmt.setInt(8, tempSituacao);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			Integer id = 0;

			if (rs.next()) {
				id = Integer.parseInt(rs.getString(1));
			}
			tempUsuario.setCodigo(id);
			tempUsuario.setNome(tempNome);
			tempUsuario.setLogin(tempLogin);
			tempUsuario.setPermissoes(tempNivel);
			tempUsuario.setEmail(tempEmail);
			tempUsuario.setEmailGerencia(tempEmailGerente);
			tempUsuario.setSetor(tempiIdsetor);
			tempUsuario.setSituacao(tempSituacao);
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempUsuario;
	}

	// edita usuario na tabela
	public static void editaUsuarioNaLista(Integer idusuario, String nome, String login, String senha, Integer nivel, String email, String emailGerente, Integer idsetor, Integer situacao) {
		String tbl = TblUsuariosController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("UPDATE " + tbl + " SET nome = (?), login = (?), senha = (?), nivel = (?), email = (?), emailgerente = (?), idsetor = (?), situacao = (?) WHERE idusuario = (?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, nome);
			stmt.setString(2, login);
			stmt.setString(3, senha);
			stmt.setInt(4, nivel);
			stmt.setString(5, email);
			stmt.setString(6, emailGerente);
			stmt.setInt(7, idsetor);
			stmt.setInt(8, situacao);
			stmt.setInt(9, idusuario);
			stmt.execute();
			ResultSet rs = stmt.getGeneratedKeys();
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
	}

	// deleta usuario na tabela
	public static String deletaUsuarioNaLista(String id) {
		String tbl = TblUsuariosController.updateNomeTabela();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			PreparedStatement stmt = con.prepareStatement("DELETE FROM " + tbl + " WHERE idusuario = (?)",
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
	public static ObservableList<Usuario> pesquisaDeUsuarios() {
		ObservableList<Usuario> observableList = FXCollections.observableArrayList();
		String tbl = TblUsuariosController.updateNomeTabela();
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				Integer id = rs.getInt("idusuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer permissoes = rs.getInt("nivel");
				String email = rs.getString("email");
				String emailGerencia = rs.getString("emailgerente");
				Integer setor = rs.getInt("idsetor");
				Integer situacao = rs.getInt("situacao");
				tempUsuario = new Usuario(id, nome, login, senha, permissoes, email, emailGerencia, setor, situacao);
				observableList.add(tempUsuario);
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
	public static TableView estruturaTblDeUsuarios() {

		ObservableList<ObservableList> data = FXCollections.observableArrayList();
		String tbl = TblUsuariosController.updateNomeTabela();
		Usuario tempUsuario = null;
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
		String tbl = TblUsuariosController.updateNomeTabela();

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
