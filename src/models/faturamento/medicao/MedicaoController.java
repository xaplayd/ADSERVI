package models.faturamento.medicao;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import models.faturamento.medicao.afastamentos.Afastamento;
import models.faturamento.medicao.alocacao.AlocacaoListController;
import models.faturamento.medicao.colaborador.Colaborador;
import models.faturamento.medicao.colaborador.ColaboradorListController;
import models.faturamento.medicao.vaga.Vaga;
import models.faturamento.medicao.vaga.VagaListController;
import models.faturamento.medicao.ws.AfastamentosWS;
import models.faturamento.medicao.ws.ColaboradoresVinculadosWS;
import models.faturamento.medicao.ws.PostoseVagasAutorizadasWS;

public class MedicaoController {
	
	private Medicao md;
    private VagaListController vagaListController;
    
    @FXML
    private ScrollPane vagaListView;

    
    private void geraListaVagas() {
    	vagaListController = (VagaListController) 
                vagaListView.getProperties().get("controller");
    }
        
    
    @FXML
    private SplitPane root;

    public void novaMedicao(String filiais, String dataInicio, String dataFim) {
    	geraListaVagas();

    	
        PostoseVagasAutorizadasWS tpvg = new PostoseVagasAutorizadasWS();
        List<Vaga> vagas = tpvg.buscaPostoseVagas(filiais, dataInicio, dataFim);
        ColaboradoresVinculadosWS tpcv = new ColaboradoresVinculadosWS();
        List<Colaborador> colaboradores = tpcv.buscaColaboradores(filiais, dataInicio, dataFim);
        AfastamentosWS afst = new AfastamentosWS();
        List<Afastamento> afastamentos = afst.buscaAfastamentos(filiais, dataInicio, dataFim);
        md = new Medicao(filiais, dataInicio, dataFim, vagas, colaboradores, afastamentos);

        
        vagaListController.initData(md);
     
    }

    public SplitPane getRoot() {
        return root;
    }
}
