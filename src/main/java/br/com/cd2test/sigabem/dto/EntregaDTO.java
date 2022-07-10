package br.com.cd2test.sigabem.dto;

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
	
	private double vlTotalFrete;
	
	private LocalDate dataPrevistaEntrega;
	
	private LocalDate dataConsulta;
	
}
