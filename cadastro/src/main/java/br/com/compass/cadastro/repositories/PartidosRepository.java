package br.com.compass.cadastro.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.compass.cadastro.enums.PartidosIdeologia;
import br.com.compass.cadastro.entities.Partidos;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidosRepository extends JpaRepository <Partidos, Long>{
	Page<Partidos> findByIdeologia(PartidosIdeologia ideologia, Pageable sort);

}
