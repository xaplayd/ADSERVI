package models.faturamento.medicao.ws;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import config.WebServiceConfig;
import connection.controller.WebServiceController;
import jakarta.xml.ws.Service;
import models.faturamento.medicao.colaborador.Colaborador;
import util.webservices.fechamentos.ObjectFactory;
import util.webservices.fechamentos.RubiSynccomSeniorG5RhFpDougFechamentos;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosIn;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOut;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores;

public class ColaboradoresVinculadosWS {

	public List<Colaborador> buscaColaboradores(String filiais, String dataInicioPeriodo, String dataFimPeriodo) {
		List<Colaborador> coList = new ArrayList();
		try {
			// Habilita o dump de mensagens SOAP
			/*System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
			System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
			System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
			System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");*/

			// URL do WSDL (ajuste para a URL real do serviço)
			URL wsdlURL = new URL(
					"http://snrsj:8080/g5-senior-services/rubi_Synccom.senior.g5.rh.fp.doug.fechamentos?wsdl");

			// QName (tem que casar com o targetNamespace e o serviceName gerados)
			QName SERVICE_NAME = new QName("http://services.senior.com.br", "g5-senior-services");
			QName PORT_NAME = new QName("http://services.senior.com.br",
					"rubi_Synccom.senior.g5.rh.fp.doug.fechamentosPort");

			// Criando o serviço
			Service service = Service.create(wsdlURL, SERVICE_NAME);
			RubiSynccomSeniorG5RhFpDougFechamentos port = service.getPort(PORT_NAME,
					RubiSynccomSeniorG5RhFpDougFechamentos.class);

			WebServiceController wsCont = new WebServiceController();
			WebServiceConfig credential = wsCont.autenticar();
			
			ObjectFactory factory = new ObjectFactory();
			SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosIn parametros = new SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosIn();

			parametros.setCodFilialCliente(
					factory.createSynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosInCodFilialCliente(filiais));
			parametros.setDataInicio(
					factory.createSynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosInDataInicio(dataInicioPeriodo));
			parametros
					.setDataFim(factory.createSynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosInDataFim(dataInicioPeriodo));

			SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOut resposta = port.colaboradoresVinculados(credential.getLogin(), credential.getSenha(), credential.getCripto(),
					parametros);

			if (resposta.getErroExecucao() != null) {
			    System.out.println("Erro: " + resposta.getErroExecucao().getValue());
			}

			// Agora acessa os colaboradores
			List<SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores> colaboradores = resposta.getTabColaboradores();

			if (colaboradores != null && !colaboradores.isEmpty()) {
			    for (SynccomSeniorG5RhFpDougFechamentosColaboradoresVinculadosOuttabColaboradores colaborador : colaboradores) {
			        Integer numCadTabEfe = colaborador.getNumCadTabEfe() != null ? colaborador.getNumCadTabEfe().getValue() : null;
			        String nomFunTabEfe = colaborador.getNomFunTabEfe() != null ? colaborador.getNomFunTabEfe().getValue() : null;
			        String posTraTabEfe = colaborador.getPosTraTabEfe() != null ? colaborador.getPosTraTabEfe().getValue() : null;
			        String optaVtTabEfe = colaborador.getOptaVtTabEfe() != null ? colaborador.getOptaVtTabEfe().getValue() : null;
			      

			        System.out.println("---- Colaborador ----");
			        System.out.println("Matrícula: " + numCadTabEfe);
			        System.out.println("Nome: " + nomFunTabEfe);
			        System.out.println("Código posto: " + posTraTabEfe);
			        System.out.println("Optante VT: " + optaVtTabEfe);
			        System.out.println("----------------");
			        System.out.println();
			        
			        Colaborador tempCo = new Colaborador(numCadTabEfe, nomFunTabEfe, posTraTabEfe, optaVtTabEfe);
			        coList.add(tempCo);
			        
			    }
			} else {
			    System.out.println("Nenhuma colaborador retornado.");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	return coList;
	
	}
}
