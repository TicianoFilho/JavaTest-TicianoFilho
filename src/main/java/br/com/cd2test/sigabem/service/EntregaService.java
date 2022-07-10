package br.com.cd2test.sigabem.service;

import java.util.List;

import javax.validation.Valid;

import br.com.cd2test.sigabem.dto.EntregaDTO;
import br.com.cd2test.sigabem.entity.Entrega;
import br.com.cd2test.sigabem.exceptions.RegraNegocioException;

public interface EntregaService {

	public Entrega save(Entrega entrega);
	public void delete(long entregaId);
	public Entrega edit(Entrega entrega);
	public List<Entrega> findAll();
	public EntregaDTO calcularFrete(@Valid EntregaDTO entregaDTO) throws RegraNegocioException;
	public Entrega findById(long entregaId);
	
}
