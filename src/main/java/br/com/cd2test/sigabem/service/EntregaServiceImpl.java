package br.com.cd2test.sigabem.service;

import org.springframework.stereotype.Service;

import br.com.cd2test.sigabem.dao.EntregaRepository;
import br.com.cd2test.sigabem.entity.Entrega;
import br.com.cd2test.sigabem.exceptions.PersistException;
import br.com.cd2test.sigabem.utils.Mensagem;

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
		} catch (Exception e) {
			throw new PersistException(Mensagem.ERRO_AO_SALVAR);
		}
		
		return entregaSalva;
	}

	@Override
	public void delete(long entregaId) {
		try {
			entregaRepository.deleteById(entregaId);
		} catch (Exception e) {
			throw new PersistException(Mensagem.ERRO_AO_DELETAR);
		}
		
	}

	

}
