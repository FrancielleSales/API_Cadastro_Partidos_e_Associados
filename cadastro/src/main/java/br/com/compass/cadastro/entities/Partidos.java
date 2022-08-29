package br.com.compass.cadastro.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import br.com.compass.cadastro.dtos.PartidosDto;
import br.com.compass.cadastro.util.ConversorData;
import br.com.compass.cadastro.enums.PartidosIdeologia;

@Entity
public class Partidos {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	private String sigla;

	@Enumerated(EnumType.STRING)
	private PartidosIdeologia ideologia;

	private LocalDate dataFundacao;

	@OneToMany(mappedBy = "partidos")
	private List<Associados> associados = new ArrayList<>();
	
	public Partidos() {
		}	

	public Partidos(long id, String nome, String sigla, PartidosIdeologia ideologia, LocalDate dataFundacao){
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;		
	}
	
	public Partidos(PartidosDto partidos) {
		this.id = partidos.getId();
		this.nome = partidos.getNome();
		this.sigla = partidos.getSigla();
		this.ideologia = partidos.getIdeologia();
		this.dataFundacao = ConversorData.paraIso(partidos.getDataFundacao());
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public PartidosIdeologia getIdeologia() {
		return ideologia;
	}

	public void setIdeologia(PartidosIdeologia ideologia) {
		this.ideologia = ideologia;
	}

	public LocalDate getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(LocalDate dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
}
