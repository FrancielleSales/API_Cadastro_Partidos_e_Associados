package br.com.compass.cadastro.controllers;

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
import br.com.compass.cadastro.dtos.VinculaAssociadoPartidoDto;
import br.com.compass.cadastro.enums.AssociadosCargo;
import br.com.compass.cadastro.services.AssociadosService;

@RestController
@RequestMapping("/associados")
public class AssociadosController {
	
	private static final Logger log = LoggerFactory.getLogger(AssociadosController.class);
	
	@Autowired
	private AssociadosService service;
	
    @GetMapping
    public ResponseEntity<Page<AssociadosDto>> findAll(AssociadosCargo cargoPolitico, Pageable pageable) {
        log.debug("findAll() - Chamando serviço");
        return ResponseEntity.ok(service.getAllAssociados(cargoPolitico, pageable));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<AssociadosDto> findAssociadosById(@PathVariable Long id) {
        log.debug("findAssociadosById() - Chamando serviço");
        return ResponseEntity.ok(service.getAssociadosById(id));
    }
	
	@PostMapping
	public ResponseEntity <AssociadosDto> createNewAssociados(@RequestBody AssociadosDto associados) {
		log.info("createNewAssociados() - INICIO - Chamando serviço");
		AssociadosDto associadosDto = service.addNewAssociados(associados);
		return ResponseEntity.ok(associadosDto);
	}
   
	@PostMapping("/partidos")
	public ResponseEntity <AssociadosDto> createNewVinculo(@RequestBody VinculaAssociadoPartidoDto vinculo) {
		log.info("createNewVinculo() - INICIO - Chamando serviço");
		AssociadosDto associadosDto= service.addNewVinculaAssociadoPartidoDto(vinculo);
		return ResponseEntity.ok(associadosDto);
	}
	
    @PutMapping("/{id}")
    public ResponseEntity<AssociadosDto> updateAssociados(@PathVariable Long id, @RequestBody AssociadosDto associados) {
        log.debug("updateAssociados() - Chamando o serviço");
        return ResponseEntity.ok(service.updateAssociadosById(id, associados));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociados(@PathVariable Long id) {
        log.debug("deleteAssociados() - Chamando o serviço");
        service.deleteAssociadosById(id);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{idAssociado}/partidos/{idPartido}")
    public ResponseEntity<Void> deleteAssociacao(@PathVariable Long idAssociado, @PathVariable Long idPartido) {
        service.removerAssociadoPartido(idAssociado, idPartido);
        return ResponseEntity.noContent().build();
    }

}
