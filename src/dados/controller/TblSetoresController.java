package dados.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.scene.control.Alert;
import models.Setor;
import models.Tabela;
import services.TblsService;

public class TblSetoresController {

	private static Integer tabelaNumero = 2;

	public static String puxaDiretorioTblSetores() {

		/* 1 - Atualiza lista de tabelas, conforme tabela temporaria */

		Tabela tempTbl = new Tabela(tabelaNumero, null, null);

		List tempListTab = TblTblsController.updateListaTabela();

		for (Object x : tempListTab) {
			tempTbl = TblsService.puxaTabela(tempListTab, tempTbl.getCodigo());
		}

		String caminho = tempTbl.getCaminho();

		return caminho;
	}

	public static List<Setor> updateListaSetores() {

		/* 2 - Atualiza lista de setores, conforme caminho da lista da tabela */

		String tempCaminho = puxaDiretorioTblSetores();

		List<Setor> listaDeSetores = new ArrayList<Setor>();
		File arquivoSetores = new File(tempCaminho);

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

	public static String insereSetorNaLista(String nome) {

		File arquivoOriginal = new File(puxaDiretorioTblSetores());
		String tempCaminho = arquivoOriginal.getParent();
		File arquivoNovo = new File(tempCaminho + "\\tempTblSetores.crypt");
		String codList = "";
		String nomeList = "";
		String novaChaveStr = "";

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(arquivoNovo, true)))) {
			Scanner x = new Scanner(new File(puxaDiretorioTblSetores()));
			x.useDelimiter("[;\n]");

			while (x.hasNext()) {
				codList = x.next();
				nomeList = x.next();
				pw.print(codList + ";" + nomeList + "\n");
			}

			Integer ultimaChave = Integer.parseInt(codList);
			Integer novaChaveInt = ultimaChave + 1;
			novaChaveStr = String.valueOf(novaChaveInt);
			pw.print(novaChaveStr + ";" + nome + "\n");

			x.close();
			pw.flush();
			pw.close();
			arquivoOriginal.delete();
			File dump = new File(puxaDiretorioTblSetores());
			arquivoNovo.renameTo(dump);

			System.out.println("SALVO COM SUCESSO!!");
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("Salvo com Sucesso!");
			alert.showAndWait();

		} catch (IOException e) {
			e.getMessage();
			System.out.println("ERRO AO SALVAR!!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Processo de salvamento interrompido devido a erro!");
			alert.showAndWait();
		}
		return novaChaveStr;
	}

	public static String deletaSetorNaLista(String codigo) {
		
		
		File arquivoOriginal = new File(puxaDiretorioTblSetores());
		String tempCaminho = arquivoOriginal.getParent();
		File arquivoNovo = new File(tempCaminho + "\\tempTblSetores.crypt");
		String codList = "";
		String nomeList = "";

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(arquivoNovo, true)))) {
			Scanner x = new Scanner(new File(puxaDiretorioTblSetores()));
			x.useDelimiter("[;\n]");

			while (x.hasNext()) {
				codList = x.next();
				nomeList = x.next();
				if (codList.equals(codigo)) {
					pw.print("");

				} else {
					pw.print(codList + ";" + nomeList + "\n");
				}

			}
			x.close();
			pw.flush();
			pw.close();
			arquivoOriginal.delete();
			File dump = new File(puxaDiretorioTblSetores());
			arquivoNovo.renameTo(dump);

			System.out.println("EXCLUIDO COM SUCESSO!!");
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("Excluido com Sucesso!");
			alert.showAndWait();
			
			String situacao = "sucesso";
			return situacao;

		} catch (IOException e) {
			e.getMessage();
			System.out.println("ERRO AO EXCLUIR!!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Processo de exclusão interrompido devido a erro!");
			alert.showAndWait();
			String situacao = "erro";
			return situacao;
		}

	}

	public static void editaSetorNaLista(String codigo, String nome) {

		File arquivoOriginal = new File(puxaDiretorioTblSetores());
		String tempCaminho = arquivoOriginal.getParent();
		File arquivoNovo = new File(tempCaminho + "\\tempTblSetores.crypt");
		String codList = "";
		String nomeList = "";

		try (PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(arquivoNovo, true)))) {
			Scanner x = new Scanner(new File(puxaDiretorioTblSetores()));
			x.useDelimiter("[;\n]");

			while (x.hasNext()) {
				codList = x.next();
				nomeList = x.next();
				if (codList.equals(codigo)) {
					pw.print(codigo + ";" + nome + "\n");

				} else {
					pw.print(codList + ";" + nomeList + "\n");
				}

			}
			x.close();
			pw.flush();
			pw.close();
			arquivoOriginal.delete();
			File dump = new File(puxaDiretorioTblSetores());
			arquivoNovo.renameTo(dump);

			System.out.println("SALVO COM SUCESSO!!");
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.setTitle("Sucesso!");
			alert.setHeaderText("Salvo com Sucesso!");
			alert.showAndWait();

		} catch (IOException e) {
			e.getMessage();
			System.out.println("ERRO AO SALVAR!!");
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Erro!");
			alert.setHeaderText("Processo de salvamento interrompido devido a erro!");
			alert.showAndWait();
		}
	}

}
