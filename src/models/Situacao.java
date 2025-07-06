package models;

import java.util.Objects;

public class Situacao {

	private Integer codigo;
	private String nome;

	public Situacao() {
	}

	public Situacao(Integer codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
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

	@Override
	public int hashCode() {
		return Objects.hash(codigo, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Situacao other = (Situacao) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "Nivel [codigo=" + codigo + ", nome=" + nome + "]";
	}
	
	
	
	
}
	