package models.faturamento.medicao;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Colaborador {

    private Integer matricula;
    private String nome;
    private String posTra;
    private String dataAlt;

    private List<Periodo> periodosDisponiveis = new ArrayList<>();
    private List<Periodo> periodosAlocados = new ArrayList<>();

    public Colaborador(Integer matricula, String nome, String posTra, String dataAlt) {
        this.matricula = matricula;
        this.nome = nome;
        this.posTra = posTra;
        this.dataAlt = dataAlt;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getPosTra() {
        return posTra;
    }

    public String getDataAlt() {
        return dataAlt;
    }

    public List<Periodo> getPeriodosDisponiveis() {
        return periodosDisponiveis;
    }

    public List<Periodo> getPeriodosAlocados() {
        return periodosAlocados;
    }

    public void adicionarPeriodoDisponivel(Periodo p) {
        periodosDisponiveis.add(p);
    }

    public void alocarPeriodo(Periodo p) {
        if (periodosDisponiveis.remove(p)) {
            periodosAlocados.add(p);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(dataAlt, matricula, nome, posTra);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Colaborador))
            return false;
        Colaborador other = (Colaborador) obj;
        return Objects.equals(matricula, other.matricula);
    }

    @Override
    public String toString() {
        return "Colaborador [matricula=" + matricula + ", nome=" + nome + "]";
    }
}
