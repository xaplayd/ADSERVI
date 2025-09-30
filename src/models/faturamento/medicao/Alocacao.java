package models.faturamento.medicao;

import java.util.Objects;

public class Alocacao {
    
    private Integer id;                 // identificador sequencial da alocação
    private PostoVaga postoVaga;        // referência ao posto
    private Colaborador colaborador;    // colaborador vinculado
    private Double horasAlocadas;       // horas/quantidade vinculada
    private String dataAlocacao;        // data do vínculo

    public Alocacao(Integer id, PostoVaga postoVaga, Colaborador colaborador, Double horasAlocadas, String dataAlocacao) {
        this.id = id;
        this.postoVaga = postoVaga;
        this.colaborador = colaborador;
        this.horasAlocadas = horasAlocadas;
        this.dataAlocacao = dataAlocacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PostoVaga getPostoVaga() {
        return postoVaga;
    }

    public void setPostoVaga(PostoVaga postoVaga) {
        this.postoVaga = postoVaga;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public void setColaborador(Colaborador colaborador) {
        this.colaborador = colaborador;
    }

    public Double getHorasAlocadas() {
        return horasAlocadas;
    }

    public void setHorasAlocadas(Double horasAlocadas) {
        this.horasAlocadas = horasAlocadas;
    }

    public String getDataAlocacao() {
        return dataAlocacao;
    }

    public void setDataAlocacao(String dataAlocacao) {
        this.dataAlocacao = dataAlocacao;
    }

    // Cálculo simples: impacto no executado
    public Double calcularImpacto() {
        return horasAlocadas != null ? horasAlocadas : 0.0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, postoVaga, colaborador, dataAlocacao);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Alocacao)) return false;
        Alocacao other = (Alocacao) obj;
        return Objects.equals(id, other.id)
            && Objects.equals(postoVaga, other.postoVaga)
            && Objects.equals(colaborador, other.colaborador)
            && Objects.equals(dataAlocacao, other.dataAlocacao);
    }

    @Override
    public String toString() {
        return "Alocacao [id=" + id 
             + ", posto=" + (postoVaga != null ? postoVaga.getPosTraTabVaga() : "-") 
             + ", colaborador=" + (colaborador != null ? colaborador.getNome() : "-") 
             + ", horasAlocadas=" + horasAlocadas 
             + ", dataAlocacao=" + dataAlocacao + "]";
    }
}
