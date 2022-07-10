package br.com.cd2test.sigabem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.cd2test.sigabem.client.CepClient;
import br.com.cd2test.sigabem.dao.EntregaRepository;
import br.com.cd2test.sigabem.dto.CepDto;
import br.com.cd2test.sigabem.dto.EntregaDTO;
import br.com.cd2test.sigabem.entity.Entrega;
import br.com.cd2test.sigabem.exceptions.PersistException;
import br.com.cd2test.sigabem.exceptions.RegraNegocioException;
import br.com.cd2test.sigabem.utils.Mensagem;
import br.com.cd2test.sigabem.utils.Parametros;

@Service
public class EntregaServiceImpl implements EntregaService {

	private EntregaRepository entregaRepository;
	
	public EntregaServiceImpl(EntregaRepository entregaRepository) {
		this.entregaRepository = entregaRepository;
	}
	
	@Override
	public Entrega save(Entrega entrega) {
		
		Entrega entregaSalva = null;
		try {
			entregaSalva = entregaRepository.save(entrega);
		} catch (PersistException p) {
			throw new PersistException(Mensagem.ERRO_AO_SALVAR);
		}		
		return entregaSalva;
	}

	@Override
	public void delete(long entregaId) {
		try {
			entregaRepository.deleteById(entregaId);
		} catch (PersistException p) {
			throw new PersistException(Mensagem.ERRO_AO_DELETAR);
		}
		
	}
	
	
	
	//usado para verificar se os DDDs são iguais e se os UFs são iguais
	private boolean saoIguais(String origem, String destino) throws RegraNegocioException {
		
		if (origem.isBlank() || destino.isBlank()) {
			throw new RegraNegocioException(Mensagem.ERRO_AO_VALIDAR_COMPARACAO);
		}
		
		if (origem.equals(destino)) {
			return true;
		}	
		return false;
	}
	
	public EntregaDTO calcularFrete(EntregaDTO entrega) throws RegraNegocioException {
		
		double valorFreteBruto = (Parametros.VALOR_FRETE * entrega.getPeso());
		double valorFreteFinal = 0;
		int diasParaEntrega = 0;
		entrega.setDataConsulta(LocalDate.now());
		
		CepDto objOrigem = new CepClient().getFromJSON(entrega.getCepOrigem());
		CepDto objDestino = new CepClient().getFromJSON(entrega.getCepDestino());
		
		if (saoIguais(objOrigem.getDdd(), objDestino.getDdd())) { //Se os DDDs iguais = desconto de 50%			
			valorFreteFinal = (valorFreteBruto * 0.5);
			diasParaEntrega = 1;		
		} else if (saoIguais(objOrigem.getUf(), objDestino.getUf())) { //Se DDDs diferentes e UFs iguais = desconto 75%
			valorFreteFinal = (valorFreteBruto * 0.25); 
			diasParaEntrega = 3;
		} else if (!saoIguais(objOrigem.getUf(), objDestino.getUf())) { //Se UFs diferentes
			valorFreteFinal = valorFreteBruto;
			diasParaEntrega = 10;
		}
		
		entrega.setVlTotalFrete(valorFreteFinal);
		entrega.setDataPrevistaEntrega(entrega.getDataConsulta().plusDays(diasParaEntrega));		
		
		return entrega;
	}

	@Override
	public Entrega edit(Entrega entrega) {

		Entrega entregaEditada = null;
		try {
			entregaEditada = entregaRepository.save(entrega);
		} catch (PersistException p) {
			throw new PersistException(Mensagem.ERRO_AO_EDITAR);
		}		
		return entregaEditada;
	}

	@Override
	public List<Entrega> findAll() {
		try {
			return entregaRepository.findAll();		
		} catch (PersistException p) {
			throw new PersistException(Mensagem.ERRO_AO_LISTAR);
		}
	}

	@Override
	public Entrega findById(long entregaId) {
		Optional<Entrega> entregaOptional = entregaRepository.findById(entregaId);
		if (!entregaOptional.isPresent()) {
			throw new PersistException(Mensagem.ERRO_AO_BUSCAR_REGISTRO);
		}		
		return entregaOptional.get();
	}
	

}
