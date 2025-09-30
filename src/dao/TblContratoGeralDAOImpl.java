package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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
import models.comercial.ContratoGeral;
import models.sis.TabelaColuna;

public class TblContratoGeralDAOImpl implements TblContratoGeralDAO {

	private Integer idTabela = 10;

	private String chavePrimaria = "id_contratogeral";

	@Override
	public ContratoGeral getById(Integer id) {
		ContratoGeral tempContratoGeral = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT id_contratogeral, nome_contratogeral, apelido_contratogeral, id_gestordocsusuario, id_gestormedusuario, id_formatocontratogeral, id_geramedicao, id_imr_ans, id_desconta_vt, id_desconta_va, id_executa_he, id_executa_diaria, id_tipocliente, iniciovigencia_contratogeral, fimvigencia_contratogeral FROM "
					+ tbl + " WHERE id_contratogeral = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer idTempContratoGeral = rs.getInt("id_contratogeral"); 
				String nomeContratoGeral = rs.getString("nome_contratogeral"); 
				String apelidoContratoGeral = rs.getString("apelido_contratogeral"); 
				Integer idTempGestorDocUsuario = rs.getInt("id_gestordocsusuario"); 
				Integer idTempGestorMedUsuario = rs.getInt("id_gestormedusuario"); 
				Integer idTempFormatoContratoGeral = rs.getInt("id_formatocontratogeral"); 
				Integer geraMedicao = rs.getInt("id_geramedicao"); 
				Integer imrAns = rs.getInt("id_imr_ans"); 
				Integer descontaVt = rs.getInt("id_desconta_vt"); 
				Integer descontaVa = rs.getInt("id_desconta_va"); 
				Integer executaHe = rs.getInt("id_executa_he"); 
				Integer executaDiaria = rs.getInt("id_executa_diaria"); 
				Integer idTempTipoCliente = rs.getInt("id_tipocliente"); 
				Timestamp tsInicioVigencia = rs.getTimestamp("iniciovigencia_contratogeral");
				LocalDateTime LdInicioVigencia = tsInicioVigencia.toLocalDateTime();
				Timestamp tsFimVigencia = rs.getTimestamp("fimvigencia_contratogeral");
				LocalDateTime LdFimVigencia = tsFimVigencia.toLocalDateTime();
				tempContratoGeral = new ContratoGeral(idTempContratoGeral, nomeContratoGeral, apelidoContratoGeral, idTempGestorDocUsuario, idTempGestorMedUsuario, idTempFormatoContratoGeral, geraMedicao, imrAns, descontaVt, descontaVa, executaHe, executaDiaria, idTempTipoCliente, LdInicioVigencia, LdFimVigencia);	
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempContratoGeral;
	}

