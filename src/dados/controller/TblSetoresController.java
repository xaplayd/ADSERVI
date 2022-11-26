package dados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Setor;

public class TblSetoresController {

public static List<Setor> updateListaSetores() {
		
		List<Setor> listaDeSetores = new ArrayList<Setor>();
		File arquivoSetores = new File("src\\dados\\tblSetores.csv");

		try (BufferedReader tblSetores = new BufferedReader(new FileReader(arquivoSetores))) {
			String setor = tblSetores.readLine();
			while (setor != null) {
				String[] fields = setor.split(";");
				Integer codigo = Integer.parseInt(fields[0]);
				String nome = fields[1];
				
				listaDeSetores.add(new Setor(codigo, nome));
				setor = tblSetores.readLine();
				
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return listaDeSetores;
	}

}
