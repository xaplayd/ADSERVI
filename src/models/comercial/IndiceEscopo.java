package models.comercial;

import java.time.LocalDateTime;
import java.util.Objects;

public class IndiceEscopo {
	
	private Integer idEscopo;
	private Integer idContratoGeral;
	private LocalDateTime dataVigente;
	
	public IndiceEscopo() {};
	
	public IndiceEscopo(Integer idEscopo, Integer idContratoGeral, LocalDateTime dataVigente) {
		this.idEscopo = idEscopo;
		this.idContratoGeral = idContratoGeral;
		this.dataVigente = dataVigente;
	}

	public Integer getId() {
		return idEscopo;
	}

	public void setId(Integer idEscopo) {
		this.idEscopo = idEscopo;
	}

	public Integer getIdContratoGeral() {
		return idContratoGeral;
	}

	public void setIdContratoGeral(Integer idContratoGeral) {
		this.idContratoGeral = idContratoGeral;
	}

	public LocalDateTime getDataVigente() {
		return dataVigente;
	}

	public void setDataVigente(LocalDateTime dataVigente) {
		this.dataVigente = dataVigente;
	}

	@Override
	public String toString() {
		return "Escopo [idEscopo=" + idEscopo + ", idContratoGeral=" + idContratoGeral + ", dataVigente=" + dataVigente
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(dataVigente, idContratoGeral, idEscopo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		IndiceEscopo other = (IndiceEscopo) obj;
		return Objects.equals(dataVigente, other.dataVigente) && Objects.equals(idContratoGeral, other.idContratoGeral)
				&& Objects.equals(idEscopo, other.idEscopo);
	}
	
	

}
