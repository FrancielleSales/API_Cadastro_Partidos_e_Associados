package br.com.compass.cadastro.services;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.compass.cadastro.enums.PartidosIdeologia;
import br.com.compass.cadastro.entities.Partidos;
import br.com.compass.cadastro.repositories.PartidosRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PartidosRepositoryTest {
	
    @Autowired
    private PartidosRepository repository;

    @Autowired
    private TestEntityManager em;
    
    @Test
    void testeFiltroIdeologia() {
    	Partidos partidos = new Partidos(0, "Ziah", "Z", PartidosIdeologia.ESQUERDA, LocalDate.now());
        em.persist(partidos);
        
        Page<Partidos> partidosList = repository.findByIdeologia(PartidosIdeologia.ESQUERDA, null);
        Partidos value = partidosList.toList().get(0);

        Assertions.assertNotNull(value);
        Assertions.assertEquals(PartidosIdeologia.ESQUERDA, value.getIdeologia());

    }

}
