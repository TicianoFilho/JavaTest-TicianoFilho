package br.com.cd2test.sigabem.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CepDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cep;
	private String logradouro;
	private String complemento;
	private String bairro;
	private String localidade;
	private String uf;
	private String ibge;
	private String gia;
	private String ddd;
	private String siafi;
	
	
}

/*
 * Exemplo do json
 * 
 * "cep": "01001-000",
 * "logradouro": "Praça da Sé",
 * "complemento": "lado ímpar",
 * "bairro": "Sé",
 * "localidade": "São Paulo",
 * "uf": "SP",
 * "ibge": "3550308",
 * "gia": "1004",
 * "ddd": "11",
 * "siafi": "7107"

*/
