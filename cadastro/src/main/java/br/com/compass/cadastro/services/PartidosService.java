package br.com.compass.cadastro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import br.com.compass.cadastro.dtos.AssociadosDto;
import br.com.compass.cadastro.dtos.PartidosDto;
import br.com.compass.cadastro.enums.PartidosIdeologia;
import br.com.compass.cadastro.entities.Associados;
import br.com.compass.cadastro.entities.Partidos;
import br.com.compass.cadastro.repositories.AssociadosRepository;
import br.com.compass.cadastro.repositories.PartidosRepository;
import br.com.compass.cadastro.util.ConversorData;

@Service
public class PartidosService {
	
	private static final Logger log = LoggerFactory.getLogger(PartidosService.class);
	
	@Autowired
	private PartidosRepository repository;
	
	@Autowired
	private AssociadosRepository repositoryAssociados;
	
    
	// Adicionar partido
	public PartidosDto addNewPartidos(PartidosDto partidos) {

		log.info("addNewPartidos() - INICIO - Salvando o partido {}", partidos.getNome());
		Partidos savedPartidos = repository.save(new Partidos(partidos));
		log.info("addNewPartidos() - FIM - Partido salvo com o ID {}", partidos.getId());
		return new PartidosDto(savedPartidos);
	}

	// Localizar partido pelo Id
    public PartidosDto getPartidosById(Long partidosId) {
        log.info("getPartidosById() - INICIO - Buscando o partido pelo ID: {}", partidosId);
        Partidos partidos = getValidPartidosById(partidosId);
        log.info("getPartidosById() - FIM - Partido {} encontrado", partidos.getNome());
        return new PartidosDto(partidos);
    }
    
    // Validar procura do partido pelo Id
    private Partidos getValidPartidosById(long id) {
        Optional<Partidos> optionalPartidos = repository.findById(id);
        if (optionalPartidos.isPresent()) {
            return optionalPartidos.get();
        } else {
            log.warn("getValidPartidosById() - AVISO - Partido com ID: {} não existe na base", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID informado não localizado");
        }
    }
    
    // Listar partidos presentes no banco de dados
    public List<PartidosDto> getAllPartidos(){
    	List<Partidos> partidosEntityList = repository.findAll();
    	List<PartidosDto> partidosDto = new ArrayList<>();
    	
    	for (Partidos p : partidosEntityList) {
    		PartidosDto partido = new PartidosDto(p);
    		partidosDto.add(partido);
    	}
    	
    	return partidosDto;
    }
    
    // Atualizar o partido pelo Id
    public PartidosDto updatePartidosById(Long partidosId, PartidosDto partidos) {
        log.info("updatePartidosById() - INICIO - Atualizando o partido pelo ID: {}", partidosId);
        Partidos partidosEntity = getValidPartidosById(partidosId);
        partidosEntity.setNome(partidos.getNome() != null ? partidos.getNome() : partidosEntity.getNome());
        partidosEntity.setSigla(partidos.getSigla() != null ? partidos.getSigla() : partidosEntity.getSigla());
        partidosEntity.setIdeologia(partidos.getIdeologia() != null ? partidos.getIdeologia() : partidosEntity.getIdeologia());
        partidosEntity.setDataFundacao(partidos.getDataFundacao() != null ?
        				ConversorData.paraIso(partidos.getDataFundacao()) : partidosEntity.getDataFundacao());
        repository.save(partidosEntity);
        log.info("updatePartidosById() - FIM - Informações atualizadas");
        return new PartidosDto(partidosEntity);
    }
    
    // Deletar partido pelo Id
    public void deletePartidosById(Long partidosId) {
        log.info("deletePartidosById() - INICIO - Excluindo o partido pelo ID: {}", partidosId);
        Partidos partidos = getValidPartidosById(partidosId);
        repository.delete(partidos);
        log.info("deletePartidosById() - FIM - Partido excluido com sucesso");
    }
    
    // Listar associados presentes no partido desejado
    public List<AssociadosDto> getAssociadosByPartido(long id) {
    	List<Associados> associados = repositoryAssociados.findByPartidos_Id(id);
    	
    	List<AssociadosDto> dto = new ArrayList<>();
    	for (Associados a : associados) {
    		dto.add(new AssociadosDto(a));
    	}
    	
    	return dto;
    }
    
    // Converter para dto
    private Page<PartidosDto> convertToDto(Page<Partidos> partidos) {
    	return partidos.map(PartidosDto::new);
    }
    
    // Filtros
    public Page<PartidosDto> getAllPartidos(@RequestParam(required = false) PartidosIdeologia ideologia, 
    		@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        if (ideologia == null) {
            Page<Partidos> partidos = repository.findAll(pageable);
            return convertToDto(partidos);
        } else {
            Page<Partidos> partidos = repository.findByIdeologia(ideologia, pageable);
            return convertToDto(partidos);
        }
    }
}
