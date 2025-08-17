package models.comercial;

import java.util.Objects;

public class FormatoContrato {
	
	private Integer idFilialCliente;
	private String nomeFilialCliente;
	
	public FormatoContrato() {};
	
	public FormatoContrato(Integer idFilialCliente, String nomeFilialCliente) {
		this.idFilialCliente = idFilialCliente;
		this.nomeFilialCliente = nomeFilialCliente;
	}
	public Integer getIdFormatoContrato() {
		return idFilialCliente;
	}
	public void setIdFormatoContrato(Integer idFilialCliente) {
		this.idFilialCliente = idFilialCliente;
	}
	public String getNomeFormatoContrato() {
		return nomeFilialCliente;
	}
	public void setNomeFormatoContrato(String nomeFilialCliente) {
		this.nomeFilialCliente = nomeFilialCliente;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idFilialCliente, nomeFilialCliente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FormatoContrato other = (FormatoContrato) obj;
		return Objects.equals(idFilialCliente, other.idFilialCliente)
				&& Objects.equals(nomeFilialCliente, other.nomeFilialCliente);
	}
	@Override
	public String toString() {
		return "TipoCliente [idTipoCliente=" + idFilialCliente + ", nomeTipoCliente=" + nomeFilialCliente + "]";
	}
	
	

}
