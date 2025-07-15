package models;

public class TabelaColuna {

	private String nome;
	private Object valor;
	private Integer tipoSQL;

	public TabelaColuna(String nome, Object valor, int tipoSQL) {
		this.nome = nome;
		this.valor = valor;
		this.tipoSQL = tipoSQL;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Object getValor() {
		return valor;
	}

	public void setValor(Object valor) {
		this.valor = valor;
	}

	public Integer getTipoSQL() {
		return tipoSQL;
	}

	public void setTipoSQL(Integer tipoSQL) {
		this.tipoSQL = tipoSQL;
	}
	
	
}
