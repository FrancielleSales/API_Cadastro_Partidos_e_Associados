package br.com.compass.cadastro.services;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.com.compass.cadastro.enums.AssociadosCargo;
import br.com.compass.cadastro.enums.AssociadosSexo;
import br.com.compass.cadastro.enums.PartidosIdeologia;
import br.com.compass.cadastro.entities.Associados;
import br.com.compass.cadastro.entities.Partidos;
import br.com.compass.cadastro.repositories.AssociadosRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class AssociadosRepositoryTest {
	
    @Autowired
    private AssociadosRepository repository;

    @Autowired
    private TestEntityManager em;
    
    @Test
    void findByPartido_Id() {

        Partidos partidos = new Partidos(0, "Ziah", "Z", PartidosIdeologia.ESQUERDA, LocalDate.now());
        em.persist(partidos);
        Associados associados = new Associados(0, "Ziah", AssociadosCargo.PRESIDENTE, LocalDate.now(), 
        		AssociadosSexo.MASCULINO, partidos);
        em.persist(associados);

        List<Associados> partidos_id = repository.findByPartidos_Id(associados.getPartidos().getId());
        Associados valor = partidos_id.get(0);
        
        System.out.println(valor.getNome());

        Assertions.assertNotNull(valor);
        Assertions.assertEquals(1, valor.getId());
    }

}
