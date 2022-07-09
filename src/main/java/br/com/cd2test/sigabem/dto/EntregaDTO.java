package br.com.cd2test.sigabem.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EntregaDTO {
	
	private double peso;

	private String cepOrigem;
	
	private String cepDestino;
	
	private String nomeDestinatario;
	
	private BigDecimal vlTotalFrete;
	
	private LocalDate dataPrevistaEntrega;
	
	private LocalDate dataConsulta;
	
}
