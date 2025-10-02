package models.faturamento.medicao.colaborador;

import java.util.ArrayList;
import java.util.List;

import models.faturamento.medicao.alocacao.Alocacao;

public class Colaborador {

    private Integer matricula;
    private String nome;
    private String posTra;
    private String optaVt;
    private List<Alocacao> alocacoes;

    public Colaborador(Integer matricula, String nome, String posTra, String optaVt) {
        this.matricula = matricula;
        this.nome = nome;
        this.posTra = posTra;
        this.optaVt = optaVt;
        this.alocacoes = new ArrayList<>();
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPosTra() {
        return posTra;
    }

    public void setPosTra(String posTra) {
        this.posTra = posTra;
    }

    public String getOptaVt() {
        return optaVt;
    }

    public void setOptaVt(String optaVt) {
        this.optaVt = optaVt;
    }

    public List<Alocacao> getAlocacoes() {
        return alocacoes;
    }

    public void setAlocacoes(List<Alocacao> alocacoes) {
        this.alocacoes = alocacoes;
    }

    public void addAlocacao(Alocacao alocacao) {
        if (this.alocacoes == null) {
            this.alocacoes = new ArrayList<>();
        }
        this.alocacoes.add(alocacao);
    }

    @Override
    public String toString() {
        return "Colaborador{" +
                "matricula=" + matricula +
                ", nome='" + nome + '\'' +
                ", posTra='" + posTra + '\'' +
                ", optaVt='" + optaVt + '\'' +
                ", alocacoes=" + (alocacoes != null ? alocacoes.size() : 0) +
                '}';
    }
}
