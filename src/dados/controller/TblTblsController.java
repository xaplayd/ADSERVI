package dados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Tabela;

public class TblTblsController {

	public static List<Tabela> updateListaTabela() {

	
		List<Tabela> listaDeTabelas = new ArrayList<Tabela>();
		File arquivoTbls = new File("C:\\WORKSPACE\\tbls\\tblTbls.csv");

		try (BufferedReader tblTbls = new BufferedReader(new FileReader(arquivoTbls))) {
			String item = tblTbls.readLine();
			while (item != null) {
				String[] fields = item.split(";");
				Integer codigo = Integer.parseInt(fields[0]);
				String caminho = fields[1];
				String nome = fields[2];
				listaDeTabelas.add(new Tabela(codigo, caminho, nome));
				item = tblTbls.readLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return listaDeTabelas;
	}

}
