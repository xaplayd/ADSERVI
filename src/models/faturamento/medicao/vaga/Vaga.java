package models.faturamento.medicao.vaga;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import models.faturamento.medicao.alocacao.Alocacao;

public class Vaga {

    private Integer id;
    private LocalDate inicioData;
    private LocalDate fimData;
    private Double totalExecucao;
    private Double executado;
    private String posTraTabVaga;
    private String desPosTabVaga;

    private List<Alocacao> alocacoes;

    public Vaga(Integer id, LocalDate inicioData, LocalDate fimData,
                Double totalExecucao, String posTraTabVaga, String desPosTabVaga) {
        this.id = id;
        this.inicioData = inicioData;
        this.fimData = fimData;
        this.totalExecucao = totalExecucao;
        this.posTraTabVaga = posTraTabVaga;
        this.desPosTabVaga = desPosTabVaga;
        this.executado = 0.0;
        this.alocacoes = new ArrayList<>();
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getInicioData() { return inicioData; }
    public void setInicioData(LocalDate inicioData) { this.inicioData = inicioData; }

    public LocalDate getFimData() { return fimData; }
    public void setFimData(LocalDate fimData) { this.fimData = fimData; }

    public Double getTotalExecucao() { return totalExecucao; }
    public void setTotalExecucao(Double totalExecucao) { this.totalExecucao = totalExecucao; }

    public Double getExecutado() {
        return executado != null ? executado : 0.0;
    }

    public void setExecutado(Double executado) { this.executado = executado; }

    public Double getaExecutar() {
        return totalExecucao - getExecutado();
    }

    public String getPosTraTabVaga() { return posTraTabVaga; }
    public void setPosTraTabVaga(String posTraTabVaga) { this.posTraTabVaga = posTraTabVaga; }

    public String getDesPosTabVaga() { return desPosTabVaga; }
    public void setDesPosTabVaga(String desPosTabVaga) { this.desPosTabVaga = desPosTabVaga; }

    public List<Alocacao> getAlocacoes() { return alocacoes; }
    public void setAlocacoes(List<Alocacao> alocacoes) { this.alocacoes = alocacoes; }

    public void addAlocacao(Alocacao alocacao) {

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inicioData, fimData, totalExecucao);
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "id=" + id +
                ", descricao='" + desPosTabVaga + '\'' +
                ", posTra='" + posTraTabVaga + '\'' +
                ", total=" + totalExecucao +
                ", executado=" + getExecutado() +
                ", aExecutar=" + getaExecutar() +
                ", alocacoes=" + alocacoes.size() +
                '}';
    }
}
