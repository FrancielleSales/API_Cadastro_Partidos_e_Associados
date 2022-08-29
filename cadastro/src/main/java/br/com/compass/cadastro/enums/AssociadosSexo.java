package br.com.compass.cadastro.enums;

import br.com.compass.cadastro.advices.GenericException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum AssociadosSexo {
	
	FEMININO("Feminino"),
	MASCULINO("Masculino");

	private String sexo;

	AssociadosSexo(String sexo) {

		this.sexo = sexo;
	}

	public String returnAssociadosSexo() {

		return this.sexo;
	}

	@JsonCreator
	public static AssociadosSexo decode(final String sexo) throws GenericException {
		return Stream.of(AssociadosSexo.values()).filter(
				targetEnum -> targetEnum.sexo.equals(sexo)
		).findFirst().orElseThrow(
				() -> new GenericException("Invalid value:" + sexo));
	}

	@JsonValue
	public String getSexo() {

		return sexo;
	}
}

