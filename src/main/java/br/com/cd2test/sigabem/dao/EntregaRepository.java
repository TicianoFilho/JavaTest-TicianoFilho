package br.com.cd2test.sigabem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.cd2test.sigabem.entity.Entrega;

@Repository
public interface EntregaRepository extends JpaRepository<Entrega, Long>{

}
