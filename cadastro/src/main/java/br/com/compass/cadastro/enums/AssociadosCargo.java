package br.com.compass.cadastro.enums;

import br.com.compass.cadastro.advices.GenericException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AssociadosCargo {
	
	VEREADOR("Vereador"),
	PREFEITO("Prefeito"),
	DEPUTADO_ESTADUAL("Deputado Estadual"),
	DEPUTADO_FEDERAL("Deputado Federal"),
	GOVERNADOR("Governador"),
	SENADOR("Senador"),
	PRESIDENTE("Presidente"),
	NENHUM("Nenhum");

	private String cargoPolitico;

	AssociadosCargo(String cargoPolitico) {

		this.cargoPolitico = cargoPolitico;
	}

	public String returnAssociadosCargo() {

		return this.cargoPolitico;
	}

	@JsonCreator
	public static AssociadosCargo decode(final String cargoPolitico) throws GenericException {
		return Stream.of(AssociadosCargo.values()).filter(
				targetEnum -> targetEnum.cargoPolitico.equals(cargoPolitico)
		).findFirst().orElseThrow(
				() -> new GenericException("Invalid value:" + cargoPolitico));
	}

	@JsonValue
	public String getCargoPolitico() {

		return cargoPolitico;
	}

}
