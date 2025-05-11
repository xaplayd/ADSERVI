package dados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.TabelaConfig;

public class DicTabelasController {

	public static List<TabelaConfig> updateListaTabela() {

	
		List<TabelaConfig> listaDeTabelas = new ArrayList<TabelaConfig>();
		File dicTabelas = new File("C:\\WS\\data\\dictabelas.cfg");

		try (BufferedReader brDicTabelas = new BufferedReader(new FileReader(dicTabelas))) {
			String item = brDicTabelas.readLine();
			while (item != null) {
				String[] fields = item.split(";");
				Integer id = Integer.parseInt(fields[0]);
				String nomeTabela = fields[1];
				listaDeTabelas.add(new TabelaConfig(id, nomeTabela));
				item = brDicTabelas.readLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return listaDeTabelas;
	}

}
