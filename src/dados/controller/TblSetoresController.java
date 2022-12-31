package dados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Setor;
import models.Tabela;
import services.TblsService;

public class TblSetoresController {

public static List<Setor> updateListaSetores() {
	/*
	 * 1 - Atualiza lista de tabelas, conforme tabela  temporaria
	 * 2 - Atualiza lista de setores, conforme caminho da lista da tabela
	 */
		Tabela tempTbl = new Tabela(2, null, null);
		
		List tempListTab = TblTblsController.updateListaTabela();
		
		for (Object x : tempListTab) {
			tempTbl = TblsService.puxaTabela(tempListTab, tempTbl.getCodigo());
		}
		
		String caminho = tempTbl.getCaminho();
		
		List<Setor> listaDeSetores = new ArrayList<Setor>();
		File arquivoSetores = new File(caminho);

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

public static void insereSetorNaLista(String nome) {
	List tempList = TblSetoresController.updateListaSetores();
	
	
	//Setor tempSetor = new Setor(xxx, nome);
	//tempList.add(tempSetor);
	
	
	

}

public static void deletaSetorNaLista() {
	

}

public static void editaSetorNaLista() {
	

}

}
