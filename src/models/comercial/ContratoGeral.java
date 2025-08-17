package models.comercial;

import java.util.Date;
import java.util.Objects;

public class ContratoGeral {
	
	private Integer idContratoGeral;
	private String nomeContratoGeral;
	private String apelidoContratoGeral;
	private Integer idGestorDocsUsuario;
	private Integer idGestorMedUsuario;
	private Integer idFormatoContratoGeral;
	private Integer geraMedição;
	private Integer imrAns;
	private Integer descontaVt;
	private Integer descontaVa;
	private Integer executaHe;
	private Integer executaDiaria;
	private Integer idTipoCliente;
	private Date inicioVigencia;
	private Date fimVigencia;
	public ContratoGeral(Integer idContratoGeral, String nomeContratoGeral, String apelidoContratoGeral,
			Integer idGestorDocsUsuario, Integer idGestorMedUsuario, Integer idFormatoContratoGeral,
			Integer geraMedição, Integer imrAns, Integer descontaVt, Integer descontaVa, Integer executaHe,
			Integer executaDiaria, Integer idTipoCliente, Date inicioVigencia, Date fimVigencia) {
		this.idContratoGeral = idContratoGeral;
		this.nomeContratoGeral = nomeContratoGeral;
		this.apelidoContratoGeral = apelidoContratoGeral;
		this.idGestorDocsUsuario = idGestorDocsUsuario;
		this.idGestorMedUsuario = idGestorMedUsuario;
		this.idFormatoContratoGeral = idFormatoContratoGeral;
		this.geraMedição = geraMedição;
		this.imrAns = imrAns;
		this.descontaVt = descontaVt;
		this.descontaVa = descontaVa;
		this.executaHe = executaHe;
		this.executaDiaria = executaDiaria;
		this.idTipoCliente = idTipoCliente;
		this.inicioVigencia = inicioVigencia;
		this.fimVigencia = fimVigencia;
	}
	public Integer getIdContratoGeral() {
		return idContratoGeral;
	}
	public void setIdContratoGeral(Integer idContratoGeral) {
		this.idContratoGeral = idContratoGeral;
	}
	public String getNomeContratoGeral() {
		return nomeContratoGeral;
	}
	public void setNomeContratoGeral(String nomeContratoGeral) {
		this.nomeContratoGeral = nomeContratoGeral;
	}
	public String getApelidoContratoGeral() {
		return apelidoContratoGeral;
	}
	public void setApelidoContratoGeral(String apelidoContratoGeral) {
		this.apelidoContratoGeral = apelidoContratoGeral;
	}
	public Integer getIdGestorDocsUsuario() {
		return idGestorDocsUsuario;
	}
	public void setIdGestorDocsUsuario(Integer idGestorDocsUsuario) {
		this.idGestorDocsUsuario = idGestorDocsUsuario;
	}
	public Integer getIdGestorMedUsuario() {
		return idGestorMedUsuario;
	}
	public void setIdGestorMedUsuario(Integer idGestorMedUsuario) {
		this.idGestorMedUsuario = idGestorMedUsuario;
	}
	public Integer getIdFormatoContratoGeral() {
		return idFormatoContratoGeral;
	}
	public void setIdFormatoContratoGeral(Integer idFormatoContratoGeral) {
		this.idFormatoContratoGeral = idFormatoContratoGeral;
	}
	public Integer getGeraMedição() {
		return geraMedição;
	}
	public void setGeraMedição(Integer geraMedição) {
		this.geraMedição = geraMedição;
	}
	public Integer getImrAns() {
		return imrAns;
	}
	public void setImrAns(Integer imrAns) {
		this.imrAns = imrAns;
	}
	public Integer getDescontaVt() {
		return descontaVt;
	}
	public void setDescontaVt(Integer descontaVt) {
		this.descontaVt = descontaVt;
	}
	public Integer getDescontaVa() {
		return descontaVa;
	}
	public void setDescontaVa(Integer descontaVa) {
		this.descontaVa = descontaVa;
	}
	public Integer getExecutaHe() {
		return executaHe;
	}
	public void setExecutaHe(Integer executaHe) {
		this.executaHe = executaHe;
	}
	public Integer getExecutaDiaria() {
		return executaDiaria;
	}
	public void setExecutaDiaria(Integer executaDiaria) {
		this.executaDiaria = executaDiaria;
	}
	public Integer getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(Integer idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getFimVigencia() {
		return fimVigencia;
	}
	public void setFimVigencia(Date fimVigencia) {
		this.fimVigencia = fimVigencia;
	}
	@Override
	public int hashCode() {
		return Objects.hash(apelidoContratoGeral, descontaVa, descontaVt, executaDiaria, executaHe, fimVigencia,
				geraMedição, idContratoGeral, idFormatoContratoGeral, idGestorDocsUsuario, idGestorMedUsuario,
				idTipoCliente, imrAns, inicioVigencia, nomeContratoGeral);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ContratoGeral other = (ContratoGeral) obj;
		return Objects.equals(apelidoContratoGeral, other.apelidoContratoGeral)
				&& Objects.equals(descontaVa, other.descontaVa) && Objects.equals(descontaVt, other.descontaVt)
				&& Objects.equals(executaDiaria, other.executaDiaria) && Objects.equals(executaHe, other.executaHe)
				&& Objects.equals(fimVigencia, other.fimVigencia) && Objects.equals(geraMedição, other.geraMedição)
				&& Objects.equals(idContratoGeral, other.idContratoGeral)
				&& Objects.equals(idFormatoContratoGeral, other.idFormatoContratoGeral)
				&& Objects.equals(idGestorDocsUsuario, other.idGestorDocsUsuario)
				&& Objects.equals(idGestorMedUsuario, other.idGestorMedUsuario)
				&& Objects.equals(idTipoCliente, other.idTipoCliente) && Objects.equals(imrAns, other.imrAns)
				&& Objects.equals(inicioVigencia, other.inicioVigencia)
				&& Objects.equals(nomeContratoGeral, other.nomeContratoGeral);
	}
	@Override
	public String toString() {
		return "ContratoGeral [idContratoGeral=" + idContratoGeral + ", nomeContratoGeral=" + nomeContratoGeral
				+ ", apelidoContratoGeral=" + apelidoContratoGeral + ", idGestorDocsUsuario=" + idGestorDocsUsuario
				+ ", idGestorMedUsuario=" + idGestorMedUsuario + ", idFormatoContratoGeral=" + idFormatoContratoGeral
				+ ", geraMedição=" + geraMedição + ", imrAns=" + imrAns + ", descontaVt=" + descontaVt + ", descontaVa="
				+ descontaVa + ", executaHe=" + executaHe + ", executaDiaria=" + executaDiaria + ", idTipoCliente="
				+ idTipoCliente + ", inicioVigencia=" + inicioVigencia + ", fimVigencia=" + fimVigencia + "]";
	}
	
}
