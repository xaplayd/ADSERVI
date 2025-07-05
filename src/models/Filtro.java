package models;

public class Filtro {
	private String coluna;
	private String valor;
	private String condicao;

	public Filtro(String coluna, String valor, String condicao) {
		this.coluna = coluna;
		this.valor = valor;
		this.condicao = condicao;
	}

	public String getColuna() {
		return coluna;
	}

	public String getValor() {
		return valor;
	}

	public String getCondicao() {
		return condicao;
	}
}