package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
	
	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private Integer permissoes;
	private String email;
	private String emailGerencia;
	private String setor;
	private Integer situacao;
	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private String filePath = "src\\dados\\tblUsuarios.csv";

	
	public Usuario() {
	}
	
	public Usuario(Integer codigo, String nome, String login, String senha, Integer permissoes, String email,
			String emailGerencia, String setor, Integer situacao) {
		this.codigo = codigo;
		this.nome = nome;
		this.login = login;
		this.senha = senha;
		this.permissoes = permissoes;
		this.email = email;
		this.emailGerencia = emailGerencia;
		this.setor = setor;
		this.situacao = situacao;
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getSenha() {
		return senha;
	}
	
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public Integer getPermissoes() {
		return permissoes;
	}
	
	public void setPermissoes(Integer permissoes) {
		this.permissoes = permissoes;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmailGerencia() {
		return emailGerencia;
	}
	
	public void setEmailGerencia(String emailGerencia) {
		this.emailGerencia = emailGerencia;
	}
	
	public String getSetor() {
		return setor;
	}
	
	public void setSetor(String setor) {
		this.setor = setor;
	}
	
	public Integer getSituacao() {
		return situacao;
	}
	
	public void setSituacao(Integer situacao) {
		this.situacao = situacao;
	}
	
	public String getFilePath() {
		return filePath;
	}
	
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", permissoes=" + permissoes + ", email=" + email + ", emailGerencia=" + emailGerencia + ", setor="
				+ setor + "]";
	}
	
	public boolean validaUsuarioLogin(String login, String senha) {
		int validaLogin = 0;
		int validaSenha = 0;
		int validaSituacao = 0;
		
		if(getLogin() == login) {
			validaLogin = 1;
		}
		if(getSenha() == senha) {
			validaSenha = 1;
		}
		if(getSituacao() == 1) {
			validaSituacao = 1;
		}
		if (validaLogin == 1 && validaSenha == 1 && validaSituacao == 1) {
			return true;
		}
		
		return false;
		
	}
	
	public void updateListaUsuarios() {
		
		File arquivoUsuarios = new File(filePath);

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
				Integer situacao = Integer.parseInt(fields[7]);
				
				listaDeUsuarios.add(new Usuario(codigo, nome, login, senha, permissoes, email, emailGerencia, setor, situacao));
				usuario = tblUsuarios.readLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
		
	}
	

}
