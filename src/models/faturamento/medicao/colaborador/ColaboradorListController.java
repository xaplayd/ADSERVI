package models.faturamento.medicao.colaborador;

import java.util.List;

import models.faturamento.medicao.Medicao;

public class ColaboradorListController {
	
	Medicao md;

	private List<Colaborador> colaboradores;
	
    public void initData(Medicao md) {
    	this.colaboradores = md.getColaboradores();
    }
    
    

}
