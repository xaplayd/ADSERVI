package config;

import java.util.Objects;

public class DatabaseConfig {

	private String nome;
	private String parametro;

	public DatabaseConfig() {
	}

	public DatabaseConfig(String nome, String parametro) {
		this.nome = nome;
		this.parametro = parametro;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome, parametro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DatabaseConfig other = (DatabaseConfig) obj;
		return Objects.equals(nome, other.nome) && Objects.equals(parametro, other.parametro);
	}
}
