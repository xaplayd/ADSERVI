package models.sis;

import java.util.Objects;

public class Setor {

	private Integer codigo;
	private String nome;

	public Setor() {
	}

	public Setor(Integer codigo, String nome) {
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
		Setor other = (Setor) obj;
		return Objects.equals(codigo, other.codigo) && Objects.equals(nome, other.nome);
	}

	public int compareTo(Setor other) {
		int validaCodigo = codigo.compareTo(other.getCodigo());
		int validaNome = nome.compareTo(other.getNome());
		if (validaCodigo == 0 && validaNome == 0) {
			return 0;
		}
		return 1;
	}
}
