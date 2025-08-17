package models.comercial;

import java.util.Objects;

public class ContratoInterno {
	
	private Integer idContratoInterno;
	private Integer idClienteFatura;
	private Integer idContratoGeral;
	private Integer idFilialCliente;
	
	public ContratoInterno(Integer idContratoInterno, Integer idClienteFatura, Integer idContratoGeral,
			Integer idFilialCliente) {
		this.idContratoInterno = idContratoInterno;
		this.idClienteFatura = idClienteFatura;
		this.idContratoGeral = idContratoGeral;
		this.idFilialCliente = idFilialCliente;
	}

	public Integer getIdContratoInterno() {
		return idContratoInterno;
	}

	public void setIdContratoInterno(Integer idContratoInterno) {
		this.idContratoInterno = idContratoInterno;
	}

	public Integer getIdClienteFatura() {
		return idClienteFatura;
	}

	public void setIdClienteFatura(Integer idClienteFatura) {
		this.idClienteFatura = idClienteFatura;
	}

	public Integer getIdContratoGeral() {
		return idContratoGeral;
	}

	public void setIdContratoGeral(Integer idContratoGeral) {
		this.idContratoGeral = idContratoGeral;
	}

	public Integer getIdFilialCliente() {
		return idFilialCliente;
	}

	public void setIdFilialCliente(Integer idFilialCliente) {
		this.idFilialCliente = idFilialCliente;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idClienteFatura, idContratoGeral, idContratoInterno, idFilialCliente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratoInterno other = (ContratoInterno) obj;
		return Objects.equals(idClienteFatura, other.idClienteFatura)
				&& Objects.equals(idContratoGeral, other.idContratoGeral)
				&& Objects.equals(idContratoInterno, other.idContratoInterno)
				&& Objects.equals(idFilialCliente, other.idFilialCliente);
	}

	@Override
	public String toString() {
		return "ContratoInterno [idContratoInterno=" + idContratoInterno + ", idClienteFatura=" + idClienteFatura
				+ ", idContratoGeral=" + idContratoGeral + ", idFilialCliente=" + idFilialCliente + "]";
	}
	
	

}
