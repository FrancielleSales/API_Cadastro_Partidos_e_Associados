package br.com.compass.cadastro.dtos;

import br.com.compass.cadastro.enums.PartidosIdeologia;
import br.com.compass.cadastro.entities.Partidos;
import br.com.compass.cadastro.util.ConversorData;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PartidosDto {
	
	private long id;
	@NotBlank(message = "O campo nome não pode ser deixado em branco ou ser preenchido com null")
	private String nome;

	@NotBlank(message = "O campo sigla não pode ser deixado em branco ou ser preenchido com null")
	private String sigla;

	@NotNull(message = "The statusPagamento field must be filled in with one of the available options: " +
			"Esquerda, Direita ou Centro")
	private PartidosIdeologia ideologia;

	@NotBlank(message = "O campo dataFundacao não pode ser deixado em branco ou ser preenchido com null")
	private String dataFundacao;
	
	public PartidosDto() {
	}
	
	public PartidosDto(long id, String nome, String sigla, PartidosIdeologia ideologia, String dataFundacao) {
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.ideologia = ideologia;
		this.dataFundacao = dataFundacao;
	}

	public PartidosDto(Partidos savedPartidos) {
		this.id = savedPartidos.getId();
		this.nome = savedPartidos.getNome();
		this.sigla = savedPartidos.getSigla();
		this.ideologia = savedPartidos.getIdeologia();
		this.dataFundacao =  ConversorData.paraBr(savedPartidos.getDataFundacao());
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

	public String getDataFundacao() {
		return dataFundacao;
	}

	public void setDataFundacao(String dataFundacao) {
		this.dataFundacao = dataFundacao;
	}
}
