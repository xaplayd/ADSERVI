package connection.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseConfig;

public class ConnectionController {

	public static String status = "Desconectado..";

	// CONECTAR
	public static Connection getConexaoMySQL() {
		List<DatabaseConfig> listaParametros = new ArrayList<DatabaseConfig>();
		File arquivoDatabaseConfig = new File("C:\\WS\\data\\ads.cfg");
		try (BufferedReader brDatabaseConfig = new BufferedReader(new FileReader(arquivoDatabaseConfig))) {
			String item = brDatabaseConfig.readLine();
			while (item != null) {
				String[] fields = item.split(";");
				String nome = fields[0];
				String parametro = fields[1];
				listaParametros.add(new DatabaseConfig(nome, parametro));
				item = brDatabaseConfig.readLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
		String classe = listaParametros.get(0).getParametro();
		String driver = listaParametros.get(1).getParametro();
		String db = listaParametros.get(2).getParametro();
		String address = listaParametros.get(3).getParametro();
		String port = listaParametros.get(4).getParametro();
		String dbname = listaParametros.get(5).getParametro();
		String user = listaParametros.get(6).getParametro();
		String pass = listaParametros.get(7).getParametro();
		String url = driver + ":" + db + ":" + "//" + address + ":" + port + "/" + dbname;
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, pass);
			if (connection != null) {
				status = ("STATUS--->Conectado com sucesso!");
				System.out.println(status);
			} else {
				status = ("STATUS--->Não foi possivel realizar conexão");
				System.out.println(status);
			}
			return connection;
		} catch (SQLException e) {
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			return null;
		}
	}

	public static List<DatabaseConfig> getParametrosDeConexao() {
		List<DatabaseConfig> listaParametros = new ArrayList<>();
		File arquivoDatabaseConfig = new File("C:\\WS\\data\\ads.cfg");

		if (!arquivoDatabaseConfig.exists()) {
			System.out.println("Arquivo de configuração não encontrado: " + arquivoDatabaseConfig.getAbsolutePath());
			return listaParametros;
		}

		try (BufferedReader brDatabaseConfig = new BufferedReader(new FileReader(arquivoDatabaseConfig))) {
			String item;
			int linha = 1;
			while ((item = brDatabaseConfig.readLine()) != null) {
				if (item.trim().isEmpty()) {
					linha++;
					continue;
				}

				String[] fields = item.split(";", 2);
				if (fields.length != 2) {
					System.out.println("Linha " + linha + " mal formatada: " + item);
				} else {
					String nome = fields[0].trim();
					String parametro = fields[1].trim();
					listaParametros.add(new DatabaseConfig(nome, parametro));
				}
				linha++;
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo de configuração: " + e.getMessage());
			e.printStackTrace();
		}

		return listaParametros;
	}

}