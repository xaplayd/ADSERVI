package dados.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Nivel;
import models.Setor;
import models.Situacao;
import models.Tabela;
import models.Usuario;

public abstract class TblController {
	
	public static String updateNomeTabela(Integer idTbl) {
		String nomeTabela = "";
		List<Tabela> tempListTab = DicTabelasController.updateListaTabela();
		for (Tabela x : tempListTab) {
			if (x.getCodigo() == idTbl) {
				nomeTabela = x.getNome();
			}
		}
		return nomeTabela.toString();
	}
	
	public static <T> List<T> updateListaSituacao(Integer idTbl, Class<T> tipoClasse) {
		String tbl = TblController.updateNomeTabela(idTbl);
		List<T> listaT = new ArrayList<>();
		T obj = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + tbl);
			while (rs.next()) {
				if (tipoClasse == Situacao.class) {
	                Integer id = rs.getInt("idsituacao");
	                String nome = rs.getString("nomesituacao");
	                obj = tipoClasse.cast(new Situacao(id, nome));
	                listaT.add(obj);
	            } else if(tipoClasse == Usuario.class) {
	            	Integer id = rs.getInt("idusuario");
					String nome = rs.getString("nome");
					String login = rs.getString("login");
					String senha = rs.getString("senha");
					Integer nivel = rs.getInt("nivel");
					String email = rs.getString("email");
					String idemailgerente = rs.getString("emailgerente");
					Integer idsetor = rs.getInt("idsetor");
					Integer situacao = rs.getInt("situacao");
					obj = tipoClasse.cast(new Usuario(id, nome, login, senha, nivel, email, idemailgerente, idsetor, situacao));
					listaT.add(obj);
	            } else if(tipoClasse == Nivel.class) {
	            	Integer id = rs.getInt("idnivel");
					String nome = rs.getString("nomenivel");
					obj = tipoClasse.cast(new Nivel(id, nome));
					listaT.add(obj);
	            } else if(tipoClasse == Setor.class) {
	            	Integer id = rs.getInt("idsetor");
					String nome = rs.getString("nomesetor");
					obj = tipoClasse.cast(new Setor(id, nome));
					listaT.add(obj);
	            }
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaT;
	}
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
	
	public static Object insereNaLista(Integer idTbl, Object obj) {
		if(obj == Setor.class) {
			
		}
		return obj;
	}
}
