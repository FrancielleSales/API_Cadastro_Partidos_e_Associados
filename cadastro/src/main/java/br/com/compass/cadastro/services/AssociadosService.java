package br.com.compass.cadastro.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import br.com.compass.cadastro.dtos.AssociadosDto;
import br.com.compass.cadastro.dtos.VinculaAssociadoPartidoDto;
import br.com.compass.cadastro.enums.AssociadosCargo;
import br.com.compass.cadastro.entities.Associados;
import br.com.compass.cadastro.entities.Partidos;
import br.com.compass.cadastro.repositories.AssociadosRepository;
import br.com.compass.cadastro.repositories.PartidosRepository;
import br.com.compass.cadastro.util.ConversorData;

@Service
public class AssociadosService {

	private static final Logger log = LoggerFactory.getLogger(AssociadosService.class);
	
	@Autowired
	private AssociadosRepository repository;
	
    @Autowired
    private PartidosRepository repositoryPartido;
	
	// Adicionar associado
	public AssociadosDto addNewAssociados(AssociadosDto associados) {

		log.info("addNewAssociados() - INICIO - Salvando o associado {}", associados.getNome());
		Associados savedAssociados = repository.save(new Associados(associados));
		log.info("addNewAssociados() - FIM - Associado salvo com o ID {}", associados.getId());
		return new AssociadosDto(savedAssociados);
	}
	
	// Localizar associado pelo Id
    public AssociadosDto getAssociadosById(Long associadosId) {
        log.info("getAssociadosById() - INICIO - Buscando o associado pelo ID: {}", associadosId);
        Associados associados = getValidAssociadosById(associadosId);
        log.info("getAssociadosById() - FIM - Associado {} encontrado", associados.getNome());
        return new AssociadosDto(associados);
    }
    
    // Validar procura do associado pelo Id
    private Associados getValidAssociadosById(long id) {
        Optional<Associados> optionalAssociados = repository.findById(id);
        if (optionalAssociados.isPresent()) {
            return optionalAssociados.get();
        } else {
            log.warn("getValidAssociadosById() - AVISO - Não existe associado com ID {} na base", id);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ID informado não localizado");
        }
    }
    
    // Listar associados presentes no banco de dados
    public List<AssociadosDto> getAllAssociados(){
    	List<Associados> associadosEntityList = repository.findAll();
    	List<AssociadosDto> associadosDto = new ArrayList<>();
    	
    	for (Associados a : associadosEntityList) {
    		AssociadosDto associado = new AssociadosDto(a);
    		associadosDto.add(associado);
    	}
    	
    	return associadosDto;
    }
    
    // Atualizar o associado pelo Id
    public AssociadosDto updateAssociadosById(Long associadosId, AssociadosDto associados) {
        log.info("updateAssociadosById() - INICIO - Atualizando o associado pelo ID: {}", associadosId);
        Associados associadosEntity = getValidAssociadosById(associadosId);
        associadosEntity.setNome(associados.getNome() != null ? associados.getNome() : associadosEntity.getNome());
        associadosEntity.setCargo(associados.getCargoPolitico() != null ? associados.getCargoPolitico() : associadosEntity.getCargoPolitico());
        associadosEntity.setDataNascimento(associados.getDataNascimento() != null ? 
        		ConversorData.paraIso(associados.getDataNascimento()) : associadosEntity.getDataNascimento());
        associadosEntity.setSexo(associados.getSexo() != null ? associados.getSexo() : associadosEntity.getSexo());
        repository.save(associadosEntity);
        log.info("updateAssociadosById() - FIM - Informações atualizadas");
        return new AssociadosDto(associadosEntity);
    }
    
    // Deletar associado pelo Id
    public void deleteAssociadosById(Long associadosId) {
        log.info("deleteAssociadosById() - INICIO - Excluindo o associado pelo ID: {}", associadosId);
        Associados associados = getValidAssociadosById(associadosId);
        repository.delete(associados);
        log.info("deleteAssociadosById() - FIM - Associado excluido com sucesso");
    }
        
    // Vincular associado a partido
    public AssociadosDto addNewVinculaAssociadoPartidoDto(VinculaAssociadoPartidoDto vinculo) {
    	log.info("addNewVinculaAssociadoPartidoDto() - INICIO - Criando vinculo");
    	Associados associadoEntity = getValidAssociadosById(vinculo.getIdAssociado());	
        Partidos partidoEntity = repositoryPartido.findById(vinculo.getIdPartido()).get();
    	associadoEntity.setPartidos(partidoEntity);
    	repository.save(associadoEntity);
    	log.info("addNewVinculaAssociadoPartidoDto() - FIM - Finalizando criação do vinculo");
    	return new AssociadosDto(associadoEntity);
    }
    
    // Deletar associado do partido
    public void removerAssociadoPartido(Long idAssociados, Long idPartido) {
    	log.info("removerAssociadoPartido() - INICIO - Deletando associado do partido");
    	Associados associadosEntity = getValidAssociadosById(idAssociados);
    	associadosEntity.setPartidos(null);
    	repository.save(associadosEntity);
    	log.info("removerAssociadoPartido() - FIM - Associação excluída com sucesso");
    }
    
    // Converter para dto
    private Page<AssociadosDto> convertToDto(Page<Associados> associados) {
    	return associados.map(AssociadosDto::new);
    }
    
    // Filtros
    public Page<AssociadosDto> getAllAssociados(@RequestParam(required = false) AssociadosCargo cargoPolitico, 
    		@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        if (cargoPolitico == null) {
            Page<Associados> associados = repository.findAll(pageable);
            return convertToDto(associados);
        } else {
            Page<Associados> associados = repository.findByCargo(cargoPolitico, pageable);
            return convertToDto(associados);
        }
    }
}
