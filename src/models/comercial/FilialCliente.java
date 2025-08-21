package models.comercial;

import java.util.Objects;

public class FilialCliente {
	
	private Integer idFormatoContrato;
	private String nomeFormatoContrato;
	
	public FilialCliente() {};
	
	public FilialCliente(Integer idFormatoContrato, String nomeFormatoContrato) {
		this.idFormatoContrato = idFormatoContrato;
		this.nomeFormatoContrato = nomeFormatoContrato;
	}
	public Integer getId() {
		return idFormatoContrato;
	}
	public void setId(Integer idFormatoContrato) {
		this.idFormatoContrato = idFormatoContrato;
	}
	public String getNome() {
		return nomeFormatoContrato;
	}
	public void setNome(String nomeFormatoContrato) {
		this.nomeFormatoContrato = nomeFormatoContrato;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idFormatoContrato, nomeFormatoContrato);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FilialCliente other = (FilialCliente) obj;
		return Objects.equals(idFormatoContrato, other.idFormatoContrato)
				&& Objects.equals(nomeFormatoContrato, other.nomeFormatoContrato);
	}
	@Override
	public String toString() {
		return "TipoCliente [idTipoCliente=" + idFormatoContrato + ", nomeTipoCliente=" + nomeFormatoContrato + "]";
	}
	
	

}
