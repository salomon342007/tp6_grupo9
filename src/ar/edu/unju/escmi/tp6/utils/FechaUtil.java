package ar.edu.unju.escmi.tp6.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FechaUtil {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    // Convierte String dd/MM/yyyy a LocalDate
    public static LocalDate convertirStringLocalDate(String fechaStr) throws DateTimeParseException {
        return LocalDate.parse(fechaStr, FORMATTER);
    }
}
