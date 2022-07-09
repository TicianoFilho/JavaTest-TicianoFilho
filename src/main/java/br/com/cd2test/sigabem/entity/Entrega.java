package br.com.cd2test.sigabem.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_entrega")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Entrega {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private double peso;
	
	@Column(name = "cep_origem")
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	private String cepOrigem;
	
	@Column(name = "cep_destino")
	@Pattern(regexp = "[0-9]{5}-[0-9]{3}")
	private String cepDestino;
	
	@Column(name = "nome_destinatario")
	private String nomeDestinatario;
	
	@Column(name = "vl_total_frete")
	private BigDecimal vlTotalFrete;
	
	@Column(name = "data_prevista_entrega")
	private LocalDate dataPrevistaEntrega;
	
	@Column(name = "data_Consulta")
	private LocalDate dataConsulta;
}
