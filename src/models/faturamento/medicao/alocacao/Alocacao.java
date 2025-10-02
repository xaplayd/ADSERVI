package models.faturamento.medicao.alocacao;

import java.time.LocalDate;
import java.time.LocalTime;

import models.faturamento.medicao.vaga.Vaga;
import models.faturamento.medicao.colaborador.Colaborador;

public class Alocacao {
    private Integer id;
    private Vaga vaga;
    private Colaborador colaborador;
    private LocalDate dataInicio;
    private LocalTime horaInicio;
    private LocalDate dataFim;
    private LocalTime horaFim;

    public Alocacao(Integer id, Vaga vaga, Colaborador colaborador,
                    LocalDate dataInicio, LocalTime horaInicio,
                    LocalDate dataFim, LocalTime horaFim) {
        this.id = id;
        this.vaga = vaga;
        this.colaborador = colaborador;
        this.dataInicio = dataInicio;
        this.horaInicio = horaInicio;
        this.dataFim = dataFim;
        this.horaFim = horaFim;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public Vaga getVaga() { return vaga; }
    public void setVaga(Vaga vaga) { this.vaga = vaga; }

    public Colaborador getColaborador() { return colaborador; }
    public void setColaborador(Colaborador colaborador) { this.colaborador = colaborador; }

    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalDate getDataFim() { return dataFim; }
    public void setDataFim(LocalDate dataFim) { this.dataFim = dataFim; }

    public LocalTime getHoraFim() { return horaFim; }
    public void setHoraFim(LocalTime horaFim) { this.horaFim = horaFim; }
}