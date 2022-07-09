package br.com.cd2test.sigabem.service;

import br.com.cd2test.sigabem.entity.Entrega;

public interface EntregaService {

	public Entrega save(Entrega entrega);
	public void delete(long entregaId);
	
}
