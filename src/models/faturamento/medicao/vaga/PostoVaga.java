package models.faturamento.medicao.vaga;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import models.faturamento.medicao.alocacao.Alocacao;

public class PostoVaga {

	private String posTraTabVaga;
	private Integer qtdQpoTabVaga;
	private String desPosTabVaga;
	private Double teto;
	private Double executado;
	private Double aExecutar;
	private List<Alocacao> alocacoes = new ArrayList<>();
	
	public PostoVaga(String posTraTabVaga, Integer qtdQpoTabVaga, String desPosTabVaga, Double teto,
			Double executado) {
		this.posTraTabVaga = posTraTabVaga;
		this.qtdQpoTabVaga = qtdQpoTabVaga;
		this.desPosTabVaga = desPosTabVaga;
		this.teto = teto;
		this.executado = executado;
	}
	
	public List<Alocacao> getAlocacoes() {
	    return alocacoes;
	}

	public void adicionarAlocacao(Alocacao alocacao) {
	    alocacoes.add(alocacao);
	    this.executado += alocacao.calcularImpacto();
	}

	public void removerAlocacao(Alocacao alocacao) {
	    alocacoes.remove(alocacao);
	    this.executado -= alocacao.calcularImpacto();
	}

	public String getPosTraTabVaga() {
		return posTraTabVaga;
	}
	public void setPosTraTabVaga(String posTraTabVaga) {
		this.posTraTabVaga = posTraTabVaga;
	}
	public Integer getQtdQpoTabVaga() {
		return qtdQpoTabVaga;
	}
	public void setQtdQpoTabVaga(Integer qtdQpoTabVaga) {
		this.qtdQpoTabVaga = qtdQpoTabVaga;
	}
	public String getDesPosTabVaga() {
		return desPosTabVaga;
	}
	public void setDesPosTabVaga(String desPosTabVaga) {
		this.desPosTabVaga = desPosTabVaga;
	}
	public Double getTeto() {
		return teto;
	}
	public void setTeto(Double teto) {
		this.teto = teto;
	}
	public Double getExecutado() {
		return executado;
	}
	public void setExecutado(Double executado) {
		this.executado = executado;
	}
	public Double getaExecutar() {
		return teto - executado;
	}

	@Override
	public int hashCode() {
		return Objects.hash(aExecutar, desPosTabVaga, executado, posTraTabVaga, qtdQpoTabVaga, teto);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PostoVaga other = (PostoVaga) obj;
		return Objects.equals(aExecutar, other.aExecutar) && Objects.equals(desPosTabVaga, other.desPosTabVaga)
				&& Objects.equals(executado, other.executado)
				&& Objects.equals(posTraTabVaga, other.posTraTabVaga)
				&& Objects.equals(qtdQpoTabVaga, other.qtdQpoTabVaga) && Objects.equals(teto, other.teto);
	}
	@Override
	public String toString() {
		return "PostoVaga [idView=" + ", posTraTabVaga=" + posTraTabVaga + ", qtdQpoTabVaga=" + qtdQpoTabVaga
				+ ", desPosTabVaga=" + desPosTabVaga + ", teto=" + teto + ", executado=" + executado + ", aExecutar="
				+ aExecutar + "]";
	}

	
	
	
}
