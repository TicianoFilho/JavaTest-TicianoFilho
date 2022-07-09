package br.com.cd2test.sigabem.service;

import static org.mockito.ArgumentMatchers.any;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import br.com.cd2test.sigabem.dao.EntregaRepository;
import br.com.cd2test.sigabem.entity.Entrega;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class EntregaServiceTest {

	@SpyBean
	EntregaServiceImpl entregaService;
	
	@MockBean
	EntregaRepository entregaRepository;
	
	@Test
	public void deveSalvarEntregaRepository() {
		
		//Cenário
		Entrega entrega = Entrega.builder()
				.peso(8)
				.cepDestino("53415-120r")
				.cepDestino("11111-222")
				.nomeDestinatario("Fool")
				.build();
		
		//Ação
		Mockito.when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);
		Entrega entregaSalva = entregaRepository.save(entrega);
		
		//Validação
		//System.out.println(entregaSalva);
		Assertions.assertNotNull(entregaSalva);
		
	}
	
	@Test
	public void deveSalvarEntregaService() {
		
		//Cenário
		Entrega entrega = Entrega.builder()
				.peso(8)
				.cepDestino("53415-120r")
				.cepDestino("11111-222")
				.nomeDestinatario("Fool")
				.build();	
		
		//Ação
		Mockito.when(entregaRepository.save(any(Entrega.class))).thenReturn(entrega);
		Entrega entregaSalva = entregaService.save(entrega);
		
		//Validação
		System.out.println(entregaSalva);
		Assertions.assertNotNull(entregaSalva);		
				
	}
	
}
