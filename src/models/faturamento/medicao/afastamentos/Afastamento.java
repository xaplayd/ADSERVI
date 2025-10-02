package models.faturamento.medicao.afastamentos;

import java.time.LocalDate;
import java.util.Objects;

public class Afastamento {

	private Integer numCad;
	private String nomFun;
	private Integer codAfa;
	private String nomAfa;
	private LocalDate datIni;
	private String hrIni;
	private LocalDate datFim;
	private String hrFim;
	public Integer getNumCad() {
		return numCad;
	}
	public void setNumCad(Integer numCad) {
		this.numCad = numCad;
	}
	public String getNomFun() {
		return nomFun;
	}
	public void setNomFun(String nomFun) {
		this.nomFun = nomFun;
	}
	public Integer getCodAfa() {
		return codAfa;
	}
	public void setCodAfa(Integer codAfa) {
		this.codAfa = codAfa;
	}
	public String getNomAfa() {
		return nomAfa;
	}
	public void setNomAfa(String nomAfa) {
		this.nomAfa = nomAfa;
	}
	public LocalDate getDatIni() {
		return datIni;
	}
	public void setDatIni(LocalDate datIni) {
		this.datIni = datIni;
	}
	public String getHrIni() {
		return hrIni;
	}
	public void setHrIni(String hrIni) {
		this.hrIni = hrIni;
	}
	public LocalDate getDatFim() {
		return datFim;
	}
	public void setDatFim(LocalDate datFim) {
		this.datFim = datFim;
	}
	public String getHrFim() {
		return hrFim;
	}
	public void setHrFim(String hrFim) {
		this.hrFim = hrFim;
	}
	public Afastamento(Integer numCad, String nomFun, Integer codAfa, String nomAfa, LocalDate datIni, String hrIni,
			LocalDate datFim, String hrFim) {
		this.numCad = numCad;
		this.nomFun = nomFun;
		this.codAfa = codAfa;
		this.nomAfa = nomAfa;
		this.datIni = datIni;
		this.hrIni = hrIni;
		this.datFim = datFim;
		this.hrFim = hrFim;
	}
	@Override
	public int hashCode() {
		return Objects.hash(codAfa, datFim, datIni, hrFim, hrIni, nomAfa, nomFun, numCad);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Afastamento other = (Afastamento) obj;
		return Objects.equals(codAfa, other.codAfa) && Objects.equals(datFim, other.datFim)
				&& Objects.equals(datIni, other.datIni) && Objects.equals(hrFim, other.hrFim)
				&& Objects.equals(hrIni, other.hrIni) && Objects.equals(nomAfa, other.nomAfa)
				&& Objects.equals(nomFun, other.nomFun) && Objects.equals(numCad, other.numCad);
	}
	@Override
	public String toString() {
		return "Afastamento [numCad=" + numCad + ", nomFun=" + nomFun + ", codAfa=" + codAfa + ", nomAfa=" + nomAfa
				+ ", datIni=" + datIni + ", hrIni=" + hrIni + ", datFim=" + datFim + ", hrFim=" + hrFim + "]";
	}
	
	
	

	
	
	

}
