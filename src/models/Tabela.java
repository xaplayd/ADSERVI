package models;

import java.util.Objects;

public class Tabela {

	private Integer codigo;
	private String caminho;
	private String nome;

	public Tabela() {
	}

	public Tabela(Integer codigo, String caminho, String nome) {
		this.codigo = codigo;
		this.caminho = caminho;
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getCaminho() {
		return caminho;
	}

	public void setCaminho(String caminho) {
		this.caminho = caminho;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(caminho, codigo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tabela other = (Tabela) obj;
		return Objects.equals(caminho, other.caminho) && Objects.equals(codigo, other.codigo);
	}

	public int compareTo(Tabela other) {
		int validaCodigo = codigo.compareTo(other.getCodigo());
		int validaCaminho = caminho.compareTo(other.getCaminho());
		if (validaCodigo == 0 && validaCaminho == 0) {
			return 0;
		}
		return 1;
	}
}
