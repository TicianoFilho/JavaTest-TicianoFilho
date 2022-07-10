package br.com.cd2test.sigabem.service;

import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;

import br.com.cd2test.sigabem.dao.EntregaRepository;
import br.com.cd2test.sigabem.dto.CepDto;
import br.com.cd2test.sigabem.dto.EntregaDTO;
import br.com.cd2test.sigabem.entity.Entrega;
import br.com.cd2test.sigabem.exceptions.RegraNegocioException;
import net.bytebuddy.asm.Advice.Local;

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
		Assertions.assertNotNull(entregaSalva);		
				
	}
	
	@Test
	public void calcularFreteDddIguais() throws RegraNegocioException {
		//Tem que dar 50% no valor do frete e 1 dia para entrega.
		
		//Cenário
		EntregaDTO cepOrigem = EntregaDTO.builder()
				.peso(8)
				.cepOrigem("53415-120")  //ddd 81 PE
				.cepDestino("50030-903") //ddd 81 PE
				.nomeDestinatario("Ticiano Filho")
				.build();
				
		//Ação
		EntregaDTO entregaFinal = entregaService.calcularFrete(cepOrigem);
		
		//Validação
		Assertions.assertEquals(4, entregaFinal.getVlTotalFrete());
		Assertions.assertEquals(LocalDate.now().plusDays(1), entregaFinal.getDataPrevistaEntrega());
	}
	
	@Test
	public void calcularFreteUfIguaisDddDiferentes() throws RegraNegocioException {
		//Tem que dar 75% no valor do frete e 3 dias para entrega.
		
		//Cenário
		EntregaDTO cepOrigem = EntregaDTO.builder()
				.peso(8)
				.cepOrigem("53415-120")  //ddd 81 PE
				.cepDestino("56800-000") //ddd 87 PE
				.nomeDestinatario("Ticiano Filho")
				.build();
				
		//Ação
		EntregaDTO entregaFinal = entregaService.calcularFrete(cepOrigem);
		
		//Validação
		Assertions.assertEquals(2, entregaFinal.getVlTotalFrete());
		Assertions.assertEquals(LocalDate.now().plusDays(3), entregaFinal.getDataPrevistaEntrega());
	}
	
	@Test
	public void calcularFreteUfDiferentes() throws RegraNegocioException {
		//Tem que dar 8 no valor do frete e 10 dias para entrega.
		
		//Cenário
		EntregaDTO cepOrigem = EntregaDTO.builder()
				.peso(8)
				.cepOrigem("53415-120")  //ddd 81 PE
				.cepDestino("88040-310") //ddd 48 SC
				.nomeDestinatario("Ticiano Filho")
				.build();
				
		//Ação
		EntregaDTO entregaFinal = entregaService.calcularFrete(cepOrigem);
		
		//Validação
		Assertions.assertEquals(8, entregaFinal.getVlTotalFrete());
		Assertions.assertEquals(LocalDate.now().plusDays(10), entregaFinal.getDataPrevistaEntrega());
	}
	
}