	@Override
	public List<ContratoGeral> getAll() {
		ContratoGeral tempContratoGeral = null;
		Connection con = ConnectionController.getConexaoMySQL();
		List<ContratoGeral> listaContratoGeral = new ArrayList<ContratoGeral>();
		try {
			String tbl = getTblName();
			String sql = "SELECT * FROM " + tbl;
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Integer idTempContratoGeral = rs.getInt("id_contratogeral"); 
				String nomeContratoGeral = rs.getString("nome_contratogeral"); 
				String apelidoContratoGeral = rs.getString("apelido_contratogeral"); 
				Integer idTempGestorDocUsuario = rs.getInt("id_gestordocsusuario"); 
				Integer idTempGestorMedUsuario = rs.getInt("id_gestormedusuario"); 
				Integer idTempFormatoContratoGeral = rs.getInt("id_formatocontratogeral"); 
				Integer geraMedicao = rs.getInt("id_geramedicao"); 
				Integer imrAns = rs.getInt("id_imr_ans"); 
				Integer descontaVt = rs.getInt("id_desconta_vt"); 
				Integer descontaVa = rs.getInt("id_desconta_va"); 
				Integer executaHe = rs.getInt("id_executa_he"); 
				Integer executaDiaria = rs.getInt("id_executa_diaria"); 
				Integer idTempTipoCliente = rs.getInt("id_tipocliente"); 
				Timestamp tsInicioVigencia = rs.getTimestamp("iniciovigencia_contratogeral");
				LocalDateTime LdInicioVigencia = tsInicioVigencia.toLocalDateTime();
				Timestamp tsFimVigencia = rs.getTimestamp("fimvigencia_contratogeral");
				LocalDateTime LdFimVigencia = tsFimVigencia.toLocalDateTime();
				tempContratoGeral = new ContratoGeral(idTempContratoGeral, nomeContratoGeral, apelidoContratoGeral, idTempGestorDocUsuario, idTempGestorMedUsuario, idTempFormatoContratoGeral, geraMedicao, imrAns, descontaVt, descontaVa, executaHe, executaDiaria, idTempTipoCliente, LdInicioVigencia, LdFimVigencia);	
				listaContratoGeral.add(tempContratoGeral);
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaContratoGeral;
	}

	@Override
	public Integer insert(ContratoGeral contratoGeral) {
	    Connection con = ConnectionController.getConexaoMySQL();
	    Integer idGerado = null;
	    try {
	        String tbl = getTblName();
	        String sql = "INSERT INTO " + tbl
	            + " (nome_contratogeral, apelido_contratogeral, id_gestordocsusuario, id_gestormedusuario, id_formatocontratogeral, id_geramedicao, id_imr_ans, id_desconta_vt, id_desconta_va, id_executa_he, id_executa_diaria, id_tipocliente, iniciovigencia_contratogeral, fimvigencia_contratogeral) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	        // Solicita retorno das chaves geradas
	        PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        ps.setString(1, contratoGeral.getNomeContratoGeral());
	        ps.setString(2, contratoGeral.getApelidoContratoGeral());
	        ps.setInt(3, contratoGeral.getIdGestorDocsUsuario());
	        ps.setInt(4, contratoGeral.getIdGestorMedUsuario());
	        ps.setInt(5, contratoGeral.getIdFormatoContratoGeral());
	        ps.setInt(6, contratoGeral.getGeraMedição());
	        ps.setInt(7, contratoGeral.getImrAns());
	        ps.setInt(8, contratoGeral.getDescontaVt());
	        ps.setInt(9, contratoGeral.getDescontaVa());
	        ps.setInt(10, contratoGeral.getExecutaHe());
	        ps.setInt(11, contratoGeral.getExecutaDiaria());
	        ps.setInt(12, contratoGeral.getIdTipoCliente());
	        ps.setTimestamp(13, Timestamp.valueOf(contratoGeral.getInicioVigencia()));
	        ps.setTimestamp(14, Timestamp.valueOf(contratoGeral.getFimVigencia()));
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
	public Integer updateById(ContratoGeral contratoGeral) {
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "UPDATE " + tbl
					+ " SET nome_contratogeral = (?) , apelido_contratogeral = (?) , id_gestordocsusuario = (?) , id_gestormedusuario = (?) , id_formatocontratogeral = (?) , id_geramedicao = (?) , id_imr_ans = (?) , id_desconta_vt = (?) , id_desconta_va = (?) , id_executa_he = (?) , id_executa_diaria = (?) , id_tipocliente = (?) , iniciovigencia_contratogeral = (?) , fimvigencia_contratogeral = (?) WHERE id_contratogeral = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, contratoGeral.getNomeContratoGeral());
	        ps.setString(2, contratoGeral.getApelidoContratoGeral());
	        ps.setInt(3, contratoGeral.getIdGestorDocsUsuario());
	        ps.setInt(4, contratoGeral.getIdGestorMedUsuario());
	        ps.setInt(5, contratoGeral.getIdFormatoContratoGeral());
	        ps.setInt(6, contratoGeral.getGeraMedição());
	        ps.setInt(7, contratoGeral.getImrAns());
	        ps.setInt(8, contratoGeral.getDescontaVt());
	        ps.setInt(9, contratoGeral.getDescontaVa());
	        ps.setInt(10, contratoGeral.getExecutaHe());
	        ps.setInt(11, contratoGeral.getExecutaDiaria());
	        ps.setInt(12, contratoGeral.getIdTipoCliente());
	        ps.setTimestamp(13, Timestamp.valueOf(contratoGeral.getInicioVigencia()));
	        ps.setTimestamp(14, Timestamp.valueOf(contratoGeral.getFimVigencia()));
			
			

			result = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return result; // retorna a quantidade de linhas afetadas, nao o ID
	}

	@Override
	public Integer deleteById(ContratoGeral contratoGeral) {
		Connection con = ConnectionController.getConexaoMySQL();
		Integer result = 0;
		try {
			String tbl = getTblName();
			String sql = "DELETE from " + tbl + " WHERE id_contratogeral = (?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, contratoGeral.getIdContratoGeral());
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
	public ContratoGeral getByName(String name) {
		ContratoGeral tempContratoGeral = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT id_contratogeral, nome_contratogeral, apelido_contratogeral, id_gestordocsusuario, id_gestormedusuario, id_formatocontratogeral, id_geramedicao, id_imr_ans, id_desconta_vt, id_desconta_va, id_executa_he, id_executa_diaria, id_tipocliente, iniciovigencia_contratogeral, fimvigencia_contratogeral FROM "
					+ tbl + " WHERE nome_contratogeral = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer idTempContratoGeral = rs.getInt("id_contratogeral"); 
				String nomeContratoGeral = rs.getString("nome_contratogeral"); 
				String apelidoContratoGeral = rs.getString("apelido_contratogeral"); 
				Integer idTempGestorDocUsuario = rs.getInt("id_gestordocsusuario"); 
				Integer idTempGestorMedUsuario = rs.getInt("id_gestormedusuario"); 
				Integer idTempFormatoContratoGeral = rs.getInt("id_formatocontratogeral"); 
				Integer geraMedicao = rs.getInt("id_geramedicao"); 
				Integer imrAns = rs.getInt("id_imr_ans"); 
				Integer descontaVt = rs.getInt("id_desconta_vt"); 
				Integer descontaVa = rs.getInt("id_desconta_va"); 
				Integer executaHe = rs.getInt("id_executa_he"); 
				Integer executaDiaria = rs.getInt("id_executa_diaria"); 
				Integer idTempTipoCliente = rs.getInt("id_tipocliente"); 
				Timestamp tsInicioVigencia = rs.getTimestamp("iniciovigencia_contratogeral");
				LocalDateTime LdInicioVigencia = tsInicioVigencia.toLocalDateTime();
				Timestamp tsFimVigencia = rs.getTimestamp("fimvigencia_contratogeral");
				LocalDateTime LdFimVigencia = tsFimVigencia.toLocalDateTime();
				tempContratoGeral = new ContratoGeral(idTempContratoGeral, nomeContratoGeral, apelidoContratoGeral, idTempGestorDocUsuario, idTempGestorMedUsuario, idTempFormatoContratoGeral, geraMedicao, imrAns, descontaVt, descontaVa, executaHe, executaDiaria, idTempTipoCliente, LdInicioVigencia, LdFimVigencia);	
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempContratoGeral;
	}

	public ContratoGeral getByNickName(String name) {
		ContratoGeral tempContratoGeral = null;
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			String tbl = getTblName();
			String sql = "SELECT id_contratogeral, nome_contratogeral, apelido_contratogeral, id_gestordocsusuario, id_gestormedusuario, id_formatocontratogeral, id_geramedicao, id_imr_ans, id_desconta_vt, id_desconta_va, id_executa_he, id_executa_diaria, id_tipocliente, iniciovigencia_contratogeral, fimvigencia_contratogeral FROM "
					+ tbl + " WHERE apelido_contratogeral = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Integer idTempContratoGeral = rs.getInt("id_contratogeral"); 
				String nomeContratoGeral = rs.getString("nome_contratogeral"); 
				String apelidoContratoGeral = rs.getString("apelido_contratogeral"); 
				Integer idTempGestorDocUsuario = rs.getInt("id_gestordocsusuario"); 
				Integer idTempGestorMedUsuario = rs.getInt("id_gestormedusuario"); 
				Integer idTempFormatoContratoGeral = rs.getInt("id_formatocontratogeral"); 
				Integer geraMedicao = rs.getInt("id_geramedicao"); 
				Integer imrAns = rs.getInt("id_imr_ans"); 
				Integer descontaVt = rs.getInt("id_desconta_vt"); 
				Integer descontaVa = rs.getInt("id_desconta_va"); 
				Integer executaHe = rs.getInt("id_executa_he"); 
				Integer executaDiaria = rs.getInt("id_executa_diaria"); 
				Integer idTempTipoCliente = rs.getInt("id_tipocliente"); 
				Timestamp tsInicioVigencia = rs.getTimestamp("iniciovigencia_contratogeral");
				LocalDateTime LdInicioVigencia = tsInicioVigencia.toLocalDateTime();
				Timestamp tsFimVigencia = rs.getTimestamp("fimvigencia_contratogeral");
				LocalDateTime LdFimVigencia = tsFimVigencia.toLocalDateTime();
				tempContratoGeral = new ContratoGeral(idTempContratoGeral, nomeContratoGeral, apelidoContratoGeral, idTempGestorDocUsuario, idTempGestorMedUsuario, idTempFormatoContratoGeral, geraMedicao, imrAns, descontaVt, descontaVa, executaHe, executaDiaria, idTempTipoCliente, LdInicioVigencia, LdFimVigencia);	
			}
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return tempContratoGeral;
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
		ContratoGeral tempContratoGeral = getById(id);
		for (TabelaColuna x : estrutura) {
			if(x.getNome().compareTo("id_contratogeral") == 0) {
				x.setValor(tempContratoGeral.getIdContratoGeral());
			} else if (x.getNome().compareTo("nome_contratogeral") == 0) {
				x.setValor(tempContratoGeral.getNomeContratoGeral());
			} else if(x.getNome().compareTo("apelido_contratogeral") == 0) {
				x.setValor(tempContratoGeral.getApelidoContratoGeral());
			} else if(x.getNome().compareTo("id_gestordocsusuario") == 0) {
				x.setValor(tempContratoGeral.getIdGestorDocsUsuario());
			} else if(x.getNome().compareTo("id_gestormedusuario") == 0) {
				x.setValor(tempContratoGeral.getIdGestorMedUsuario());
			} else if(x.getNome().compareTo("id_formatocontratogeral") == 0) {
				x.setValor(tempContratoGeral.getIdFormatoContratoGeral());
			} else if(x.getNome().compareTo("id_geramedicao") == 0) {
				x.setValor(tempContratoGeral.getGeraMedição());
			} else if(x.getNome().compareTo("id_imr_ans") == 0) {
				x.setValor(tempContratoGeral.getImrAns());
			} else if(x.getNome().compareTo("id_desconta_vt") == 0) {
				x.setValor(tempContratoGeral.getDescontaVt());
			} else if(x.getNome().compareTo("id_desconta_va") == 0) {
				x.setValor(tempContratoGeral.getDescontaVa());
			} else if(x.getNome().compareTo("id_executa_he") == 0) {
				x.setValor(tempContratoGeral.getExecutaHe());
			} else if(x.getNome().compareTo("id_executa_diaria") == 0) {
				x.setValor(tempContratoGeral.getExecutaDiaria());
			} else if(x.getNome().compareTo("id_tipocliente") == 0) {
				x.setValor(tempContratoGeral.getIdTipoCliente());
			} else if(x.getNome().compareTo("iniciovigencia_contratogeral") == 0) {
				LocalDateTime ldt = tempContratoGeral.getInicioVigencia();
				if (ldt != null) {
			        x.setValor(ldt.toLocalDate());
			    }
			} else if(x.getNome().compareTo("fimvigencia_contratogeral") == 0) {
				LocalDateTime ldt = tempContratoGeral.getFimVigencia();
				if (ldt != null) {
			        x.setValor(ldt.toLocalDate());
			    }
			}
		}
		return estrutura;
	}

	@Override
	public ContratoGeral mapperViewToEntity(List<TabelaColuna> estrutura){
		ContratoGeral tempContratoGeral = new ContratoGeral();
		for (TabelaColuna x : estrutura) {
			 if(x.getNome().compareTo("id_contratogeral") == 0) {
		            if (x.getValor() != null && !x.getValor().toString().isBlank()) {
		            	tempContratoGeral.setIdContratoGeral(Integer.parseInt(x.getValor().toString()));
		            }
			} else if (x.getNome().compareTo("nome_contratogeral") == 0) {
				tempContratoGeral.setNomeContratoGeral(x.getValor().toString());
			} else if (x.getNome().compareTo("apelido_contratogeral") == 0) {
				tempContratoGeral.setApelidoContratoGeral(x.getValor().toString());
			} else if (x.getNome().compareTo("id_gestordocsusuario") == 0) {
				tempContratoGeral.setIdGestorDocsUsuario(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_gestormedusuario") == 0) {
				tempContratoGeral.setIdGestorMedUsuario(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_formatocontratogeral") == 0) {
				tempContratoGeral.setIdFormatoContratoGeral(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_geramedicao") == 0) {
				tempContratoGeral.setGeraMedição(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_imr_ans") == 0) {
				tempContratoGeral.setImrAns(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_desconta_vt") == 0) {
				tempContratoGeral.setDescontaVt(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_desconta_va") == 0) {
				tempContratoGeral.setDescontaVa(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_executa_he") == 0) {
				tempContratoGeral.setExecutaHe(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_executa_diaria") == 0) {
				tempContratoGeral.setExecutaDiaria(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("id_tipocliente") == 0) {
				tempContratoGeral.setIdTipoCliente(Integer.parseInt(x.getValor().toString()));
			} else if (x.getNome().compareTo("iniciovigencia_contratogeral") == 0) {
				 if (x.getValor() != null) {
						Object valor = x.getValor();
						if (valor instanceof LocalDate) {
							// Converte LocalDate para LocalDateTime (meia-noite)
							tempContratoGeral.setInicioVigencia(((LocalDate) valor).atStartOfDay());
						} else if (valor instanceof LocalDateTime) {
							tempContratoGeral.setInicioVigencia((LocalDateTime) valor);
						} else if (valor instanceof String) {
							try {
								tempContratoGeral.setInicioVigencia(LocalDateTime.parse((String) valor));
							} catch (DateTimeParseException e) {
								System.err.println("Erro ao converter String para LocalDateTime: " + valor);
							}
						}
					}
			} else if (x.getNome().compareTo("fimvigencia_contratogeral") == 0) {
				 if (x.getValor() != null) {
						Object valor = x.getValor();
						if (valor instanceof LocalDate) {
							// Converte LocalDate para LocalDateTime (meia-noite)
							tempContratoGeral.setFimVigencia(((LocalDate) valor).atStartOfDay());
						} else if (valor instanceof LocalDateTime) {
							tempContratoGeral.setFimVigencia((LocalDateTime) valor);
						} else if (valor instanceof String) {
							try {
								tempContratoGeral.setFimVigencia(LocalDateTime.parse((String) valor));
							} catch (DateTimeParseException e) {
								System.err.println("Erro ao converter String para LocalDateTime: " + valor);
							}
						}
					}
			}
		}
		return tempContratoGeral;
	}
	
}
