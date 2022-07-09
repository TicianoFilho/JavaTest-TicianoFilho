package br.com.cd2test.sigabem.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cd2test.sigabem.dto.EntregaDTO;
import br.com.cd2test.sigabem.entity.Entrega;
import br.com.cd2test.sigabem.exceptions.PersistException;
import br.com.cd2test.sigabem.service.EntregaService;

@RestController
@RequestMapping("/api")
public class EntregaController {

	private EntregaService entregaService;
	
	public EntregaController(EntregaService entregaService) {
		this.entregaService = entregaService;
	}
	
	@PostMapping("/entregas")
	public ResponseEntity salvar(@Valid @RequestBody EntregaDTO entregaDTO) {
		
		Entrega entrega = Entrega.builder()
				.peso(entregaDTO.getPeso())
				.cepOrigem(entregaDTO.getCepOrigem())
				.cepDestino(entregaDTO.getCepDestino())
				.nomeDestinatario(entregaDTO.getNomeDestinatario())
				.build();
		
		try {
			System.out.println("Entrega: " + entrega);
			entregaService.save(entrega);
			return new ResponseEntity<Entrega>(entrega, HttpStatus.CREATED);
		} catch (PersistException p) {
			return ResponseEntity.badRequest().body(p.getMessage());
		}
		
	}
}
