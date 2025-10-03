package models.faturamento.medicao;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import models.faturamento.medicao.afastamentos.Afastamento;
import models.faturamento.medicao.alocacao.Alocacao;
import models.faturamento.medicao.colaborador.Colaborador;
import models.faturamento.medicao.vaga.Vaga;

public class Medicao {

	private Integer id;
	private String filiais;
	private String dataPeriodoInicio;
	private String dataPeriodoFim;

	private List<Vaga> vagas;
	private List<Colaborador> colaboradores;
	private List<Afastamento> afastamentos;
	private List<Alocacao> alocacoes = new ArrayList();
	// private List<Coberturas> coberturas;

	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFiliais() {
		return filiais;
	}

	public void setFiliais(String filiais) {
		this.filiais = filiais;
	}

	public String getDataPeriodoInicio() {
		return dataPeriodoInicio;
	}

	public void setDataPeriodoInicio(String dataPeriodoInicio) {
		this.dataPeriodoInicio = dataPeriodoInicio;
	}

	public String getDataPeriodoFim() {
		return dataPeriodoFim;
	}

	public void setDataPeriodoFim(String dataPeriodoFim) {
		this.dataPeriodoFim = dataPeriodoFim;
	}

	public Medicao(String filiais, String dataInicio, String dataFim, List<Vaga> vagas, List<Colaborador> colaboradores,
			List<Afastamento> afastamentos) {
		this.filiais = filiais;
		this.dataPeriodoInicio = dataInicio;
		this.dataPeriodoFim = dataFim;
		this.vagas = vagas;
		this.colaboradores = colaboradores;
		this.afastamentos = afastamentos;
		
	}

	public List<Vaga> getVagas() {
		return vagas;
	}

	public void setVagas(List<Vaga> vagas) {
		this.vagas = vagas;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public List<Afastamento> getAfastamentos() {
		return afastamentos;
	}

	public void setAfastamentos(List<Afastamento> afastamentos) {
		this.afastamentos = afastamentos;
	}

	public List<Alocacao> getAlocacoes() {
		return alocacoes;
	}

	public void setAlocacoes(List<Alocacao> alocacoes) {
		this.alocacoes = alocacoes;
	}
}
