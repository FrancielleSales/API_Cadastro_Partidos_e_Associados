package br.com.compass.cadastro.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.compass.cadastro.dtos.AssociadosDto;
import br.com.compass.cadastro.dtos.PartidosDto;
import br.com.compass.cadastro.enums.PartidosIdeologia;
import br.com.compass.cadastro.services.PartidosService;

@RestController
@RequestMapping("/partidos")
public class PartidosController {
	
	private static final Logger log = LoggerFactory.getLogger(PartidosController.class);
	
	@Autowired
	private PartidosService service;
	
	@PostMapping
	public ResponseEntity <Object> createNewPartidos(@Valid @RequestBody PartidosDto partidos) {
		log.info("createNewStates() - INICIO - Chamando serviço");
		PartidosDto partidosDto = service.addNewPartidos(partidos);
		return ResponseEntity.ok(partidosDto);
	}

    @GetMapping
    public ResponseEntity<Page<PartidosDto>> findAll(PartidosIdeologia ideologia, Pageable pageable) {
        log.debug("findAll() - Chamando serviço");
        return ResponseEntity.ok(service.getAllPartidos(ideologia, pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PartidosDto> findPartidosById(@PathVariable Long id) {
        log.debug("findPartidosById() - Chamando serviço");
        return ResponseEntity.ok(service.getPartidosById(id));
    }
   
    @PutMapping("/{id}")
    public ResponseEntity<PartidosDto> updatePartidos(@PathVariable Long id, @RequestBody PartidosDto partidos) {
        log.debug("updatePartidos() - Chamando o serviço");
        return ResponseEntity.ok(service.updatePartidosById(id, partidos));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePartidos(@PathVariable Long id) {
        log.debug("deletePartidos() - Chamando o serviço");
        service.deletePartidosById(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/{id}/associados")
    public ResponseEntity<List<AssociadosDto>> findAssociadosByPartido(@PathVariable Long id) {
        log.debug("findAssociadosByPartido() - Chamando serviço");
        return ResponseEntity.ok(service.getAssociadosByPartido(id));
    }

}
