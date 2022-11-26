package dados.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import models.Usuario;

public class TblUsuariosController {

public static List<Usuario> updateListaUsuarios() {
		
		List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		File arquivoUsuarios = new File("src\\dados\\tblUsuarios.csv");

		try (BufferedReader tblUsuarios = new BufferedReader(new FileReader(arquivoUsuarios))) {
			String usuario = tblUsuarios.readLine();
			while (usuario != null) {
				String[] fields = usuario.split(";");
				Integer codigo = Integer.parseInt(fields[0]);
				String nome = fields[1];
				String login = fields[2];
				String senha = fields[3];
				Integer permissoes = Integer.parseInt(fields[4]);
				String email = fields[5];
				String emailGerencia = fields[6];
				Integer setor = Integer.parseInt(fields[7]);
				Integer situacao = Integer.parseInt(fields[8]);
				
				listaDeUsuarios.add(new Usuario(codigo, nome, login, senha, permissoes, email, emailGerencia, setor, situacao));
				usuario = tblUsuarios.readLine();
				
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return listaDeUsuarios;
	}

}
