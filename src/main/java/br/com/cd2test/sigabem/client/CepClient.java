package br.com.cd2test.sigabem.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.cd2test.sigabem.dto.CepDto;

public class CepClient {

	private HttpURLConnection connection;
	private BufferedReader reader;
	private StringBuffer responseContent = new StringBuffer();
	private String jsonAsStr;
	private CepDto cepDto = null;
	
	public CepDto getJsonAsStrFromViaCep(String cep) {
		
		URL url;		
		try {
			url = new URL(String.format("https://viacep.com.br/ws/%s/json/", cep));
			connection = (HttpURLConnection) url.openConnection();
			
			if (connection.getResponseCode() >= 200 || connection.getResponseCode() <= 299) {
				
				reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				String line;
				while ((line = reader.readLine()) != null) {
					responseContent.append(line);
				}
				
				reader.close();
				
				jsonAsStr = responseContent.toString();	
				
				ObjectMapper mapper = new ObjectMapper();
				cepDto = mapper.readValue(jsonAsStr, CepDto.class);
			}
			
		} catch (MalformedURLException m) {
			m.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		return cepDto;
	}
}
