package models.faturamento.medicao;

public class Periodo {
    private String descricao;

    public Periodo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return descricao;
    }
}
