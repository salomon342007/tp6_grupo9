package ar.edu.unju.escmi.tp6.collections;

import java.util.ArrayList;
import java.util.List;

import ar.edu.unju.escmi.tp6.dominio.Prestamo;
import ar.edu.unju.escmi.tp6.exceptions.PrestamoNoEncontradoException;

public class CollectionPrestamo {
    // lista encapsulada de préstamos
    private static List<Prestamo> prestamos = new ArrayList<>();

    // Guarda un préstamo
    public static void guardarPrestamo(Prestamo prestamo) {
        prestamos.add(prestamo);
    }

    // Busca un préstamo por id o lanza excepción
    public static Prestamo buscarPrestamo(int id) throws PrestamoNoEncontradoException {
        for (Prestamo p : prestamos) {
            if (p.getId() == id)
                return p;
        }
        throw new PrestamoNoEncontradoException("Préstamo con id '" + id + "' no encontrado.");
    }

    // Muestra préstamos
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

    // Devuelve próximo id disponible
    public static int obtenerProximoId() {
        return prestamos.size() + 1;
    }
}
