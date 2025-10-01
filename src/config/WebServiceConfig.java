package config;

import java.util.Objects;

public class WebServiceConfig {

	private String login;
	private String senha;
	private Integer cripto;
	
	public WebServiceConfig(String login, String senha, Integer cripto) {
		this.login = login;
		this.senha = senha;
		this.cripto = cripto;
	}
	
	public WebServiceConfig() {}
	
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

	public Integer getCripto() {
		return cripto;
	}

	public void setCripto(Integer cripto) {
		this.cripto = cripto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cripto, login, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebServiceConfig other = (WebServiceConfig) obj;
		return Objects.equals(cripto, other.cripto) && Objects.equals(login, other.login)
				&& Objects.equals(senha, other.senha);
	}

	@Override
	public String toString() {
		return "WebServiceConfig [login=" + login + ", senha=" + senha + ", cripto=" + cripto + "]";
	}

	
	
	
}
