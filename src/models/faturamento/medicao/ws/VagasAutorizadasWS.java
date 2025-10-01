package models.faturamento.medicao.ws;

import java.net.URL;
import java.util.List;

import javax.xml.namespace.QName;

import config.WebServiceConfig;
import connection.controller.WebServiceController;
import jakarta.xml.ws.Service;
import util.webservices.fechamentos.ObjectFactory;
import util.webservices.fechamentos.RubiSynccomSeniorG5RhFpDougFechamentos;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasIn;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOut;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOuttabVagas;

public class VagasAutorizadasWS {

	public void buscaVagas() {
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
					factory.createSynccomSeniorG5RhFpDougFechamentosVagasAutorizadasInCodFilialCliente("1943"));
			parametros.setDataInicio(
					factory.createSynccomSeniorG5RhFpDougFechamentosVagasAutorizadasInDataInicio("01/08/2025"));
			parametros
					.setDataFim(factory.createSynccomSeniorG5RhFpDougFechamentosVagasAutorizadasInDataFim("31/08/2025"));

			SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOut resposta = port.vagasAutorizadas(credential.getLogin(), credential.getSenha(), credential.getCripto(),
					parametros);

			if (resposta.getErroExecucao() != null) {
			    System.out.println("Erro: " + resposta.getErroExecucao().getValue());
			}

			// Agora acessa as vagas
			List<SynccomSeniorG5RhFpDougFechamentosVagasAutorizadasOuttabVagas> vagas = resposta.getTabVagas();

			if (vagas != null && !vagas.isEmpty()) {
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
			    }
			} else {
			    System.out.println("Nenhuma vaga retornada.");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
