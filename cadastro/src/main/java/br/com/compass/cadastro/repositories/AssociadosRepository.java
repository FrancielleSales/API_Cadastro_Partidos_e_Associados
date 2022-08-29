package br.com.compass.cadastro.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.cadastro.enums.AssociadosCargo;
import br.com.compass.cadastro.entities.Associados;
import org.springframework.stereotype.Repository;

@Repository
public interface AssociadosRepository extends JpaRepository <Associados, Long>{
	Page<Associados> findByCargo(AssociadosCargo cargoPolitico, Pageable sort);
	
    List<Associados> findByPartidos_Id(Long id);

}
