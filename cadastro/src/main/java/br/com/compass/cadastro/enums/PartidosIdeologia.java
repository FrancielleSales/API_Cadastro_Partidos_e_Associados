package br.com.compass.cadastro.enums;

import br.com.compass.cadastro.advices.GenericException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum PartidosIdeologia {
	
	DIREITA("Direita"),
	ESQUERDA("Esquerda"),
	CENTRO("Centro");

	private String ideologia;

	PartidosIdeologia(String ideologia) {

		this.ideologia = ideologia;
	}

	public String returnPartidosIdeologia() {

		return this.ideologia;
	}

	@JsonCreator
	public static PartidosIdeologia decode(final String ideologia) throws GenericException {
		return Stream.of(PartidosIdeologia.values()).filter(
				targetEnum -> targetEnum.ideologia.equals(ideologia)
		).findFirst().orElseThrow(
				() -> new GenericException("Invalid value:" + ideologia));
	}

	@JsonValue
	public String getIdeologia() {

		return ideologia;
	}
}
