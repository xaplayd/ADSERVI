package models.faturamento.medicao;

import java.util.List;

public class Medicao {

    private List<PostoVaga> postos;
    private List<Colaborador> colaboradores;

    public Medicao(List<PostoVaga> postos, List<Colaborador> colaboradores) {
        this.postos = postos;
        this.colaboradores = colaboradores;
    }

    public List<PostoVaga> getPostos() {
        return postos;
    }

    public void setPostos(List<PostoVaga> postos) {
        this.postos = postos;
    }

    public List<Colaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<Colaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }
}
