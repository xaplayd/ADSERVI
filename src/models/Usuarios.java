package models;

public class Usuarios {
	
	private Integer codigo;
	private String nome;
	private String login;
	private String senha;
	private Integer permissoes;
	private String email;
	private String emailGerencia;
	private String setor;
	private Integer situacao;
	
	public Usuarios() {
	}
	
	//construtor de usuario para login
	public Usuarios(String login, String senha, Integer permissoes, Integer situacao) {
		this.login = login;
		this.senha = senha;
		this.permissoes = permissoes;
		this.situacao = situacao;
	}
	
	//construtor de usuario completo
	public Usuarios(Integer codigo, String nome, String login, String senha, Integer permissoes, String email,
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
	@Override
	public String toString() {
		return "Usuario [codigo=" + codigo + ", nome=" + nome + ", login=" + login + ", senha=" + senha
				+ ", permissoes=" + permissoes + ", email=" + email + ", emailGerencia=" + emailGerencia + ", setor="
				+ setor + "]";
	}
	
	
	
	

}
