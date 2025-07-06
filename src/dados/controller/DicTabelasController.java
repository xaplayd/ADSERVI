package dados.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Tabela;

public class DicTabelasController {

	public static List<Tabela> updateListaTabela() {
		Tabela tempTabela = null;
		List<Tabela> listaDeTbl = new ArrayList<Tabela>();
		Connection con = ConnectionController.getConexaoMySQL();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM tbls");
			while (rs.next()) {
				Integer id = rs.getInt("idtbl");
				String nome = rs.getString("nometbl");
				tempTabela = new Tabela(id, nome);
				listaDeTbl.add(tempTabela);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return listaDeTbl;
	}

}
