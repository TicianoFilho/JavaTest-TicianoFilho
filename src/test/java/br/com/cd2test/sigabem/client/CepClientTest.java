package br.com.cd2test.sigabem.client;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import br.com.cd2test.sigabem.dto.CepDto;

@SpringBootTest
public class CepClientTest {

	@SpyBean
	CepClient cepClient;
	
	@Test
	public void deveTrazerOJsonComoStringDaApiViaCep() {
		
		//Cenário
		var cep = "53415120";
		CepDto cepDto = null;
		
		//Ação
		cepDto = cepClient.getJsonAsStrFromViaCep(cep);
		
		//Validação
		//System.out.println(cepDto);
		Assertions.assertNotNull(cepDto);
	}
}
