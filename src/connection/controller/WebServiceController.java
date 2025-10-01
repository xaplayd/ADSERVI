package connection.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import config.WebServiceConfig;

public class WebServiceController {


	public static WebServiceConfig autenticar() {
		WebServiceConfig tempWS = null;
		List<WebServiceConfig> listaParametros = new ArrayList<WebServiceConfig>();
		File arquivoWebServiceConfig = new File("C:\\WS\\data\\ws.cfg");
		try (BufferedReader brWebServiceConfig = new BufferedReader(new FileReader(arquivoWebServiceConfig))) {
			String item = brWebServiceConfig.readLine();
			while (item != null) {
				String[] fields = item.split(";");
				String nome = fields[0];
				String senha = fields[1];
				listaParametros.add(tempWS = new WebServiceConfig(nome, senha, 0));
				item = brWebServiceConfig.readLine();
			}
		} catch (IOException e) {
			e.getMessage();
		}
		return tempWS; 
	}
}