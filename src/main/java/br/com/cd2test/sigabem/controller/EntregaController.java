package br.com.cd2test.sigabem.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cd2test.sigabem.dto.EntregaDTO;
import br.com.cd2test.sigabem.entity.Entrega;
import br.com.cd2test.sigabem.exceptions.PersistException;
import br.com.cd2test.sigabem.exceptions.RegraNegocioException;
import br.com.cd2test.sigabem.service.EntregaService;
import br.com.cd2test.sigabem.utils.Mensagem;

@RestController
@RequestMapping("/api")
public class EntregaController {

	private EntregaService entregaService;
	
	public EntregaController(EntregaService entregaService) {
		this.entregaService = entregaService;
	}
	
	@PostMapping("/entregas")
	public ResponseEntity salvar(@Valid @RequestBody EntregaDTO entregaDTO) {
		
		try {		
			EntregaDTO entregaComFrete = entregaService.calcularFrete(entregaDTO);
			
			Entrega entrega = Entrega.builder()
					.peso(entregaComFrete.getPeso())
					.cepOrigem(entregaComFrete.getCepOrigem())
					.cepDestino(entregaComFrete.getCepDestino())
					.nomeDestinatario(entregaComFrete.getNomeDestinatario())
					.vlTotalFrete(entregaComFrete.getVlTotalFrete())
					.dataPrevistaEntrega(entregaComFrete.getDataPrevistaEntrega())
					.dataConsulta(LocalDate.now())
					.build();
			
			entregaService.save(entrega);
			
			return new ResponseEntity<Entrega>(entrega, HttpStatus.CREATED);
			
		} catch (PersistException p) {
			return ResponseEntity.badRequest().body(p.getMessage());
		} catch (RegraNegocioException r) {
			return ResponseEntity.badRequest().body(r.getMessage());
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}		
	}
	
	@DeleteMapping("/entregas/{entregaId}")
	public String deletar(@PathVariable long entregaId) {
		
		Entrega entrega = entregaService.findById(entregaId);
		
		if (entrega != null) {
			entregaService.delete(entregaId);
			return Mensagem.DELETE_CONFIRMATION;
		}
		return Mensagem.ERRO_AO_DELETAR;
		
	}
	
	@GetMapping("/entregas")
	public List<Entrega> listarTodos() {
		return entregaService.findAll();
	}
	
	@GetMapping("/entregas/{entregaId}")
	public Entrega buscarEntregaPorId(@PathVariable long entregaId) {
		Entrega entrega = entregaService.findById(entregaId);
		return entrega;
	}
	
}
