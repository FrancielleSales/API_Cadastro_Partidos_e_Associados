package br.com.compass.cadastro.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import br.com.compass.cadastro.dtos.AssociadosDto;
import br.com.compass.cadastro.enums.AssociadosCargo;
import br.com.compass.cadastro.enums.AssociadosSexo;
import br.com.compass.cadastro.util.ConversorData;

@Entity
public class Associados {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String nome;

	@Enumerated(EnumType.STRING)
	private AssociadosCargo cargoPolitico;

	private LocalDate dataNascimento;

	@Enumerated(EnumType.STRING)
	private AssociadosSexo sexo;

    @ManyToOne(optional = true, cascade = CascadeType.REFRESH)
    private Partidos partidos;
	
	public Associados() {
	}

	public Associados(long id, String nome, AssociadosCargo cargoPolitico, LocalDate dataNascimento, AssociadosSexo sexo,
			Partidos partidos) {
		this.id = id;
		this.nome = nome;
		this.cargoPolitico = cargoPolitico;
		this.dataNascimento = dataNascimento;
		this.sexo = sexo;
		this.partidos = partidos;
	}

	public Associados(AssociadosDto associados) {
		this.id = associados.getId();
		this.nome = associados.getNome();
		this.cargoPolitico = associados.getCargoPolitico();
		this.dataNascimento = ConversorData.paraIso(associados.getDataNascimento());
		this.sexo = associados.getSexo();
		this.partidos = associados.getPartido();
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public AssociadosSexo getSexo() {
		return sexo;
	}

	public void setSexo(AssociadosSexo sexo) {
		this.sexo = sexo;
	}

	public Partidos getPartidos() {
		return partidos;
	}

	public void setPartidos(Partidos partidos) {
		this.partidos = partidos;
	}
}
