package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.exceptions.LibroNoEncontradoException;

public class CollectionPrestamo {
    // cambiado a private para encapsular
    private static final List<Prestamo> prestamos = new ArrayList<>();

    public static void guardarPrestamo(Prestamo prestamo) {
        if (prestamo == null) {
            throw new IllegalArgumentException("El préstamo no puede ser null");
        }
        prestamos.add(prestamo);
    }

    public static Prestamo buscarPrestamo(int id) throws LibroNoEncontradoException {
        for (Prestamo p : prestamos) {
            // si getId() devuelve Long/Integer, adaptar la comparación
            if (p.getId() == id) return p;
        }
        throw new LibroNoEncontradoException("Préstamo con id '" + id + "' no encontrado.");
    }

    public static void mostrarPrestamos() {
        if (prestamos.isEmpty()) {
            System.out.println("No hay préstamos registrados.");
            return;
        }
        for (Prestamo p : prestamos) {
            p.mostrarDatos();
            System.out.println();
        }
    }
}