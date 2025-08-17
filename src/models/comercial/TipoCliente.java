package models.comercial;

import java.util.Objects;

public class TipoCliente {
	
	private Integer idTipoCliente;
	private String nomeTipoCliente;
	
	public TipoCliente() {};
	
	public TipoCliente(Integer idTipoCliente, String nomeTipoCliente) {
		this.idTipoCliente = idTipoCliente;
		this.nomeTipoCliente = nomeTipoCliente;
	}
	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public String getNomeTipoCliente() {
		return nomeTipoCliente;
	}
	public void setNomeTipoCliente(String nomeTipoCliente) {
		this.nomeTipoCliente = nomeTipoCliente;
	}
	@Override
	public int hashCode() {
		return Objects.hash(idTipoCliente, nomeTipoCliente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoCliente other = (TipoCliente) obj;
		return Objects.equals(idTipoCliente, other.idTipoCliente)
				&& Objects.equals(nomeTipoCliente, other.nomeTipoCliente);
	}
	@Override
	public String toString() {
		return "TipoCliente [idTipoCliente=" + idTipoCliente + ", nomeTipoCliente=" + nomeTipoCliente + "]";
	}
	
	

}
