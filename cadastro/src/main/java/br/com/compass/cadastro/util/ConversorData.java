package br.com.compass.cadastro.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class ConversorData {
	
	 public static String paraBr (LocalDate dataIso) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return dataIso.format(formato);
	}

	public static LocalDate paraIso (String dataBr) {
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return LocalDate.parse(dataBr, formato);
	}
}