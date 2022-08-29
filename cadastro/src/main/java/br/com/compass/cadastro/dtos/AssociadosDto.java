package br.com.compass.cadastro.dtos;

import br.com.compass.cadastro.enums.AssociadosCargo;
import br.com.compass.cadastro.enums.AssociadosSexo;
import br.com.compass.cadastro.entities.Associados;
import br.com.compass.cadastro.entities.Partidos;
import br.com.compass.cadastro.util.ConversorData;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AssociadosDto {

	private long id;

	@NotBlank(message = "O campo nome não pode ser deixado em branco ou ser preenchido com null")
	private String nome;

	@NotNull(message = "The statusPagamento field must be filled in with one of the available options: " +
			"Vereador, Prefeito, Deputado Estadual, Deputado Federal, Governador, Senador, Presidente ou Nenhum")
	private AssociadosCargo cargoPolitico;

	@NotBlank(message = "O campo dataNascimento não pode ser deixado em branco ou ser preenchido com null")
	private String dataNascimento;

	@NotNull(message = "The statusPagamento field must be filled in with one of the available options: " +
			"Feminino ou Masculino")
	private AssociadosSexo sexo;

	@Valid
	@NotNull(message = "O campo partido não pode ser deixado nulo")
	private Partidos partido;
	
	public AssociadosDto() {
	}

	public AssociadosDto(long id, String nome, AssociadosCargo cargoPolitico, String dataNascimento, AssociadosSexo sexo,
			Partidos partido) {
		this.id = id;
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.partido = partido;
	}

	public AssociadosDto(Associados savedAssociados) {
		this.id = savedAssociados.getId();
		this.nome = savedAssociados.getNome();
		this.cargoPolitico = savedAssociados.getCargoPolitico();
		this.dataNascimento = ConversorData.paraBr(savedAssociados.getDataNascimento());
		this.sexo = savedAssociados.getSexo();
		this.partido = savedAssociados.getPartidos();
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

	public AssociadosCargo getCargoPolitico() {
		return cargoPolitico;
	}

	public void setCargo(AssociadosCargo cargoPolitico) {
		this.cargoPolitico = cargoPolitico;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public AssociadosSexo getSexo() {
		return sexo;
	}

	public void setSexo(AssociadosSexo sexo) {
		this.sexo = sexo;
	}

	public Partidos getPartido() {
		return partido;
	}

	public void setPartido(Partidos partido) {
		this.partido = partido;
	}
	
}
