package config;

import java.util.Objects;

public class TabelaConfig {

	private Integer nomeid;
	private String nomeTabela;
	
	public TabelaConfig() {}
	
	public TabelaConfig(Integer nomeid, String nomeTabela) {
		super();
		this.nomeid = nomeid;
		this.nomeTabela = nomeTabela;
	}

	public Integer getNomeid() {
		return nomeid;
	}

	public void setNomeid(Integer nomeid) {
		this.nomeid = nomeid;
	}

	public String getNomeTabela() {
		return nomeTabela;
	}

	public void setNomeTabela(String nomeTabela) {
		this.nomeTabela = nomeTabela;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nomeTabela, nomeid);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaConfig other = (TabelaConfig) obj;
		return Objects.equals(nomeTabela, other.nomeTabela) && Objects.equals(nomeid, other.nomeid);
	}
	

	
	
	
	
}