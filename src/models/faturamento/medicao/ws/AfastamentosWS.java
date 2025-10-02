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
import models.faturamento.medicao.afastamentos.Afastamento;
import models.faturamento.medicao.colaborador.Colaborador;
import util.webservices.fechamentos.ObjectFactory;
import util.webservices.fechamentos.RubiSynccomSeniorG5RhFpDougFechamentos;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosAfastamentosIn;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosAfastamentosOut;
import util.webservices.fechamentos.SynccomSeniorG5RhFpDougFechamentosAfastamentosOuttabAfastamentos;

public class AfastamentosWS {

	public List<Afastamento> buscaAfastamentos(String filiais, String dataInicioPeriodo, String dataFimPeriodo) {
		List<Afastamento> afastList = new ArrayList();
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
			SynccomSeniorG5RhFpDougFechamentosAfastamentosIn parametros = new SynccomSeniorG5RhFpDougFechamentosAfastamentosIn();

			parametros.setCodFilialCliente(
					factory.createSynccomSeniorG5RhFpDougFechamentosAfastamentosInCodFilialCliente(filiais));
			parametros.setDataInicio(
					factory.createSynccomSeniorG5RhFpDougFechamentosAfastamentosInDataInicio(dataInicioPeriodo));
			parametros
					.setDataFim(factory.createSynccomSeniorG5RhFpDougFechamentosAfastamentosInDataFim(dataFimPeriodo));

			SynccomSeniorG5RhFpDougFechamentosAfastamentosOut resposta = port.afastamentos(credential.getLogin(), credential.getSenha(), credential.getCripto(),
					parametros);

			if (resposta.getErroExecucao() != null) {
			    System.out.println("Erro: " + resposta.getErroExecucao().getValue());
			}

			// Agora acessa os afastamentos
			List<SynccomSeniorG5RhFpDougFechamentosAfastamentosOuttabAfastamentos> afastamentos = resposta.getTabAfastamentos();

			if (afastamentos != null && !afastamentos.isEmpty()) {
			    for (SynccomSeniorG5RhFpDougFechamentosAfastamentosOuttabAfastamentos afastamento : afastamentos) {
			        Integer numCadTabAfast = afastamento.getNumCadTabAfast() != null ? afastamento.getNumCadTabAfast().getValue() : null;
			        String nomFunTabAfast = afastamento.getNomFunTabAfast() != null ? afastamento.getNomFunTabAfast().getValue() : null;
			        String desSitTabAfast = afastamento.getDesSitTabAfast() != null ? afastamento.getDesSitTabAfast().getValue() : null;
			        Integer sitAfaTabAfast = afastamento.getSitAfaTabAfast() != null ? afastamento.getSitAfaTabAfast().getValue() : null;
			        String datAfaTabAfast = afastamento.getDatAfaTabAfast() != null ? afastamento.getDatAfaTabAfast().getValue() : null;
			        String horAfaTabAfast = afastamento.getHorAfaTabAfast()  != null ? afastamento.getHorAfaTabAfast().getValue() : null;
			        String datTerTabAfast = afastamento.getDatTerTabAfast() != null ? afastamento.getDatTerTabAfast().getValue() : null;
			        String horTerTabAfast = afastamento.getHorTerTabAfast() != null ? afastamento.getHorTerTabAfast().getValue() : null;
			     
			        
			        System.out.println("---- Afastamento ----");
			        System.out.println("Matrícula: " + numCadTabAfast);
			        System.out.println("Nome: " + nomFunTabAfast);
			        System.out.println("Afastamento " + sitAfaTabAfast + " - " + desSitTabAfast);
			        System.out.println("Data Início: " + datAfaTabAfast);
			        System.out.println("Hora Início: " + horAfaTabAfast);
			        System.out.println("Data Término: " + datTerTabAfast);
			        System.out.println("Hora Término: " + horTerTabAfast);
			        System.out.println("----------------");
			        System.out.println();
			        
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			        LocalDate ini = LocalDate.parse(datAfaTabAfast, formatter);
			        LocalDate ter = null;
			        
			        if(datTerTabAfast != null) {
			        	ter = LocalDate.parse(datTerTabAfast, formatter);
			        }
			        
			        Afastamento tpAfast = new Afastamento(numCadTabAfast, nomFunTabAfast, sitAfaTabAfast, desSitTabAfast, ini, horAfaTabAfast,
			        		ter, horTerTabAfast); 
			        
			        afastList.add(tpAfast);
			    }
			} else {
			    System.out.println("Nenhum afastamento retornado.");
			}
			
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return afastList;
	}
}
