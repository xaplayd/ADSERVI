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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;
import models.sis.Nivel;
import models.sis.Setor;
import models.sis.Situacao;
import models.sis.TabelaColuna;
import models.sis.Usuario;

public class TblUsuariosDAOImpl implements TblUsuariosDAO {

	private Integer idTabela = 1;

	private String chavePrimaria = "id_usuario";

	@Override
	public Usuario getById(Integer id) {
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT id_usuario, nome, login, senha, id_nivel, email, email_gerente, id_setor, id_situacao FROM "
					+ tbl + " WHERE id_usuario = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer iduser = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("id_nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("email_gerente");
				Integer idsetor = rs.getInt("id_setor");
				Integer situacao = rs.getInt("id_situacao");
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

	public Integer getNivelByLogin(String login) {
		Integer nivel = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT login, id_nivel FROM " + tbl + " WHERE login = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nivel = rs.getInt("id_nivel");
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
	public List<Usuario> getAll() {
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		List<Usuario> listaUsuarios = new ArrayList<Usuario>();
		try {
			String tbl = getTblName();
			String sql = "SELECT * FROM " + tbl;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer iduser = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("id_nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("email_gerente");
				Integer idsetor = rs.getInt("id_setor");
				Integer situacao = rs.getInt("id_situacao");
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
	public Integer insert(Usuario usuario) {
	    Connection con = ConnectionController.getConexaoMySQL();
	    Integer idGerado = null;
	    try {
	        String tbl = getTblName();
	        String sql = "INSERT INTO " + tbl
	            + " (nome, login, senha, id_nivel, email, email_gerente, id_setor, id_situacao) VALUES (?,?,?,?,?,?,?,?)";

	        // Solicita retorno das chaves geradas
	        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, usuario.getNome());
	        ps.setString(2, usuario.getLogin());
	        ps.setString(3, usuario.getSenha());
	        ps.setInt(4, usuario.getPermissoes());
	        ps.setString(5, usuario.getEmail());
	        ps.setString(6, usuario.getEmailGerencia());
	        ps.setInt(7, usuario.getSetor());
	        ps.setInt(8, usuario.getSituacao());

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
	public Integer updateById(Usuario usuario) {
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "UPDATE " + tbl
					+ " SET nome = (?), login = (?), senha = (?), id_nivel = (?), email = (?), email_gerente = (?), id_setor = (?), id_situacao = (?) WHERE id_usuario = (?)";
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
		return result; // retorna a quantidade de linhas afetadas, nao o ID
	}

	@Override
	public Integer deleteById(Usuario usuario) {
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "DELETE from " + tbl + " WHERE id_usuario = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, usuario.getCodigo());

			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; // retorna a quantidade de linhas afetadas, nao o ID
	}

	@Override
	public TableView estruturaTbl() {
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
	public ObservableList<String> obterNomesDasColunas() {
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
	public String getTblName() {
		Connection con = ConnectionController.getConexaoMySQL();
		String nameCurrentTbl = "";
		try {
			String tbls = "";
			DicTabelasDAO tblsdao = new DicTabelasDAOImpl();
			tbls = tblsdao.getTblName();
			String sql = "SELECT nome_tbl FROM " + tbls + " WHERE id_tbl = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, this.idTabela);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				nameCurrentTbl = rs.getString("nome_tbl");
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
	public Usuario getByName(String name) {
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT id_usuario, nome, login, senha, id_nivel, email, email_gerente, id_setor, id_situacao FROM "
					+ tbl + " WHERE nome = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer iduser = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("id_nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("email_gerente");
				Integer idsetor = rs.getInt("id_setor");
				Integer situacao = rs.getInt("id_situacao");
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

	public Usuario getByLogin(String tempLogin) {
		Usuario tempUsuario = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT id_usuario, nome, login, senha, id_nivel, email, email_gerente, id_setor, id_situacao FROM "
					+ tbl + " WHERE login = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, tempLogin);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer iduser = rs.getInt("id_usuario");
				String nome = rs.getString("nome");
				String login = rs.getString("login");
				String senha = rs.getString("senha");
				Integer nivel = rs.getInt("id_nivel");
				String email = rs.getString("email");
				String idemailgerente = rs.getString("email_gerente");
				Integer idsetor = rs.getInt("id_setor");
				Integer situacao = rs.getInt("id_situacao");
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

	public Nivel getNivelByUserId(Integer user) {
		Nivel nivel = null;
		try {
			TblNiveisDAO niveldao = new TblNiveisDAOImpl();
			Usuario tempUser = getById(user);
			Integer idNivel = tempUser.getPermissoes();
			nivel = niveldao.getById(idNivel);
		} catch (SQLException exception) {
			exception.getMessage();
		}
		return nivel;
	}

	public Situacao getSituacaoByUserId(Integer user) {
		Situacao situacao = null;
		try {
			TblSituacaoDAO situacaodao = new TblSituacaoDAOImpl();
			Usuario tempUser = getById(user);
			Integer idSituacao = tempUser.getSituacao();
			situacao = situacaodao.getById(idSituacao);
		} catch (SQLException exception) {
			exception.getMessage();
		}
		return situacao;
	}

	public Setor getSetorByUserId(Integer user) {
		Setor setor = null;
		try {
			TblSetoresDAO situacaodao = new TblSetoresDAOImpl();
			Usuario tempUser = getById(user);
			Integer idSetor = tempUser.getSituacao();
			setor = situacaodao.getById(idSetor);
		} catch (SQLException exception) {
			exception.getMessage();
		}
		return setor;
	}

	@Override
	public List getColunasDaTabela() {
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
		} catch (SQLException exception) {
			exception.getMessage();
		}
		return colunas;
	}

	@Override
	public String getChavePrimaria() {
		return this.chavePrimaria;
	}

	@Override
	public List<TabelaColuna> mapperEntityToView(Integer id, List<TabelaColuna> estrutura) {
		Usuario tempUser = getById(id);
		for (TabelaColuna x : estrutura) {
			if (x.getNome().compareTo("nome") == 0) {
				x.setValor(tempUser.getNome());
			} else if (x.getNome().compareTo("login") == 0) {
				x.setValor(tempUser.getLogin());
			} else if (x.getNome().compareTo("senha") == 0) {
				x.setValor(tempUser.getSenha());
			} else if (x.getNome().compareTo("id_nivel") == 0) {
				x.setValor(tempUser.getPermissoes());
			} else if (x.getNome().compareTo("email") == 0) {
				x.setValor(tempUser.getEmail());
			} else if (x.getNome().compareTo("email_gerente") == 0) {
				x.setValor(tempUser.getEmailGerencia());
			} else if (x.getNome().compareTo("id_setor") == 0) {
				x.setValor(tempUser.getSetor());
			} else if (x.getNome().compareTo("id_situacao") == 0) {
				x.setValor(tempUser.getSituacao());
			}
		}

		return estrutura;
	}

	@Override
	public Usuario mapperViewToEntity(List<TabelaColuna> estrutura){
		Usuario tempUser = new Usuario();
		for (TabelaColuna x : estrutura) {
			 if(x.getNome().compareTo("id_usuario") == 0) {
		            if (x.getValor() != null && !x.getValor().toString().isBlank()) {
		            	tempUser.setCodigo(Integer.parseInt(x.getValor().toString()));
		            }
			} else if (x.getNome().compareTo("nome") == 0) {
				tempUser.setNome(x.getValor().toString());
			} else if (x.getNome().compareTo("login") == 0) {
				tempUser.setLogin(x.getValor().toString());
			} else if (x.getNome().compareTo("senha") == 0) {
				tempUser.setSenha(x.getValor().toString());
			} else if (x.getNome().compareTo("id_nivel") == 0) {
				tempUser.setPermissoes(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("email") == 0) {
				tempUser.setEmail(x.getValor().toString());
			} else if (x.getNome().compareTo("email_gerente") == 0) {
				tempUser.setEmailGerencia(x.getValor().toString());
			} else if (x.getNome().compareTo("id_setor") == 0) {
				tempUser.setSetor(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_situacao") == 0) {
				tempUser.setSituacao(Integer.parseInt(x.getValor().toString()));
			}
		}
		return tempUser;
	}
	
}
