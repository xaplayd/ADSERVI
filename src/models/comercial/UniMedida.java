package models.comercial;

import java.util.Objects;

public class UniMedida {
	
	private Integer idUniMedida;
	private String abrevUniMedida;
	private String nomeUniMedida;
	
	public UniMedida() {};
	
	public UniMedida(Integer idUniMedida, String abrevUniMedida, String nomeUniMedida) {
		this.idUniMedida = idUniMedida;
		this.abrevUniMedida = abrevUniMedida;
		this.nomeUniMedida = nomeUniMedida;
	}

	public Integer getId() {
		return idUniMedida;
	}

	public void setId(Integer idUniMedida) {
		this.idUniMedida = idUniMedida;
	}

	public String getAbrevUniMedida() {
		return abrevUniMedida;
	}

	public void setAbrevUniMedida(String abrevUniMedida) {
		this.abrevUniMedida = abrevUniMedida;
	}

	public String getNome() {
		return nomeUniMedida;
	}

	public void setNome(String nomeUniMedida) {
		this.nomeUniMedida = nomeUniMedida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abrevUniMedida, idUniMedida, nomeUniMedida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniMedida other = (UniMedida) obj;
		return Objects.equals(abrevUniMedida, other.abrevUniMedida) && Objects.equals(idUniMedida, other.idUniMedida)
				&& Objects.equals(nomeUniMedida, other.nomeUniMedida);
	}

	@Override
	public String toString() {
		return "UniMedida [idUniMedida=" + idUniMedida + ", abrevUniMedida=" + abrevUniMedida + ", nomeUniMedida="
				+ nomeUniMedida + "]";
	}
	
	

}
