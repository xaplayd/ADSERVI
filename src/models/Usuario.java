package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dados.controller.TblUsuariosController;

public class Usuario implements Comparable<Usuario>{
	
	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private Integer permissoes;
	private String email;
	private String emailGerencia;
	private Integer setor;
	private Integer situacao;
	private List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	private String filePath = "src\\dados\\tblUsuarios.csv";

	
	public Usuario() {
	}
	
	public Usuario(Integer codigo, String nome, String login, String senha, Integer permissoes, String email,
			String emailGerencia, Integer setor, Integer situacao) {
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
	
	public Integer getSetor() {
		return setor;
	}
	
	public void setSetor(Integer setor) {
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
	
	public List getListaDeUsuarios() {
		TblUsuariosController.updateListaUsuarios();
		return listaDeUsuarios;
	}
	
	
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", permissoes=" + permissoes + ", email=" + email + ", emailGerencia=" + emailGerencia + ", setor="
				+ setor + "]";
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(codigo, login, senha, situacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(login, other.login)
				&& Objects.equals(senha, other.senha) && Objects.equals(situacao, other.situacao);
	}

	
	public int compareTo(Usuario other) {
		int validaLogin = login.compareTo(other.getLogin());
		int validaSenha = senha.compareTo(other.getSenha());
		if(validaLogin == 0 && validaSenha == 0) {
			return 0;
		}
		return 1;
	}

}
