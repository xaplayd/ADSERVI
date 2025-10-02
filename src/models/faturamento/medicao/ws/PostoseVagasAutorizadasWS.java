package models.faturamento.medicao.ws;

import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.namespace.QName;

import config.WebServiceConfig;
import connection.controller.WebServiceController;
import jakarta.xml.ws.Service;
import models.faturamento.medicao.vaga.Vaga;
import util.webservices.fechamentos.ObjectFactory;
import util.webservices.fechamentos.RubiSynccomSeniorG5RhFpDougFechamentos;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasIn;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOut;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOuttabVagas;

public class PostoseVagasAutorizadasWS {

	public List<Vaga> buscaPostoseVagas(String filiais, String dataInicioPeriodo, String dataFimPeriodo) {
		List<Vaga> vgList = new ArrayList();
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
			SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasIn parametros = new SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasIn();

			parametros.setCodFilialCliente(
					factory.createSynccomSeniorG5RhFpDougFechamentosVagasAutorizadasInCodFilialCliente(filiais));
			parametros.setDataInicio(
					factory.createSynccomSeniorG5RhFpDougFechamentosVagasAutorizadasInDataInicio(dataInicioPeriodo));
			parametros
					.setDataFim(factory.createSynccomSeniorG5RhFpDougFechamentosVagasAutorizadasInDataFim(dataFimPeriodo));

			SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOut resposta = port.vagasAutorizadas(credential.getLogin(), credential.getSenha(), credential.getCripto(),
					parametros);

			if (resposta.getErroExecucao() != null) {
			    System.out.println("Erro: " + resposta.getErroExecucao().getValue());
			}

			// Agora acessa as vagas
			List<SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOuttabVagas> vagas = resposta.getTabVagas();

			if (vagas != null && !vagas.isEmpty()) {
				Integer tpid = 1;
				
			    for (SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOuttabVagas vaga : vagas) {
			        Integer codPlvTabVaga = vaga.getCodPlvTabVaga() != null ? vaga.getCodPlvTabVaga().getValue() : null;
			        String datQpoTabVaga = vaga.getDatQpoTabVaga() != null ? vaga.getDatQpoTabVaga().getValue() : null;
			        String desPosTabVaga = vaga.getDesPosTabVaga() != null ? vaga.getDesPosTabVaga().getValue() : null;
			        Integer estPosTabVaga = vaga.getEstPosTabVaga() != null ? vaga.getEstPosTabVaga().getValue() : null;
			        String posTraTabVaga = vaga.getPosTraTabVaga() != null ? vaga.getPosTraTabVaga().getValue() : null;
			        Integer qtdQpoTabVaga = vaga.getQtdQpoTabVaga() != null ? vaga.getQtdQpoTabVaga().getValue() : null;

			        System.out.println("---- Vaga ----");
			        System.out.println("Plano de Vagas: " + codPlvTabVaga);
			        System.out.println("Data Vigência: " + datQpoTabVaga);
			        System.out.println("Descrição: " + desPosTabVaga);
			        System.out.println("Estrutura: " + estPosTabVaga);
			        System.out.println("Código Posto: " + posTraTabVaga);
			        System.out.println("Qtde: " + qtdQpoTabVaga);
			        System.out.println("----------------");
			        System.out.println();
			        
			        for(int x = 0; x < qtdQpoTabVaga; x++) {
			        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			        	LocalDate inicioData = LocalDate.parse(dataInicioPeriodo, formatter);
			        	LocalDate fimData = LocalDate.parse(dataFimPeriodo, formatter);
			        	Double totalExecucao = 30.0;
			        	Vaga tpVaga = new Vaga(tpid, inicioData, fimData, totalExecucao, posTraTabVaga, desPosTabVaga);
			        	vgList.add(tpVaga);
			        	tpid++;
			        }
			    }
			} else {
			    System.out.println("Nenhuma vaga retornada.");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return vgList;
	}
}
